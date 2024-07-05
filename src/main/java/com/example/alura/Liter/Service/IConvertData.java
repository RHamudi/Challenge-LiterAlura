package com.example.alura.Liter.Service;

public interface IConvertData {
    <T> T convertData(String json, Class<T> classe);
}
