package com.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Objects;

/**
 * ClassName:Certificate_info
 * Description: 实体类
 */
@TableName("Certificate_info")
public class Certificate_info {

    @TableId(type = IdType.AUTO) //主键自增
    /**
     * 证书号码
     */
    private String cert_num;
    /**
     * 用户姓名
     */
    private String user_name;
    /**
     * 机构名称
     */
    private String agency_name;
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


    public String getCert_num() {
        return cert_num;
    }

    public void setCert_num(String cert_num) {
        this.cert_num = cert_num;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
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


    @TableField(exist = false)
    private String codeimg;
    @TableField(exist = false)
    private String uuid;

    public String getCodeimg() {
        return codeimg;
    }

    public void setCodeimg(String codeimg) {
        this.codeimg = codeimg;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Certificate_info that = (Certificate_info) o;
        return cert_num.equals(that.cert_num);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cert_num);
    }

}
