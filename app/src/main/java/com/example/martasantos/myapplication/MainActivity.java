package com.example.martasantos.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martasantos.myapplication.database.DbHelper;
import com.example.martasantos.myapplication.interests.UserInterests;
import com.example.martasantos.myapplication.login.Login;
import com.example.martasantos.myapplication.register.UserRegister;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

//private LinearLayout Image_Section;
    Button register,login;
    private SignInButton SignIn;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
   // private ImageView X_Agenda;

    //facebook
    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register=(Button)findViewById(R.id.bRegister);
        login=(Button)findViewById(R.id.bLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x= new Intent(getApplicationContext(),UserRegister.class);
                startActivity(x);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent y=new Intent(getApplicationContext(),Login.class);
                startActivity(y);
            }
        });

        SignIn = (SignInButton)findViewById(R.id.bt_login);
        SignIn.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient =  new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();


        //Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());

        loginButton =(LoginButton)findViewById(R.id.login_button);
        textView = (TextView)findViewById(R.id.textView);
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent q = new Intent(getApplicationContext(), UserInterests.class);
                startActivity(q);
                textView.setText("Login Sucess \n" + loginResult.getAccessToken().getUserId()+ "\n"+loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {

                textView.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
//Image_Section = (LinearLayout)findViewById(R.id.image_section);

    }




    //Google
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt_login:
                signIn();
                break;
            //se quisermos fazer aqui case logout
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void signIn(){

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);

    }
    private void handleResult(GoogleSignInResult result) throws Exception {

        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();

            DbHelper dbHelper = new DbHelper(MainActivity.this);
            SQLiteDatabase db= dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("name", account.getDisplayName().toString());
            values.put("email", account.getEmail().toString());

            long id_countGoogle = db.insert("user", null, values);
            if (id_countGoogle < 0) {
                throw new Exception("Não é possível registar o utilizador com o login da google");
            }

            updateUI(true, id_countGoogle);
        }
        else{

            updateUI(false, 0);
        }

    }
    private void updateUI(boolean isLogin, long id_google){

        if (isLogin){

            Toast.makeText(getApplicationContext(), "Login with google sucessful", Toast.LENGTH_SHORT).show();
            Intent z = new Intent(getApplicationContext(), UserInterests.class);
            Bundle b = new Bundle();
            b.putLong("user_id_google",id_google);

            z.putExtras(b);
            startActivity(z);
        }

        else {

            SignIn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //face
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //google
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            try {
                handleResult(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

