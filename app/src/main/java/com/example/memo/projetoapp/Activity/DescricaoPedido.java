package com.example.memo.projetoapp.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.memo.projetoapp.Dados;
import com.example.memo.projetoapp.Model.InfoPedido;
import com.example.memo.projetoapp.R;

public class DescricaoPedido extends AppCompatActivity {

    private TextView numPedido, cliente, end, produto, qtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao_pedido);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Menu");

        Intent j = getIntent();

        int pos = (int) j.getExtras().get("pedido");
        InfoPedido infoPedido = Dados.getInstance().getPedidoEleito(pos);

        numPedido=(TextView)findViewById(R.id.numPedido);
        cliente=(TextView)findViewById(R.id.respostaNome);
        end=(TextView)findViewById(R.id.respostaEnd);
        produto=(TextView)findViewById(R.id.respostaProduto);
        qtd=(TextView)findViewById(R.id.respostaQuant);


        numPedido.setText("Pedido "+(pos+1));
        cliente.setText(infoPedido.getCliente());
        end.setText(infoPedido.getEnd());
        produto.setText(infoPedido.getProduto());
        qtd.setText(String.valueOf(infoPedido.getQtd()));

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
