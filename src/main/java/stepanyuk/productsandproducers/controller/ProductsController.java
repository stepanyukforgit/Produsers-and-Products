package stepanyuk.productsandproducers.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import stepanyuk.productsandproducers.services.ProductService;

/**
 *
 * @author stepanyuk
 */
@Controller
@RequestMapping("/products")
public class ProductsController {
    
    @Resource(name = "productService")
    private ProductService productService;
    
    @RequestMapping("/ProductsList")
    public String showProductsList(Model model){
        
        return "products/ProductsList";
    }
    
    @RequestMapping("/ProductInfo")
    public String showProductInfo(){
        return "products/ProductInfo";
    }
    
    @RequestMapping("/ProductEdit")
    public String showProductEdit(){
        return "products/ProductEdit";
    }
}
