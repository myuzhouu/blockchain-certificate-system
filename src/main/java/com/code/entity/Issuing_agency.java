package com.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

        import java.lang.Integer;
        import java.lang.Integer;

/**
* ClassName:Issuing_agency
* Description: 机构信息实体类
*/
@TableName("Issuing_agency")
public class Issuing_agency {

 @TableId(type = IdType.AUTO) //主键自增
    /**
    * 机构编号
    */
        private Integer agency_id;
    /**
    * 机构名称
    */
        private String agency_name;
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


        public Integer getAgency_id() {
        return agency_id;
        }

        public void setAgency_id(Integer agency_id) {
        this.agency_id = agency_id;
        }

        public String getAgency_name() {
        return agency_name;
        }

        public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
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
