package com.code;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.code.util.CommonUtils;
import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.Properties;


//mybatis 分页插件 参数配置

@Configuration
@EnableTransactionManagement
@MapperScan("com.code.mapper")
public class PageHelperConfig  implements WebMvcConfigurer {


    //配置static静态资源路径映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String location_file = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static";
        System.out.println("location_file = " + location_file);
        System.out.println("registry = " + registry);
        //图片资源映射　　　　　//其中/images是访问图片资源的前缀，比如要访问test1.png。则全路径为：http://localhost:端口号/项目名/images/test1.png
        registry.addResourceHandler("/**")
                .addResourceLocations("file:"+location_file);//此处为设置服务端存储图片的路径（前段上传到后台的图片保存位置）
    }


    //设置项目所有资源跨域
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); //允许任何域名
        corsConfiguration.addAllowedHeader("*"); //允许任何头
        corsConfiguration.addAllowedMethod("*"); //允许任何方法
        source.registerCorsConfiguration("/**", corsConfiguration); //注册
        return new CorsFilter(source);
    }


    //3.4.0后新版本写法
    @Bean
    public MybatisPlusInterceptor page(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }


    //配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper() throws Exception {
        PageHelper pageHelper = new PageHelper();
        if (CommonUtils.checkCode()) {
            Properties properties = new Properties();
            properties.setProperty("offsetAsPageNum", "true");
            properties.setProperty("rowBoundsWithCount", "true");
            properties.setProperty("reasonable", "true");
            properties.setProperty("dialect", "mysql");    //配置mysql数据库的方言
            pageHelper.setProperties(properties);
        }
        return pageHelper;
    }


    /**
     * 显示springboot项目启动访问地址
     *
     * @return
     */
    public static void showStartInfo() {
        try {
            //加载属性文件，读取数据库连接配置信息
            Properties pro = new Properties();
            try {
                pro.load(PageHelperConfig.class.getResourceAsStream("/application.properties"));
                String server_port = pro.getProperty("server.port");
                String context_path = pro.getProperty("server.servlet.context-path");
                //启动vue批处理命令
                String userDir = System.getProperty("user.dir");
                //Process process = Runtime.getRuntime().exec("cmd /c start   " + userDir + "/vue-myproject/点我启动VUE项目.bat");

                String url = "http://localhost:" + server_port + context_path + "/index.html";
                System.out.println("建议使用谷歌浏览器，点击后面的地址访问程序 = " + url);
                Runtime.getRuntime().exec("cmd /c start " + url);  // 启动浏览器

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("未找到配置文件！！！" + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
