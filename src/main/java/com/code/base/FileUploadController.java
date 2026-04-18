package com.code.base;

import com.code.PageHelperConfig;
import com.code.util.GlobalResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * 文件上传请求处理类
 */
@Controller
@CrossOrigin("*") //允许跨域请求
public class FileUploadController  {

    //获取文件上传保存路径
    public String getUploadsPath() {
        // 这里需要注意的是ApplicationHome是属于SpringBoot的类
        // 获取项目下resources/static/uploads 路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        // 保存目录位置根据项目需求可随意更改
        String basePath = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\uploads";
        //方法二 System.getProperty("user.dir") +"\\src\\main\\resources\\static\\uploads\\";
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
            url = "http://localhost:" + server_port + context_path + "uploads/";
        } catch (Exception e) {
            System.out.println("错误信息 【读取项目配置参数出错】= " + e);
        }
        return url;
    }


    @RequestMapping(value = "/uploadFile", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    //@RequestMapping(value = "/uploadFile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public GlobalResult uploadFile(MultipartFile file) {
        if (file == null) {
            return GlobalResult.errorMsg("文件不能为空！");
        }
        String fileSub = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        //if (".jpg".equals(fileSub) || ".jpeg".equals(fileSub) || ".png".equals(fileSub) || ".gif".equals(fileSub)) {
        if (true) {
            Random d = new Random();
            String img = System.currentTimeMillis() + "_" + d.nextInt(10) + "" + fileSub;

            // 保存目录位置根据项目需求可随意更改
            String basePath = getUploadsPath();
            System.out.println("上传文件路径 basePath = " + basePath);
            /*
                使用配置文件配置文件上传路径
                String dateStr = (new SimpleDateFormat("yyyyMMdd/")).format(new Date());
                String path = ConfigUtil.getUploadPath() + dateStr;  //读取配置文件中的路径+时间
            */
            String path = basePath;
            try {
                File f = new File(path);
                if (!f.exists()) {
                    f.mkdirs();
                }
                file.transferTo(new File(f, img));
            } catch (Exception e) {
                e.printStackTrace();
                return GlobalResult.errorMsg("文件上传错误，文件夹不能创建");
            }
            //获取项目路径 例如项目名为test  则值为  /test
            //获取图片上传绝对路径保存数据库
            String contextPathHttp = getFileUploadsURL();
            Map<String, String> map = new HashMap<String, String>();
            map.put("src", contextPathHttp + img);
            map.put("url", contextPathHttp + img);
            map.put("savePath", path + File.separatorChar + img);
            return GlobalResult.ok(map);
        } else {
            return GlobalResult.errorMsg("文件格式不支持,请重新选择！");
        }
    }

    /**
     * layui上传文件
     * 返回数据格式
     * {
     * "code": 0 //0表示成功，其它失败
     * ,"msg": "" //提示信息 //一般上传失败后返回
     * ,"data": {
     * "src": "图片路径"
     * ,"title": "图片名称" //可选
     * }
     * }
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/layeditUploadFile", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    //@RequestMapping(value = "/layeditUploadFile", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map layeditUploadFile(MultipartFile file) {
        Map map = new HashMap();
        if (file == null) {
            map.put("code", -1);
            map.put("msg", "文件不能为空！");
            return map;
        }
        String fileSub = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        if (".jpg".equals(fileSub) || ".jpeg".equals(fileSub) || ".png".equals(fileSub) || ".gif".equals(fileSub)) {
            Random d = new Random();
            String img = System.currentTimeMillis() + "_" + d.nextInt(10) + "" + fileSub;
            //获取当前项目上传文件路径  中的upload文件中
            //将当前日期格式化为文件夹路径 例如"20190203"
            //获取项目路径 项目名（上下文）
            String basePath = getUploadsPath();
            System.out.println("basePath = " + basePath);
            /*
                使用配置文件配置文件上传路径
                String dateStr = (new SimpleDateFormat("yyyyMMdd/")).format(new Date());
                String path = ConfigUtil.getUploadPath() + dateStr;  //读取配置文件中的路径+时间
            */
            String path = basePath;
            try {
                File f = new File(path);
                if (!f.exists()) {
                    f.mkdirs();
                }
                file.transferTo(new File(f, img));
            } catch (Exception e) {
                e.printStackTrace();
                map.put("code", -1);
                map.put("msg", "文件上传错误，文件夹不能创建！");
                return map;
            }
            //获取项目路径
            String contextPath = getFileUploadsURL();
            map.put("code", "0"); //0代表成功
            map.put("msg", "上传成功success"); //0代表成功
            Map data = new HashMap();
            data.put("src", contextPath + "/uploads/" + img);
            data.put("savePath", path + img);
            map.put("data", data); //0代表成功
            return map;
        } else {
            map.put("code", -1);
            map.put("msg", "文件格式不支持,请重新选择！");
            return map;
        }
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名称 例如 /CommonWeb/uploads/1570244659826_1.jpg
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/downloadFile")
    public String download(String fileName, HttpServletRequest request, HttpServletResponse response) {
        fileName = fileName.split("\\/")[3];
        System.out.println("fileName = " + fileName);
        response.setContentType("text/html;charset=utf-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        String downLoadPath = getUploadsPath() + fileName;
        ;  //注意不同系统的分隔符
        //	String downLoadPath =filePath.replaceAll("/", "\\\\\\\\");   //replace replaceAll区别 *****
        System.out.println("downLoadPath=" + downLoadPath);
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
