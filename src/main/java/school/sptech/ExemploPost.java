package school.sptech;

import com.fasterxml.jackson.databind.ObjectMapper;
import school.sptech.dto.Personage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExemploPost {
    public static void main(String[] args) {
        // Requisição POST
        try (HttpClient client = HttpClient.newHttpClient()) {
            // Cria um objeto para transformar JSON em objeto Java ou vice-versa
            ObjectMapper mapper = new ObjectMapper();

            // Cria um novo personagem
            Personage newCharacter = new Personage("Rick", "Alive", "Human", "Scientist");

            // Transforma objeto do java em JSON
            String json = mapper.writeValueAsString(newCharacter);

            // Criar a requisição POST
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://rickandmortyapi.com/api/character"))
                    .POST(HttpRequest.BodyPublishers.ofString(json)).build();

            // Envia a requisição
            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

            // Exibe o status da requisição
            System.out.println(postResponse.statusCode());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
