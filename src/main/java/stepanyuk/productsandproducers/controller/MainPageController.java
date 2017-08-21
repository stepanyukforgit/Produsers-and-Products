package stepanyuk.productsandproducers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import stepanyuk.productsandproducers.services.ProducerService;
import stepanyuk.productsandproducers.services.ProductService;

@Controller
public class MainPageController {
    
    @Autowired
    private ProducerService producerService;
    
    @Autowired
    private ProductService productService;
    
    @RequestMapping({"/","/home"})
    public String showMainPage(){
        return "index";
    }
    
    @RequestMapping("/search_results")
    public String searchAll(String search, Model model){
        model.addAttribute("producers", producerService.searchProducers(search));
        model.addAttribute("products", productService.searchProducts(search));
        return "/search_results";
    }
}
