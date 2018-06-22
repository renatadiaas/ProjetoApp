package com.example.memo.projetoapp.Model;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by renat on 07/06/2018.
 */

public class Info {
    private String nome;
    private String end;
    private String tel;
    private int num;
    private String email;

    public Info(String nome, String end, String tel, int num, String email){
        this.end=end;
        this.nome=nome;
        this.tel=tel;
        this.num=num;
        this.email=email;
    }
    public Info(String s){
        s = s.replace("{", "").replace("}", "");
        String split [] = s.split(", ");
        Log.i("InfoLog", s);
        ArrayList<String> saida = new ArrayList<>();
        for (String x : split){
            String split2 [] = x.split("=");
            saida.add(split2[1]);
        }

        this.num=Integer.parseInt(saida.get(0));
        this.nome = saida.get(1);
        this.end=saida.get(3);
        this.tel=saida.get(4);
        this.email=saida.get(2);




    }

    public Info(){}
    @Override
    public String toString() {
        return "Info{" +
                "nome='" + nome + '\'' +
                ", end='" + end + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", num=" + num +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
