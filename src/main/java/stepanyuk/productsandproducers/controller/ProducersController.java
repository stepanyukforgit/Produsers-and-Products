package stepanyuk.productsandproducers.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import stepanyuk.productsandproducers.model.Producer;
import stepanyuk.productsandproducers.model.Product;
import stepanyuk.productsandproducers.services.ProducerService;

/**
 *
 * @author stepanyuk
 */
@Controller
@RequestMapping("/producers")
public class ProducersController {
    
    @Resource(name = "producerService")
    private ProducerService producerService;
    
    @RequestMapping("/ProducersList")
    public String showProducersList(){
        return "producers/ProducersList"; 
    }
    
    @RequestMapping("/ProducerInfo")
    public String showProducerInfo(Map<String,Object> model){
        model.put("producer", producerService.findById(1L));
        return "producers/ProducerInfo";
    }
    
    @RequestMapping("/ProducerEdit")
    public String showProducerEdit(){
        return "producers/ProducerEdit";
    }
    
    @RequestMapping("/ProducerProducts")
    public String showProducerProducts(){
        return "producers/ProducerProducts";
    }
}
