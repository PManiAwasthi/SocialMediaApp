package com.example.socialmediaappfirebaseversion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    public static boolean fetchUsername;

    private EditText edtLogInEmail, edtLogInPassword;
    private Button btnLogIn;
    private TextView txtLogInTransition;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if(mUser != null){
            socialActivityTransition();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        edtLogInPassword = findViewById(R.id.edtLogInPassword);
        edtLogInEmail = findViewById(R.id.edtLogInEmail);
        btnLogIn = findViewById(R.id.btnLogIn);
        txtLogInTransition = findViewById(R.id.txtSignUpTransition);



        txtLogInTransition.setOnClickListener(LogInActivity.this);
        btnLogIn.setOnClickListener(LogInActivity.this);

        edtLogInPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    logIn();
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtSignUpTransition:signUpTransition();
                break;

            case R.id.btnLogIn:logIn();
                break;
        }
    }

    private void signUpTransition(){
        Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
    private void logIn(){
        fetchUsername = true;
        ProgressDialog progressDialog = new ProgressDialog(LogInActivity.this);
        progressDialog.setMessage("Logging in....");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(edtLogInEmail.getText().toString(),edtLogInPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                    mAuth.signInWithEmailAndPassword(edtLogInEmail.getText().toString(), edtLogInPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                socialActivityTransition();
                            }else{
                                Toast.makeText(LogInActivity.this, "Invalid details", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    progressDialog.dismiss();
            }
        });
    }

    private void socialActivityTransition(){
        Intent intent = new Intent(LogInActivity.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}