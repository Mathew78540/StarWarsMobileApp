package bangbang.yodasbox;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;

import java.util.ArrayList;
import java.util.Random;

import bangbang.yodasbox.Model.CharacterResultJSON;
import bangbang.yodasbox.Network.Network;


public class QuestionActivity extends Activity  {

    private JsonObjectRequest request;
    private Network networkAccess;
    private YodaSearchResultReceiver receiver;
    private Random random;
    private int level, queuePerson, numberHidePerson;

    private ArrayList<Integer> listPersonages;
    private CharacterResultJSON hidePerson, fake1Person, fake2Person;

    private TextView gender, eye_color, skin_color, hair_color, height, homeworld;
    private ArrayList<Button> listChoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);

        listPersonages = new ArrayList<Integer>();

        receiver = new YodaSearchResultReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(Network.YODA_REQUEST_INTENT));


        random = new Random();
        initViews();
        initPersonages();

    }

    public void initViews()
    {
        gender = (TextView)findViewById(R.id.gender);
        eye_color = (TextView)findViewById(R.id.eye_color);
        skin_color = (TextView)findViewById(R.id.skin_color);
        hair_color = (TextView)findViewById(R.id.hair_color);
        height = (TextView)findViewById(R.id.height);
        homeworld = (TextView)findViewById(R.id.homeworld);


        listChoice = new ArrayList<Button>();
        listChoice.add((Button)findViewById(R.id.firstChoice));
        listChoice.add((Button)findViewById(R.id.secondChoice));
        listChoice.add((Button)findViewById(R.id.thirdChoice));

        numberHidePerson = random.nextInt(listChoice.size());
    }

    public void initPersonages()
    {
        // init
        networkAccess = new Network();
        queuePerson = 0;

        listPersonages.add(generateUniquePersonage());
        networkAccess.requestYoda("people", listPersonages.get(listPersonages.size()-1));


        int fake1 = generateUniquePersonage();
        networkAccess.requestYoda("people", fake1);

        int fake2;
        do {
            fake2 = generateUniquePersonage();
        } while(fake2 == fake1);
        networkAccess.requestYoda("people", fake2);

    }

    public int generateUniquePersonage()
    {
        int randomInt;
        do {
            randomInt = random.nextInt(85)+1;
        } while(listPersonages.contains(randomInt));

        return randomInt;
    }


    /*
    private class AsyncTest extends AsyncTask<Void, Void, Integer>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Debut du traitement asynchrone", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Integer doInBackground(Void... arg0)
        {
            int flag =
            character.setText(hidePerson.getName());
            return flag;
        }

        @Override
        protected void onPostExecute(Integer result) {
            Toast.makeText(getApplicationContext(), "Fin de l'asynch", Toast.LENGTH_LONG).show();
        }
    }

    */




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Listener for new INTENT with differents EXTRA
    class YodaSearchResultReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("received results & queue person = " + queuePerson);


            if(queuePerson == 0)
            {
                hidePerson = (CharacterResultJSON)intent.getSerializableExtra(Network.YODA_SEARCH_RESULT_EXTRA);

                if(hidePerson !=null )
                {
                    // init button of hidePerson
                    listChoice.get(numberHidePerson).setText(hidePerson.getName());

                    gender.setText(hidePerson.getGender());
                    eye_color.setText(hidePerson.getEye_color());
                    skin_color.setText(hidePerson.getSkin_color());
                    hair_color.setText(hidePerson.getHair_color());
                    height.setText(hidePerson.getHeight());
                    homeworld.setText(hidePerson.getHomeworld());
                }

            }

            if(queuePerson == 1)
            {
                fake1Person = (CharacterResultJSON)intent.getSerializableExtra(Network.YODA_SEARCH_RESULT_EXTRA);
                if(fake1Person != null)
                {
                    for( int i = 0; i < listChoice.size(); i++)
                    {
                        if(listChoice.get(i).getText() == getResources().getString(R.string.undefined))
                        {
                            listChoice.get(i).setText(fake1Person.getName());
                            break;
                        }
                    }
                }
            }

            if(queuePerson == 2)
            {
                fake2Person = (CharacterResultJSON)intent.getSerializableExtra(Network.YODA_SEARCH_RESULT_EXTRA);
                for( int i = 0; i < listChoice.size(); i++)
                {
                    if(listChoice.get(i).getText() == getResources().getString(R.string.undefined))
                        listChoice.get(i).setText(fake1Person.getName());


                    if( i == numberHidePerson )
                        listChoice.get(i).setOnClickListener(winListener);
                    else
                        listChoice.get(i).setOnClickListener(loseListener);

                }
            }

            queuePerson++;

        }
    }

    View.OnClickListener winListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent winActivity = new Intent(QuestionActivity.this, ResultatQuestionActivity.class);
            winActivity.putExtra("namePersonage", hidePerson.getName());
            winActivity.putExtra("personagesList", listPersonages.toString());

            startActivity(winActivity);
        }
    };

    View.OnClickListener loseListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "LOSE", Toast.LENGTH_SHORT).show();
        }
    };
}
