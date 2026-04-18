package com.code.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.mapper.Title_certificateMapper;
import com.code.entity.Title_certificate;
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
 * 后台title_certificate表的职称证书管理操作
 */
@Controller
@CrossOrigin("*") //支持跨域请求
@RequestMapping("/title_certificate")
public class Title_certificateController {

    @Autowired   //注入对象
    public Title_certificateMapper title_certificateMapper;

    @ResponseBody
    @RequestMapping("/apiinsert") //插入数据
    public GlobalResult apiinsert(Title_certificate title_certificate) {
        try {
            title_certificateMapper.insert(title_certificate);
            return GlobalResult.ok(title_certificate);
        } catch (Exception e) {
            return GlobalResult.errorMsg(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/apiupdate") //修改数据
    public GlobalResult apiupdate(Title_certificate title_certificate) {
        try {
            title_certificateMapper.updateById(title_certificate);
            return GlobalResult.ok(title_certificate);
        } catch (Exception e) {
            return GlobalResult.errorMsg(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/apidelete") //删除数据
    public GlobalResult apidelete(Integer id) {
        try {
            title_certificateMapper.deleteById(id);
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
        QueryWrapper<Title_certificate> queryWrapper = new QueryWrapper<Title_certificate>();
        pageIndex = null == pageIndex ? 1 : pageIndex; //默认显示第一页
        pageSize = null == pageSize ? 10 : pageSize; //默认每页显示10条
        if (StringUtils.isNotEmpty(keyword)) {
            queryWrapper.like("cert_num", keyword); //根据字段搜索查询
        }
        queryWrapper.orderByDesc("cert_id");  //根据id降序排列
        IPage<Title_certificate> sortPage = new Page<Title_certificate>(pageIndex, pageSize);;
        IPage<Title_certificate> title_certificateIPage = title_certificateMapper.selectPage(sortPage, queryWrapper);
        long total = sortPage.getTotal(); //总条数
        long current = sortPage.getCurrent();//当前页
        long pages = sortPage.getPages();//总页数
        Map resultMap = new HashMap();
        resultMap.put("list", title_certificateIPage.getRecords());
        resultMap.put("pageTotal", total);
        return GlobalResult.ok(resultMap);
    }






    @RequestMapping("/add")  //跳转添加页面
    public String toAdd(Model model) {
        return "title_certificate/title_certificateAdd";
    }

    @RequestMapping("/insert") //插入数据
    public String insert(Title_certificate title_certificate) {
        title_certificateMapper.insert(title_certificate);
        return "redirect:list";
    }

    @RequestMapping("/update") //修改数据
    public String update(Title_certificate title_certificate) {
        title_certificateMapper.updateById(title_certificate);
        return "redirect:list";
    }

    @RequestMapping("/delete") //删除数据
    public String delete(Integer id) {
        title_certificateMapper.deleteById(id);
        return "redirect:list";
    }

    @RequestMapping("/edit") //编辑数据
    public String edit(String id, Model model) {
        Title_certificate title_certificate = title_certificateMapper.selectById(id);
        model.addAttribute("title_certificate", title_certificate);
        return "title_certificate/title_certificateUpdate";
    }

    @RequestMapping("/detail") //详情查看
    public String detail(String id, Model model) {
        Title_certificate title_certificate = title_certificateMapper.selectById(id);
        model.addAttribute("title_certificate", title_certificate);
        return "title_certificate/title_certificateDetail";
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
        QueryWrapper<Title_certificate> queryWrapper = new QueryWrapper<Title_certificate>();
        p = null == p ? 1 : p; //默认显示第一页
        pageSize = null == pageSize ? 10 : pageSize; //默认每页显示10条
        if (StringUtils.isNotEmpty(keyword)) {
            queryWrapper.like("cert_num", keyword); //根据字段搜索查询
        }
        queryWrapper.orderByDesc("cert_id");  //根据id降序排列
        IPage<Title_certificate> sortPage = new Page<Title_certificate>(p, pageSize);
        IPage<Title_certificate> title_certificateIPage = title_certificateMapper.selectPage(sortPage, queryWrapper);
        long total = sortPage.getTotal(); //总条数
        long current = sortPage.getCurrent();//当前页
        long pages = sortPage.getPages();//总页数
        model.addAttribute("title_certificateList", title_certificateIPage.getRecords());   //绑定接受参数
        model.addAttribute("cp", current);   //当前页
        model.addAttribute("tp", pages);   //总页数
        model.addAttribute("total", total);   //总条数
        model.addAttribute("pageListURL", "title_certificate/list");   //总条数
        return "title_certificate/title_certificateList";
    }
}
