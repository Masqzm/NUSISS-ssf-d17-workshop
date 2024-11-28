package ssf.day17.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssf.day17.repositories.OrderRepository;

// Handles order logic/queries
@Service    
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    // Inserts order to db and returns generated cartID str
    public String insertOrder(String json) {
        // Generate random orderID
        String orderID = UUID.randomUUID().toString().substring(0, 8);

        orderRepo.insertOrder(orderID, json);

        return orderID;
    }
}
