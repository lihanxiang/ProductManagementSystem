package service.impl;

import exception.UserException;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.User;
import service.ExceptionService;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final ExceptionService exceptionService;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, ExceptionService exceptionService) {
        this.userMapper = userMapper;
        this.exceptionService = exceptionService;
    }

    @Override
    public void addUser(User user) throws UserException {
        //先判断用户的输入是否有错
        exceptionService.addUserException1(user);
        //再判断用户的信息是否已被注册
        exceptionService.addUserException2(user);
        userMapper.addUser(user);
    }

    @Override
    //根据用户输入名字去数据库查找有没有这个用户，如果没有，就会抛出异常
    public void login(User user) throws UserException{
        User db_user = userMapper.findUserByName(user.getUsername());
        exceptionService.loginException(user, db_user);
        if (db_user.getStatus().equals("1")){
            throw new UserException("该用户已在其他地点登录");
        } else {
            user.setStatus("1");
            userMapper.setStatus(user);
        }
    }

    @Override
    public String logout(User user) {
        user.setStatus("0");
        userMapper.setStatus(user);
        return user.getUsername();
    }

    //显示用户信息
    @Override
    public User showInfo(String username) {
        return userMapper.showInfo(username);
    }

    //显示当前登录状态
    @Override
    public String getStatus(String username) {
        return username;
    }

    //修改用户信息
    @Override
    public void setUserInfo(User user) throws UserException {
        exceptionService.setInfoException(user);
        userMapper.setUserInfo(user);
    }

    //得到验证码
    @Override
    public void verifyCode(String userCode, String verifyCode) throws UserException {
        exceptionService.verifyCodeException(userCode, verifyCode);
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }
}
