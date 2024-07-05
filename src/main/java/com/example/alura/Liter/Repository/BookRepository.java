package com.example.alura.Liter.Repository;

import com.example.alura.Liter.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String bookname);


    @Query(value = "SELECT * FROM Books WHERE :language = ANY(language)", nativeQuery = true)
    List<Book> findByLanguageContaining(@Param("language") String language);
}
