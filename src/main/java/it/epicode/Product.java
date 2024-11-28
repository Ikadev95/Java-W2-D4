package it.epicode;

public class Product {
    private Long id;
    private String name;
    private String category;
    private Double price;

    public Product(String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void stampa(){
        System.out.println(name + " " + category + " " + price + " ");
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
