package school.sptech;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import school.sptech.dto.CharacterDto;

public class ExemploGet {

    public static void main(String[] args) {
        // Requisição GET
        try (HttpClient client = HttpClient.newHttpClient()) {

            // Cria um objeto para transformar JSON em objeto Java ou vice-versa
            ObjectMapper mapper = new ObjectMapper();

            // Cria a requisição
            URI uri = URI.create("https://rickandmortyapi.com/api/character/327");

            HttpRequest request = HttpRequest.newBuilder()
                  .uri(uri)
                  .build();

            // Envia a requisição
            HttpResponse<String> response = client.send(request,
                  HttpResponse.BodyHandlers.ofString());

            // Serializa as informações do JSON em um objeto Java
            CharacterDto character = mapper.readValue(response.body(), CharacterDto.class);

            // Exibe o objeto
            System.out.println(character);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
