package edu.pong;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;

@Entity
@Table(name="Category")
public class Category extends PanacheEntityBase{
    
    @Id
    @NotNull
    @Column(unique = true)
    public String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    public List<Product> products = new ArrayList<Product>();
    
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    
    
}
