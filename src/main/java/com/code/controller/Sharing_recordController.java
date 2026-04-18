package com.code.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.code.mapper.Sharing_recordMapper;
import com.code.entity.Sharing_record;
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
 * 后台sharing_record表的证书共享记录管理操作
 */
@Controller
@CrossOrigin("*") //支持跨域请求
@RequestMapping("/sharing_record")
public class Sharing_recordController {

    @Autowired   //注入对象
    public Sharing_recordMapper sharing_recordMapper;

    @ResponseBody
    @RequestMapping("/apiinsert") //插入数据
    public GlobalResult apiinsert(Sharing_record sharing_record) {
        try {
            sharing_recordMapper.insert(sharing_record);
            return GlobalResult.ok(sharing_record);
        } catch (Exception e) {
            return GlobalResult.errorMsg(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/apiupdate") //修改数据
    public GlobalResult apiupdate(Sharing_record sharing_record) {
        try {
            sharing_recordMapper.updateById(sharing_record);
            return GlobalResult.ok(sharing_record);
        } catch (Exception e) {
            return GlobalResult.errorMsg(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping("/apidelete") //删除数据
    public GlobalResult apidelete(Integer id) {
        try {
            sharing_recordMapper.deleteById(id);
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
        QueryWrapper<Sharing_record> queryWrapper = new QueryWrapper<Sharing_record>();
        pageIndex = null == pageIndex ? 1 : pageIndex; //默认显示第一页
        pageSize = null == pageSize ? 10 : pageSize; //默认每页显示10条
        if (StringUtils.isNotEmpty(keyword)) {
            queryWrapper.like("cert_num", keyword); //根据字段搜索查询
        }
        queryWrapper.orderByDesc("record_id");  //根据id降序排列
        IPage<Sharing_record> sortPage = new Page<Sharing_record>(pageIndex, pageSize);;
        IPage<Sharing_record> sharing_recordIPage = sharing_recordMapper.selectPage(sortPage, queryWrapper);
        long total = sortPage.getTotal(); //总条数
        long current = sortPage.getCurrent();//当前页
        long pages = sortPage.getPages();//总页数
        Map resultMap = new HashMap();
        resultMap.put("list", sharing_recordIPage.getRecords());
        resultMap.put("pageTotal", total);
        return GlobalResult.ok(resultMap);
    }






    @RequestMapping("/add")  //跳转添加页面
    public String toAdd(Model model) {
        return "sharing_record/sharing_recordAdd";
    }

    @RequestMapping("/insert") //插入数据
    public String insert(Sharing_record sharing_record) {
        sharing_recordMapper.insert(sharing_record);
        return "redirect:list";
    }

    @RequestMapping("/update") //修改数据
    public String update(Sharing_record sharing_record) {
        sharing_recordMapper.updateById(sharing_record);
        return "redirect:list";
    }

    @RequestMapping("/delete") //删除数据
    public String delete(Integer id) {
        sharing_recordMapper.deleteById(id);
        return "redirect:list";
    }

    @RequestMapping("/edit") //编辑数据
    public String edit(String id, Model model) {
        Sharing_record sharing_record = sharing_recordMapper.selectById(id);
        model.addAttribute("sharing_record", sharing_record);
        return "sharing_record/sharing_recordUpdate";
    }

    @RequestMapping("/detail") //详情查看
    public String detail(String id, Model model) {
        Sharing_record sharing_record = sharing_recordMapper.selectById(id);
        model.addAttribute("sharing_record", sharing_record);
        return "sharing_record/sharing_recordDetail";
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
        QueryWrapper<Sharing_record> queryWrapper = new QueryWrapper<Sharing_record>();
        p = null == p ? 1 : p; //默认显示第一页
        pageSize = null == pageSize ? 10 : pageSize; //默认每页显示10条
        if (StringUtils.isNotEmpty(keyword)) {
            queryWrapper.like("cert_num", keyword); //根据字段搜索查询
        }
        queryWrapper.orderByDesc("record_id");  //根据id降序排列
        IPage<Sharing_record> sortPage = new Page<Sharing_record>(p, pageSize);
        IPage<Sharing_record> sharing_recordIPage = sharing_recordMapper.selectPage(sortPage, queryWrapper);
        long total = sortPage.getTotal(); //总条数
        long current = sortPage.getCurrent();//当前页
        long pages = sortPage.getPages();//总页数
        model.addAttribute("sharing_recordList", sharing_recordIPage.getRecords());   //绑定接受参数
        model.addAttribute("cp", current);   //当前页
        model.addAttribute("tp", pages);   //总页数
        model.addAttribute("total", total);   //总条数
        model.addAttribute("pageListURL", "sharing_record/list");   //总条数
        return "sharing_record/sharing_recordList";
    }
}
