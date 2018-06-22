package com.example.memo.projetoapp.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.memo.projetoapp.Dados;
import com.example.memo.projetoapp.Model.Info;
import com.example.memo.projetoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Date;

public class Cadastro extends AppCompatActivity implements View.OnClickListener {
    private AutoCompleteTextView editemail;
    private EditText editsenha;
    private Button registrar, voltar;
    private FirebaseAuth auth;
    private EditText nome;
    private EditText end;
    private EditText num;
    private EditText tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        editemail=(AutoCompleteTextView)findViewById(R.id.email);
        editsenha=(EditText)findViewById(R.id.password);
        registrar=(Button)findViewById(R.id.registrar);
        voltar=(Button)findViewById(R.id.voltar);
        voltar.setOnClickListener(this);
        registrar.setOnClickListener(this);
        nome=(EditText)findViewById(R.id.nome);
        end=(EditText)findViewById(R.id.end);
        num=(EditText)findViewById(R.id.num);
        tel=(EditText)findViewById(R.id.tel);

    }

    @Override
    public void onClick(View view) {
        if(view ==voltar){
            finish();
        }
        if(view==registrar){
            String email = editemail.getText().toString().trim();
            String senha = editsenha.getText().toString().trim();
            String nomee = nome.getText().toString();
            String endd = end.getText().toString();
            String numm = num.getText().toString();
            String tell = tel.getText().toString();
            criarCliente(email, senha, nomee, endd, numm, tell);
        }


    }


    private void criarCliente(final String email, String senha, final String nomee, final String endd, final String tell, final String numm) {
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    alert("Usuário cadastrado com Sucesso");
                    Date hora = new Date();
                    hora.getTime();
                    String dStr = hora.toString();
                    Log.i("EEEEEE", dStr);
                    Info info = new Info();
                    info.setNome(nomee);
                    info.setEnd(endd);
                    info.setNum(Integer.parseInt(numm));
                    info.setTel(tell);
                    info.setEmail(email);
                    Dados.getInstance().insertInfo(info,dStr);
                    Intent i = new Intent(Cadastro.this, MenuActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    alert("Erro de Cadastro");
                }
            }
        });
    }
    private void alert(String msg){
        Toast.makeText(Cadastro.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
