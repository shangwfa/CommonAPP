package com.wjs.myapplication.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.wjs.myapplication.R;

/**
 * Created by Administrator on 2015/7/21.
 */
public class LoginDialogFragment extends android.app.DialogFragment {
    private EditText mUsername;
    private EditText mPassword;

    public interface LoginInputListener
    {
        void onLoginInputComplete(String username, String password);
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_login_dialog, null);
        mUsername = (EditText) view.findViewById(R.id.id_txt_username);
        mPassword = (EditText) view.findViewById(R.id.id_txt_password);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Sign in",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                LoginInputListener mLoginInputListener= (LoginInputListener) getActivity();

                                mLoginInputListener.onLoginInputComplete(mUsername.getText().toString(),mPassword.getText().toString());

                            }
                        }).setNegativeButton("Cancel", null);
        return builder.create();

    }
}
