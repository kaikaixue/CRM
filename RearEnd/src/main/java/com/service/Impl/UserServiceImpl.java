package com.service.Impl;

import com.dto.*;
import com.mapper.*;
import com.utils.ReturnCode;
import com.vo.ReturnVO;
import com.bean.*;
import com.service.UserService;
import com.utils.Tools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fall
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private PlanMapper planMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private EmployeeTypeMapper employeeTypeMapper;


    @Override
    public ReturnVO registered(RegisteredDTO registeredDTO) {
        if (userMapper.query(registeredDTO.getAccount()) != null) {
            return new ReturnVO(ReturnCode.FAIL, "该账号已存在");
        }

        String password = registeredDTO.getPassword();
        String salt = Tools.generateSalt();

        Integer num = userMapper.registered(registeredDTO.getAccount(), Tools.encryption(password, salt), registeredDTO.getName(), "", "未填写", "未填写", registeredDTO.getMarker() == 2 ? 100.0 : -1.0, registeredDTO.getMarker() == 2 ? 0.0 : -1.0, "", "", registeredDTO.getMarker() == 1 ? 1000.0 : 0, registeredDTO.getMarker(), salt, Tools.getDate(), Tools.getDate());

        if (num == null) {
            return new ReturnVO(ReturnCode.FAIL, "插入失败");
        }

        return new ReturnVO(ReturnCode.SUCCESS);
    }

    @Override
    public ReturnVO updateUser(UpdateUserDTO updateUserDTO) {
        if(departmentMapper.queryDepartmentPower(updateUserDTO.getDepartment()) == null ||
                employeeTypeMapper.queryEmployeeTypePower(updateUserDTO.getType()) == null) {
            return new ReturnVO(ReturnCode.FAIL, "部门或这种类型的员工不存在");
        }

        if (userMapper.query(updateUserDTO.getAccount()) == null) {
            return new ReturnVO(ReturnCode.FAIL, "用户不存在");
        }

        userMapper.updateUser(updateUserDTO.getAccount(), updateUserDTO.getName(), updateUserDTO.getSex(), updateUserDTO.getAvatar(), updateUserDTO.getPhone(), updateUserDTO.getAddress(), updateUserDTO.getCred(), updateUserDTO.getSpeed_time(), updateUserDTO.getType(), updateUserDTO.getDepartment(), updateUserDTO.getWages(), Tools.getDate());

        return new ReturnVO();
    }

    @Override
    public ReturnVO login(LoginDTO loginDTO) {
        UserBean userBean = userMapper.login(loginDTO.getAccount(), loginDTO.getMarker());

        if (userBean == null) {
            return new ReturnVO(ReturnCode.FAIL, "用户不存在");
        }

        if (!Tools.encryption(loginDTO.getPassword(), userBean.getSalt()).equals(userBean.getPassword())) {
            return new ReturnVO(ReturnCode.FAIL, "账号或密码错误");
        }

        if (loginDTO.getMarker() == 2) {
            userBean.setCred(userBean.getCred() - orderMapper.queryOrderCancel(userBean.getId()).size());
        } else if (loginDTO.getMarker() == 1) {
            userBean.setWages(
                    userBean.getWages() *
                    departmentMapper.queryDepartmentPower(userBean.getDepartment()).getPower() *
                    employeeTypeMapper.queryEmployeeTypePower(userBean.getType()).getPower());
        }

        return new ReturnVO(userBean);
    }

    @Override
    public ReturnVO updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        UserBean userBean = userMapper.query(updatePasswordDTO.getAccount());
        if (userMapper.query(updatePasswordDTO.getAccount()) == null) {
            return new ReturnVO(ReturnCode.FAIL, "用户不存在");
        }

        if (!userBean.getPassword().equals(Tools.encryption(updatePasswordDTO.getLastPassword(), userBean.getSalt()))) {
            return new ReturnVO(ReturnCode.FAIL, "原密码错误");
        }

        userMapper.updatePassword(updatePasswordDTO.getAccount(), Tools.encryption(updatePasswordDTO.getNewPassword(), userBean.getSalt()), Tools.getDate());

        return new ReturnVO();
    }

    @Override
    public ReturnVO queryUserId(QueryUserIdDTO queryUserIdDTO) {
        UserBean userBean = userMapper.queryUserId(queryUserIdDTO.getUserId(), 1);

        if (userBean == null) {
            userBean = userMapper.queryUserId(queryUserIdDTO.getUserId(), 2);
            if (userBean == null) {
                userBean = userMapper.queryUserId(queryUserIdDTO.getUserId(), 0);
                if (userBean == null) {
                    new ReturnVO(ReturnCode.FAIL, "用户不存在");
                }
            }
        }

        if (userBean.getMarker() == 2) {
            userBean.setCred(userBean.getCred() - orderMapper.queryOrderCancel(userBean.getId()).size());
        } else if (userBean.getMarker() == 1) {
            userBean.setWages(
                    userBean.getWages() *
                            departmentMapper.queryDepartmentPower(userBean.getDepartment()).getPower() *
                            employeeTypeMapper.queryEmployeeTypePower(userBean.getType()).getPower());
        }

        return new ReturnVO(userBean);
    }

    @Override
    public ReturnVO queryAllUser(QueryAllUser queryAllUser) {
        if (queryAllUser.getMarker() == 0) {
            return new ReturnVO(ReturnCode.FAIL, "你无法查找管理员");
        }

        return new ReturnVO(userMapper.queryAllUser(queryAllUser.getMarker()));
    }

    @Override
    public ReturnVO deleteUser(DeleteUserDTO deleteUserDTO) {
        Integer num = userMapper.deleteUser(deleteUserDTO.getUserId(), deleteUserDTO.getMarker());

        if (num == null) {
            return new ReturnVO(ReturnCode.FAIL, "删除失败");
        }

        return new ReturnVO();
    }

    @Override
    public ReturnVO queryUser(UserDTO userDTO) {
        UserBean userBean = userMapper.queryUserId(userDTO.getUserId(), 2);

        List<OrderDTO> orderDTOS = orderMapper.queryUserAllOrder(userBean.getId());

        if (orderDTOS == null) {
            return new ReturnVO(ReturnCode.FAIL, "查找失败");
        }

        List<OrderDisplayDTO> list = new ArrayList<>();

        for (int i = 0; i < orderDTOS.size(); i++) {
            OrderDTO orderDTO = orderDTOS.get(i);
            ProductBean productBean = productMapper.queryProduct(orderDTO.getProduct_id());
            PlanBean planBean = planMapper.queryPlan(orderDTO.getPlan_id());
            list.add(new OrderDisplayDTO(
                    orderDTO.getId(),
                    productBean.getName(),
                    orderDTO.getNumber(),
                    productBean.getPrice(),
                    orderDTO.getNumber() * productBean.getPrice(),
                    planBean.getEmployee_id(),
                    orderDTO.getPhase() == 0 ? "未完成" : "已完成",
                    "",
                    null
            ));
        }

        return new ReturnVO(new QueryUserDTO(
                userBean.getId(),
                userBean.getAccount(),
                userBean.getName(),
                userBean.getSex(),
                userBean.getAvatar(),
                userBean.getPhone(),
                userBean.getAddress(),
                userBean.getCred() - orderMapper.queryOrderCancel(userBean.getId()).size(),
                userBean.getSpeed_time() / 3600 + "小时",
                list));
    }

    @Override
    public ReturnVO queryAllDepartment() {
        return new ReturnVO(userMapper.queryAllDepartment());
    }

    @Override
    public ReturnVO queryAllEmployeeType() {
        return new ReturnVO(userMapper.queryAllEmployeeType());
    }
}