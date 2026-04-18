package com.code.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.mapper.Certificate_infoMapper;
import com.code.entity.Certificate_info;
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
 * 后台certificate_info表的管理操作
 */
@Controller
@CrossOrigin("*") //支持跨域请求
@RequestMapping("/certificate_info")
public class Certificate_infoController {

    @Autowired   //注入对象
    public Certificate_infoMapper certificate_infoMapper;

    @ResponseBody
    @RequestMapping("/apiinsert") //插入数据
    public GlobalResult apiinsert(Certificate_info certificate_info) {
        try {
            certificate_infoMapper.insert(certificate_info);
            return GlobalResult.ok(certificate_info);
        } catch (Exception e) {
            return GlobalResult.errorMsg(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/apiupdate") //修改数据
    public GlobalResult apiupdate(Certificate_info certificate_info) {
        try {
            certificate_infoMapper.updateById(certificate_info);
            return GlobalResult.ok(certificate_info);
        } catch (Exception e) {
            return GlobalResult.errorMsg(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/apidelete") //删除数据
    public GlobalResult apidelete(Integer id) {
        try {
            certificate_infoMapper.deleteById(id);
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
        QueryWrapper<Certificate_info> queryWrapper = new QueryWrapper<Certificate_info>();
        pageIndex = null == pageIndex ? 1 : pageIndex; //默认显示第一页
        pageSize = null == pageSize ? 10 : pageSize; //默认每页显示10条
        if (StringUtils.isNotEmpty(keyword)) {
            queryWrapper.like("用户姓名", keyword); //根据字段搜索查询
        }
        queryWrapper.orderByDesc("证书编号");  //根据id降序排列
        IPage<Certificate_info> sortPage = new Page<Certificate_info>(pageIndex, pageSize);;
        IPage<Certificate_info> certificate_infoIPage = certificate_infoMapper.selectPage(sortPage, queryWrapper);
        long total = sortPage.getTotal(); //总条数
        long current = sortPage.getCurrent();//当前页
        long pages = sortPage.getPages();//总页数
        Map resultMap = new HashMap();
        resultMap.put("list", certificate_infoIPage.getRecords());
        resultMap.put("pageTotal", total);
        return GlobalResult.ok(resultMap);
    }






    @RequestMapping("/add")  //跳转添加页面
    public String toAdd(Model model) {
        return "certificate_info/certificate_infoAdd";
    }

    @RequestMapping("/insert") //插入数据
    public String insert(Certificate_info certificate_info) {
        certificate_infoMapper.insert(certificate_info);
        return "redirect:list";
    }

    @RequestMapping("/update") //修改数据
    public String update(Certificate_info certificate_info) {
        certificate_infoMapper.updateById(certificate_info);
        return "redirect:list";
    }

    @RequestMapping("/delete") //删除数据
    public String delete(Integer id) {
        certificate_infoMapper.deleteById(id);
        return "redirect:list";
    }

    @RequestMapping("/edit") //编辑数据
    public String edit(String id, Model model) {
        Certificate_info certificate_info = certificate_infoMapper.selectById(id);
        model.addAttribute("certificate_info", certificate_info);
        return "certificate_info/certificate_infoUpdate";
    }

    @RequestMapping("/detail") //详情查看
    public String detail(String id, Model model) {
        Certificate_info certificate_info = certificate_infoMapper.selectById(id);
        model.addAttribute("certificate_info", certificate_info);
        return "certificate_info/certificate_infoDetail";
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
        QueryWrapper<Certificate_info> queryWrapper = new QueryWrapper<Certificate_info>();
        p = null == p ? 1 : p; //默认显示第一页
        pageSize = null == pageSize ? 10 : pageSize; //默认每页显示10条
        if (StringUtils.isNotEmpty(keyword)) {
            queryWrapper.like("用户姓名", keyword); //根据字段搜索查询
        }
        queryWrapper.orderByDesc("证书编号");  //根据id降序排列
        IPage<Certificate_info> sortPage = new Page<Certificate_info>(p, pageSize);
        IPage<Certificate_info> certificate_infoIPage = certificate_infoMapper.selectPage(sortPage, queryWrapper);
        long total = sortPage.getTotal(); //总条数
        long current = sortPage.getCurrent();//当前页
        long pages = sortPage.getPages();//总页数
        model.addAttribute("certificate_infoList", certificate_infoIPage.getRecords());   //绑定接受参数
        model.addAttribute("cp", current);   //当前页
        model.addAttribute("tp", pages);   //总页数
        model.addAttribute("total", total);   //总条数
        model.addAttribute("pageListURL", "certificate_info/list");   //总条数
        return "certificate_info/certificate_infoList";
    }
}
