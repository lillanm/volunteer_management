package com.lillanm.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lillanm.management.common.BaseContext;
import com.lillanm.management.common.R;
import com.lillanm.management.entity.Student;
import com.lillanm.management.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 学生注册
     * @param student
     * @return
     */
    @CrossOrigin
    @PostMapping("/register")
    public R<String> register(@RequestBody Student student){
        //先查询该用户是否存在，若存在，注册失败
        LambdaQueryWrapper<Student >    queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getStuNumber,student.getStuNumber());
        Student one = studentService.getOne(queryWrapper);
        if (!Objects.isNull(one)){
            return R.error("该学生已存在，注册失败");
        }

        //学生不存在，可以注册
        String password = student.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        student.setPassword(md5Password);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentService.save(student);
        return R.success("注册成功");
    }


    /**
     * 学生登录
     * @param request
     * @param student
     * @return
     */
    @CrossOrigin
    @PostMapping("/login")
    public R<Student> login(HttpServletRequest request, @RequestBody Student student) {
        //将页面提交的密码进行md5加密
        String password = student.getPassword();

        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        //根据学号查询，若没有查到，返回登录失败
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getStuNumber, student.getStuNumber());

        Student one = studentService.getOne(studentLambdaQueryWrapper);

        if (Objects.isNull(one)) {
            return R.error("用户名或密码错误");
        }
        //对比密码，密码错误，返回登录失败
        if (!Objects.equals(one.getPassword(), md5Password)) {
            return R.error("用户名或密码错误");
        }
        //都成功继续下一步
        //将学生id存入session，返回登录成功的学生

        request.getSession().setAttribute("stuId",one.getId());
        return R.success(one);
    }


    /**
     * 学生登出
     * @param request
     * @return
     */
    @CrossOrigin
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("stuId");
        return R.success("账号退出成功");
    }


    /**
     * 展示学生个人信息
     * @return
     */
    @CrossOrigin
    @GetMapping
    public R<Student> showDetail(){
        long stuId = BaseContext.getCurrentId();
        Student student = studentService.getById(stuId);
        return R.success(student);
    }


    /**
     * 更新学生个人信息
     * @param student
     * @return
     */
    @CrossOrigin
    @PutMapping
    public R<String> update(@RequestBody Student student){
        String password = student.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        student.setPassword(md5Password);
        student.setUpdateTime(LocalDateTime.now());
        studentService.updateById(student);
        return R.success("学生信息修改成功");
    }

}
