package com.example.memo.projetoapp.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memo.projetoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetSenha extends AppCompatActivity implements View.OnClickListener {
    private AutoCompleteTextView email;
    private Button resetar;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);


        email=(AutoCompleteTextView)findViewById(R.id.email);
        resetar=(Button)findViewById(R.id.resetar);
        resetar.setOnClickListener(this);




    }

    @Override
    protected void onStart() {
        super.onStart();
        auth=Conexao.getFirebaseAuth();
    }

    @Override
    public void onClick(View view) {
        String emaill = email.getText().toString().trim();
        resetSenha(emaill);
    }

    private void resetSenha(String email) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(ResetSenha.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    alert("Verifique seu email para recuperação da senha");
                    finish();
                }else{
                    alert("O e-mail fornecido não foi encontrado");
                }
            }
        });
    }

    private void alert(String s) {
        Toast.makeText(ResetSenha.this, s, Toast.LENGTH_SHORT).show();
    }
}
