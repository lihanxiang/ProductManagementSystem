package mapper;

import po.User;

public interface UserMapper {
    void addUser(User user);
    User findUserByName(String username);
    User findUserByPhone(String phone);
    User findUserByEmail(String email);
    User showInfo(String username);
    void setUserInfo(User user);
    void setStatus(User user);
}
