package br.com.screengeek.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ConverteDados implements IConverteDados{
    //Objeto do Jackson que faz a conversão de Json para objeto.
    private ObjectMapper mapper = new ObjectMapper();

    //Basicamente, consegue pegar o Json enviado da API e retorna instanciado na Classe desejada. TryCatch é obrigatório.
    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } 
    }

}
