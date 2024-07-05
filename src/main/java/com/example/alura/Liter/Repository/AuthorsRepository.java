package com.example.alura.Liter.Repository;

import com.example.alura.Liter.Model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<Authors, Long> {

    @Query("SELECT a FROM Authors a WHERE a.death_year > :year")
    List<Authors> pegarAutoresPorMorte(@Param("year") int year);
}
