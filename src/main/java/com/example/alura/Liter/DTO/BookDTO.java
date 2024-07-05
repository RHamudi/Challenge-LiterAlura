package com.example.alura.Liter.DTO;

import com.example.alura.Liter.Model.Book;

public record BookDTO(Long id, String titulo, String autor, String idioma, String numeroDowloads) {
}
