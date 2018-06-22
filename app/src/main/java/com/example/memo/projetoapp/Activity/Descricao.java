package com.example.memo.projetoapp.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memo.projetoapp.CarrinhoProdutos;
import com.example.memo.projetoapp.Dados;
import com.example.memo.projetoapp.Fragment.Carrinho;
import com.example.memo.projetoapp.Model.Item;
import com.example.memo.projetoapp.Model.ItemCarrinho;
import com.example.memo.projetoapp.R;

public class Descricao extends AppCompatActivity implements View.OnClickListener {

    private TextView titulo, descricao, numQtd;
    private ImageView img;
    private Button carrinho, qtd, qtd2;
    private int i=0;
    private String nomeProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Menu");

        Intent j = getIntent();

        int pos = (int) j.getExtras().get("posicao");
        Item item = Dados.getInstance().getProdutoEleito(pos);

        titulo = (TextView) findViewById(R.id.titulo);
        descricao = (TextView) findViewById(R.id.descricao);
        numQtd = (TextView) findViewById(R.id.numQtd);
        img = (ImageView) findViewById(R.id.img);

        titulo.setText(item.getNome());
        descricao.setText(item.getDescricao());
        nomeProduto=item.getNome();
        Log.i("Imagem:", item.getImg());
        String nomeFoto = item.getImg();
        int t = getResources().getIdentifier(nomeFoto, "drawable", getPackageName());
        img.setImageResource(t);


        carrinho = (Button) findViewById(R.id.carrinho);
        qtd = (Button) findViewById(R.id.qtd);
        qtd2 = (Button) findViewById(R.id.qtd2);
        carrinho.setOnClickListener(this);
        qtd.setOnClickListener(this);
        qtd2.setOnClickListener(this);



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
    @Override
    public void onClick(View view) {
        if (view==qtd){
            i++;
            numQtd.setText(String.valueOf(i));

        }
        if(view==qtd2){
            i--;
            numQtd.setText(String.valueOf(i));
        }
        if (view==carrinho) {
            ItemCarrinho item = new ItemCarrinho(nomeProduto, i);

            CarrinhoProdutos.getInstance().adicionarCarrinho(item);
            Toast.makeText(getApplicationContext(),"Produto adicionado ao carrinho",Toast.LENGTH_SHORT).show();
        }

    }



}
