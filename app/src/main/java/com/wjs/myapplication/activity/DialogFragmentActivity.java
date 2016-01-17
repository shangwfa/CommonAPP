package com.wjs.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wjs.myapplication.R;
import com.wjs.myapplication.fragment.EditNameDialogFragment;
import com.wjs.myapplication.fragment.LoginDialogFragment;

public class DialogFragmentActivity extends Activity implements LoginDialogFragment.LoginInputListener {
    private Button bt1;
    private Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
        bt1= (Button) findViewById(R.id.bt1);
        bt2= (Button) findViewById(R.id.bt2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showEditDialog(v);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showLoginDialog(v);
            }
        });
    }

    public void showEditDialog(View view)
    {
        EditNameDialogFragment editNameDialog = new EditNameDialogFragment();
        editNameDialog.show(getFragmentManager(), "xx");
    }

    public void showLoginDialog(View view)
    {
        LoginDialogFragment dialog = new LoginDialogFragment();
        dialog.show(getFragmentManager(), "loginDialog");
    }

    @Override
    public void onLoginInputComplete(String username, String password) {
        Toast.makeText(this, "帐号：" + username + ",  密码 :" + password,
                Toast.LENGTH_SHORT).show();
    }
}
