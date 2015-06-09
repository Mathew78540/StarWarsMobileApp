package bangbang.yodasbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ResultatQuestionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);

        TextView textWin = (TextView)findViewById(R.id.test);
        Button playButton = (Button)findViewById(R.id.continueGame);


        Bundle extras = getIntent().getExtras();
        String namePersonage = extras.getString("namePersonage");

        //textWin.setText(namePersonage);

        //String[] parts = extras.getString("personagesList").split(",");

        /*
        ArrayList<Integer> listPersonage = new ArrayList<Integer>();
        for( int i = 0; i < parts.length; i++)
        {
            listPersonage.add(Integer.parseInt(parts[i]));
        }

        System.out.println(listPersonage);
        */


        /*
        playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent gameActivity = new Intent(ResultatQuestionActivity.this, QuestionActivity.class);


                startActivity(gameActivity);
            }
        });
        */

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
