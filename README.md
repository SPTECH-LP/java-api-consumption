# Tutorial: Utilizando HTTP Requests e Serialização JSON com Jackson em Java

## Objetivo
Neste tutorial, vamos aprender a realizar requisições HTTP (`GET`, `POST`, `PUT`, `DELETE`) em Java usando a classe `HttpClient`. Além disso, vamos utilizar a biblioteca `Jackson` para converter (serializar) objetos Java em JSON e vice-versa (desserializar). Para exemplificar, acessaremos a API pública do Rick and Morty (https://rickandmortyapi.com/).

## Dependências
```xml
 <dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.17.2</version>
</dependency>
```

## Passo a Passo

### 1. Estrutura do Projeto
O código está estruturado em um pacote chamado `school.sptech`. A classe principal `Main` contém a lógica de requisições HTTP e de serialização/desserialização JSON.

### 2. Importando Dependências
No código, importamos:
- **HttpClient** e **HttpRequest** para realizar as requisições HTTP.
- **ObjectMapper** da biblioteca Jackson para manipulação de JSON.

### 3. Criando a Classe `Personage`
Para representar os dados dos personagens da API, criamos uma classe `Personage` com os atributos correspondentes. Esta classe precisa ser compatível com o JSON que a API retorna.

```java
package school.sptech.dto;

public class Personage {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private Location location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;

    // Construtor, getters e setters...
}
```


A classe personage foi construidar dessa forma porque ela deve ser um representação do JSON que a gente vai receber, no nosso caso, iremos receber um JSON com os seguintes atributos:
```json
{
  "id": 1,
  "name": "",
  "status": "",
  "species": "",
  "type": "",
  "gender":"",
  "origin": {
    "name": "",
    "url": ""
  },
  "location": {
    "name": "",
    "url": ""
    },
    "image": "",
    "episode": [],
    "url": "",
    "created": ""
}
  
 ```

Perceba que os atributos `origin` e `location` são objetos JSON aninhados. Para representá-los, criamos as classes `Origin` e `Location`:


### 4. Estrutura da Classe `Main`
A classe `Main` realiza as seguintes operações HTTP: `GET`, `POST`, `DELETE` e `PUT`. Cada uma será explicada abaixo:

#### A) Requisição `GET`
A requisição `GET` é usada para buscar um personagem específico da API pelo seu ID. O JSON retornado é convertido para um objeto `Personage` usando Jackson.

```java
// Criação do cliente HTTP
HttpClient client = HttpClient.newHttpClient();
ObjectMapper mapper = new ObjectMapper();

// Requisição GET
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://rickandmortyapi.com/api/character/327"))
    .build();
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

// Serializa o JSON em um objeto Java
Personage character = mapper.readValue(response.body(), Personage.class);
System.out.println(character);
```

**Explicação:**
1. Criamos uma instância de `HttpClient`.
2. Criamos uma instância de `ObjectMapper` para a manipulação de JSON.
3. Montamos a requisição `GET` para a URL do personagem.
4. Enviamos a requisição e armazenamos a resposta em `response`.
5. Usamos `mapper.readValue` para desserializar o JSON da resposta para o objeto `Personage`.

#### B) Requisição `POST`
A requisição `POST` é utilizada para enviar dados à API (aqui simulamos a criação de um novo personagem).

```java
// Criação de um novo objeto Personage
Personage newCharacter = new Personage("Rick", "Alive", "Human", "Scientist");
String json = mapper.writeValueAsString(newCharacter);

HttpRequest postRequest = HttpRequest.newBuilder()
    .uri(URI.create("https://rickandmortyapi.com/api/character"))
    .POST(HttpRequest.BodyPublishers.ofString(json))
    .build();
HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
System.out.println(postResponse.statusCode());
```

**Explicação:**
1. Criamos um novo objeto `Personage`.
2. Serializamos esse objeto para JSON usando `mapper.writeValueAsString`.
3. Construímos e enviamos a requisição `POST`, passando o JSON no corpo da requisição.
4. Exibimos o status da resposta, que indica se a operação foi bem-sucedida.

#### C) Requisição `DELETE`
A requisição `DELETE` é utilizada para remover um recurso específico. Aqui, tentamos deletar um personagem pelo ID.

```java
HttpRequest deleteRequest = HttpRequest.newBuilder()
    .uri(URI.create("https://rickandmortyapi.com/api/character/1"))
    .DELETE()
    .build();
HttpResponse<String> deleteResponse = client.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
System.out.println(deleteResponse.statusCode());
```

**Explicação:**
1. Criamos a requisição `DELETE` para o personagem com ID 1.
2. Enviamos a requisição e exibimos o código de status da resposta.

#### D) Requisição `PUT`
A requisição `PUT` é utilizada para atualizar os dados de um recurso existente.

```java
Personage updatedCharacter = new Personage("Rick", "Alive", "Human", "Scientist");
String jsonUpdate = mapper.writeValueAsString(updatedCharacter);

HttpRequest putRequest = HttpRequest.newBuilder()
    .uri(URI.create("https://rickandmortyapi.com/api/character/1"))
    .PUT(HttpRequest.BodyPublishers.ofString(jsonUpdate))
    .build();
HttpResponse<String> putResponse = client.send(putRequest, HttpResponse.BodyHandlers.ofString());
System.out.println(putResponse.statusCode());
```

**Explicação:**
1. Criamos o objeto `Personage` atualizado.
2. Serializamos o objeto para JSON.
3. Construímos e enviamos a requisição `PUT`, passando o JSON atualizado no corpo da requisição.
4. Exibimos o status da resposta.

### 5. Tratamento de Exceções
As operações HTTP e de serialização JSON podem gerar exceções (`IOException`, `InterruptedException`). Neste exemplo, elas são tratadas em um bloco `try-catch`.

## Segue abaixo os links da documentação do Jackson e do HttpClient:
- [HttpClient](https://www.baeldung.com/java-9-http-client)
- [Jackson](https://github.com/FasterXML/jackson-docs)