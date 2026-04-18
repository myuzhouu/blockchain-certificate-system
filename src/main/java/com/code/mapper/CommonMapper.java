package com.code.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.code.entity.Userinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CommonMapper extends BaseMapper<Userinfo> {

    //公共的查询方法
    @Select(" ${sql} ")
    public List<Map> selectAction(@Param("sql") String sql);

    //公共的增删修改方法
    @Update(" ${sql} ")
    public int updateAction(@Param("sql") String sql);

    /**
     * 单实体查询
     *
     * @param tableName 表名
     * @param column    字段名
     * @param value     字段值
     * @return
     */
    @Select("select * from ${tableName} where  ${column} = '${value}' limit 1 ")
    public Map selectOneObject(@Param("tableName") String tableName, @Param("column") String column, @Param("value") String value);

    /**
     * 多实体查询
     *
     * @param tableName 表名
     * @param column    字段名
     * @param value     字段值
     * @return
     */
    @Select("select * from ${tableName} where  ${column} = '${value}' ")
    public List<Map> selectListObject(@Param("tableName") String tableName, @Param("column") String column, @Param("value") String value);

}
