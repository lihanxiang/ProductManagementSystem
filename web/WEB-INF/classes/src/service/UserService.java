package service;

import exception.UserException;
import org.springframework.stereotype.Service;
import po.User;

public interface UserService {
    void addUser(User user) throws UserException;
    User showInfo(String username);
    void setUserInfo(User user) throws UserException;
    void verifyCode(String userCode, String verifyCode) throws UserException;
    User findUserByName(String username);
}
