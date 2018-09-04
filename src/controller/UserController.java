package controller;

import exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import po.User;
import service.ExceptionService;
import service.UserService;
import util.VerifyCode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class UserController {

    private final UserService userService;

    private final VerifyCode verifyCode;

    private final ExceptionService exceptionService;

    @Autowired
    public UserController(UserService userService, VerifyCode verifyCode, ExceptionService exceptionService) {
        this.userService = userService;
        this.verifyCode = verifyCode;
        this.exceptionService = exceptionService;
    }

    //在注册之前需要先得到注册的界面
    @RequestMapping("/preAdd")
    public ModelAndView preAdd(){
        return new ModelAndView("user/addUser");
    }

    @RequestMapping("/addUser")
    public ModelAndView addUser(User user, HttpServletRequest request){
        //如果下面的 try 语句块没有抛出异常，则返回 addUserSuccessful.jsp
        ModelAndView modelAndView = new ModelAndView("user/addUserSuccessful");
        try{
            //先调用添加用户的方法，看看有没有因为不符规定的输入而导致异常抛出
            userService.addUser(user);
            //然后再看有没有因为验证码错误而导致异常抛出
            exceptionService.verifyCodeException(request.getParameter("verifyCode"), verifyCode.getText());
        } catch (UserException e){
            //如果捕获异常，就带着异常信息返回注册界面
            modelAndView.setViewName("user/addUser");
            modelAndView.addObject("message", e.getMessage());
        }
        return modelAndView;
    }

    //同样的，需要先得到界面
    @RequestMapping("preLogin")
    public ModelAndView preLogin(){
        return new ModelAndView("user/login");
    }

    //登录的逻辑和上面是一样的
    @RequestMapping("/login")
    public ModelAndView login(User user, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("user/main");
        try {
            userService.login(user);
            userService.verifyCode(request.getParameter("verifyCode"), verifyCode.getText());
            //创建 Session，保持登录状态
            request.getSession().setAttribute("username", user.getUsername());
            //在模型中添加对象，用于 JSP 读取
            modelAndView.addObject("username", request.getSession().getAttribute("username"));
        } catch (UserException e){
            //如果未登录成功，就重新登录
            modelAndView.setViewName("user/login");
            modelAndView.addObject("message", e.getMessage());
        }
        return modelAndView;
    }

    //得到验证码，然后用于 jsp 文件的 <img> 标签的 src 属性中
    @RequestMapping("/getVerifyCode")
    public void setVerifyCode(HttpServletResponse response)
            throws IOException{
        //设置响应格式
        response.setContentType("image/jpeg");
        //得到图片
        BufferedImage image = verifyCode.getImage();
        //输出
        verifyCode.output(image, response.getOutputStream());
    }

    //登出账户，不需要具体用户名称，直接废除 session 就行
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        request.getSession().invalidate();
        return new ModelAndView("user/login").addObject("message", "已登出");
    }

    //查看用户状态，显示是哪个用户在登录，如果没有登录的用户，就会提示你先登录
    @RequestMapping("/userStatus")
    public ModelAndView userStatus(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("user/userStatus");
        modelAndView.addObject("username",
                userService.getStatus((String)request.getSession().getAttribute("username")));
        return modelAndView;
    }

    //显示用户信息
    @RequestMapping("showInfo")
    public ModelAndView showInfo(HttpServletRequest request){
        return new ModelAndView("user/userInfo")
                .addObject("user", userService.showInfo(
                        ((String)request.getSession().getAttribute("username"))));
    }

    //对用户信息进行修改
    @RequestMapping("setUserInfo")
    public ModelAndView setUserInfo(User user){
        ModelAndView modelAndView = new ModelAndView("user/userInfo");
        try {
            userService.setUserInfo(user);
            //设置提示信息
            modelAndView.addObject("message", "修改成功");
            //跳转
            modelAndView.setViewName("user/main");
        } catch (UserException e){
            modelAndView.addObject("message", e.getMessage());
        }
        return modelAndView;
    }
}
