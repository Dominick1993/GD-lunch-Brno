package com.labuda.gdlunch.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Single menu item
 */
@Entity
@Table(name = "menu_item")
public class MenuItem {

    /**
     * Database ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the item
     */
    @Column(length = 1000)
    private String name;

    /**
     * Name of the item
     */
    @Column(length = 1000)
    private String translatedName;

    /**
     * Price
     */
    private Float price;

    /**
     * Constructor
     */
    public MenuItem() {

    }

    /**
     * Constructor
     *
     * @param name item name
     * @param price price
     */
    public MenuItem(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTranslatedName() {
        return translatedName;
    }

    public void setTranslatedName(String translatedName) {
        this.translatedName = translatedName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MenuItem)) {
            return false;
        }
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(getName(), menuItem.getName()) &&
                Objects.equals(getPrice(), menuItem.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice());
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price + '\'' +
                ", translatedName=" + translatedName +
                '}';
    }
}
