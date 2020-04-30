package adapters;

public class SavedItems {

    private String itemName;
    private String price;
    private String size;
    private String color;

    public SavedItems(String itemName,String price, String size,String color)
    {
        this.itemName = itemName;
        this.price = price;
        this.size = size;
        this.color =  color;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
