/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package captulo11.modelo.pagamento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author leone
 */
public class Payment {

    private final List<Product> products;
    private final LocalDateTime date;
    private final Customer customer;

    public Payment(List<Product> products,
            LocalDateTime date,
            Customer customer) {

        this.products = Collections.unmodifiableList(products);
        this.date = date;
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    @Override
    public String toString() {
        return "[Payment: "
                + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " " + customer + " " + products + "]";
    }
}
