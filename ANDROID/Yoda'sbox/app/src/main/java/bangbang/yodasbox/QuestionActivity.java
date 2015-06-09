package bangbang.yodasbox;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;

import bangbang.yodasbox.Model.CharacterResultJSON;
import bangbang.yodasbox.Network.Network;


public class QuestionActivity extends Activity  {

    private JsonObjectRequest request;
    private Network networkAccess;
    private YodaSearchResultReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);

        TextView character = (TextView) findViewById(R.id.characters_name);

        networkAccess = new Network();
        receiver = new YodaSearchResultReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(Network.YODA_SEARCH_INTENT));

        Button launchRequest = (Button)findViewById(R.id.yoda);
        launchRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                networkAccess.requestYoda();
                System.out.println("Click");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


    }



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


    class YodaSearchResultReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("received results");
            CharacterResultJSON resultsJSON = (CharacterResultJSON)intent.getSerializableExtra(Network.YODA_SEARCH_RESULT_EXTRA);
            System.out.println(resultsJSON.toString());
        }
    }
}
