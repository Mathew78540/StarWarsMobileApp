package bangbang.yodasbox;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import bangbang.yodasbox.Fragment.MenuDialogsActions;


public class ResultatQuestionActivity extends Activity {

    private String level;
    private ArrayList<Integer> listPersonages;
    private String namePersonage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
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
    public void onBackPressed() {
        MenuDialogsActions backToMenu = new MenuDialogsActions();
        FragmentManager manager = getFragmentManager();
        backToMenu.show(manager, "backToMenuFrag");
    }

}
