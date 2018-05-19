package com.ebay.ws.external.model;

import java.util.Objects;

public class Product {

    private String title;
    private Double price;

    public Product(){
    }

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, price);
    }

    @Override
    public String toString() {
        return "FindByProductResponse{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
