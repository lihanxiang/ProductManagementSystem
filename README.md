## ProductManagementSystem

### v1.1 —— 控制台输出用户操作记录

可通过[查看 tags](https://github.com/lihanxiang/ProductManagementSystem/tree/v1.0) 来显示先前版本

#### 1. 功能截图

![捕获.PNG](https://upload-images.jianshu.io/upload_images/3426615-ed476dc77d6a8bd5.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 2. 修改配置文件

`<aop:aspectj-autoproxy/>` 开启自动创建代理，因为目标对象是实现了接口的类，所以采用的是 JDK 动态代理，就不需要加上这一项：`proxy-target-class="true"`

然后添加切面组件扫描：`<context:component-scan base-package="aspect"/>`

#### 3. 切面

首先使用注解来标注类：

```
@Component
@Aspect
public class LogManagement {}
```

然后我们来写具体的实现：

```
    @AfterReturning("execution(* service.impl.UserServiceImpl.login(..)) && args(user)")
    public void loginDetection(User user){
        System.out.println("=============================================================");
        System.out.println("用户 " + user.getUsername() + " 在 " + new Date().toString() + " 登录系统");
    }

    @AfterReturning("execution(* service.impl.UserServiceImpl.setUserInfo(..)) && args(user)")
    public void setInfo(User user){
        System.out.println("在 " + new Date().toString() + " 修改了个人信息");
    }

    @Before("execution(* service.impl.UserServiceImpl.logout(..)) && args(user)")
    public void logout(User user){
        System.out.println("在 " + new Date().toString() + " 登出系统");
        System.out.println("=============================================================");
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
```

方法的写法都大同小异，只要知道连接点在哪些位置就行了

#### 4. 退出商品系统

商品系统的入口在用户登录之后的主页面中，但是没有出口，所以这次添加一个出口，但是需要用 JavaScript 来帮助跳出 frame 框架

在 top.jsp 中添加：

```
    <script type="text/javascript">
        function main() {
            window.top.location = '${pageContext.request.contextPath}/getMain.action'
        }
    </script>
```

然后添加一行 <a> 标签：

```
<a style="text-align: center" onclick="main()" href="">退出管理系统</a>
```

![](https://upload-images.jianshu.io/upload_images/3426615-2c304a576064ebed.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 5. 总结

这一版本添加的功能就这些，接下来的新版本会将对数据库的增、删和改操作改为事务
