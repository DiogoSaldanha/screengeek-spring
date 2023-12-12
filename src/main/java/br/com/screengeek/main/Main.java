package br.com.screengeek.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.screengeek.model.DadosDaSerie;
import br.com.screengeek.model.DadosDaTemporada;
import br.com.screengeek.model.DadosDoEpisodio;
import br.com.screengeek.model.Episodio;
import br.com.screengeek.service.ConsumoApi;
import br.com.screengeek.service.ConverteDados;

//Classe Main feita para definir funções e métodos para interagir com o usuário.
//Copiando funcionalidades da classe Application para deixar o código mais legível.
public class Main {
    
    //Constantes.
    Scanner scanner = new Scanner(System.in);
    private final String URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=4e7d669c";
    private ConsumoApi consumoDeApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();     //Consegue converter a API para um objeto instanciado.

    public void exibirMenu(){
        System.out.println("Digite o nome da série que deseja buscar: ");
        var nomeSerie = scanner.nextLine();
		var json = consumoDeApi.obterDados(URL + nomeSerie.replace(" ", "+") + API_KEY);
        //Prcesso abaixo feito para puxar as informações da API e mostrar na tela, serve também para a classe DadosDoEpisodio. Toda vez que usar o conversor, precisa atualizar a variável JSON.
		DadosDaSerie dadosDaSerie = conversor.obterDados(json, DadosDaSerie.class); //Fazendo a conversão do Json e atribuindo para uma variável, nesse caso uma classe DadosDaSerie.
		System.out.println("\n\nSérie --->  " + dadosDaSerie);

        //Lista vai ser usada para armazenar todas as instâncias, para depois imprimir a mesma.
        //Mesmo processo, mas o primeiro for serve para criar um objeto para cada uma das temporadas e armazenar no array 'temporadas'. Para saber quantas vezes iterar (número de temporadas da serie), apenas buscar o atributo temporadas da classe DadosDaSerie instanciada anteriormente. 
		//A cada iteração, é necessário alterar o link.
		List<DadosDaTemporada> listaDeTemporadas = new ArrayList<>();
		System.out.println("\n\nInformações de todas as temporadas da série: \n");
		for (int i = 1; i <= dadosDaSerie.temporadas(); i++) {
			json = consumoDeApi.obterDados(URL + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosDaTemporada dadosDaTemporada = conversor.obterDados(json, DadosDaTemporada.class);
			listaDeTemporadas.add(dadosDaTemporada);
		}

        //Imprimindo temporadas.
        // Usando lambdas. Maneira mais fácil para iterar sob esses dados, ao invés de fazer duas iterações diferentes para filtrar e outra apenas para printar.
        listaDeTemporadas.forEach(t -> t.episodios().forEach(e -> System.out.println("Temporada " + t.numeroTemporada() + 
                                                                                     " | Episódio " + e.numeroEpisodio() + 
                                                                                     ": " + e.titulo())));
        
        //Lista com apenas DadosDeEpisódios. Quero recolher informações de todos os episódios de todas as temporadas de uma vez, para não precisar verificar lista dentro de lista.
        List<DadosDoEpisodio> dadosDosEpisodios = listaDeTemporadas.stream()
                                                  .flatMap(t -> t.episodios().stream())
                                                  .collect(Collectors.toList());

        System.out.println("\n\nTop 5 episódios mais bem avaliados dessa série:");
        dadosDosEpisodios.stream()
                         .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                         .sorted(Comparator.comparing(DadosDoEpisodio::avaliacao).reversed())
                         .limit(5)
                         .forEach(e -> System.out.println("- Título: " + e.titulo() + " | Avaliação " + e.avaliacao()));

        List<Episodio> episodios = listaDeTemporadas.stream()
                                   .flatMap(t -> t.episodios().stream()
                                        .map(d -> new Episodio(t.numeroTemporada(), d))
                                        ).collect(Collectors.toList());

        System.out.println("\n\n");
        episodios.forEach(System.out::println);

        System.out.println("A partir de que ano deseja ver os episódios?");
        var ano = scanner.nextInt();
        scanner.nextLine();
        LocalDate dataDeBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                 .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataDeBusca))
                 .forEach(e -> System.out.println("Temporada " + e.getTemporada() + 
                                                  " | Episódio " + e.getTitulo() + 
                                                  " | Lançamento: " + e.getDataLancamento().format(formatador)));
    }
}
