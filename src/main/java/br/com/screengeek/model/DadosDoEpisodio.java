package br.com.screengeek.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Mesmo procedimento feito com a classe DadosDaSerie.
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDoEpisodio(@JsonAlias("Title") String titulo, 
                              @JsonAlias("Episode") int numeroEpisodio,
                              @JsonAlias("imdbRating") String avaliacao,
                              @JsonAlias("Released") String dataDeLancamento) {
}
