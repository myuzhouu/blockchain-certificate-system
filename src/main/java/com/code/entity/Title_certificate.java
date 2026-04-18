package com.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

        import java.lang.Integer;
        import java.lang.Integer;
        import java.lang.Integer;
        import java.util.Date;
        import java.util.Date;
        import java.util.Date;

/**
* ClassName:Title_certificate
* Description: 职称证书实体类
*/
@TableName("Title_certificate")
public class Title_certificate {

 @TableId(type = IdType.AUTO) //主键自增
    /**
    * 证书编号
    */
        private Integer cert_id;
    /**
    * 证书号码
    */
        private String cert_num;
    /**
    * 用户编号
    */
        private Integer user_id;
    /**
    * 机构编号
    */
        private Integer agency_id;
    /**
    * 职称名称
    */
        private String title_name;
    /**
    * 发证日期
    */
        private Date issue_date;
    /**
    * 有效起始
    */
        private Date valid_start;
    /**
    * 有效截止
    */
        private Date valid_end;
    /**
    * 证书状态
    */
        private String cert_status;
    /**
    * 区块哈希
    */
        private String block_hash;


        public Integer getCert_id() {
        return cert_id;
        }

        public void setCert_id(Integer cert_id) {
        this.cert_id = cert_id;
        }

        public String getCert_num() {
        return cert_num;
        }

        public void setCert_num(String cert_num) {
        this.cert_num = cert_num;
        }

        public Integer getUser_id() {
        return user_id;
        }

        public void setUser_id(Integer user_id) {
        this.user_id = user_id;
        }

        public Integer getAgency_id() {
        return agency_id;
        }

        public void setAgency_id(Integer agency_id) {
        this.agency_id = agency_id;
        }

        public String getTitle_name() {
        return title_name;
        }

        public void setTitle_name(String title_name) {
        this.title_name = title_name;
        }

        public Date getIssue_date() {
        return issue_date;
        }

        public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
        }


        public Date getValid_start() {
        return valid_start;
        }

        public void setValid_start(Date valid_start) {
        this.valid_start = valid_start;
        }


        public Date getValid_end() {
        return valid_end;
        }

        public void setValid_end(Date valid_end) {
        this.valid_end = valid_end;
        }


        public String getCert_status() {
        return cert_status;
        }

        public void setCert_status(String cert_status) {
        this.cert_status = cert_status;
        }

        public String getBlock_hash() {
        return block_hash;
        }

        public void setBlock_hash(String block_hash) {
        this.block_hash = block_hash;
        }


}
