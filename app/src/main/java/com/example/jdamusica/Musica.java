package com.example.jdamusica;


public class Musica {

    private String titulo;
    private String autor;
    private String urlPortada;
    private String urlCancion;

    public Musica() {
    }

    public Musica(String titulo, String autor, String urlPortada, String urlCancion) {
        this.titulo = titulo;
        this.autor = autor;
        this.urlPortada = urlPortada;
        this.urlCancion = urlCancion;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getUrlPortada() {
        return urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public String getUrlCancion() {
        return urlCancion;
    }

    public void setUrlCancion(String urlCancion) {
        this.urlCancion = urlCancion;
    }



}
