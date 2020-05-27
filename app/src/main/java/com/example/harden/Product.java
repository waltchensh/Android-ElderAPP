package com.example.harden;

public class Product {
    private String product;
    private String price;
    private String content;
    private String category_name;

    public Product() {
    }

    public Product(String product, String price, String content, String category_name) {
        this.product = product;
        this.price= price;
        this.content = content;
        this.category_name = category_name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}