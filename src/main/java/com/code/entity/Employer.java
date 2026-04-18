package com.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

        import java.lang.Integer;
        import java.lang.Integer;

/**
* ClassName:Employer
* Description: 单位信息实体类
*/
@TableName("Employer")
public class Employer {

 @TableId(type = IdType.AUTO) //主键自增
    /**
    * 单位编号
    */
        private Integer org_id;
    /**
    * 单位名称
    */
        private String org_name;
    /**
    * 信用代码
    */
        private String credit_code;
    /**
    * 联系方式
    */
        private String contact;
    /**
    * 关联账号
    */
        private Integer user_id;


        public Integer getOrg_id() {
        return org_id;
        }

        public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
        }

        public String getOrg_name() {
        return org_name;
        }

        public void setOrg_name(String org_name) {
        this.org_name = org_name;
        }

        public String getCredit_code() {
        return credit_code;
        }

        public void setCredit_code(String credit_code) {
        this.credit_code = credit_code;
        }

        public String getContact() {
        return contact;
        }

        public void setContact(String contact) {
        this.contact = contact;
        }

        public Integer getUser_id() {
        return user_id;
        }

        public void setUser_id(Integer user_id) {
        this.user_id = user_id;
        }


}
