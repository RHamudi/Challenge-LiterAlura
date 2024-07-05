package com.example.alura.Liter.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String [] language;
    private Integer dowload_count;
    private String image;

    @OneToMany(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authors> authors = new ArrayList<>();

    public Book(){}

    public Book(BookData bookData) {
        this.title = bookData.title();
        this.language = bookData.languages();
        this.dowload_count = bookData.download_count();
        List<AuthorsData> authorData = bookData.authors().stream().toList();
        authorData.forEach(a -> authors.add(new Authors(a, this)));
        this.image = bookData.formats().get("image/jpeg");
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getLanguage() {
        return language;
    }

    public void setLanguage(String[] language) {
        this.language = language;
    }

    public Integer getDowload_count() {
        return dowload_count;
    }

    public void setDowload_count(Integer dowload_count) {
        this.dowload_count = dowload_count;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Título= " + title  +
                ", Autores= " + authorsName()+
                ", Idioma= " + language[0] +
                ", Total de Downloads =" + dowload_count;
    }

    private String authorsName(){
        StringBuilder authorsName = new StringBuilder();
        for(Authors a: authors){
            authorsName.append(a.getName()).append(", ");
        }
        return authorsName.toString();
    }
}
