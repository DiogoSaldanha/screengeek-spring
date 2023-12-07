package br.com.screengeek.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.screengeek.model.DadosDaSerie;
import br.com.screengeek.model.DadosDaTemporada;
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
		DadosDaSerie dados = conversor.obterDados(json, DadosDaSerie.class); //Fazendo a conversão do Json e atribuindo para uma variável, nesse caso uma classe DadosDaSerie.
		System.out.println("\n\nSérie --->  " + dados);

        //Lista vai ser usada para armazenar todas as instâncias, para depois imprimir a mesma.
		List<DadosDaTemporada> temporadas = new ArrayList<>();
		//Mesmo processo, mas o primeiro for serve para criar um objeto para cada uma das temporadas e armazenar no array 'temporadas'. Para saber quantas vezes iterar (número de temporadas da serie), apenas buscar o atributo temporadas da classe DadosDaSerie instanciada anteriormente. 
		//A cada iteração, é necessário alterar o link.
		System.out.println("\n\nInformações de todas as temporadas da série: \n");
		for (int i = 1; i <= dados.temporadas(); i++) {
			json = consumoDeApi.obterDados(URL + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosDaTemporada dadosDaTemporada = conversor.obterDados(json, DadosDaTemporada.class);
			temporadas.add(dadosDaTemporada);
		}

        //Imprimindo temporadas.
		temporadas.forEach(System.out::println);
    }
}
