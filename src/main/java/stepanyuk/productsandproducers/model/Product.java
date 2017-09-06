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
public class Product implements Serializable{
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    @Column (name = "ID")
    @Getter @Setter
    private Long id;
    
    @Column (name = "NAME", nullable = false)
    @Getter @Setter
    private String name;
    
    @Column (name = "DESCRIPTION", nullable = true)
    @Lob
    @Getter @Setter
    private String description;
    
    @Column (name = "PRICE", nullable = true, precision = 10, scale = 2)
    @Getter @Setter
    private BigDecimal price;
    
    @ManyToOne
    @JoinColumn (name = "ID_PRODUCER")
    @Getter @Setter
    private Producer producer;

    public Product() {}

    public Product(String name, String description, BigDecimal price, Producer producer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.producer = producer;
    }
}
