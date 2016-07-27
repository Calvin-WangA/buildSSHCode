package com.wch.build.impl;

import com.wch.util.CommonTools;

import java.util.List;

/**
 * Created by calvinwang on 16-7-19.
 */
public class BuildJavaServiceImpl extends BuildJavaInterface{

    private String daoName;

    private String proName;

    public BuildJavaServiceImpl(String tableName) {

        super(tableName);
        this.daoName = CommonTools.buildEntityName(getTableName()) + "Mapper";
        this.proName = CommonTools.buildPropertyName(getTableName()) + "Mapper";

    }

    @Override
    public String buildFileHead(String fileName) {

        StringBuilder builder = new StringBuilder();
        builder.append("package ");
        builder.append(fileName.substring(0, fileName.lastIndexOf(".")));
        builder.append(";\n\n");
        builder.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        builder.append("import org.springframework.stereotype.Service;\n\n");
        builder.append("@Service(\"");
        builder.append(proEntity);
        builder.append("Service");
        builder.append("\")\n");
        builder.append("public class ");
        builder.append(fileName.substring(fileName.lastIndexOf(".") + 1));
        builder.append("{\n");

        return builder.toString();
    }

    /**
     * 重新Body以构建该层要使用的属性及方法体
     * @param cols table的信息
     * @return String
     */
    @Override
    public String buildFileBody(List<String[]> cols) {

        StringBuilder builder = new StringBuilder();

        builder.append("@Autowired\n");
        builder.append("private ");
        builder.append(daoName);
        builder.append(" ");
        builder.append(proName);
        builder.append(";\n");

        /*builder.append("public void set");
        builder.append(daoName);
        builder.append("(");
        builder.append(daoName);
        builder.append(" ");
        builder.append(proName);
        builder.append("){\n");
        builder.append("this.");
        builder.append(proName);
        builder.append(" = ");
        builder.append(proName);
        builder.append(";\n}\n");*/

        builder.append(super.buildFileBody(cols));

        return builder.toString();
    }

    /**
     * 重写以增加方法体
     * @param entityName 传入的类型
     * @return
     */
    @Override
    public String buildQueryForList(String entityName) {

        StringBuilder builder = new StringBuilder();

        String declare = super.buildQueryForList(entityName);

        builder.append(declare.substring(0, declare.length() - 2));
        builder.append("{\n");

        //TO DO 方法体的实现
        builder.append("return ");
        builder.append(proName);
        builder.append(".");
        builder.append("get");
        builder.append(entityName);
        builder.append("forList(");
        builder.append(proEntity);
        builder.append(");\n");

        builder.append("}\n");

        return builder.toString();
    }

    /**
     * 重写代码以增加方法业务
     * @param entityName 类名
     * @return
     */
    @Override
    public String buildSingleQueryForObject(String entityName) {

        StringBuilder builder = new StringBuilder();
        String declare = super.buildSingleQueryForObject(entityName);

        builder.append(declare.substring(0, declare.length() - 2));
        builder.append("{\n");

        //TO DO 方法体的实现
        builder.append("return ");
        builder.append(proName);
        builder.append(".");
        builder.append("getSingle");
        builder.append(entityName);
        builder.append("(");
        builder.append(proEntity);
        builder.append(");\n");

        builder.append("}\n");

        return builder.toString();
    }

    /**
     * 重写方法以增加方法业务
     * @param entityName 类名
     * @return
     */
    @Override
    public String buildInsert(String entityName) {

        StringBuilder builder = new StringBuilder();
        String declare = super.buildInsert(entityName);

        builder.append(declare.substring(0, declare.length() - 2));
        builder.append("{\n");

        //TO DO 方法体的实现
        builder.append("return ");
        builder.append(proName);
        builder.append(".");
        builder.append("insert");
        builder.append(entityName);
        builder.append("(");
        builder.append(proEntity);
        builder.append(");\n");

        builder.append("}\n");

        return builder.toString();
    }

    /**
     * 重写方法以增加方法业务
     * @param entityName 类名
     * @return
     */
    @Override
    public String buildUpdate(String entityName) {

        StringBuilder builder = new StringBuilder();
        String declare = super.buildUpdate(entityName);

        builder.append(declare.substring(0, declare.length() - 2));
        builder.append("{\n");

        //TO DO 方法体的实现
        builder.append("return ");
        builder.append(proName);
        builder.append(".");
        builder.append("update");
        builder.append(entityName);
        builder.append("(");
        builder.append(proEntity);
        builder.append(");\n");

        builder.append("}\n");

        return builder.toString();
    }

    /**
     * 重写方法以增加方法业务
     * @param entityName 类名
     * @return
     */
    @Override
    public String buildDelete(String entityName) {

        StringBuilder builder = new StringBuilder();
        String declare = super.buildDelete(entityName);

        builder.append(declare.substring(0, declare.length() - 2));
        builder.append("{\n");

        //TO DO 方法体的实现
        builder.append("return ");
        builder.append(proName);
        builder.append(".");
        builder.append("delete");
        builder.append(entityName);
        builder.append("(");
        builder.append(proEntity);
        builder.append(");\n");

        builder.append("}\n");

        return builder.toString();
    }
}
