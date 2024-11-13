package school.sptech;

import com.fasterxml.jackson.databind.ObjectMapper;
import school.sptech.dto.CharacterDto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExemploPut {
    // Requisição PUT
    public static void main(String[] args) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            // Cria um objeto para transformar JSON em objeto Java ou vice-versa
            ObjectMapper mapper = new ObjectMapper();

            // Cria um novo personagem
            CharacterDto updatedCharacter = new CharacterDto("Rick", "Alive", "Human", "Scientist");

            // Transforma objeto do java em JSON
            String jsonUpdate = mapper.writeValueAsString(updatedCharacter);

            // Cria a requisição PUT
            HttpRequest putRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://rickandmortyapi.com/api/character/1"))
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonUpdate))
                    .build();

            // Envia a requisição
            HttpResponse<String> putResponse = client.send(putRequest, HttpResponse.BodyHandlers.ofString());

            // Exibe o status da requisição
            System.out.println(putResponse.statusCode());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
