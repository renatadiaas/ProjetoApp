package com.example.memo.projetoapp.Model;

import java.util.ArrayList;

/**
 * Created by renat on 22/05/2018.
 */

public class Item {
    private String nome;
    private String quantidade;
    private String img;
    private String descricao;

    public Item(String nome, String quantidade, String img, String descricao) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.img = img;
        this.descricao = descricao;
    }
    public Item(String s){
        s = s.replace("{", "").replace("}", "");
        String split [] = s.split(", ");
        ArrayList<String> saida = new ArrayList<>();
        for (String x : split){
            String split2 [] = x.split("=");
            saida.add(split2[1]);
        }
        this.img = saida.get(0);
        this.descricao=saida.get(1);
        this.nome=saida.get(2);
        this.quantidade=saida.get(3);


    }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", quantidade='" + quantidade + '\'' +
                ", img='" + img + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
