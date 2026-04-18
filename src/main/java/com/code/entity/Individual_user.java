package com.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

        import java.lang.Integer;

/**
* ClassName:Individual_user
* Description: 个人用户实体类
*/
@TableName("Individual_user")
public class Individual_user {

 @TableId(type = IdType.AUTO) //主键自增
    /**
    * 用户编号
    */
        private Integer user_id;
    /**
    * 用户姓名
    */
        private String user_name;
    /**
    * 身份证号
    */
        private String id_card;
    /**
    * 联系方式
    */
        private String contact;


        public Integer getUser_id() {
        return user_id;
        }

        public void setUser_id(Integer user_id) {
        this.user_id = user_id;
        }

        public String getUser_name() {
        return user_name;
        }

        public void setUser_name(String user_name) {
        this.user_name = user_name;
        }

        public String getId_card() {
        return id_card;
        }

        public void setId_card(String id_card) {
        this.id_card = id_card;
        }

        public String getContact() {
        return contact;
        }

        public void setContact(String contact) {
        this.contact = contact;
        }


}
