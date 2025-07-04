package br.com.alura.literalura.model;

import br.com.alura.literalura.dto.DadosLivro;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String titulo;
    private String idioma;
    private Integer downloads;

    @ManyToOne
    private Autor autor;

    public Livro() {};

    public Livro(DadosLivro dadosLivro, Autor autor) {
        this.titulo = dadosLivro.titulo();
        this.autor = autor;

        //List<String> idiomas = dadosLivro.idioma();
        //this.idioma = (idioma != null && !idiomas.isEmpty()) ? idiomas.get(0) : "desconhecido";
        this.idioma = dadosLivro.idioma().get(0);
        this.downloads = dadosLivro.downloads();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return "Título: '" + titulo + '\'' +
                ", " + autor +
                ", Idioma: " + idioma +
                ", Número Downloads: " + downloads;
    }
}
