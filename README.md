## ProductManagementSystem

### v1.0

将[登录界面](https://github.com/lihanxiang/Registration-login-interface2)与[管理系统](https://github.com/lihanxiang/new-p-m)整合在一起，做成的一个小 Demo，合并的过程没什么可说的，就来说说需要修改的几点

#### 1. spring-mvc.xml

两个项目的配置不太一样，我们就采用[登录界面](https://github.com/lihanxiang/Registration-login-interface2)中的配置文件，因为有配置了拦截器

#### 2. Controller

对 ProductController 进行请求窄化操作： `@RequestMapping("product")`

因为对 jsp 进行分类了，分两个文件夹存放，所以在 controller 中定义视图时，需要加上 `user/` 或 `product/` 才能够定位到相应视图

在 ProductController 中添加一个方法，在登录之后，作为管理系统的入口：

```
    @RequestMapping("/getFrame")
    public ModelAndView getFrame(){
        return new ModelAndView("product/frame");
    }
```

#### 3. JSP

因为 ProductController 使用了窄化请求，所以在页面跳转的时候，会发生部分 URL 重叠，目前还没有想到好办法，只能先使用绝对 URL 来代替

所以 product 的 JSP 中，在链接前加上 `${pageContext.request.contextPath}` 表示使用绝对路径，否则就会 404

#### 4. 总结

二者的整合没有什么难度，就是简单合并一下，稍微修改一点就行，接下来新增一些 spring 的功能，比如 aop，事务管理之类的