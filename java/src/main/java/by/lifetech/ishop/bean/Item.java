package by.lifetech.ishop.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Item implements Serializable {
    public static final long serialVersionUID = 3650830057666549258L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemId == item.itemId &&
                Double.compare(item.rating, rating) == 0 &&
                count == item.count &&
                Objects.equals(categoryName, item.categoryName) &&
                Objects.equals(nameShort, item.nameShort) &&
                Objects.equals(nameFull, item.nameFull) &&
                Objects.equals(description, item.description) &&
                Objects.equals(manufacturer, item.manufacturer) &&
                Objects.equals(price, item.price) &&
                Objects.equals(stateName, item.stateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, categoryName, nameShort, nameFull, description, manufacturer, price, stateName, rating, count);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", categoryName='" + categoryName + '\'' +
                ", nameShort='" + nameShort + '\'' +
                ", nameFull='" + nameFull + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", stateName='" + stateName + '\'' +
                ", rating=" + rating +
                ", count=" + count +
                '}';
    }

}
