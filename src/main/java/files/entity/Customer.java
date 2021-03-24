package files.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "customer", schema = "shop")

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name", length = 128)
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Cart> cartList;

    @Override
    public String toString() {
        return String.format("id: %s, name: %s", id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer(String title) {
        this.name = name;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
