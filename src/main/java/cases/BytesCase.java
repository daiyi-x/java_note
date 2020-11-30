package cases;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BytesCase {
    public static void main(String[] args) {

//        Matcher matcher = Pattern.compile(".*连我激活码：([0-9]{6}).*\n.*").matcher("【14:30:19】收到：【LINE】<#> 连我激活码：486726，请在30分钟内完成输入。连我欢迎您的加入！\nJFoQLtyexga");  //TODO test
//        System.out.println(matcher.matches());
//        matcher.find();
//        String message = "       <string name=\"client_static_keypair\">uDlT9OWRLRv1EoTletNe0O4p0yYUuV3dpO+Ic3lxu16q30wJNYEJ/zcfm0GcM9RQVitoa9AeaFgAQCAJnUVGTA</string> ";
//        Matcher matcher = Pattern.compile(".*client_static_keypair\">([^<]*)<.*").matcher(message);
//        matcher.find();
//        System.out.println(matcher.matches());
//        System.out.println(matcher.group(1));
        String string = "Dianthe";
        System.out.println(string.matches("^[\\da-zA-Z]+$"));
    }



}
