package com.example.memo.projetoapp;

import android.util.Log;

import com.example.memo.projetoapp.Model.Item;
import com.example.memo.projetoapp.Model.ItemCarrinho;

import java.util.ArrayList;

public class CarrinhoProdutos {
    private static final CarrinhoProdutos ourInstance = new CarrinhoProdutos();
    private ArrayList<ItemCarrinho> produtosCarrinho =new ArrayList<>();
    public static CarrinhoProdutos getInstance() {
        return ourInstance;
    }

    private CarrinhoProdutos() {
    }

    public void adicionarCarrinho(ItemCarrinho item){
        produtosCarrinho.add(item);
    }
    public void tirarCarrinho(){
        produtosCarrinho.clear();
    }

    public ArrayList<String>getNomeProdutos(){
        ArrayList<String> produtos = new ArrayList<>();
        for(ItemCarrinho i : produtosCarrinho){
            produtos.add(i.getQuantidade()+" - "+i.getNome());
        }
        return  produtos;
    }


}
