package abassawo.c4q.nyc.fe_nyc;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CreateAccount extends ActionBarActivity {

    String clientname, username, userpass, confpass;
    Context mContext = this;
    @Bind(R.id.clientName) EditText mClientName;
    @Bind(R.id.userName) EditText mUserName;
    @Bind(R.id.password) EditText mPassword;
    @Bind(R.id.confirmPassword) EditText mConfirmPass;
    @Bind(R.id.register) Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientname = mClientName.getText().toString();
                username = mUserName.getText().toString();
                userpass = mPassword.getText().toString();
                confpass = mConfirmPass.getText().toString();

                if(!(userpass.equals(confpass))){
                    Toast.makeText(getBaseContext(), getString(R.string.pwnotmatch), Toast.LENGTH_SHORT).show();
                    mClientName.setText("");
                    mUserName.setText("");
                    mPassword.setText("");
                    mConfirmPass.setText("");}
                else{
                    UserSetUpData DB = new UserSetUpData(mContext);
                    DB.insertInfo(DB, clientname, username, userpass);
                    Toast.makeText(getBaseContext(), getString(R.string.success), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
