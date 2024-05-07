package io.github.matheusfy.tabelafipe.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Fipe(
        @JsonAlias String codigo,
        @JsonAlias  String nome
) {
    @Override
    public String toString() {
        return "(codigo: %s, nome: %s)".formatted(codigo, nome);
    }
}
