package aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import po.Product;
import po.User;
import java.util.Date;

@Component
@Aspect
public class LogManagement {

    @AfterReturning("execution(* controller.UserController.login(..)) && args(user)")
    public void loginDetection(User user){
        System.out.println("=============================================================");
        System.out.println("用户 " + user.getUsername() + " 在 " + new Date().toString() + " 登录系统");
    }

    @AfterReturning("execution(* service.impl.UserServiceImpl.setUserInfo(..)) && args(user)")
    public void setUserInfo(User user){
        System.out.println("在 " + new Date().toString() + " 修改了个人信息");
    }

    @AfterReturning("execution(* service.impl.ProductServiceImpl.addProduct(..)) && args(product)")
    public void addProduct(Product product){
        System.out.println("在 " + new Date().toString() + " 添加商品 " + product.getName());
    }

    @After("execution(* service.impl.ProductServiceImpl.deleteProduct(..)) && args(id)")
    public void deleteProduct(String id){
        System.out.println("在 " + new Date().toString() + " 删除ID为 " + id + " 的商品");
    }

    @After("execution(* service.impl.ProductServiceImpl.updateProduct(..)) && args(product)")
    public void updateProduct(Product product){
        System.out.println("在 " + new Date().toString() + " 修改商品 " + product.getName() + " 的信息");
    }

    @After("execution(* service.impl.ProductServiceImpl.findProduct(..)) && args(product)")
    public void findProduct(Product product){
        System.out.println("在 " + new Date().toString() + " 查询商品信息");
    }

    @After("execution(* service.impl.ProductServiceImpl.productList(..))")
    public void productList(){
        System.out.println("在 " + new Date().toString() + " 查看商品列表");
    }
}
