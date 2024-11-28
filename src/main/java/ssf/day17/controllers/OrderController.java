package ssf.day17.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        String orderID = orderSvc.insertOrder(payload);

        JsonObject resp = Json.createObjectBuilder()
                        .add("orderId", orderID)
                        .build();

        // Response: 202 Accepted
        // Content-Type: application/json
        // Body: { "orderId":"<orderID>" }
        // return ResponseEntity.accepted()
        //                     .body(resp.toString());
        // Response: 200 OK
        return ResponseEntity.ok(resp.toString());
    }

    // GET /orders
    // Accept: text/csv
    @GetMapping(path="/orders", produces = "text/csv")
    public ResponseEntity<String> getOrders() {

        String csv = orderSvc.getOrders();

        // Response: 200 OK
        // Content-Type: text/csv
        // csv:
        // orderID
        // <orderID>
        // ...
        return ResponseEntity.ok(csv);
    }

    // GET /order/{id}      // orderID exist ? 200 : 404
    // Return JSON object
    @GetMapping(path="/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOrderByID(@PathVariable String id) {
        Optional<String> optOrder = orderSvc.getOrderByID(id);
        
        // Response: 404
        if(optOrder.isEmpty()) 
            return ResponseEntity.notFound().build();

        // Response: 200 OK
        // Content-Type: application/json
        // {order}
        return ResponseEntity.ok(optOrder.get());
    }
}
