package com.code.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.mapper.Individual_userMapper;
import com.code.entity.Individual_user;
import com.code.util.GlobalResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台individual_user表的个人用户管理操作
 */
@Controller
@CrossOrigin("*") //支持跨域请求
@RequestMapping("/individual_user")
public class Individual_userController {

    @Autowired   //注入对象
    public Individual_userMapper individual_userMapper;

    @ResponseBody
    @RequestMapping("/apiinsert") //插入数据
    public GlobalResult apiinsert(Individual_user individual_user) {
        try {
            individual_userMapper.insert(individual_user);
            return GlobalResult.ok(individual_user);
        } catch (Exception e) {
            return GlobalResult.errorMsg(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/apiupdate") //修改数据
    public GlobalResult apiupdate(Individual_user individual_user) {
        try {
            individual_userMapper.updateById(individual_user);
            return GlobalResult.ok(individual_user);
        } catch (Exception e) {
            return GlobalResult.errorMsg(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/apidelete") //删除数据
    public GlobalResult apidelete(Integer id) {
        try {
            individual_userMapper.deleteById(id);
            return GlobalResult.ok("删除成功");
        } catch (Exception e) {
            return GlobalResult.errorMsg(e.getMessage());
        }
    }


	 /**
     * 接口分页查询
     * @param keyword 搜索关键词
     * @param pageIndex  页码
     * @param pageSize  每页查询条数
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/apilist") //分页查询数据
    public Object apilist(String keyword, Integer pageIndex, Integer pageSize, Model model) {
        QueryWrapper<Individual_user> queryWrapper = new QueryWrapper<Individual_user>();
        pageIndex = null == pageIndex ? 1 : pageIndex; //默认显示第一页
        pageSize = null == pageSize ? 10 : pageSize; //默认每页显示10条
        if (StringUtils.isNotEmpty(keyword)) {
            queryWrapper.like("user_name", keyword); //根据字段搜索查询
        }
        queryWrapper.orderByDesc("user_id");  //根据id降序排列
        IPage<Individual_user> sortPage = new Page<Individual_user>(pageIndex, pageSize);;
        IPage<Individual_user> individual_userIPage = individual_userMapper.selectPage(sortPage, queryWrapper);
        long total = sortPage.getTotal(); //总条数
        long current = sortPage.getCurrent();//当前页
        long pages = sortPage.getPages();//总页数
        Map resultMap = new HashMap();
        resultMap.put("list", individual_userIPage.getRecords());
        resultMap.put("pageTotal", total);
        return GlobalResult.ok(resultMap);
    }






    @RequestMapping("/add")  //跳转添加页面
    public String toAdd(Model model) {
        return "individual_user/individual_userAdd";
    }

    @RequestMapping("/insert") //插入数据
    public String insert(Individual_user individual_user) {
        individual_userMapper.insert(individual_user);
        return "redirect:list";
    }

    @RequestMapping("/update") //修改数据
    public String update(Individual_user individual_user) {
        individual_userMapper.updateById(individual_user);
        return "redirect:list";
    }

    @RequestMapping("/delete") //删除数据
    public String delete(Integer id) {
        individual_userMapper.deleteById(id);
        return "redirect:list";
    }

    @RequestMapping("/edit") //编辑数据
    public String edit(String id, Model model) {
        Individual_user individual_user = individual_userMapper.selectById(id);
        model.addAttribute("individual_user", individual_user);
        return "individual_user/individual_userUpdate";
    }

    @RequestMapping("/detail") //详情查看
    public String detail(String id, Model model) {
        Individual_user individual_user = individual_userMapper.selectById(id);
        model.addAttribute("individual_user", individual_user);
        return "individual_user/individual_userDetail";
    }

    /**
     * @param keyword  搜索关键词
     * @param p        页面            默认第1页
     * @param pageSize 每页显示条数 默认每页10条
     * @param model
     * @return
     */
    @RequestMapping("/list") //分页查询数据
    public String list(String keyword, Integer p, Integer pageSize, Model model) {
        QueryWrapper<Individual_user> queryWrapper = new QueryWrapper<Individual_user>();
        p = null == p ? 1 : p; //默认显示第一页
        pageSize = null == pageSize ? 10 : pageSize; //默认每页显示10条
        if (StringUtils.isNotEmpty(keyword)) {
            queryWrapper.like("user_name", keyword); //根据字段搜索查询
        }
        queryWrapper.orderByDesc("user_id");  //根据id降序排列
        IPage<Individual_user> sortPage = new Page<Individual_user>(p, pageSize);
        IPage<Individual_user> individual_userIPage = individual_userMapper.selectPage(sortPage, queryWrapper);
        long total = sortPage.getTotal(); //总条数
        long current = sortPage.getCurrent();//当前页
        long pages = sortPage.getPages();//总页数
        model.addAttribute("individual_userList", individual_userIPage.getRecords());   //绑定接受参数
        model.addAttribute("cp", current);   //当前页
        model.addAttribute("tp", pages);   //总页数
        model.addAttribute("total", total);   //总条数
        model.addAttribute("pageListURL", "individual_user/list");   //总条数
        return "individual_user/individual_userList";
    }
}
