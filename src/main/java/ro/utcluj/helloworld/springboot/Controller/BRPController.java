package ro.utcluj.helloworld.springboot.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ro.utcluj.helloworld.springboot.Model.User;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class BRPController {
    private static final int BRP_TIMEOUT = 1000; // retransmission timeout in milliseconds
    private static final int BRP_MAX_RETRIES = 5; // maximum number of retries
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BRPController(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping("/brp")
    public String viewBrpPage() {
        return "brp";
    }


}