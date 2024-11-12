package school.sptech;

import com.fasterxml.jackson.databind.ObjectMapper;
import school.sptech.dto.Personage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            ObjectMapper mapper = new ObjectMapper();

            // Requisição GET
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://rickandmortyapi.com/api/character/327")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //Serializa as informações do JSON em um obejeto Java
            Personage character = mapper.readValue(response.body(), Personage.class);
            System.out.println(character);

            // Requisição POST
            Personage newCharacter = new Personage("Rick", "Alive", "Human", "Scientist");
            // Transforma objeto do java em JSON
            String json = mapper.writeValueAsString(newCharacter);

            HttpRequest postRequest = HttpRequest.newBuilder().uri(URI.create("https://rickandmortyapi.com/api/character")).POST(HttpRequest.BodyPublishers.ofString(json)).build();
            HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(postResponse.statusCode());

            // Requisição DELETE
            HttpRequest deleteRequest = HttpRequest.newBuilder().uri(URI.create("https://rickandmortyapi.com/api/character/1")).DELETE().build();
            HttpResponse<String> deleteResponse = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(deleteResponse.statusCode());


            // Requisição PUT
            Personage updatedCharacter = new Personage("Rick", "Alive", "Human", "Scientist");
            String jsonUpdate = mapper.writeValueAsString(updatedCharacter);
            HttpRequest putRequest = HttpRequest.newBuilder().uri(URI.create("https://rickandmortyapi.com/api/character/1")).PUT(HttpRequest.BodyPublishers.ofString(jsonUpdate)).build();
            HttpResponse<String> putResponse = client.send(putRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(putResponse.statusCode());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}