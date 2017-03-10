package com.example.olga.growaround.viewcontroller.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.example.olga.growaround.R;
import com.example.olga.growaround.client.MainCardActivity;

/**
 * Created by olga on 3/10/17.
 */

public class FacebookConnectionDoneDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("התחברות בוצעה בהצלחה").setPositiveButton
                (R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(1);
                        Intent intent= new Intent(getContext(), MainCardActivity.class);
                        intent.putExtra("flag", 1);
                        //homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                });
        return builder.create();
    }
}
