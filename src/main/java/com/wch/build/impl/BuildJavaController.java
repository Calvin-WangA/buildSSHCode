package com.wch.build.impl;

import com.wch.util.CommonTools;

import java.util.List;

/**
 * Created by calvinwang on 16-7-20.
 */
public class BuildJavaController  extends BuildJavaInterface{

    private String serviceName;

    private String proName;

    private String methodHead = "public String ";

    public BuildJavaController(String tableName) {
        super(tableName);
        this.serviceName = CommonTools.buildEntityName(getTableName()) + "Service";
        this.proName = CommonTools.buildPropertyName(getTableName()) + "Service";
    }

    @Override
    public String buildFileHead(String fileName) {

        StringBuilder builder = new StringBuilder();
        builder.append("package ");
        builder.append(fileName.substring(0, fileName.lastIndexOf(".")));
        builder.append(";\n\n");
        builder.append("import javax.servlet.http.HttpServletRequest;\n");
        builder.append("import org.springframework.beans.factory.annotation.Autowired;\n");
        builder.append("import org.springframework.stereotype.Controller;\n");
        builder.append("import org.springframework.web.bind.annotation.RequestMapping;\n\n");
        builder.append("@Controller\n");
        builder.append("@RequestMapping(\"\")\n");
        builder.append("public class ");
        builder.append(fileName.substring(fileName.lastIndexOf(".") + 1));
        builder.append("{\n");

        return builder.toString();
    }

    @Override
    public String buildFileBody(List<String[]> cols) {

        StringBuilder builder = new StringBuilder();

        builder.append("@Autowired\n");
        builder.append("private ");
        builder.append(serviceName);
        builder.append(" ");
        builder.append(proName);
        builder.append(";\n");

        /*    builder.append("public void set");
        builder.append(serviceName);
        builder.append("(");
        builder.append(serviceName);
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

        builder.append("@RequestMapping(\"\")\n");
        builder.append(methodHead);
        builder.append(" get");
        builder.append(entityName);
        builder.append("forList(");
        builder.append(entityName);
        builder.append(" ");
        builder.append(proEntity);
        builder.append("){\n");

        //TO DO 方法体的实现
        builder.append("return \"\";\n");

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

        builder.append("@RequestMapping(\"\")\n");
        builder.append(methodHead);
        builder.append("getSingle");
        builder.append(entityName);
        builder.append("(");
        builder.append(entityName);
        builder.append(" ");
        builder.append(proEntity);
        builder.append("){\n");

        //TO DO 方法体的实现
        builder.append("return \"\";\n");


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

        builder.append("@RequestMapping(\"\")\n");
        builder.append(methodHead);
        builder.append("insert");
        builder.append(entityName);
        builder.append("(");
        builder.append(entityName);
        builder.append(" ");
        builder.append(proEntity);
        builder.append("){\n");

        //TO DO 方法体的实现
        builder.append("return \"\";\n");

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

        builder.append("@RequestMapping(\"\")\n");
        builder.append(methodHead);
        builder.append("update");
        builder.append(entityName);
        builder.append("(");
        builder.append(entityName);
        builder.append(" ");
        builder.append(proEntity);
        builder.append("){\n");

        //TO DO 方法体的实现
        builder.append("return \"\";\n");

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

        builder.append("@RequestMapping(\"\")\n");
        builder.append(methodHead);
        builder.append("delete");
        builder.append(entityName);
        builder.append("(");
        builder.append(entityName);
        builder.append(" ");
        builder.append(proEntity);
        builder.append("){\n");

        //TO DO 方法体的实现
        builder.append("return \"\";\n");

        builder.append("}\n");

        return builder.toString();
    }
}
