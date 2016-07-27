package com.wch.build.iface;

import com.wch.build.iface.BuildFile;
import com.wch.util.CommonTools;
import com.wch.util.JdbcTools;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

/**
 * 所有创建文件的基类
 * Created by calvinwang on 16-7-12.
 */
public abstract class BuildFileCommon implements BuildFile {

    public static Properties prop = JdbcTools.getProp();

    /**
     * 创建一个新文件
     *
     * @param fileName 文件名
     * @return 文件流
     */
    public File createFile(String fileName, String type) {
        File file = null;
        try {
            file = new File(fileName + "." + type);
            if (!file.exists()) {

                file.createNewFile();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * 构建mapper文件
     *
     * @param fileName 类名
     */
    public String buildFile(String fileName, String type, List<String[]> cols) {

        StringBuilder builder = new StringBuilder();

        builder.append(buildFileHead(fileName));
        builder.append(buildFileBody(cols));
        builder.append(buildFileTail());

        return builder.toString();

    }
}
