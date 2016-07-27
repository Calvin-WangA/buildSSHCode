package com.wch.util;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by calvinwang on 16-7-10.
 */
public class CommonTools {

    /**
     * 构建实体类名
     *
     * @param tableName 表名
     * @return 返回bean名字
     */
    public static String buildEntityName(String tableName) {
        String entityName = "";
        String[] words = tableName.split("_");
        String word;
        int len = words.length;

        for (int i = 0; i < len; i++) {
            word = words[i];
            entityName += word.toLowerCase().replaceFirst(word.substring(0, 1)
                    , word.substring(0, 1).toUpperCase());
        }

        return entityName;
    }

    /**
     * 构建属性名
     *
     * @param columnName 列名
     * @return 属性名
     */
    public static String buildPropertyName(String columnName) {

        String propName = buildEntityName(columnName);

        propName = propName.replaceFirst(propName.substring(0, 1)
                , propName.substring(0, 1).toLowerCase());

        return propName;
    }

    /**
     * @param cols
     * @return
     */
    public static String[] columnsToProperties(List<String[]> cols) {

        String[] names = new String[cols.size()];
        for (int i = 0; i < cols.size(); i++) {
            names[i] = buildPropertyName(cols.get(i)[0]);
        }

        return names;
    }

    /**
     * @param cols
     * @return
     */
    public static String[] getColumnNames(List<String[]> cols) {
        String[] names = new String[cols.size()];

        for (int i = 0; i < cols.size(); i++) {
            names[i] = cols.get(i)[0];
        }

        return names;
    }

    /**
     * @param cols
     * @return
     */
    public static String getStringForColumns(List<String[]> cols) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < cols.size(); i++) {
            builder.append(cols.get(i)[0]);
            builder.append(",");
        }
        builder = new StringBuilder(builder.substring(0, builder.length() - 1));

        return builder.toString();
    }

    public static String formatXml(String inputXML) throws Exception {

        SAXReader reader = new SAXReader();
        Document document = reader.read(new StringReader(inputXML));
        String requestXML = null;
        XMLWriter writer = null;
        if (document != null) {
            try {
                StringWriter stringWriter = new StringWriter();
                OutputFormat format = new OutputFormat("  ", true);
                writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                requestXML = stringWriter.getBuffer().toString();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return requestXML;

    }
}
