package com.example.memo.projetoapp;

import android.util.Log;

import com.example.memo.projetoapp.Activity.Conexao;
import com.example.memo.projetoapp.Fragment.Clientes;
import com.example.memo.projetoapp.Fragment.Perfil;
import com.example.memo.projetoapp.Model.Info;
import com.example.memo.projetoapp.Model.InfoPedido;
import com.example.memo.projetoapp.Model.Item;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by renat on 22/05/2018.
 */

public class Dados {
    private static final Dados ourInstance = new Dados();
    RespostaServidor respostaServidorProdutos;
    RespostaServidor respostaServidorClientes;
    RespostaServidor respostaServidorPedidos;

    private ArrayList<Item> produtos = new ArrayList<>();
    private ArrayList<Info> clientes = new ArrayList<>();
    private ArrayList<InfoPedido> pedidos = new ArrayList<>();
    public String informacaoPerfil;
    private FirebaseUser user;
    private ArrayList<String> informacoes = new ArrayList<>();

    public static Dados getInstance() {
        return ourInstance;
    }


    public void readData(final FirebaseCallback firebaseCallback){
        user = Conexao.getFirebaseUser();
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference myRef2 = database2.getReference("Clientes");

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clientes.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Info novaInfo = new Info(ds.getValue().toString());
                    if(novaInfo.getEmail().equals(user.getEmail().toString())){
                        informacoes.add(novaInfo.getNome());
                        informacoes.add(novaInfo.getEnd());
                        informacoes.add(String.valueOf(novaInfo.getNum()));
                    }
                }
                firebaseCallback.OnCallback(informacoes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError2) {

            }

        });
    }

    public interface FirebaseCallback{
        void OnCallback(ArrayList<String> informacoes);
    }

    public Item getProdutoEleito(int i){
        return produtos.get(i);
    }
    public Info getClienteEleito(int i){
        return clientes.get(i);
    }
    public InfoPedido getPedidoEleito(int i){return pedidos.get(i);}

    public ArrayList<String> getProdutos() {
        ArrayList<String> nomesPro = new ArrayList<>();
        for(Item i : produtos){
            nomesPro.add(i.getNome());
        }
        return nomesPro;
    }
    public ArrayList<String> getClientes(){
        ArrayList<String>nomesCli = new ArrayList<>();
        for (Info i : clientes){
            nomesCli.add(i.getNome());
        }
        return nomesCli;
    }

    public ArrayList<String> getPedidos(){
        ArrayList<String>nomesPed = new ArrayList<>();
        for (InfoPedido i : pedidos){
            nomesPed.add(i.getCliente());
        }
        return nomesPed;
    }


    private Dados() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Produtos");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                produtos.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Item novoItem = new Item(ds.getValue().toString());
                    produtos.add(novoItem);
                }
                ArrayList<String> nomes = new ArrayList<>();

                for(Item p : produtos) {
                    Log.i("NovoTeste", p.getNome());
                    nomes.add(p.getNome());
                }
                respostaServidorProdutos.respostaAtualizado(nomes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void inicializaCliente(){

        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference myRef2 = database2.getReference("Clientes");

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clientes.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Info novaInfo = new Info(ds.getValue().toString());
                    Log.i("BancoCliente", ds.getValue().toString());
                    clientes.add(novaInfo);
                }
                ArrayList<String> nomes2 = new ArrayList<>();

                for(Info c : clientes) {
                    Log.i("DadosCliente", c.getNome());
                    Log.i("DadosCliente", c.getEnd());
                    Log.i("DadosCliente", String.valueOf(c.getNum()));
                    Log.i("DadosCliente", c.getTel());
                    nomes2.add(c.getNome());
                }
                respostaServidorClientes.respostaAtualizado(nomes2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError2) {

            }
        });

    }

    public void inicializaPedidos(){

        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database3.getReference("Pedidos");

        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pedidos.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    InfoPedido novaInfo = new InfoPedido(ds.getValue().toString());
                    pedidos.add(novaInfo);
                }
                ArrayList<String> nomes2 = new ArrayList<>();
                for(InfoPedido c : pedidos) {
                    nomes2.add(c.getCliente());
                    Log.i("olamundo4",nomes2.toString());
                }

                respostaServidorPedidos.respostaAtualizado(nomes2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError2) {

            }
        });

    }





    public void insertInfo(Info clientes, String i){
        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference myRef2 = database2.getReference("Clientes");
        myRef2.child(i).setValue(clientes);

    }

    public void insertInfoPedidos(InfoPedido pedidos, String position){
        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        DatabaseReference myRef3 = database3.getReference("Pedidos");
        Log.i("olamundo2",pedidos.toString());
        myRef3.child(position).setValue(pedidos);
    }



    public void atualizaDados(Item item, int position, Info info){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Produtos");
        myRef.child(String.valueOf(position)).setValue(item);

    }

    public void setRespostaServidorProdutos(RespostaServidor respostaServidorProdutos) {
        this.respostaServidorProdutos = respostaServidorProdutos;
    }

    public void setRespostaServidorClientes(RespostaServidor respostaServidorClientes) {
        this.respostaServidorClientes = respostaServidorClientes;
    }
    public void setRespostaServidorPedidos(RespostaServidor respostaServidorPedidos) {
        this.respostaServidorPedidos = respostaServidorPedidos;
    }
}
