package com.titanium.moodmusic.component.ui;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.titanium.moodmusic.R;

public class ErrorDialog extends DialogFragment {

    private static final String MESSAGE_KEY = "MESSAGE_KEY";

    static ErrorDialog newInstance(String message) {
        Bundle args = new Bundle();
        args.putString(MESSAGE_KEY, message);

        ErrorDialog fragment = new ErrorDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        String message = this.getString(R.string.error_load);

        if (args != null) {
            message = args.getString(MESSAGE_KEY);
        }

        return new AlertDialog.Builder(requireContext())
                .setCancelable(true)
                .setMessage(message)
                .setPositiveButton(this.getString(R.string.btn_ok_text), (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }
}
