package com.wch.build.iface;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by calvinwang on 16-7-11.
 */
public interface BuildFile {

    public String buildFileHead(String fileName);

    public String buildFileBody(List<String[]> cols);

    public String buildFileTail();

    public String buildFile(String fileName, String type, List<String[]> cols);
}
