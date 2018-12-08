package service.impl;

import exception.UserException;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.User;
import service.ExceptionService;
import service.UserService;
import util.Encryption;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final Encryption encryption;
    private final ExceptionService exceptionService;

    @Autowired
    public UserServiceImpl(ExceptionService exceptionService, UserMapper userMapper, Encryption encryption) {
        this.exceptionService = exceptionService;
        this.userMapper = userMapper;
        this.encryption = encryption;
    }

    @Override
    public void addUser(User user) throws UserException {
        //先判断用户的输入是否有错
        exceptionService.addUserException1(user);
        //再判断用户的信息是否已被注册
        exceptionService.addUserException2(user);
        encryption.encryptPassword(user);
        userMapper.addUser(user);
    }

    @Override
    public User showInfo(String username) {
        return userMapper.showInfo(username);
    }

    @Override
    public void setUserInfo(User user) throws UserException {
        exceptionService.setInfoException(user);
        userMapper.setUserInfo(user);
    }

    @Override
    public void verifyCode(String userCode, String verifyCode) throws UserException {
        exceptionService.verifyCodeException(userCode, verifyCode);
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }
}
