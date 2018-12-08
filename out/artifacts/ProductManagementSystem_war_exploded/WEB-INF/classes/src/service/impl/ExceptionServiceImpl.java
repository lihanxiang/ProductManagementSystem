package service.impl;

import exception.UserException;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.User;
import service.ExceptionService;

@Service
public class ExceptionServiceImpl implements ExceptionService {

    private final UserMapper userMapper;

    @Autowired
    public ExceptionServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void addUserException1(User user) throws UserException {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()){
            throw new UserException("用户名不能为空");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()){
            throw new UserException("密码不能为空");
        }

        if (user.getPhone() == null || user.getPhone().trim().isEmpty()){
            throw new UserException("电话号码不能为空");
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()){
            throw new UserException("邮箱不能为空");
        }
    }

    public void addUserException2(User user) throws UserException {
        //这三者都必须是唯一的
        if (userMapper.findUserByName(user.getUsername()) != null){
            throw new UserException("该用户名已被注册");
        } else if (userMapper.findUserByPhone(user.getPhone()) != null){
            throw new UserException("该电话号码已被注册");
        } else if (userMapper.findUserByEmail(user.getEmail()) != null){
            throw new UserException("该邮箱已被注册");
        }
    }

    @Override
    public void verifyCodeException(String inputVerifyCode, String code) throws UserException {
        if (inputVerifyCode == null || inputVerifyCode.trim().isEmpty()){
            throw new UserException("验证码不能为空");
        } else if (inputVerifyCode.length() != 4){
            throw new UserException("验证码长度应为 4 位");
        } else if (!inputVerifyCode.equals(code)){
            throw new UserException("验证码错误");
        }
    }

    @Override
    public void setInfoException(User user) throws UserException {
        if (user.getPhone() == null || user.getPhone().trim().isEmpty()){
            throw new UserException("电话号码不能为空");
        } else if (user.getEmail() == null || user.getEmail().trim().isEmpty()){
            throw new UserException("邮箱不能为空");
        }
    }
}
