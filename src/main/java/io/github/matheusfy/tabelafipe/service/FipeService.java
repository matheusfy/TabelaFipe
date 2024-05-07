package io.github.matheusfy.tabelafipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.matheusfy.tabelafipe.Conversor;
import io.github.matheusfy.tabelafipe.controller.out.HttpConnectionManager;
import io.github.matheusfy.tabelafipe.model.Fipe;
import io.github.matheusfy.tabelafipe.model.TabelaFipe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class FipeService {
    private final HttpConnectionManager httpManager;
    private final Conversor conversor;

    public FipeService() {
        this.httpManager = new HttpConnectionManager();
        this.conversor       = new Conversor();
    }



    public void getMarcas(String uri){

        List<Fipe> marcaAutomovel = conversor.obterListDados(getFipeInfo(uri), Fipe.class);
        sortFipeObj(marcaAutomovel);
        marcaAutomovel.forEach(System.out::println);
    }

    public void getModelos(String uri){

        List<Fipe> modelosAutomovel = conversor.obterListDados(conversor.getNode(getFipeInfo(uri), "modelos"), Fipe.class);
        sortFipeObj(modelosAutomovel);
        modelosAutomovel.forEach(System.out::println);
    }

    public void getAnos(String uri){
        List<Fipe> anosAutomovel = conversor.obterListDados(getFipeInfo(uri), Fipe.class);
        anosAutomovel.stream().sorted(Comparator.comparing(Fipe::nome));

        String linkTabela = uri + "/%s";
        for(Fipe automovel: anosAutomovel){
            System.out.println("*------------------------------------------------------------------------------------*");
            System.out.println(getTabelaFipe(linkTabela.formatted(automovel.codigo())).toString());
        }
        System.out.println("*------------------------------------------------------------------------------------*");
    }

    public TabelaFipe getTabelaFipe(String uri){

        String tabelaJson = getFipeInfo(uri);
        return conversor.obterDados(tabelaJson, TabelaFipe.class);
    }

    private void sortFipeObj(List<Fipe> fipeObj){
        fipeObj.stream().sorted(Comparator.comparing(Fipe::nome)).toList();
    }

    private String getFipeInfo(String uri){
        return httpManager.buildRequestAndSend(uri);
    }

    private void writeJsonToFile(String jsonData){
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File("src/main/java/io/github/matheusfy/tabelafipe/dataFipe.json"), jsonData);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readJsonFromFile(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String dados = mapper.readValue(new File("src/main/java/io/github/matheusfy/tabelafipe/dataFipe.json"), String.class);

            System.out.println(dados);
            TabelaFipe fipe = mapper.readValue(dados,TabelaFipe.class);
            System.out.println(fipe);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
