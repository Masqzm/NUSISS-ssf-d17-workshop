package ssf.day17.services;

import java.util.Optional;
import java.util.Set;
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

    public String getOrders() {
        StringBuilder csv = new StringBuilder();

        // Add header
        csv.append("orderID\n");

        Set<String> orders = orderRepo.getOrders();

        for(String order : orders) {
            csv.append(order + "\n");
        }

        return csv.toString();
    }

    public Optional<String> getOrderByID(String orderID) {
        return orderRepo.getOrderByID(orderID);
    }
}
