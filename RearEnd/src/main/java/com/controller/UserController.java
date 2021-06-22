package com.controller;

import com.dto.*;
import com.vo.ReturnVO;
import com.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Fall
 */
@RestController
@Api(tags = "用户接口")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    @ApiOperation(value = "登录接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO login(@RequestBody @Validated LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @RequestMapping("/registered")
    @ApiOperation(value = "注册接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO registered(@RequestBody @Validated RegisteredDTO registeredDTO) {
        return userService.registered(registeredDTO);
    }

    @RequestMapping("/updateUser")
    @ApiOperation(value = "更新用户数据接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO updateUser(@RequestBody @Validated UpdateUserDTO updateUserDTO) {
        return userService.updateUser(updateUserDTO);
    }

    @RequestMapping("/updatePassword")
    @ApiOperation(value = "更新用户密码接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO updatePassword(@RequestBody @Validated UpdatePasswordDTO updatePasswordDTO) {
        return userService.updatePassword(updatePasswordDTO);
    }

    @RequestMapping("/queryUserId")
    @ApiOperation(value = "通过Id查询用户接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryUserId(@RequestBody @Validated QueryUserIdDTO queryUserIdDTO) {
        return userService.queryUserId(queryUserIdDTO);
    }

    @RequestMapping("/queryAllUser")
    @ApiOperation(value = "查询所有用户接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryAllUser(@RequestBody @Validated QueryAllUser queryAllUser) {
        return userService.queryAllUser(queryAllUser);
    }

    @RequestMapping("/deleteUser")
    @ApiOperation(value = "删除用户接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO deleteUser(@RequestBody @Validated DeleteUserDTO deleteUserDTO) {
        return userService.deleteUser(deleteUserDTO);
    }

    @RequestMapping("/queryUser")
    @ApiOperation(value = "通过Id查询用户接口", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryUser(@RequestBody @Validated UserDTO userDTO) {
        return userService.queryUser(userDTO);
    }

    @RequestMapping("/queryAllDepartment")
    @ApiOperation(value = "查询所有的部门", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryAllDepartment() {
        return userService.queryAllDepartment();
    }

    @RequestMapping("/queryAllEmployeeType")
    @ApiOperation(value = "查询所有雇员类型", httpMethod = "POST", response = ReturnVO.class)
    public ReturnVO queryAllEmployeeType() {
        return userService.queryAllEmployeeType();
    }
}
