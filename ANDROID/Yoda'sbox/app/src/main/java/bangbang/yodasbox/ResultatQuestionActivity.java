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
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import bangbang.yodasbox.Fragment.MenuDialogsActions;
import bangbang.yodasbox.Network.Network;


public class ResultatQuestionActivity extends Activity {

    private String level, namePersonage, listPersonages;
    private boolean win;
    private TextView nameView, winView, levelView, titleView;
    private ImageView imagePersonage, imageBackground;
    private YodaSearchResultReceiver receiver;
    private Network network;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.hide();
        setContentView(R.layout.resultat_layout);


        // Set font for title
        titleView = (TextView)findViewById(R.id.app_name);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/starjedi.ttf");
        titleView.setTypeface(face);

        // Init views
        levelView = (TextView)findViewById(R.id.level);
        nameView = (TextView)findViewById(R.id.nameResponse);
        imagePersonage = (ImageView)findViewById(R.id.pictureResponse);
        winView = (TextView)findViewById(R.id.result_text);
        imageBackground = (ImageView)findViewById(R.id.backgroundImage);
        Button playButton = (Button)findViewById(R.id.continueGame);

        getExtras();

        if(!win)
        {
            playButton.setText("TRY AGAIN");
            winView.setText("You failed !");
            imageBackground.setBackgroundResource(R.drawable.background_image_lost);
        }
        levelView.setText(level);

        receiver = new YodaSearchResultReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(Network.YODA_REQUEST_INTENT));

        network = new Network();
        try {
            network.requestYodaImage(URLEncoder.encode(namePersonage +" StarWars", "UTF-8"));
        }
        catch(Exception e)
        {
            Log.e("Error", e.toString());
        }


        // Init all vars
        nameView.setText(namePersonage);
        playButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Intent gameActivity = new Intent(getApplicationContext(), QuestionActivity.class);
                if(win)
                {
                    gameActivity.putExtra("level", level);
                    gameActivity.putExtra("personagesList", listPersonages);
                }
                else
                {
                    gameActivity.putExtra("level", "0");
                    gameActivity.putExtra("personagesList", new ArrayList<Integer>().toString());
                }


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
        win = extras.getBoolean("win");

        listPersonages = extras.getString("personagesList");
    }

    class YodaSearchResultReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if(intent.getExtras().containsKey(Network.YODA_SEARCH_URL_IMAGE_EXTRA))
                network.downloadImage((String) intent.getSerializableExtra(Network.YODA_SEARCH_URL_IMAGE_EXTRA), getApplicationContext());


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
