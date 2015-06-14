package bangbang.yodasbox;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import bangbang.yodasbox.Fragment.MenuDialogsActions;
import bangbang.yodasbox.Network.Network;


public class ResultatQuestionActivity extends Activity {

    private String level, namePersonage, listPersonages;
    private TextView textWin;
    private ImageView imagePersonage;
    private YodaSearchResultReceiver receiver;
    private Network network;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        setContentView(R.layout.resultat_layout);

        // Init views
        textWin = (TextView)findViewById(R.id.nameResponse);
        imagePersonage = (ImageView)findViewById(R.id.pictureResponse);
        Button playButton = (Button)findViewById(R.id.continueGame);

        getExtras();

        receiver = new YodaSearchResultReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(Network.YODA_REQUEST_INTENT));

        network = new Network();
        try {
            //network.requestYodaImage(URLEncoder.encode(namePersonage +" StarWars", "UTF-8"));
            network.requestYodaImage(URLEncoder.encode(namePersonage, "UTF-8"));
        }
        catch(Exception e)
        {
            Log.e("Error", e.toString());
        }


        // Init all vars
        textWin.setText(namePersonage);
        playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Intent gameActivity = new Intent(getApplicationContext(), QuestionActivity.class);
                gameActivity.putExtra("level", level);
                gameActivity.putExtra("personagesList", listPersonages);

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

        listPersonages = extras.getString("personagesList");
    }

    class YodaSearchResultReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(intent.getExtras().containsKey(Network.YODA_SEARCH_URL_IMAGE_EXTRA))
                network.downloadImage((String) intent.getSerializableExtra(Network.YODA_SEARCH_URL_IMAGE_EXTRA), imagePersonage, getApplicationContext());


            if(intent.getExtras().containsKey(Network.YODA_SEARCH_IMAGE_EXTRA))
            {
                Bitmap bMap = null;
                try {
                    FileInputStream is = openFileInput((String) intent.getSerializableExtra(Network.YODA_SEARCH_IMAGE_EXTRA));
                    bMap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                imagePersonage.setImageBitmap(bMap);
            }


        }
    }

    @Override
    public void onBackPressed() {
        MenuDialogsActions backToMenu = new MenuDialogsActions();
        FragmentManager manager = getFragmentManager();
        backToMenu.show(manager, "backToMenuFrag");
    }


}
