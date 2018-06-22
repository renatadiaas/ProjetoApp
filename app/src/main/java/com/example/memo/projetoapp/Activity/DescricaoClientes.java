package com.example.memo.projetoapp.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.memo.projetoapp.Dados;
import com.example.memo.projetoapp.Model.Info;
import com.example.memo.projetoapp.R;

public class DescricaoClientes extends AppCompatActivity {


    private TextView nome, respostaTel, respostaEnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_clientes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Menu");


        Intent j = getIntent();

        int pos = (int) j.getExtras().get("posicao");
        Info info = Dados.getInstance().getClienteEleito(pos);

        nome = (TextView) findViewById(R.id.nome);
        respostaEnd = (TextView) findViewById(R.id.respostaEnd);
        respostaTel = (TextView) findViewById(R.id.respostaTel);


        nome.setText(info.getNome());
        respostaTel.setText(info.getTel());
        respostaEnd.setText(info.getEnd()+info.getNum());

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, MenuActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }
}
