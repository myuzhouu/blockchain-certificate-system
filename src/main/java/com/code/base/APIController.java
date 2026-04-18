package com.code.base;


import com.code.PageHelperConfig;
import com.code.mapper.CommonMapper;
import com.code.util.GlobalResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.*;


@Controller
@CrossOrigin("*") //允许跨域请求
@RequestMapping("/")
public class APIController {
    @Autowired   //注入对象
    public CommonMapper commonMapper;


    //公共分页查询方法
    @ResponseBody
    @RequestMapping("/selectPageAction")
    public GlobalResult selectPageAction(String sql, String p, Integer pageSize) {
        int pageNum = 1;

        if (null != p) {
            pageNum = Integer.parseInt(p);
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        Integer total = 0;
        List<Map> mapList = commonMapper.selectAction(sql);
        total = mapList.size();
        long pages = total / pageSize;
        if (total % pageSize > 0) {
            pages = pages + 1;
        }
        String sqlTwo = sql + " limit " + (pageNum - 1) * pageSize + "," + pageSize + " ";
        List<Map> records = commonMapper.selectAction(sqlTwo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pages", pages);
        map.put("current", pageNum);
        map.put("records", records);
        map.put("total", total);
        return GlobalResult.ok(map);
    }

    //公共查询方法
    @ResponseBody
    @RequestMapping("/selectAction")
    public GlobalResult selectAction(String sql) {
        List<Map> mapList = commonMapper.selectAction(sql);
        return GlobalResult.ok(mapList);
    }


    //公共修改方法
    @ResponseBody
    @RequestMapping("/updateAction")
    public GlobalResult updateAction(String sql) {
        try {
            commonMapper.updateAction(sql);
            return GlobalResult.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.ok("操作失败" + e.getMessage());
        }

    }



    //验证码
    @ResponseBody
    @RequestMapping("/code")
    public GlobalResult code(HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        // 将认证码存入SESSION  sRand
        String contextPathHttp = getFileUploadsURL() + "/";
        g.dispose();
        String imgName = System.currentTimeMillis() + ".jpg";
        String savePath = getUploadsPath() + "/" + imgName;
        //System.out.println("图片保存路径 = " + savePath);
        OutputStream out = new FileOutputStream(savePath); // 将图片生成到 e盘下面
        ImageIO.write(image, "JPEG", out);
        Map map = new HashMap();
        map.put("src", contextPathHttp + imgName);
        map.put("code", sRand);
        return GlobalResult.ok(map);
    }


    //图片验证码工具类
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    //获取文件上传保存路径
    public String getUploadsPath() {
        // 这里需要注意的是ApplicationHome是属于SpringBoot的类
        // 获取项目下resources/static/uploads 路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        // 保存目录位置根据项目需求可随意更改
        String basePath = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\uploads";
        return basePath;
    }

    /**
     * 使用静态路径映射网址返回
     *
     * @return
     */
    public String getFileUploadsURL() {
        String url = "";
        Properties pro = new Properties();
        try {
            pro.load(PageHelperConfig.class.getResourceAsStream("/application.properties"));
            String server_port = pro.getProperty("server.port");
            String context_path = pro.getProperty("server.servlet.context-path");
            url = "http://localhost:" + server_port + context_path + "/uploads/";
        } catch (Exception e) {
            System.out.println("错误信息 【读取项目配置参数出错】= " + e);
        }
        return url;
    }

}
