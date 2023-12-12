package br.com.screengeek.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//Classe para filtrar dados das outras classes que reitram dados diretamente da API.
public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;

    
    public Episodio(Integer numeroTemporada, DadosDoEpisodio dadosDoEpisodio) {
        this.temporada = numeroTemporada;
        this.titulo = dadosDoEpisodio.titulo();
        this.numeroEpisodio = dadosDoEpisodio.numeroEpisodio();

        try{
            this.avaliacao = Double.valueOf(dadosDoEpisodio.avaliacao());
        } catch(NumberFormatException ex){
            this.avaliacao = 0.0;
        }

        try{
            this.dataLancamento = LocalDate.parse(dadosDoEpisodio.dataDeLancamento());
        } catch(DateTimeParseException ex){
            this.dataLancamento = null;
        }
    }
    public Integer getTemporada() {
        return temporada;
    }
    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }
    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }
    public Double getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    @Override
    public String toString() {
        return "temporada=" + temporada + ", titulo=" + titulo + ", numeroEpisodio=" + numeroEpisodio
                + ", avaliacao=" + avaliacao + ", dataLancamento=" + dataLancamento;
    }

    
}
