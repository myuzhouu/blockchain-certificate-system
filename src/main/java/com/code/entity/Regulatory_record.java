package com.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

        import java.lang.Integer;
        import java.lang.Integer;
        import java.util.Date;

/**
* ClassName:Regulatory_record
* Description: 监管记录实体类
*/
@TableName("Regulatory_record")
public class Regulatory_record {

 @TableId(type = IdType.AUTO) //主键自增
    /**
    * 记录编号
    */
        private Integer record_id;
    /**
    * 部门编号
    */
        private Integer dept_id;
    /**
    * 证书号码
    */
        private String cert_num;
    /**
    * 监管时间
    */
        private Date reg_time;
    /**
    * 监管结果
    */
        private String reg_result;
    /**
    * 监管备注
    */
        private String reg_remark;


        public Integer getRecord_id() {
        return record_id;
        }

        public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
        }

        public Integer getDept_id() {
        return dept_id;
        }

        public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
        }

        public String getCert_num() {
        return cert_num;
        }

        public void setCert_num(String cert_num) {
        this.cert_num = cert_num;
        }

        public Date getReg_time() {
        return reg_time;
        }

        public void setReg_time(Date reg_time) {
        this.reg_time = reg_time;
        }

        public String getReg_result() {
        return reg_result;
        }

        public void setReg_result(String reg_result) {
        this.reg_result = reg_result;
        }

        public String getReg_remark() {
        return reg_remark;
        }

        public void setReg_remark(String reg_remark) {
        this.reg_remark = reg_remark;
        }


}
