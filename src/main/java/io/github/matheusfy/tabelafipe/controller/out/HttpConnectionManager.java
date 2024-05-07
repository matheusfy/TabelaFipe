package io.github.matheusfy.tabelafipe.controller.out;

import io.github.matheusfy.tabelafipe.Conversor;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpConnectionManager {

    HttpClient client = HttpClient.newHttpClient();

    public HttpConnectionManager(){

    }

    public String buildRequestAndSend(String uri) {
        HttpRequest request;
        String body;

        try {
            request = HttpRequest.newBuilder(new URI(uri)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            body = response.body();

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return body;
    }
}
