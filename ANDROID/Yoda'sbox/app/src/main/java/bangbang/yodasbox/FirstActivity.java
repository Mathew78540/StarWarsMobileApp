package bangbang.yodasbox;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import bangbang.yodasbox.Fragment.InformationsDialog;
import bangbang.yodasbox.Fragment.MenuDialogsActions;


public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.main_layout);

        Button playButton = (Button)findViewById(R.id.play_button);
        Button informationsButton = (Button)findViewById(R.id.informations_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, QuestionActivity.class));
            }
        });

        informationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InformationsDialog informationsShow = new InformationsDialog();
                FragmentManager manager = getFragmentManager();
                informationsShow.show(manager, "backToMenuFrag");
            }
        });



    }
}
