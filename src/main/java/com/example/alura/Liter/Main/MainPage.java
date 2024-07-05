package com.example.alura.Liter.Main;

import com.example.alura.Liter.Model.Authors;
import com.example.alura.Liter.Model.Book;
import com.example.alura.Liter.Model.BookData;
import com.example.alura.Liter.Model.ResultsData;
import com.example.alura.Liter.Repository.AuthorsRepository;
import com.example.alura.Liter.Repository.BookRepository;
import com.example.alura.Liter.Service.DataConvert;
import com.example.alura.Liter.Service.RequestAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainPage {
    private Scanner leitura = new Scanner(System.in);
    private RequestAPI request = new RequestAPI();
    private DataConvert convert = new DataConvert();
    List<Book> books = new ArrayList<>();
    private BookRepository bookRepository;
    private AuthorsRepository authorsRepository;

    private final String url = "https://gutendex.com/books/?search=";

    public MainPage(BookRepository bookRepository, AuthorsRepository authorsRepository) {
        this.bookRepository = bookRepository;
        this.authorsRepository = authorsRepository;
    }

    public void showMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar livros
                    2 - Listar Todos Os Livros
                    3 - Listar Autores Registrados
                    4 - Listar autores vivos por ano
                    5 - Listar livros em um determinado idioma
                                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    getBook();
                    break;
                case 2:
                    listAllBooks();
                    break;
                case 3:
                    listAllAuthors();
                    break;
                case 4:
                    listAuthorsAlive();
                    break;
                case 5:
                    listBooksByLanguage();
                default:
                    System.out.println("opção Invalida");
            }
        }
    }



    private ResultsData searchBook() {
        System.out.println("Digite o nome do livro");
        var nomeLivro = leitura.nextLine();
        var json = request.RequestUrl(url + nomeLivro.toLowerCase().replace(" ", "%20"));
        return convert.convertData(json, ResultsData.class);
    }

    private void getBook() {
        ResultsData results = searchBook();
        List<BookData> booksData = results.results().stream().toList();
        booksData.forEach(b -> books.add(new Book(b)));
        try{
            books.forEach(b -> bookRepository.save(b));
        } catch (Exception e){
            System.out.println(e);
        }
        books.forEach(System.out::println);
    }

    private void listAllBooks() {
        System.out.println("Listando todos os livros salvos");
        List<Book> listBooks = bookRepository.findAll();

        listBooks.forEach(System.out::println);
    }

    private void listAllAuthors(){
        System.out.println("Listando todos os autores salvos");
        List<Authors> listAuthors = authorsRepository.findAll();

        listAuthors.forEach(System.out::println);
    }

    private void listAuthorsAlive(){
        System.out.println("Digite um ano para listar os autores vivos");
        var years = leitura.nextInt();

        List<Authors> authorsAlive = authorsRepository.pegarAutoresPorMorte(years);

        authorsAlive.forEach(System.out::println);
    }

    private void listBooksByLanguage(){
        System.out.println("Insira o idioma para realizar a busca:");
        System.out.println("es - espanhol");
        System.out.println("en - inglês");
        System.out.println("fr - francês");
        System.out.println("pt - português");

        var language = leitura.nextLine();
        String[] languageArray = new String[]{language};
        List<Book> books = bookRepository.findByLanguageContaining(language);
        books.forEach(System.out::println);
    }
}
