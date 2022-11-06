package com.lillanm.management.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lillanm.management.common.R;
import com.lillanm.management.entity.Active;
import com.lillanm.management.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/active")
public class ActiveController {

    @Autowired
    private ActiveService activeService;

    /**
     * 添加活动
     * @param active
     * @return
     */
    @CrossOrigin
    @PostMapping
    public R<String>add(@RequestBody Active active){
        activeService.save(active);
        return R.success(active.toString());
    }


    /**
     * 更新活动信息
     * @param active
     * @return
     */
    @CrossOrigin
    @PutMapping
    public R<String> update(@RequestBody Active active){
        activeService.updateById(active);
        return R.success("活动信息修改成功");
    }


    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @CrossOrigin
    @GetMapping("/page")
    public R<Page<Active>> page(int page, int pageSize, String name) {
        Page<Active> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Active> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Active::getName, name);
        queryWrapper.orderByAsc(Active::getName);
        activeService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);

    }
}
