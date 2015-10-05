package com.projecthade.androidloginoauth;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.projecthade.androidloginoauth.io.Constant;
import com.projecthade.androidloginoauth.io.ResponseLogin;
import com.projecthade.androidloginoauth.io.RestClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.edtUsername)
    EditText edtUsername;
    @Bind(R.id.editPassword)
    EditText edtPassword;

    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @OnClick(R.id.btnLogin)
    public void postLogin() {

        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();

        RestClient.get().oauthToken(username, password,
                Constant.GRANT_TYPE,
                Constant.SCOPE,
                Constant.CLIENT_SECRET,
                Constant.CLIENT_ID, new Callback<ResponseLogin>() {
                    @Override
                    public void success(ResponseLogin responseLogin, Response response) {
                        Snackbar.make(coordinatorLayout, responseLogin.getAccess_token(), Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Snackbar.make(coordinatorLayout, error.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
