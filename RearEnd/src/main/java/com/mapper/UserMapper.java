package com.mapper;

import com.bean.*;
import com.dto.DepartmentDTO;
import com.dto.EmployeeTypeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Fall
 */
@Mapper
public interface UserMapper {
    /**
     * 注册
     * @param account String
     * @param password String
     * @param name String
     * @param sex String
     * @param phone String
     * @param address String
     * @param cred Double
     * @param speed_time Double
     * @param type String
     * @param department String
     * @param wages Double
     * @param marker Integer
     * @param salt Integer
     * @param create_time Long
     * @param update_time Long
     * @return Integer
     */
    Integer registered(String account, String password, String name, String sex, String phone, String address, Double cred, Double speed_time, String type, String department, Double wages, Integer marker, String salt, Long create_time, Long update_time);

    /**
     * 更新用户数据
     * @param account String
     * @param name String
     * @param sex String
     * @param avatar String
     * @param phone String
     * @param address String
     * @param cred Double
     * @param speed_time Double
     * @param type String
     * @param department String
     * @param wages Double
     * @param update_time  Long
     * @return Integer
     */
    Integer updateUser(String account, String name, String sex, String avatar, String phone, String address, Double cred, Double speed_time, String type, String department, Double wages, Long update_time);

    /**
     * 登录接口
     * @param account String
     * @param marker Integer
     * @return UserBean
     */
    UserBean login(String account, Integer marker);

    /**
     * 更新用户密码
     * @param account String
     * @param password String
     * @param updateTime Long
     * @return Integer
     */
    Integer updatePassword(String account, String password, Long updateTime);

    /**
     * 查找用户
     * @param account String
     * @return UpdateUserDTO
     */
    UserBean query(String account);

    /**
     * 查找所有的顾客
     * @param marker Integer
     * @return List<UserBean>
     */
    List<UserBean> queryAllUser(Integer marker);

    /**
     * 查找用户账号
     * @param id Integer
     * @param marker Integer
     * @return String
     */
    UserBean queryUserId(Integer id, Integer marker);

    /**
     * 删除用户
     * @param id Integer
     * @param marker Integer
     * @return deleteUser
     */
    Integer deleteUser(Integer id, Integer marker);

    /**
     * 查询所有部门
     * @return List<DepartmentDTO>
     */
    List<DepartmentDTO> queryAllDepartment();

    /**
     * 查询所有雇员类型
     * @return List<EmployeeTypeDTO>
     */
    List<EmployeeTypeDTO> queryAllEmployeeType();
}
