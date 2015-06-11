package bangbang.yodasbox;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonObjectRequest;


import java.util.ArrayList;
import java.util.Random;

import bangbang.yodasbox.Fragment.MenuDialogsActions;
import bangbang.yodasbox.Model.CharacterResultJSON;
import bangbang.yodasbox.Model.PlanetResultJSON;
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
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.question_layout);

        receiver = new YodaSearchResultReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(Network.YODA_REQUEST_INTENT));


        // Get extra or init vars
        Bundle extras = getIntent().getExtras();


        if(extras!=null && extras.getString("level") != null)
            level = Integer.parseInt(extras.getString("level"))+1;
        else
            level = 0;

        listPersonages = new ArrayList<Integer>();
        if(extras!=null && extras.getString("personagesList") != null)
        {
            String[] listPersonagesStrings = extras.getString("personagesList").split(",");

            for (int i = 0; i < listPersonagesStrings.length; i++)
                listPersonages.add(Integer.parseInt(listPersonagesStrings[i].replace("[","").replace("]","").trim()));
        }

        System.out.println("level: " + level);
        System.out.println("Personages passed: " + listPersonages.toString());



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
        queuePerson = -1;

        listPersonages.add(generateUniquePersonage());
        networkAccess.requestYoda("people", listPersonages.get(listPersonages.size()-1));


        int fake1 = generateUniquePersonage();
        networkAccess.requestYoda("people", fake1);

        int fake2;
        do {
            fake2 = generateUniquePersonage();
        } while(fake2 == fake1);
        networkAccess.requestYoda("people", fake2);

        System.out.println(fake1);
        System.out.println(fake2);
        System.out.println(listPersonages.get(listPersonages.size()-1));
    }

    public int generateUniquePersonage()
    {
        int randomInt;
        do {
            randomInt = random.nextInt(85)+1;
        } while(listPersonages.contains(randomInt));

        return randomInt;
    }


    // Listener for new INTENT with differents EXTRA
    class YodaSearchResultReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {

            queuePerson++;
            if(queuePerson == 0)
                hidePerson = (CharacterResultJSON)intent.getSerializableExtra(Network.YODA_SEARCH_PEOPLE_EXTRA);

            if(queuePerson == 1)
                fake1Person = (CharacterResultJSON)intent.getSerializableExtra(Network.YODA_SEARCH_PEOPLE_EXTRA);

            if(queuePerson == 2)
            {
                fake2Person = (CharacterResultJSON)intent.getSerializableExtra(Network.YODA_SEARCH_PEOPLE_EXTRA);

                if(hidePerson !=null )
                {
                    // init button of hidePerson
                    listChoice.get(numberHidePerson).setText(hidePerson.getName());

                    gender.setText(hidePerson.getGender());
                    eye_color.setText(hidePerson.getEye_color());
                    skin_color.setText(hidePerson.getSkin_color());
                    hair_color.setText(hidePerson.getHair_color());
                    height.setText(hidePerson.getHeight());

                    // get planet's name in API
                    networkAccess.requestYodaPlanet(hidePerson.getHomeworld());
                }

                // instanciate first fake person
                if(fake1Person != null)
                {
                    if(listChoice.get(0).getText() == getResources().getString(R.string.undefined))
                        listChoice.get(0).setText(fake1Person.getName());
                    else
                        listChoice.get(1).setText(fake1Person.getName());
                }

                for( int i = 0; i < listChoice.size(); i++)
                {
                    if(listChoice.get(i).getText() == getResources().getString(R.string.undefined))
                        listChoice.get(i).setText(fake2Person.getName());

                    if( i == numberHidePerson )
                        listChoice.get(i).setOnClickListener(winListener);
                    else
                        listChoice.get(i).setOnClickListener(loseListener);
                }
            }

            if(intent.getExtras().containsKey(Network.YODA_SEARCH_PLANET_EXTRA))
            {
                PlanetResultJSON planet = (PlanetResultJSON)intent.getSerializableExtra(Network.YODA_SEARCH_PLANET_EXTRA);
                homeworld.setText(planet.getName());
            }

        }
    }

    View.OnClickListener winListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent winActivity = new Intent(QuestionActivity.this, ResultatQuestionActivity.class);
            winActivity.putExtra("namePersonage", hidePerson.getName());
            winActivity.putExtra("level", Integer.toString(level));
            winActivity.putExtra("personagesList", listPersonages.toString());

            startActivity(winActivity);
        }
    };

    View.OnClickListener loseListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "LOSE", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onBackPressed() {
        MenuDialogsActions backToMenu = new MenuDialogsActions();
        FragmentManager manager = getFragmentManager();
        backToMenu.show(manager, "backToMenuFrag");
    }

}
