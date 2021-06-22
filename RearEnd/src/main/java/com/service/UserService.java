package com.service;

import com.dto.*;
import com.vo.ReturnVO;

//swagger


/**
 * @author Fall
 */
public interface UserService {
    /**
     * 注册
     * @param userBean UpdateUserDTO
     * @return ReturnVO
     */
    ReturnVO registered(RegisteredDTO userBean);

    /**
     * 更新用户数据
     * @param updateUserDTO UpdateUserDTO
     * @return ReturnVO
     */
    ReturnVO updateUser(UpdateUserDTO updateUserDTO);

    /**
     * 登录接口
     * @param loginDTO LoginDTO
     * @return ReturnVO
     */
    ReturnVO login(LoginDTO loginDTO);

    /**
     * 更新用户密码
     * @param updatePasswordDTO UpdateUserDTO
     * @return ReturnVO
     */
    ReturnVO updatePassword(UpdatePasswordDTO updatePasswordDTO);

    /**
     * 通过id查询用户
     * @param queryUserIdDTO QueryUserIdDTO
     * @return ReturnVO
     */
    ReturnVO queryUserId(QueryUserIdDTO queryUserIdDTO);

    /**
     * 查找所有的用户
     * @param queryAllUser QueryAllUser
     * @return ReturnVO
     */
    ReturnVO queryAllUser(QueryAllUser queryAllUser);

    /**
     * 删除用户
     * @param deleteUserDTO DeleteUserDTO
     * @return ReturnVO
     */
    ReturnVO deleteUser(DeleteUserDTO deleteUserDTO);

    /**
     * 查看用户详细情况
     * @param userDTO UserDTO
     * @return ReturnVO
     */
    ReturnVO queryUser(UserDTO userDTO);

    /**
     * 查询所有的部门
     * @return ReturnVO
     */
    ReturnVO queryAllDepartment();

    /**
     * 查询所有员工类型
     * @return ReturnVO
     */
    ReturnVO queryAllEmployeeType();
}
