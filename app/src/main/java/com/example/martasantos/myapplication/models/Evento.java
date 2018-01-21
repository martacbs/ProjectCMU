package com.example.martasantos.myapplication.models;

/**
 * Classe Evento
 */

public class Evento {
    private String nome;
    private String comeca;
    private int id;
    private String local;
    private String duracao;
    private String data;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getComeca() {
        return comeca;
    }

    public void setComeca(String comeca) {
        this.comeca = comeca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }


    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }


    public Evento(String nome, String data, String comeca, int id, String local, String duracao) {

        this.nome = nome;
        this.data = data;
        this.comeca = comeca;
        this.id = id;
        this.local = local;
        this.duracao = duracao;

    }
    public Evento(){

    }
}
