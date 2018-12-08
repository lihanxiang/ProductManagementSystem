package controller;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import exception.UserException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping(value = "user")
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

    @RequestMapping(value = "pre-login", method = RequestMethod.GET)
    public ModelAndView preLogin(){
        return new ModelAndView("user/login");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(User user, HttpServletRequest request){
        ModelAndView modelAndView;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            userService.verifyCode(request.getParameter("verifyCode"), verifyCode.getText());
            subject.login(token);
            subject.getSession().setAttribute("username", user.getUsername());
            modelAndView = new ModelAndView("/user/main");
            modelAndView.addObject("username", subject.getSession().getAttribute("username"));
        } catch (UserException  e){
            modelAndView = new ModelAndView("/user/login");
            modelAndView.addObject("message", "验证码错误");
        } catch (AuthenticationException e){
            modelAndView = new ModelAndView("/user/login");
            modelAndView.addObject("message", "用户名/密码错误");
        }
        return modelAndView;
    }

    @RequestMapping(value = "pre-add", method = RequestMethod.GET)
    public ModelAndView preAdd(){
        return new ModelAndView("user/addUser");
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView addUser(User user){
        try{
            userService.addUser(user);
        } catch (UserException e){
            return new ModelAndView("user/addUser").addObject("message", e.getMessage());
        }
        return new ModelAndView("user/addUserSuccessful");
    }

    @RequestMapping(value = "main",method = RequestMethod.GET)
    public ModelAndView getMain(){
        return new ModelAndView("user/main");
    }

    @RequestMapping(value = "verify_code", method = RequestMethod.GET)
    public void setVerifyCode(HttpServletResponse response)
            throws IOException{
        response.setContentType("image/jpeg");
        BufferedImage image = verifyCode.getImage();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
        encoder.encode(image);
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout(){
        SecurityUtils.getSubject().logout();
        return new ModelAndView("user/login");
    }

    @RequestMapping(value = "user-info", method = RequestMethod.GET)
    public ModelAndView userInfo(){
        return new ModelAndView("user/userInfo")
                .addObject("user", userService.showInfo(
                        ((String)SecurityUtils.getSubject().getSession().getAttribute("username"))));
    }

    @RequestMapping(value = "set-info", method = RequestMethod.POST)
    public ModelAndView setUserInfo(User user){
        ModelAndView modelAndView = new ModelAndView("user/userInfo");
        try {
            userService.setUserInfo(user);
            modelAndView.addObject("message", "修改成功");
            modelAndView.setViewName("user/main");
        } catch (UserException e){
            modelAndView.addObject("message", e.getMessage());
        }
        return modelAndView;
    }
}
