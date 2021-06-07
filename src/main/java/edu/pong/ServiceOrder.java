package edu.pong;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServiceOrder {

    public ServiceOrder() {
    }

    public List<Orders> listOrders() {
        // stream requiere una transaction
        Stream<Orders> orders = Orders.streamAll();
        return orders.collect(Collectors.toList());
    }

    public void addOrder(Orders order) {
        Optional<Person> customer = Person.find("dni", order.person.dni).firstResultOptional();
        if (customer.isPresent()) {
            order.person = customer.get();
        } else {
            order.person.persist();
        }
        Optional<Product> item = Product.find("name", order.product.name).firstResultOptional();
        if (item.isPresent()) {
            order.product = item.get();
        } else {
            order.product.persist();
        }
        order.persist();
    }
    public void remove(String id) {
        Optional<Orders> order = Product.find("id", id).firstResultOptional();
        if (order.isPresent()) {
            order.get().delete();
        }
    }

    public Optional<Orders> getOrder(String id) {
        return id.isBlank()? 
            Optional.ofNullable(null) : 
            Orders.find("id", id).firstResultOptional();
    }
}
