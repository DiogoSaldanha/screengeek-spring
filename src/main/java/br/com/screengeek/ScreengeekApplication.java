package br.com.screengeek;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.screengeek.main.Main;

//Implements CommandLineRunner para poder puxar o método 'run()', que é basicamente o nosso main().
@SpringBootApplication
public class ScreengeekApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ScreengeekApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.exibirMenu();
	}

}