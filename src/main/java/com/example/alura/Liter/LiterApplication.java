package com.example.alura.Liter;

import com.example.alura.Liter.Main.MainPage;
import com.example.alura.Liter.Repository.AuthorsRepository;
import com.example.alura.Liter.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorsRepository authorsRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MainPage main = new MainPage(bookRepository, authorsRepository);
		main.showMenu();
	}
}
