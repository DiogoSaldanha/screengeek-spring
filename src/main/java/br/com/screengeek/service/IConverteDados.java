package br.com.screengeek.service;

//Nome é IConverteDados por boas práticas, interfaces geralmente começam com esse nome.
public interface IConverteDados {
    //<T> T dessa maneira indica que eu ainda 'não sei' que tipo vai ser retornado por esse método, isso é um Generics.
    //Como em nenhum momento foi definido que objeto quero como retorno, em algum lugar devo indicar que tipo de dados quero retornar.
    //Nesse caso, o segundo parâmetro passado será o tipo de retorno que esperamos.
    <T> T obterDados(String json, Class<T> classe);
}
