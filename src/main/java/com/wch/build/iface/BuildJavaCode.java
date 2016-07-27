package com.wch.build.iface;

import com.wch.build.iface.BuildFileCommon;
import com.wch.util.FormatJavaCode;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by calvinwang on 16-7-18.
 */
public abstract class BuildJavaCode extends BuildFileCommon {

    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public BuildJavaCode(String tableName) {
        this.tableName = tableName;
    }

    public String buildFileHead(String fileName) {
        String value = "package " + fileName.substring(0, fileName.lastIndexOf(".")) + ";\n\n";
        value += "public interface " + fileName.substring(fileName.lastIndexOf(".") + 1) + "{\n";

        return value;
    }

    public String buildFileTail() {

        String tail = "}\n";

        return tail;
    }

    @Override
    public String buildFile(String fileName, String type, List<String[]> cols) {

        String content = (String) super.buildFile(fileName, type, cols);
        File file = null;
        PrintWriter out = null;

        try {
            file = createFile(fileName.substring(fileName.lastIndexOf(".") + 1), type);
            out = new PrintWriter(file);

            out.append(content);
            out.close();
            //进行java文件的格式化
            FormatJavaCode.formatFile(file);

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "fail";
    }

}

