package com.lillanm.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lillanm.management.common.BaseContext;
import com.lillanm.management.common.R;
import com.lillanm.management.entity.Active;
import com.lillanm.management.entity.Admin;
import com.lillanm.management.entity.Student;
import com.lillanm.management.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public R<String> add(@RequestBody Admin admin) {
        //判断该管理员是否存在，若存在，不能注册

        String password = admin.getPassword();
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        admin.setPassword(md5DigestAsHex);
        adminService.save(admin);
        return R.success("添加管理员(测试专用)");
    }


    /**
     * 管理员登录
     * @param request
     * @param admin
     * @return
     */
    @CrossOrigin
    @PostMapping("/login")
    public R<Admin> login(HttpServletRequest request, @RequestBody Admin admin) {
        //将页面提交的密码进行md5加密
        String password = admin.getPassword();

        String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        //根据账号查询管理员是否存在
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, admin.getUsername());
        Admin one = adminService.getOne(queryWrapper);
        if (Objects.isNull(one)) {
            return R.error("账号或密码错误");
        }
        if (!Objects.equals(one.getPassword(), md5DigestAsHex)) {
            return R.error("账号或密码错误");
        }

        //可以登录
        request.getSession().setAttribute("admId",one.getId());
        Object admId = request.getSession().getAttribute("admId");
        log.info("{}",admId);
        return R.success(one);
    }


    /**
     * 管理员登出
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("admId");
        return R.success("账号退出成功");
    }


    /**
     * 展示管理员个人信息
     * @return
     */
    @CrossOrigin
    @GetMapping
    public R<Admin> showDetail() {
        long admId = BaseContext.getCurrentId();
        Admin admin = adminService.getById(admId);
        return R.success(admin);
    }


    /**
     * 更新管理员信息
     * @param admin
     * @return
     */
    @CrossOrigin
    @PutMapping
    public R<String> update(@RequestBody Admin admin) {
        String password = admin.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        admin.setPassword(md5Password);
        adminService.updateById(admin);
        return R.success("管理员信息修改成功");
    }



    @CrossOrigin
    @GetMapping("/page")
    public R<Page<Admin>> page(int page, int pageSize, String name) {
        Page<Admin> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Admin::getName, name);
        queryWrapper.orderByAsc(Admin::getUpdateTime);
        adminService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);

    }
}
