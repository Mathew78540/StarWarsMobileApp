package bangbang.yodasbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ResultatQuestionActivity extends Activity {

    private String level;
    private ArrayList<Integer> listPersonages;
    private String namePersonage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultat_layout);


        // Init views
        TextView textWin = (TextView)findViewById(R.id.test);
        Button playButton = (Button)findViewById(R.id.continueGame);


        getExtras();

        // Init all vars
        textWin.setText(namePersonage);
        playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Intent gameActivity = new Intent(ResultatQuestionActivity.this, QuestionActivity.class);
                gameActivity.putExtra("level", level);
                gameActivity.putExtra("personagesList", listPersonages.toString());

                startActivity(gameActivity);

            }
        });

    }

    public void getExtras()
    {
        // Get all extra sent by previous activity
        Bundle extras = getIntent().getExtras();
        namePersonage = extras.getString("namePersonage");
        level = extras.getString("level");

        listPersonages = new ArrayList<Integer>();
        String[] listPersonagesStrings = extras.getString("personagesList").split(",");

        for (int i = 0; i < listPersonagesStrings.length; i++)
            listPersonages.add(Integer.parseInt(listPersonagesStrings[i].replace("[","").replace("]","").trim()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
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
}
