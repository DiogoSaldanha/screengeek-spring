package br.com.screengeek;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.screengeek.model.DadosDaSerie;
import br.com.screengeek.model.DadosDoEpisodio;
import br.com.screengeek.service.ConsumoApi;
import br.com.screengeek.service.ConverteDados;

//Implements CommandLineRunner para poder puxar o método 'run()', que é basicamente o nosso main().
@SpringBootApplication
public class ScreengeekApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreengeekApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=game+of+thrones&apikey=4e7d669c");
		System.out.println("\n\nTodas as informações do JSON --->  " + json);

		//Consegue converter a API para um objeto instanciado.
		ConverteDados conversor = new ConverteDados();

		//Fazendo a conversão do Json e atribuindo para uma variável, nesse caso uma classe DadosDaSerie.
		DadosDaSerie dados = conversor.obterDados(json, DadosDaSerie.class);
		System.out.println("\n\nInformações retiradas desse JSON pela classe DadosDaSerie --->  " + dados);

		//Antes de utilizar o conversor de novo, vou mudar a variável json, para que a busca do espisódio seja feita corretamente.
		json = consumoApi.obterDados("https://www.omdbapi.com/?t=game+of+thrones&season=4&episode=2&apikey=4e7d669c");
		DadosDoEpisodio dadosDoEpisodio = conversor.obterDados(json, DadosDoEpisodio.class);
		System.out.println("\n\nInformações retiradas do novo JSON pela classe DadosDoEpisodio --->  " + dadosDoEpisodio);
	}

}


// https://www.omdbapi.com/?t=&apikey=4e7d669c