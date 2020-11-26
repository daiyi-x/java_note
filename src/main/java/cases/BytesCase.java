package cases;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BytesCase {
    public static void main(String[] args) {

//        Matcher matcher = Pattern.compile("连我激活码：([0-9]{6})").matcher("【14:30:19】收到：【LINE】<#> 连我激活码：486726，请在30分钟内完成输入。连我欢迎您的加入！\nJFoQLtyexga");  //TODO test
//        System.out.println(matcher.matches());
//        matcher.find();
        String message = "【14:59:39】收到：【LINE】<#> 连我激活码：423612，请在30分钟内完成输入。连我欢迎您的加入！\nFoQLtyexga";
        Matcher matcher = Pattern.compile("连我激活码：([0-9]{6})").matcher(message);  //TODO test
        matcher.find();
        System.out.println(matcher.group(1));

    }



}
