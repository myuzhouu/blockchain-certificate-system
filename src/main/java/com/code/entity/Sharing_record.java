package com.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

        import java.lang.Integer;
        import java.lang.Integer;
        import java.lang.Integer;
        import java.util.Date;

/**
* ClassName:Sharing_record
* Description: 证书共享记录实体类
*/
@TableName("Sharing_record")
public class Sharing_record {

 @TableId(type = IdType.AUTO) //主键自增
    /**
    * 记录编号
    */
        private Integer record_id;
    /**
    * 证书号码
    */
        private String cert_num;
    /**
    * 用户编号
    */
        private Integer user_id;
    /**
    * 单位编号
    */
        private Integer org_id;
    /**
    * 共享时间
    */
        private Date share_time;
    /**
    * 共享状态
    */
        private String share_status;


        public Integer getRecord_id() {
        return record_id;
        }

        public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
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

        public Integer getOrg_id() {
        return org_id;
        }

        public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
        }

        public Date getShare_time() {
        return share_time;
        }

        public void setShare_time(Date share_time) {
        this.share_time = share_time;
        }

        public String getShare_status() {
        return share_status;
        }

        public void setShare_status(String share_status) {
        this.share_status = share_status;
        }


}
