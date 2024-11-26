package ssf.day17.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import ssf.day17.models.Order;
import ssf.day17.services.OrderService;

@RestController
@RequestMapping
public class OrderController {

    @Autowired
    OrderService orderSvc;
    
    // PUT /order
    // Content-Type: application/json
    // Accept: application/json
    @PutMapping(path="/order", 
                produces = MediaType.APPLICATION_JSON_VALUE,    // to client
                consumes = MediaType.APPLICATION_JSON_VALUE)    // from client
    public ResponseEntity<String> postOrder(@RequestBody String payload) {
        System.out.printf("PAYLOAD: %s\n", payload);

        // Conversion of json to Order obj
        Order order = Order.jsonToOrder(payload);

        System.out.printf("Order: %s\n", order);

        // Service to store order
        String cartID = orderSvc.insertOrder(payload);

        JsonObject resp = Json.createObjectBuilder()
                        .add("orderId", cartID)
                        .build();

        // Response: 202 Accepted
        // Content-Type: application/json
        // Body: { "cartID":"<cartID>" }
        // return ResponseEntity.accepted()
        //                     .body(resp.toString());
        // Response: 200 OK
        return ResponseEntity.ok(resp.toString());
    }

}
