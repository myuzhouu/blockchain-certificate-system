package com.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

        import java.lang.Integer;

/**
* ClassName:Userinfo
* Description: 用户账号实体类
*/
@TableName("Userinfo")
public class Userinfo {

 @TableId(type = IdType.AUTO) //主键自增
    /**
    * 用户编号
    */
        private Integer user_id;
    /**
    * 登录账号
    */
        private String username;
    /**
    * 登录密码
    */
        private String password;
    /**
    * 用户角色
    */
        private String role;
    /**
    * 用户头像
    */
        private String picurl;
    /**
    * 创建时间
    */
        private String create_time;
    /**
    * 联系电话
    */
        private String phone;


        public Integer getUser_id() {
        return user_id;
        }

        public void setUser_id(Integer user_id) {
        this.user_id = user_id;
        }

        public String getUsername() {
        return username;
        }

        public void setUsername(String username) {
        this.username = username;
        }

        public String getPassword() {
        return password;
        }

        public void setPassword(String password) {
        this.password = password;
        }

        public String getRole() {
        return role;
        }

        public void setRole(String role) {
        this.role = role;
        }

        public String getPicurl() {
        return picurl;
        }

        public void setPicurl(String picurl) {
        this.picurl = picurl;
        }

        public String getCreate_time() {
        return create_time;
        }

        public void setCreate_time(String create_time) {
        this.create_time = create_time;
        }

        public String getPhone() {
        return phone;
        }

        public void setPhone(String phone) {
        this.phone = phone;
        }


}
