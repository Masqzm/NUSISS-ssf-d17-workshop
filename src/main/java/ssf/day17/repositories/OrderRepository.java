package ssf.day17.repositories;

import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

// Interfaces with db of key=CART:<cartID>
// K: <cartID>, V: 
// {
//    "name":"<name>"
//    "address":"<addr>"
//    "phone":"<phone>"
//    "del-date":<name>
// }
@Repository     
public class OrderRepository {
    private final Logger logger = Logger.getLogger(OrderRepository.class.getName());

    // DependencyInject (DI) the RedisTemplate into ContactRepository
    @Autowired @Qualifier("redis-0")
    private RedisTemplate<String, String> template;

    // SET <orderID> "{<cart json string>}"
    public void insertOrder(String orderID, String json) {
        ValueOperations<String, String> valueOps = template.opsForValue();

        valueOps.set(orderID, json);
    }

    // GET keys *
    public Set<String> getOrders() {
        return template.keys("*");
    }

    // GET <orderID>
    public Optional<String> getOrderByID(String orderID) {
        ValueOperations<String, String> valueOps = template.opsForValue();

        String order = valueOps.get(orderID);

        if(order == null)
            return Optional.empty();
        
        return Optional.of(order);
    }
}
