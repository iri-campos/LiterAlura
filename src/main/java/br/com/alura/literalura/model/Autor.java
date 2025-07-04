package br.com.alura.literalura.model;

import br.com.alura.literalura.dto.Dados;
import br.com.alura.literalura.dto.DadosAutor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livro = new ArrayList<>();

    public Autor(DadosAutor dadosAutor) {
        this.nome = String.valueOf(dadosAutor.nome());
        this.anoNascimento = Integer.valueOf(dadosAutor.anoNascimento());
        this.anoFalecimento = Integer.valueOf(dadosAutor.anoFalecimento());
    }

    public Autor(){    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivro() {
        return livro;
    }

    public void setLivro(List<Livro> livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "Nome: '" + nome + '\'' +
                ", Ano de Nascimento: " + anoNascimento +
                ", Ano de Falecimento: " + anoFalecimento +
                ", Livros: " + livro;
    }
}
