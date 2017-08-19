package stepanyuk.productsandproducers.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/products")
public class ProductsController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProducerService producerService;  
    
    @RequestMapping(value="/products_list", method=GET)
    public String showProductsList(Model model){
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("products", productService.findAll());
        return "products/products_list";
    }
//make with AJAX
    @RequestMapping(value="/products_list", params="productId", method=GET)
    public String deleteProduct(String productId, Model model){
        productService.delete(productService.findById(Long.valueOf(productId)));
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("products", productService.findAll());
        return "products/products_list";
    }

//@RequestParam is for domonstration
    @RequestMapping(value="/products_list", method=POST)
    public String addNewProduct(
            @RequestParam("productName") String productName,
            @RequestParam String productPrice,
            String productDescription, String producerId, Model model){
        productService.saveProduct(productName, productDescription, 
            productPrice, producerService.findById(Long.valueOf(producerId)));
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("products", productService.findAll());
        return "products/products_list";
    }
    
    @RequestMapping(value="/product_info/{productId}", method=GET)
    public String showProductInfo(@PathVariable Long productId, Model model){
        model.addAttribute("product", productService.findById(productId));
        return "products/product_info";
    }    
    
    @RequestMapping(value="/product_edit/{productId}", method=GET)
    public String showProductEdit(@PathVariable Long productId, Model model){      
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("product", productService.findById(productId));
        return "products/product_edit";
    }
    @RequestMapping(value="/product_info", method=POST)
    public String editProduct(String productName, String productPrice, String productDescription,
            String producerId, String productId, Model model){
        productService.updateProduct(productName, productPrice, 
                productDescription, productId, producerService.findById(Long.valueOf(producerId)));
        model.addAttribute("product", productService.findById(Long.valueOf(productId)));
        return "products/product_info";
    }
}
