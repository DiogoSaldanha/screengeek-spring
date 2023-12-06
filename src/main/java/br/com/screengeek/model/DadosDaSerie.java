package br.com.screengeek.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Como a única função dessa classe é armazenar os dados e não criar nenhum método, utilei o record.
@JsonIgnoreProperties(ignoreUnknown = true) //JsonIgnoreProperties serve para que tudo que não seja titulo, temporadas e avaliação lá da API seja ignorado e ão interfira na hora de buscar essas informações.
public record DadosDaSerie(@JsonAlias("Title") String titulo,
                           @JsonAlias("totalSeasons") int temporadas,
                           @JsonAlias("imdbRating") String avaliacao) {
}
//Utilizando JsonAlias, basicamente o que vi na oputra formação. Interessante manter em mente a funcionalidade do @JsonProperty.