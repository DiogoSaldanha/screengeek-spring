package br.com.screengeek.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Mesmo processo das outras classes, mas como é uma temporada, armazena uma lista de episódios (classe DadosDoEpisodio)
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDaTemporada(@JsonAlias("Season") Integer numeroTemporada,
                               @JsonAlias("Episodes") List<DadosDoEpisodio> episodios) {
    
}
