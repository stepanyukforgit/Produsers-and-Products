package stepanyuk.productsandproducers.services;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import stepanyuk.productsandproducers.model.Producer;

/**
 *
 * @author stepanyuk
 */
public interface ProducerService {
    
    long saveProducer(String producerName, String producerAddress, String producerDescription);
    List<Producer> findAll();
    List<Producer> searchProducers(String searchProducers);
    Producer findById(Long id);
    Producer findByIdWithProducts(Long id);
    Producer findByIdWithLogo(Long id);
    void updateProducer(String producerName, String producerAddress, String producerDescription,
            String relLogoPath, String pathToDir, String id);
    void delete(String id);
    String uploadLogo(MultipartFile logo, String contextPath);
}
