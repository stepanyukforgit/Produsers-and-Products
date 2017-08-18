package stepanyuk.productsandproducers.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.bind.annotation.RequestParam;
import stepanyuk.productsandproducers.services.ProducerService;
import stepanyuk.productsandproducers.services.ProductService;

/**
 *
 * @author stepanyuk
 */
@Controller
@RequestMapping("/producers")
public class ProducersController {
        
    @Resource(name = "productService")
    private ProductService productService;
    
    @Resource(name = "producerService")
    private ProducerService producerService;  
    
    @RequestMapping(value="/producers_list", method=GET)
    public String showProducersList(Model model){
        model.addAttribute("producers", producerService.findAll());
        return "producers/producers_list";
    }

//make with AJAX
    @RequestMapping(value="/producers_list", params="producerId", method=GET)
    public String showProducersList(@RequestParam String producerId, Model model){
        producerService.delete(producerId);
        model.addAttribute("producers", producerService.findAll());
        return "producers/producers_list";
    }

//remove @RequestParams
    @RequestMapping(value="/producers_list", method=POST)
    public String addNewProducer(@RequestParam String producerName, @RequestParam String producerAddress, 
            @RequestParam String producerDescription, Model model){
        producerService.saveProducer(producerName, producerAddress, producerDescription);
        model.addAttribute("producers", producerService.findAll());
        return "producers/producers_list";
    }

//pass Producer
    @RequestMapping(value="/producer_info/{producerId}", method=GET)
    public String showProducerInfo(@PathVariable Long producerId, Model model){
        model.addAttribute("producer", producerService.findById(producerId));
        return "producers/producer_info";
    }
        
    @RequestMapping(value="/producer_edit/{producerId}", method=GET)
    public String showProducerEdit(@PathVariable Long producerId, Model model){
        model.addAttribute("producer", producerService.findById(producerId));
        return "producers/producer_edit";
    }
    
    @RequestMapping(value="/producer_info", method=POST)
    public String editProducer(@RequestParam String producerName,
            @RequestParam String producerAddress, @RequestParam String producerDescription,
            @RequestParam String producerId, Model model){
        producerService.updateProducer(producerName, producerAddress, producerDescription, producerId);
        model.addAttribute("producer", producerService.findById(Long.valueOf(producerId)));
        return "producers/producer_info";
    }
   
    @RequestMapping("/producer_products/{producerId}")
    public String showProducerProducts(@PathVariable Long producerId, Model model){
System.out.println("----------------in showProducerProducts");
        model.addAttribute("producer", producerService.findByIdWithProducts(producerId));
        return "producers/producer_products";
    }

//replace String with Producer
    @RequestMapping(value="/producer_products", method=POST)
    public String showNewProducerProducts(@RequestParam String productName,
            @RequestParam String productPrice, @RequestParam String productDescription,
            @RequestParam String producerId, Model model){
        productService.saveProduct(productName, productDescription, 
                productPrice, producerService.findById(Long.valueOf(producerId)));
        model.addAttribute("producer", producerService.findByIdWithProducts(Long.valueOf(producerId)));
        return "producers/producer_products";
    }
}
