package it.epicode;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Product> products;
    private Customer customer;

    public Order(Customer customer, List<Product> products, LocalDate data) {
        this.customer = customer;
        this.products = products;
        this.orderDate = data;
        generaDataDelivery(3);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void stampaOrdine(){
        System.out.println( "customer: " + customer.getName());
        products.forEach(p -> p.stampa());
        System.out.println("----------");
    }

    private void generaDataOrdine() {
        this.orderDate = LocalDate.now();
    }

    private void generaDataDelivery(int giorniPerSpedizione){
            this.deliveryDate = this.orderDate.plusDays(giorniPerSpedizione);
    }
}
