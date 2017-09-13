package stepanyuk.productsandproducers.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LOGO")
@Getter @Setter @NoArgsConstructor 
public class ProducerLogo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "PATH_TO_LOGO", nullable = false)
    private String pathToLogo;
    
    public ProducerLogo(String pathToLogo) {
        this.pathToLogo = pathToLogo;
    }
}
