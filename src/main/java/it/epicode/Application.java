package it.epicode;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        // Clienti
        Customer c = new Customer("marco", 2);
        Customer c1 = new Customer("mattia", 2);
        Customer c2 = new Customer("veronica", 1);
        Customer c3 = new Customer("sofia", 3);
        Customer c4 = new Customer("giovanni", 1);

        // Prodotti
        Product p = new Product("prodotto1", "Books", 120.0);
        Product p1 = new Product("prodotto2", "Books", 180.0);
        Product p2 = new Product("prodotto3", "Baby", 60.0);
        Product p3 = new Product("prodotto4", "Boys", 10.0);
        Product p4 = new Product("prodotto5", "Baby", 12.0);
        Product p5 = new Product("prodotto6", "Boys", 18.0);
        Product p6 = new Product("prodotto7", "Electronics", 250.0);
        Product p7 = new Product("prodotto8", "Toys", 35.0);
        Product p8 = new Product("prodotto9", "Beauty", 45.0);
        Product p9 = new Product("prodotto10", "Home", 150.0);
        Product p10 = new Product("prodotto11", "Clothing", 85.0);

        List<Product> prodotti = new ArrayList<>();

        prodotti.add(p);
        prodotti.add(p1);
        prodotti.add(p2);
        prodotti.add(p3);
        prodotti.add(p4);
        prodotti.add(p5);
        prodotti.add(p6);
        prodotti.add(p7);
        prodotti.add(p8);
        prodotti.add(p9);
        prodotti.add(p10);


        // Ordini
        List<Product> productList = new ArrayList<>();
        productList.add(p);
        productList.add(p2);
        Order ordine = new Order(c, productList, LocalDate.of(2021, 2, 3));

        List<Product> productList1 = new ArrayList<>();
        productList1.add(p3);
        productList1.add(p4);
        Order ordine2 = new Order(c1, productList1, LocalDate.of(2021, 3, 3));

        List<Product> productList2 = new ArrayList<>();
        productList2.add(p5);
        productList2.add(p4);
        Order ordine3 = new Order(c2, productList2, LocalDate.of(2023, 5, 6));

        List<Product> productList3 = new ArrayList<>();
        productList3.add(p1);
        productList3.add(p6);
        Order ordine4 = new Order(c3, productList3, LocalDate.of(2022, 7, 15));

        List<Product> productList4 = new ArrayList<>();
        productList4.add(p7);
        productList4.add(p8);
        Order ordine5 = new Order(c4, productList4, LocalDate.of(2023, 10, 21));

        List<Product> productList5 = new ArrayList<>();
        productList5.add(p9);
        productList5.add(p10);
        Order ordine6 = new Order(c1, productList5, LocalDate.of(2024, 2, 14));

        List<Product> productList6 = new ArrayList<>();
        productList6.add(p8);
        productList6.add(p2);
        Order ordine7 = new Order(c, productList6, LocalDate.of(2024, 3, 20));

        // Lista di tutti gli ordini
        List<Order> ordini = new ArrayList<>();
        ordini.add(ordine);
        ordini.add(ordine2);
        ordini.add(ordine3);
        ordini.add(ordine4);
        ordini.add(ordine5);
        ordini.add(ordine6);
        ordini.add(ordine7);


        //Esercizio #1
        //Raggruppare gli ordini per cliente utilizzando Stream e Lambda Expressions.
        //Crea una mappa in cui la chiave è il cliente e il valore è una lista di ordini effettuati da quel cliente

        Map<Customer, List<Order>> ordiniRaggruppati = ordini.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        ordiniRaggruppati.forEach((customer, orders) -> {
            System.out.println("Cliente: " + customer.getName());
            orders.forEach(order -> {
                order.getProducts().forEach(Product::stampa);
            });
        });

        System.out.println("------------------------");
        //Esercizio #2
        //Dato un elenco di ordini, calcola il totale delle vendite per ogni cliente utilizzando Stream e Lambda Expressions.
        //Crea una mappa in cui la chiave è il cliente e il valore è l'importo totale dei suoi acquisti

        Map<Customer, Double> totaleVenditePerCliente = ordini.stream()
                .collect(Collectors.toMap(
                        Order::getCustomer,
                        order -> order.getProducts().stream()
                                .mapToDouble(Product::getPrice)  // Per ogni prodotto, otteniamo il prezzo
                                .sum(),
                        Double::sum  // Se ci sono più ordini per lo stesso cliente, sommiamo i totali
                ));

        totaleVenditePerCliente.forEach((customer, totalSales) -> {
            System.out.println("Cliente: " + customer.getName() + " - Totale: €" + totalSales);
        });

        System.out.println("------------------------");

        //Esercizio #3
        //Dato un elenco di prodotti, trova i prodotti più costosi utilizzando Stream e Lambda Expressions

        OptionalDouble prezzoMassimo = prodotti.stream()
                .mapToDouble(Product::getPrice)
                .max();

        if (prezzoMassimo.isPresent()) {
            double max = prezzoMassimo.getAsDouble();
            List<Product> prodottiCari = prodotti.stream()
                    .filter(pr -> pr.getPrice() == max)
                    .toList();
            System.out.println("Prodotti costosi: ");
            prodottiCari.forEach(Product::stampa);
        }

        System.out.println("------------------------");

        //Esercizio #4
        //Dato un elenco di ordini, calcola la media degli importi degli ordini utilizzando Stream e Lambda Expressions

        double mediaImporti = ordini.stream()
                .mapToDouble(order -> order.getProducts().stream()
                        .mapToDouble(Product::getPrice)
                        .sum()
                )
                .average()
                .orElse(0.0);

        System.out.println("La media degli importi degli ordini è: " + mediaImporti);

        System.out.println("------------------------");

        //Esercizio #5
        //Dato un elenco di prodotti, raggruppa i prodotti per categoria e calcola la somma degli importi per ogni categoria
        // utilizzando Stream e Lambda Expressions.

        Map<String, Double> prodottiPerCategoria = prodotti.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.summingDouble(Product::getPrice)
                ));
        prodottiPerCategoria.forEach((category, total) ->
                System.out.println("Categoria: " + category + ", Totale: " + total));

        System.out.println("------------------------");

        //[EXTRA] Esercizio #6
        //Usando la classe Apache Commons 10 FileUtils implementare un metodo salvaProdottiSuDisco che salvi su disco un file
        // contenente la lista dei prodotti. Utilizzare un formato simile al seguente per storicizzare i dati su file:
        //nomeprodotto1@categoriaprodotto1@prezzoprodotto1#nomeprodotto2@categoriaprodotto2@prezzoprodotto2


            // Crea una Stringa rappresentante i prodotti
            StringBuilder stringa = new StringBuilder();
            for (Product product : prodotti) {
                stringa.append(product.getName())
                        .append("@")
                        .append(product.getCategory())
                        .append("@")
                        .append(product.getPrice())
                        .append("#");
            }


            // Scrive il contenuto nel file
            File file = new File("filename");
            try {
                FileUtils.write(file, stringa, "UTF-8");
                System.out.println("Prodotti salvati su disco con successo!");
            } catch (IOException e) {
                System.err.println("Errore durante il salvataggio dei prodotti su disco.");
            }

        System.out.println("------------------------");

            //[EXTRA] Esercizio #7
            //Sempre usando la classe Apache Commons 10 FileUtils implementare un metodo leggiProdottiDaDisco che
            //riempia un ArrayList con il contenuto del file salvato al punto 6

        List<Product> products = new ArrayList<>();

        try {
            // Leggi il contenuto del file in una stringa
            File file1 = new File("filename");
            String fileContent = FileUtils.readFileToString(file, "UTF-8");

            // Dividi il contenuto per ogni prodotto
            String[] productStrings = fileContent.split("#");
            for (String productString : productStrings) {
                // Dividi ogni prodotto nei suoi campi
                String[] productFields = productString.split("@");

                String name = productFields[0];
                String category = productFields[1];
                double price = Double.parseDouble(productFields[2]);

                // Crea il prodotto e aggiungilo alla lista
                products.add(new Product(name, category, price));
            }
        } catch (IOException e) {
            System.err.println("Errore durante la lettura del file: " );
        } catch (NumberFormatException e) {
            System.err.println("Errore nel formato del prezzo in uno dei prodotti.");
        }

        products.forEach(Product::stampa);

        }
    }

