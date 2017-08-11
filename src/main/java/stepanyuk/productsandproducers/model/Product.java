package stepanyuk.productsandproducers.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author stepanyuk
 */
@Entity
@Table (name = "PRODUCT")
public class Product implements Serializable{
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Producer producer;

    public Product() {}

    public Product(String name, String description, Integer price, Producer producer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.producer = producer;
    }

    @Id
    @GeneratedValue (strategy = IDENTITY)
    @Column (name = "ID")
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

    @Column (name = "DESCRIPTION", nullable = true)
    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column (name = "PRICE", nullable = true)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
    @ManyToOne
    @JoinColumn (name = "ID_PRODUCER")
    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
