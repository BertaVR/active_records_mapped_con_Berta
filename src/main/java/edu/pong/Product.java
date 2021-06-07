package edu.pong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;

@Entity
@Table(name="Product")
public class Product extends PanacheEntityBase{

    @Id
    @NotNull
    @Column(unique=true)
    public String name;

    @ManyToOne
    @JoinColumn(name = "category")
    public Category category;

    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Product() {
    }
}
