package ro.utcluj.helloworld.springboot.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class BRPController {
    private static final int BRP_TIMEOUT = 1000; // retransmission timeout in milliseconds
    private static final int BRP_MAX_RETRIES = 5; // maximum number of retries

    @PostMapping("/brp")
    public String handleRequest(@RequestBody String request) throws JsonProcessingException {
        // Process the request and return the response
        String response;
        // Add code to process the request here
        ObjectMapper mapper = new ObjectMapper();
        JsonNode requestJson = mapper.readTree(request);

        // Extract the data you need from the request JSON
        String data = requestJson.get("data").asText();

        // Use the data to retrieve information from a database
        // For example, you could use the JdbcTemplate to query a database and retrieve the information you need
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM table WHERE data = ?", data);

        // Use the results to generate the response
        // For example, you could create a JSON object that contains the information you retrieved from the database
        ObjectNode responseJson = mapper.createObjectNode();
        responseJson.put("results", (BigDecimal) results);
        response = responseJson.toString();

        return response;
    }

    @GetMapping("/brp")
    public String viewBrpPage() {
        return "brp";
    }


    public String sendRequest(String request) {
        int retries = 0;
        String response;

        // Send the request and wait for the response
        while (retries < BRP_MAX_RETRIES) {
            response = send(request);
            if (response != null) {
                // Return the response if it is received
                return response;
            } else {
                // Increment the retry count and wait for the timeout
                retries++;
                try {
                    Thread.sleep(BRP_TIMEOUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Return null if the maximum number of retries is reached
        return null;
    }

    private String send(String request) {
        // Send the request to the server and return the response
        String response = null;
        try {
            // Use the RestTemplate to send the request and receive the response
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/handle-request", HttpMethod.POST, entity, String.class);
            response = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        return response;
    }
}
