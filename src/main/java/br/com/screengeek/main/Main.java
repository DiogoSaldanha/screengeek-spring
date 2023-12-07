package br.com.screengeek.main;

import java.util.Scanner;

import br.com.screengeek.service.ConsumoApi;

//Classe Main feita para definir funções e métodos para interagir com o usuário.
//Copiando funcionalidades da classe Application para deixar o código mais legível.
public class Main {
    
    //Constantes.
    Scanner scanner = new Scanner(System.in);
    private final ConsumoApi CONSUMO_DE_API = new ConsumoApi();
    private final String URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "4e7d669c";

    public void exibirMenu(){
        System.out.println("Digite o nome da série que deseja buscar: ");
        var nomeSerie = scanner.nextLine();
		var json = CONSUMO_DE_API.obterDados(URL + nomeSerie.replace(" ", "+") + API_KEY);

    }
}
