package myBatis;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author: starcold
 * createTime: 2020/8/11 16:31
 * context:
 * updateTime:
 * updateContext:
 */
public interface UserInfoMapper{
    UserInfo getUser(int id);
    UserInfo selectUser(@Param("info") UserInfo userInfo, @Param("password") String password);
    List<UserInfo> getUserByIds(int []ids);
}
