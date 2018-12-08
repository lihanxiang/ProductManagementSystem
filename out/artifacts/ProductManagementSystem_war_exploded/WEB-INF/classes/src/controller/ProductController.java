package controller;

import cn.itcast.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import po.CustomizeProduct;
import po.Product;
import service.ProductService;

import java.util.List;

@Controller
@RequestMapping(value = "product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/pre-add", method = RequestMethod.GET)
    public ModelAndView preAdd(){
        return new ModelAndView("product/add");
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView addProduct(Product product){
        ModelAndView modelAndView = new ModelAndView("product/message");
        product.setId(CommonUtils.uuid());
        productService.addProduct(product);
        modelAndView.addObject("message","添加商品成功");
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteProduct(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView("product/message");
        productService.deleteProduct(id);
        modelAndView.addObject("message","删除商品成功");
        return modelAndView;
    }

    @RequestMapping(value = "/{id}/pre-edit", method = RequestMethod.GET)
    public ModelAndView editProduct(@PathVariable("id") String id){
        ModelAndView modelAndView = new ModelAndView("product/edit");
        Product product = productService.findProductByID(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @RequestMapping(value = "edit",method = RequestMethod.POST)
    public ModelAndView editProductSubmit(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView("product/message");
        productService.updateProduct(product);
        modelAndView.addObject("message","Edit Product Successfully");
        return modelAndView;
    }

    @RequestMapping(value = "pre-find", method = RequestMethod.GET)
    public String preFind(){
        return "product/query";
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public ModelAndView findProduct(CustomizeProduct customizeProduct){
        ModelAndView modelAndView = new ModelAndView("product/productsList");
        List<CustomizeProduct> productList = productService.findProduct(customizeProduct);
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public ModelAndView showProducts(){
        List<CustomizeProduct> productList = productService.productList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("product/productsList");
        return modelAndView;
    }

    @RequestMapping(value = "top", method = RequestMethod.GET)
    public ModelAndView top(){
        return new ModelAndView("product/top");
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public ModelAndView main(){
        return new ModelAndView("product/main");
    }

    @RequestMapping(value = "frame", method = RequestMethod.GET)
    public ModelAndView getFrame(){
        return new ModelAndView("product/frame");
    }
}
