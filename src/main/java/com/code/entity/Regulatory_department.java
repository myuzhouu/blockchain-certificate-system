package com.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

        import java.lang.Integer;
        import java.lang.Integer;

/**
* ClassName:Regulatory_department
* Description: 监管部门实体类
*/
@TableName("Regulatory_department")
public class Regulatory_department {

 @TableId(type = IdType.AUTO) //主键自增
    /**
    * 部门编号
    */
        private Integer dept_id;
    /**
    * 部门名称
    */
        private String dept_name;
    /**
    * 联系方式
    */
        private String contact;
    /**
    * 关联账号
    */
        private Integer user_id;


        public Integer getDept_id() {
        return dept_id;
        }

        public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
        }

        public String getDept_name() {
        return dept_name;
        }

        public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
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
