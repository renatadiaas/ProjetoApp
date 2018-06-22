package com.example.memo.projetoapp.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memo.projetoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity implements OnClickListener {

    private static final int REQUEST_READ_CONTACTS = 0;

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    // UI references.
    private EditText editEmail;
    private EditText editSenha;
    private Button entrar, cadastrar;
    private TextView esqueceuSenha;


    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        entrar = (Button)findViewById(R.id.entrar);
        cadastrar=(Button)findViewById(R.id.cadastrar);
        esqueceuSenha=(TextView)findViewById(R.id.esqueceuSenha);
        editEmail=(EditText)findViewById(R.id.email);
        editSenha=(EditText) findViewById(R.id.password);
        cadastrar.setOnClickListener(this);
        entrar.setOnClickListener(this);
        esqueceuSenha.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    @Override
    public void onClick(View view) {
        if (view==entrar){
            String email = editEmail.getText().toString().trim();
            String senha = editSenha.getText().toString().trim();
            login(email, senha);
        }
        if (view==cadastrar) {
            Intent j = new Intent(getApplicationContext(), Cadastro.class);
            startActivity(j);
        }
        if (view==esqueceuSenha){
            Intent i = new Intent(LoginActivity.this, ResetSenha.class);
            startActivity(i);
        }
    }

    private void login(String email, String senha) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(i);
                }else{
                    alert("e-mail ou senha errados");
                }
            }
        });
    }

    private void alert(String s) {
        Toast.makeText(LoginActivity.this, s,Toast.LENGTH_SHORT).show();
    }




}

