package ro.utcluj.helloworld.springboot.Controller.REST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ro.utcluj.helloworld.springboot.Model.User;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
public class BrpRestController {
    private static final int BRP_TIMEOUT = 1000; // retransmission timeout in milliseconds
    private static final int BRP_MAX_RETRIES = 5; // maximum number of retries
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BrpRestController(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostMapping(value = "/handle-request", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String handleRequest(@RequestBody User request) throws JsonProcessingException {
        String response;
        // Use the data to retrieve information from a database
        // For example, you could use the JdbcTemplate to query a database and retrieve the information you need
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM user WHERE username = ?", request.getUsername());
        ObjectMapper mapper = new ObjectMapper();
// Use the results to generate the response
        ObjectNode responseJson = mapper.createObjectNode();
        responseJson.put("results", (BigDecimal) results);
        response = responseJson.toString();
        return response;
    }


    @PostMapping("/send-request")
    public String sendRequest(@RequestBody User request) {
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

    private String send(User request) {
        // Send the request to the server and return the response
        String response = null;
        try {
            // Use the RestTemplate to send the request and receive the response
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<User> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/brp/handle-request", HttpMethod.POST, entity, String.class);
            response = responseEntity.getBody();
            System.out.println(response);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
        return response;
    }
}
