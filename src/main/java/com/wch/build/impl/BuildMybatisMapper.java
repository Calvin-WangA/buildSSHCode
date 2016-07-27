package com.wch.build.impl;

import com.wch.build.iface.BuildFileCommon;
import com.wch.util.BuildMybatisSQL;
import com.wch.util.CommonTools;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by calvinwang on 16-7-11.
 */
public class BuildMybatisMapper extends BuildFileCommon {

    private String namespace = prop.getProperty("package");

    /**
     * 构建mapper的文件头
     * @param fileName 类名
     */
    public String buildFileHead(String fileName) {
        StringBuilder head = new StringBuilder("<!DOCTYPE mapper\n" +
                "PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n" +
                "\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
        head.append("<mapper namespace=\"");
        head.append(fileName);
        head.append("\" >\n");

        return head.toString();
    }

    @Override
    public String buildFile(String fileName, String type, List<String[]> cols) {

        String content = (String)super.buildFile(fileName, type, cols);
        File file = null;
        PrintWriter out = null;
        try{
            file = createFile(fileName.substring(fileName.lastIndexOf(".") + 1), type);
            out = new PrintWriter(file);
            content = CommonTools.formatXml(content);
            out.append(content);

            return "success";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            out.close();
        }

        return "fail";
    }

    public String buildFileBody(List<String[]> cols) {

        BuildMybatisSQL builder = new BuildMybatisSQL(prop.getProperty("tableName"), cols);

        String content = null;
        try{
            content = builder.buildDml();

            return content;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 追加文件结尾
     */
    public String buildFileTail() {

        String tail = "</mapper>\n";

        return tail;
    }
}
