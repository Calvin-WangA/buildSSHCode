package com.wch;

import com.wch.util.FormatJavaCode;
import org.junit.Test;

import java.io.File;

/**
 * Created by calvinwang on 16-7-21.
 */
public class formatTest {

    @Test
    public void testFormatCode() {

        //format java code
        FormatJavaCode formater = new FormatJavaCode();
        formater.formatCode(new File("UsrUsers.java"));
    }
}
