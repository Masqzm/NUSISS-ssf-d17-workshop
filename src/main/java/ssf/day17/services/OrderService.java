package ssf.day17.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import ssf.day17.repositories.OrderRepository;

// Handles order logic/queries
@Service    
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    public static final String POST_URL = "http://localhost:8080/order";

    // Inserts order to db and returns generated cartID str
    public String insertOrder(String json) {
        // Generate random orderID
        String orderID = UUID.randomUUID().toString().substring(0, 8);

        orderRepo.insertOrder(orderID, json);

        return orderID;
    }

    // public void postJson(String json) {
    //     // Create req
    //     RequestEntity<String> req = RequestEntity
    //                 .put(POST_URL)
    //                 .contentType(MediaType.APPLICATION_JSON)
    //                 .accept(MediaType.APPLICATION_JSON)     
    //                 .body(json.toString(), String.class);

    //     RestTemplate template = new RestTemplate();

    //     try {
    //         ResponseEntity<String> resp = template.exchange(req, String.class);
    //         String payload = resp.getBody();
            
    //         System.out.printf("Payload: %s\n", payload);
    //     } catch(Exception ex) {
    //         ex.printStackTrace();
    //     }
    // }
}
