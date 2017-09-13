package stepanyuk.productsandproducers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author stepanyuk
 */
@Entity
@Table (name = "PRODUCER")
@Getter @Setter
public class Producer implements Serializable{
    
    @Id
    @GeneratedValue (strategy = IDENTITY)
    private Long id;
    
    @Column (name = "NAME", nullable = false)
    private String name;
    
    @Column (name = "ADDRESS", nullable = false)
    private String address;
    
    @Column (name = "DESCRIPTION", nullable = true)
    @Lob
    private String description;
    
    @OneToMany (mappedBy = "producer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Product> products = new HashSet<Product>();
    
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "LOGO_ID")
    private ProducerLogo logo;
            
    public Producer() {}

    public Producer(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }
}
