package com.example.martasantos.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.martasantos.myapplication.database.Interesses;
import com.example.martasantos.myapplication.interests.UserInterests;
import com.example.martasantos.myapplication.login.Login;
import com.example.martasantos.myapplication.register.UserRegister;
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

//Image_Section = (LinearLayout)findViewById(R.id.image_section);



    }

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
    private void handleResult(GoogleSignInResult result){

        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
          //  String name = account.getDisplayName();
       //     String email = account.getEmail();
          //  String img_url = account.getPhotoUrl().toString();
           // Name.setText(name);
            //Email.setText(name);
           // Glide.with(this).load(img_url).into(imagem_util);
         //   String img_url = X_Agenda.toString();
         //   Glide.with(this).load(img_url).into(X_Agenda);
          //  Image_Section.setVisibility(View.VISIBLE);
            updateUI(true);
        }
        else{
           updateUI(false);
        }

    }
    private void updateUI(boolean isLogin){

        if (isLogin){
           //por imagem_util visivel


            Toast.makeText(getApplicationContext(), "Login with google sucessful", Toast.LENGTH_SHORT).show();
            Intent z = new Intent(getApplicationContext(), UserInterests.class);
            startActivity(z);
        }

        else {
            //por imagem_util gone
            SignIn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
}

