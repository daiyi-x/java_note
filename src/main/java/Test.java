import utils.CommonUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test {




    public static void main(String[] args) {
//        byte[] aa = new byte[]{117, 51};
//        System.out.println(new String(aa));
//        String tem1 = Base64.getEncoder().encodeToString(aa);
//        System.out.println(aa);
//        byte[] tem2 = Base64.getDecoder().decode(tem1);
//        System.out.println(tem2);
//        System.out.println(new String(tem2));
//        System.out.println(new String(Base64.getDecoder().decode(Base64.getEncoder().encodeToString(aa))));
//        System.out.println((char)117);
//        System.out.println((char)51);
//        String hex = "234afced1234";
//        byte[] tem1 = CommonUtils.convertHexToByte(hex);
//        System.out.println(tem1);
//        for (byte b:tem1) {
//            System.out.println(b);
//        }
////        try {
////            MessageDigest md = MessageDigest.getInstance("md5");
////            tem1 = md.digest(hex.getBytes());
////            System.out.println(tem1);
////            for (byte b:tem1) {
////                System.out.println(b);
////            }
////        } catch (NoSuchAlgorithmException e) {
////            e.printStackTrace();
////        }
//        BigInteger bigInteger  = new BigInteger(1, tem1);
//        System.out.println(bigInteger.toString(16));
        String REQUEST_URL = "http://list.rola-ip.site:8088/user_get_ip_list?token=2sWKsxRdhVUrb0ap1577069465978&qty=1&country=%s&time=10&format=json&protocol=socks5&filter=1";
        System.out.println(String.format(REQUEST_URL, "cn"));
    }

}