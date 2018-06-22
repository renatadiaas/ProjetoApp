package com.example.memo.projetoapp.Model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by renat on 18/06/2018.
 */

public class InfoPedido {
    private String cliente;
    private String end;
    private int num;
    private String produto;
    private int qtd;

    public InfoPedido(String cliente, String end, int num, String produto, int qtd){
        this.cliente=cliente;
        this.end = end;
        this.num=num;
        this.produto=produto;
        this.qtd=qtd;
    }

    public InfoPedido() {

    }

    @Override
    public String toString() {
        return "InfoPedido{" +
                "cliente='" + cliente + '\'' +
                ", end='" + end + '\'' +
                ", num=" + num +
                ", produto='" + produto + '\'' +
                ", qtd=" + qtd +
                '}';
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public InfoPedido(String s){
        s = s.replace("{", "").replace("}", "");
        String split [] = s.split(", ");
        Log.i("InfoLog", s);
        ArrayList<String> saida = new ArrayList<>();
        for (String x : split){
            String split2 [] = x.split("=");
            saida.add(split2[1]);
        }

        this.num=Integer.parseInt(saida.get(0));
        this.cliente = saida.get(3);
        this.end=saida.get(4);
        this.produto=saida.get(1);
        this.qtd=Integer.parseInt(saida.get(2));




    }
}
