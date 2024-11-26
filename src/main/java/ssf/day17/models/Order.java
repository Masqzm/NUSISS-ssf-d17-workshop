package ssf.day17.models;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Order {
    private String name;
    private String address;
    private String phone;
    private Date deliveryDate;

    private List<Item> itemsList;

    // Converts json and returns it as Order obj
    public static Order jsonToOrder(String json) {
        Order order = new Order();

        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObj = reader.readObject();

        order.setName(jsonObj.getString("name"));
        order.setAddress(jsonObj.getString("address"));
        order.setPhone(jsonObj.getString("phone"));

        Date delDate = new Date();
        try {
            delDate = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObj.getString("deliveryDate"));
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        order.setDeliveryDate(delDate);

        List<Item> itemsList = new ArrayList<>();
        JsonArray arr = jsonObj.getJsonArray("items");
        for(int i = 0; i < arr.size(); i++) {
            Item item = new Item();
            item.setItemName(arr.get(i).asJsonObject().getString("itemName"));
            item.setQuantity(arr.get(i).asJsonObject().getInt("quantity"));
        }

        return order;
    }

    @Override
    public String toString() {
        return "Order [name=" + name + ", address=" + address + ", phone=" + phone + ", deliveryDate=" + deliveryDate
                + ", itemsList=" + itemsList + "]";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public List<Item> getItemsList() {
        return itemsList;
    }
    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }
}
