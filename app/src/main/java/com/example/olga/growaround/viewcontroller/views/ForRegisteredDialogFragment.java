package com.example.olga.growaround.viewcontroller.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.example.olga.growaround.R;
import com.example.olga.growaround.client.LogInActivity;

/**
 * Created by olga on 3/7/17.
 */

public class ForRegisteredDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage(R.string.dialog_register_text).setPositiveButton
                    (R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        Intent logInIntent = new Intent(getContext(), LogInActivity.class);
                        //logInIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(logInIntent);

                        }
                    }).setNegativeButton

                    (R.string.no, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            ///SAVE TO DATA!!!!!!
                        }
                    });
            return builder.create();
        }
}
