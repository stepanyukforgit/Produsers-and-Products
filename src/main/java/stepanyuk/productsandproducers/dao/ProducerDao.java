package stepanyuk.productsandproducers.dao;

import java.util.List;
import stepanyuk.productsandproducers.model.Producer;

/**
 *
 * @author stepanyuk
 */
public interface ProducerDao {

    List<Producer> findAll();
    Producer findById(Long id);
    Producer save(Producer product);
    void delete(Producer product);
}
