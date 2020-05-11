package com.classManage.tusdt.dao;

import com.classManage.tusdt.model.BO.UserCountBO;
import com.classManage.tusdt.model.BO.UserListBO;
import com.classManage.tusdt.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<UserListBO> selectUserByName(@Param("schoolId") Integer schoolId, @Param("userName") String userName);

    Integer countBySchool(@Param("schoolId") Integer schoolId);

    User loginByEmail(String email);

    Integer countStu();

    Integer countTea();

    Integer countAdmin();
}