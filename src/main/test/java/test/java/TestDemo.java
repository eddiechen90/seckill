package test.java;

import org.junit.Test;
import util.ZxingUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Eddie on 2017/6/2.
 */
public class TestDemo {

    String content = "二维码内容";
    @Test
    public void test01() throws IOException {
        ZxingUtils.getQRCodeImge(content,300,"qr.jpg");
        File file=new File("qr.jpg");
        file.createNewFile();

    }

    @Test
    public void test02(){
        Boolean b=new Boolean("true");
        System.out.println(b);
    }
}
