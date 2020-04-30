package gauravkumar.com.shoopie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    protected MainApplication mainApplication;
    TextView loginButton,forgotButton,signupButton,facebookButton,googleButton;
    EditText emailET,passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mainApplication = (MainApplication)this.getApplicationContext();
        mainApplication.setCurrentActivity(this);
        mainApplication.setSplashRunnigForTheFirstTimeFalse();

        loginButton= (TextView)findViewById(R.id.login_button_textview);
        forgotButton = (TextView)findViewById(R.id.forgot_button_textview);
        signupButton = (TextView)findViewById(R.id.signup_button_textview);
        facebookButton = (TextView)findViewById(R.id.facebook_button_textview);
        googleButton = (TextView)findViewById(R.id.google_button_textview);
        emailET = (EditText)findViewById(R.id.email_edittext);
        passwordET = (EditText)findViewById(R.id.password_edittext);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
                finish();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mainApplication.setCurrentActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
