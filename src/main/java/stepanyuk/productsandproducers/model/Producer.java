package stepanyuk.productsandproducers.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author stepanyuk
 */
@Entity
@Table (name = "PRODUCER")
public class Producer implements Serializable{
    private Long id;
    private String name;
    private String address;
    private String description;
    private Set<Product> products = new HashSet<Product>();

    public Producer() {}

    public Producer(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    @Column (name = "ID_PRODUCER")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column (name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column (name = "ADDRESS", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column (name = "DESCRIPTION", nullable = true)
    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany (mappedBy = "producer", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
