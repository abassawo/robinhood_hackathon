package abassawo.c4q.nyc.fe_nyc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by c4q-tashasmith on 8/2/15.
 */
public class AlertDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Context context = getActivity();
            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.error_title))
                    .setMessage(context.getString(R.string.error_message))
                    .setPositiveButton(context.getString(R.string.error_ok_button_text), null); //close dialog after click

            AlertDialog dialog = builder.create();
            return dialog;
        }

    }


