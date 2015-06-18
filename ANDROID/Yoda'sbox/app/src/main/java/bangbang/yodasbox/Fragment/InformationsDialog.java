package bangbang.yodasbox.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import bangbang.yodasbox.R;


public class InformationsDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.informations_layout, null);

        // Set font for title
        TextView titleView = (TextView)layout.findViewById(R.id.howtoplay);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(), "fonts/starjedi.ttf");
        titleView.setTypeface(face);

        Button testButton = (Button)layout.findViewById(R.id.close_btn);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDialog().cancel();
            }
        });

        builder.setView(layout);
        return builder.create();

    }
}