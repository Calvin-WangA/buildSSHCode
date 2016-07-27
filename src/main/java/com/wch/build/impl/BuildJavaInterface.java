package com.wch.build.impl;

import com.wch.build.iface.BuildJavaCode;
import com.wch.util.CommonTools;

import java.util.List;

/**
 * Created by calvinwang on 16-7-19.
 */
public class BuildJavaInterface extends BuildJavaCode {

    /** 实体名*/
    public String proEntity;

    public BuildJavaInterface(String tableName) {
        super(tableName);
        this.proEntity = CommonTools.buildPropertyName(tableName);
    }

    /**
     * 构建对应的java业务代码
     * @param cols table的信息
     * @return String 一个包含java代码的字符串
     */
    public String buildFileBody(List<String[]> cols) {

        StringBuilder builder = new StringBuilder();

        String entityName = CommonTools.buildEntityName(getTableName());
        builder.append(buildQueryForList(entityName));
        builder.append(buildSingleQueryForObject(entityName));
        builder.append(buildInsert(entityName));
        builder.append(buildUpdate(entityName));
        builder.append(buildDelete(entityName));

        return builder.toString();
    }

    /**
     * 构建查询集合方法
     * @param entityName 传入的类型
     * @return String
     */
    public String buildQueryForList(String entityName){

        StringBuilder builder = new StringBuilder();
        builder.append("public List<");
        builder.append(entityName);
        builder.append("> get");
        builder.append(entityName);
        builder.append("forList(");
        builder.append(entityName);
        builder.append(" ");
        builder.append(proEntity);
        builder.append(");\n");

        return builder.toString();
    }

    /**
     * 查询唯一的记录
     * @param entityName 类名
     * @return String
     */
    public String buildSingleQueryForObject(String entityName){

        StringBuilder builder = new StringBuilder();

        builder.append("public ");
        builder.append(entityName);
        builder.append(" getSingle");
        builder.append(entityName);
        builder.append("(");
        builder.append(entityName);
        builder.append(" ");
        builder.append(proEntity);
        builder.append(");\n");

        return builder.toString();
    }

    /**
     * 插入一条记录
     * @param entityName 类名
     * @return String
     */
    public String buildInsert(String entityName){

        StringBuilder builder = new StringBuilder();

        builder.append("public int insert");
        builder.append(entityName);
        builder.append("(");
        builder.append(entityName);
        builder.append(" ");
        builder.append(proEntity);
        builder.append(");\n");

        return builder.toString();
    }

    /**
     * 更新指定条件的记录
     * @param entityName 类名
     * @return String
     */
    public String buildUpdate(String entityName){

        StringBuilder builder = new StringBuilder();

        builder.append("public int update");
        builder.append(entityName);
        builder.append("(");
        builder.append(entityName);
        builder.append(" ");
        builder.append(proEntity);
        builder.append(");\n");

        return builder.toString();
    }

    /**
     * 删除指定条件的记录
     * @param entityName 类名
     * @return String
     */
    public String buildDelete(String entityName){

        StringBuilder builder = new StringBuilder();

        builder.append("public int delete");
        builder.append(entityName);
        builder.append("(");
        builder.append(entityName);
        builder.append(" ");
        builder.append(proEntity);
        builder.append(");\n");

        return builder.toString();
    }

}
