package stepanyuk.productsandproducers.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author stepanyuk
 */
@Entity
@Table (name = "PRODUCT")
@Getter @Setter
public class Product implements Serializable{
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    @Column (name = "ID")
    private Long id;
    
    @Column (name = "NAME", nullable = false)
    private String name;
    
    @Column (name = "DESCRIPTION", nullable = true)
    @Lob
    private String description;
    
    @Column (name = "PRICE", nullable = true, precision = 10, scale = 2)
    private BigDecimal price;
    
    @ManyToOne
    @JoinColumn (name = "PRODUCER_ID")
    private Producer producer;

    public Product() {}

    public Product(String name, String description, BigDecimal price, Producer producer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.producer = producer;
    }
}
