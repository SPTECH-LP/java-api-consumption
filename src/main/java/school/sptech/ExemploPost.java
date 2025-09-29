package school.sptech;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import school.sptech.dto.CharacterDto;

public class ExemploPost {

    public static void main(String[] args) {
        // Requisição POST
        try (HttpClient client = HttpClient.newHttpClient()) {
            // Cria um objeto para transformar JSON em objeto Java ou vice-versa
            ObjectMapper mapper = new ObjectMapper();

            // Cria um novo personagem
            CharacterDto newCharacter = new CharacterDto("Rick", "Alive", "Human", "Scientist");

            // Transforma objeto do java em JSON
            String json = mapper.writeValueAsString(newCharacter);

            // Criar a requisição POST
            URI uri = URI.create("https://rickandmortyapi.com/api/character");
            BodyPublisher body = BodyPublishers.ofString(json);

            HttpRequest postRequest = HttpRequest.newBuilder()
                  .uri(uri)
                  .POST(body)
                  .build();

            // Envia a requisição
            HttpResponse<String> postResponse = client.send(postRequest,
                  HttpResponse.BodyHandlers.ofString());

            // Exibe o status da requisição
            System.out.println(postResponse.statusCode());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
