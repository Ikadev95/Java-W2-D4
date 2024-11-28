package it.epicode;

public class Customer {
    private Long id;
    private String name;
    private Integer tier;

    public Customer(String name, Integer tier) {
        this.name = name;
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public Integer getTier() {
        return tier;
    }
}
