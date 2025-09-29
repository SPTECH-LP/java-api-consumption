package school.sptech;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExemploDelete {

    public static void main(String[] args) {
        // Requisição DELETE
        try (HttpClient client = HttpClient.newHttpClient()) {

            // Cria a requisição DELETE
            URI uri = URI.create("https://rickandmortyapi.com/api/character/1");

            HttpRequest deleteRequest = HttpRequest.newBuilder()
                  .uri(uri)
                  .DELETE()
                  .build();

            // Envia a requisição
            HttpResponse<String> deleteResponse = client.send(deleteRequest,
                  HttpResponse.BodyHandlers.ofString());

            // Exibe o status da requisição
            System.out.println(deleteResponse.statusCode());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
