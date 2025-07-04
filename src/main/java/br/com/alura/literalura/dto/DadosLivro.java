package br.com.alura.literalura.dto;

import br.com.alura.literalura.model.Autor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro (
                         @JsonAlias("title") String titulo,
                         @JsonAlias("authors") List<DadosAutor> autor,
                         @JsonProperty("languages") List<String> idioma,
                         @JsonAlias("download_count") Integer downloads) {
}
