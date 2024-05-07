package io.github.matheusfy.tabelafipe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Conversor {

    private final ObjectMapper mapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe){
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> obterListDados(String json, Class<T> classe){

        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, classe));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNode(String json, String campo){

        JsonNode node = null;
        try {
            node = mapper.readTree(json).get(campo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return node.toString();
    }

}
