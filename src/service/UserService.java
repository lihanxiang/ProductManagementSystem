package service;

import exception.UserException;
import po.User;

public interface UserService {
    void addUser(User user) throws UserException;
    void login(User user) throws UserException;
    String logout(User user);
    User showInfo(String username);
    String getStatus(String username);
    void setUserInfo(User user) throws UserException;
    void verifyCode(String userCode, String verifyCode) throws UserException;
    User findUserByName(String username);
}
