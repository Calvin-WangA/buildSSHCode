package com.wch.util;

import sun.util.BuddhistCalendar;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by calvinwang on 16-7-21.
 */
public class FormatJavaCode {

    private static  String space = "";

    public static void formatFile(File file){
        File format = formatCode(file);
        rewriteFile(format, file);
    }
    /**
     * 格式化java代码
     * @param file 需要格式化的文件
     * @return File已格式化好的文件
     */
    public static File formatCode(File file){

        FileInputStream fis = null;
        BufferedReader reader = null;
        FileOutputStream fos = null;
        BufferedWriter writer = null;
        File out = new File("format.java");

        try{
            out.createNewFile();
            fis = new FileInputStream(file);
            fos = new FileOutputStream(out);
            reader = new BufferedReader(new InputStreamReader(fis));
            writer = new BufferedWriter(new OutputStreamWriter(fos));

            String value;
            String preValue = "";

            while(null != (value = reader.readLine())){

                preValue = preValue + value;
                if(value.startsWith("@") || value.endsWith(";") || value.endsWith("{") || value.endsWith("}")){
                    writeString(writer, preValue);
                }
                else{
                    continue;
                }
                preValue = "";
            }

            fis.close();
            reader.close();
            writer.close();
            fos.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return out;
    }

    /**
     * 对处于一行的进行处理
     * @param writer 写出文件
     * @param line 当前行
     * @throws Exception
     */
    public static void writeString(BufferedWriter writer, String line) throws Exception{

        String preValue = "";

        if("".equals(line.trim())){
            return ;
        }
        else if(line.contains("{")){
            preValue = line.substring(0, line.indexOf("{") + 1);
            writer.append(space + preValue + "\n");
            space += "    ";
            writeString(writer, line.substring(line.indexOf("{") + 1, line.length()).trim());
        }
        else if(line.contains(";")){
            preValue = space + line.substring(0, line.indexOf(";") + 1);
            writer.append(preValue + "\n");
            writeString(writer, line.substring(line.indexOf(";") + 1, line.length()).trim());
        }
        else if(line.contains("}")) {
            preValue = line.substring(0, line.indexOf("}") + 1);
            space = space.substring(0, space.length() - 4);
            preValue = space + preValue;
            writer.append(preValue + "\n\n");
            writeString(writer, line.substring(line.indexOf("}") + 1, line.length()).trim());
        }
        else{
            line = space + line;
            writer.append(line + "\n");
        }

        return ;
    }

    /**
     * 将格式化好的文件重新写入源文件中
     * @param format 格式化的文件
     * @param dest 原文件
     */
    public static void rewriteFile(File format, File dest){
        FileInputStream fis = null;
        BufferedReader reader = null;
        FileOutputStream fos = null;
        BufferedWriter writer = null;
        try{
            fis = new FileInputStream(format);
            fos = new FileOutputStream(dest);
            reader = new BufferedReader(new InputStreamReader(fis));
            writer = new BufferedWriter(new OutputStreamWriter(fos));
            String value;
            dest.createNewFile();
            while(null != (value = reader.readLine())){

                writer.append(value );
            }

            fis.close();
            reader.close();
            writer.close();
            fos.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}
