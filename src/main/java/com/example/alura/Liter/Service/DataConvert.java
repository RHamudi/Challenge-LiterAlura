package com.example.alura.Liter.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConvert implements IConvertData{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T convertData(String json, Class<T> className){
        try {
            return objectMapper.readValue(json,className);
        }catch (JsonProcessingException e){
            throw new RuntimeException();
        }
    }
}
