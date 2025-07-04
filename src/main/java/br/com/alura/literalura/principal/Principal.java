package br.com.alura.literalura.principal;

import br.com.alura.literalura.dto.Dados;
import br.com.alura.literalura.dto.DadosAutor;
import br.com.alura.literalura.dto.DadosLivro;
import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoAPI;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    @Autowired
    private final LivroRepository livroRepositorio;

    @Autowired
    private final AutorRepository autorRepositorio;

    public Principal(LivroRepository livroRepositorio, AutorRepository autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private Scanner leitura = new Scanner(System.in);

    private String json;

    private String menu = """
            *** LiterAlura ***
            
            1 - Buscar livro pelo título
            2 - Listar livros registrados
            3 - Listar autores registrados
            4 - Listar autores vivos em um determinado ano
            5 - Listar livros em um determinado idioma
            
            0 - Sair      
            """;

    public void mostrarMenu() {
        var opcao = -1;
        while (opcao != 0) {
            json = consumoAPI.obterDados(URL_BASE);
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();
            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivosDeterminadoAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("\nEncerrando...");
                default:
                    System.out.println("\nOpção inválida.");

            }

        }
    }

    private void buscarLivroPorTitulo() {
        DadosLivro dadosLivro = obterDadosLivro();
        if(dadosLivro != null) {
            DadosAutor dadosAutor = dadosLivro.autor().get(0);
            Livro livro;
            Autor autorExistente = autorRepositorio.findByNome(dadosAutor.nome());
            if(autorExistente != null) {
                livro = new Livro(dadosLivro, autorExistente);
            } else {
                Autor autor = new Autor(dadosAutor);
                livro = new Livro(dadosLivro, autor);
                autorRepositorio.save(autor);
            }
            livroRepositorio.save(livro);
            System.out.println(livro);
        }
    }

    private DadosLivro obterDadosLivro() {
        System.out.println("Informe o título do livro para busca: ");
        var nome = leitura.nextLine();
        json = consumoAPI.obterDados(URL_BASE + "?search=" + nome.replace(" ", "+"));
        Dados dadosBusca = conversor.obterDados(json, Dados.class);
        Optional <DadosLivro> dadosLivro = dadosBusca.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nome.toUpperCase()))
                .findFirst();
        if (dadosLivro.isPresent()) {
            return dadosLivro.get();
        } else {
            return null;
        }

    }

    private void listarLivros() {
    }

    private void listarAutores() {
    }

    private void listarAutoresVivosDeterminadoAno() {
    }

    private void listarLivrosPorIdioma() {
    }
}
