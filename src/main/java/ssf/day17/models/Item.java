package ssf.day17.models;

public class Item {
    private String itemName;
    private int quantity;
    
    @Override
    public String toString() {
        return "Item [itemName=" + itemName + ", quantity=" + quantity + "]";
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
