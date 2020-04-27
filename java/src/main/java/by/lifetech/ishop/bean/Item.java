package by.lifetech.ishop.bean;

import java.math.BigDecimal;

public class Item {
    private int itemId;
    private String categoryName;
    private String nameShort;
    private String nameFull;
    private String description;
    private String manufacturer;
    private BigDecimal price;
    private String stateName;
    private double rating;
    private int count;

    public int getItemId() {
        return itemId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getNameShort() {
        return nameShort;
    }

    public String getNameFull() {
        return nameFull;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getStateName() {
        return stateName;
    }

    public double getRating() {
        return rating;
    }

    public int getCount() {
        return count;
    }

    public Item(int itemId, String categoryName, String nameShort, String nameFull, String description, String manufacturer, BigDecimal price, String stateName, double rating, int count) {
        this.itemId = itemId;
        this.categoryName = categoryName;
        this.nameShort = nameShort;
        this.nameFull = nameFull;
        this.description = description;
        this.manufacturer = manufacturer;
        this.price = price;
        this.stateName = stateName;
        this.rating = rating;
        this.count = count;
    }

    public Item () {}
}
