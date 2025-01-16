package org.example.client.restClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Month;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MonthRestDataSource implements IMonthDataSource {

    @Override
    public List<Month> findAll() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/rest_client_war_exploded/month/list"))
                .GET()
                .build();

        HttpResponse response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        String respBody = (String) response.body();

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(respBody, new TypeReference<List<Month>>() {
        });
    }

    @Override
    public Month findById(Long id) throws URISyntaxException, IOException, InterruptedException {
        // TODO send get to http://localhost:8080/rest_client_war_exploded/month/{id}
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/rest_client_war_exploded/month/" + id))
                .GET()
                .build();

        HttpResponse response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        String respBody = (String) response.body();

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(respBody, new TypeReference<Month>() {
        });
    }

    @Override
    public Month save(Month month) throws IOException, InterruptedException, URISyntaxException {
        // TODO send post to http://localhost:8080/lab12rest/month/save
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        String jsonRequestBody = mapper.writeValueAsString(month);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/rest_client_war_exploded/month/save"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        HttpResponse response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        String respBody = (String) response.body();

        if (response.statusCode() == 200) {
            return mapper.readValue(respBody, Month.class); // Десериализация ответа в объект Month
        } else {
            throw new IOException("Failed to save month: " + response.body());
        }
    }

    @Override
    public void delete(Long id) throws URISyntaxException, IOException, InterruptedException {
        // TODO send post to http://localhost:8080/lab12rest/month/delete/{id}
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/rest_client_war_exploded/month/delete/" + id))
                .DELETE()
                .build();

        HttpResponse response =
                client.send(request, HttpResponse.BodyHandlers.ofString());


        if (response.statusCode() == 200) {
            System.out.println("Month with ID " + id + " was successfully deleted.");
        } else if (response.statusCode() == 404) {
            throw new IOException("Month with ID " + id + " does not exist.");
        } else {
            throw new IOException("Failed to delete month: " + response.body());
        }
    }
}