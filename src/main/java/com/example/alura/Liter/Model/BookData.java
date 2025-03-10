package com.example.alura.Liter.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(@JsonAlias("title") String title,
                        @JsonAlias("authors") List<AuthorsData> authors,
                        @JsonAlias("download_count")Integer download_count,
                        @JsonAlias("languages")String[]languages,
                        @JsonAlias("formats") Map<String, String> formats) {
}
