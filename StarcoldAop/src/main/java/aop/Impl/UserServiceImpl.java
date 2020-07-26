package aop.Impl;

import aop.UserService;

public class UserServiceImpl implements UserService {
    public UserServiceImpl() {
    }

    public String getUser() {
        System.out.println("执行获取方法");
        return null;
    }

    public String setUser() {
        return null;
    }
}
