package io.github.matheusfy.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TabelaFipe{
    @JsonProperty("TipoVeiculo") String tipoVeiculo;
    @JsonProperty("Valor")  String valor;
    @JsonProperty("Marca")  String marca;
    @JsonProperty("Modelo")  String modelo;
    @JsonProperty("AnoModelo")  Integer anoModelo;
    @JsonProperty("Combustivel")  String combustivel;
    @JsonProperty("CodigoFipe")  String codigoFipe;
    @JsonProperty("MesReferencia")  String mesReferencia;

    @Override
    public String toString() {
        return """
                TabelaFipe: \s
                tipoVeiculo = %s
                 valor = %s
                 marca = %s
                modelo = %s
                anoModelo = %s
                combustivel = %s
                codigoFipe = %s
                mesReferencia = %s
                """.formatted(tipoVeiculo, valor, marca, modelo, anoModelo, combustivel, codigoFipe, mesReferencia);
    }
}
