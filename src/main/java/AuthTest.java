import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthTest {

    public static int bruteForceKey(String authKeyValue){
        for (int i = 0x00; i < 0xff; i++) {
            String plainText = decrypt_setting(authKeyValue, i);
            if(is_profile_auth_key(plainText)){
                return i;
            }
        }
        throw new RuntimeException("没有找到key");
    }

    public static boolean is_profile_auth_key(String value){
        Matcher matcher = Pattern.compile("^u[a-z0-9]{32}:[a-zA-Z0-9+/]+$").matcher(value);
        return matcher.matches();
    }

    public static String AESDecode(byte[] bytes, String content) {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(bytes);
            keygen.init(128, random);   //
            //3.产生原始对称密钥
            SecretKey original_key = keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] raw = original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key = new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte[] byte_content = new byte[0];
            try {
                byte_content = Base64.decode(content);
            } catch (Base64DecodingException e) {
                e.printStackTrace();
            }
            /*
             * 解密
             */
            byte[] byte_decode;
            try {
                byte_decode = cipher.doFinal(byte_content);
            }catch (BadPaddingException e){ //wrong key
                return null;
            }
            String AES_decode = new String(byte_decode, "utf-8");
            return AES_decode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        //如果有错就返加nulll
        return null;
    }

    public static String decrypt_setting(String value, int key){
        byte[] aesKey = crazy_operation(key, 0xec4ba7);
        String plainText= AESDecode(aesKey, value);
        if(plainText == null){
            return "";
        }
        return plainText;
    }

    public static byte[] crazy_operation(int key, long constant){
        long[] arr = new long[16];
        Arrays.fill(arr, 0);
        arr[0] = toByte(key);
        arr[1] = toByte(key - 71);
        arr[2] = toByte(key - 142);
        for (int i = 3; i < 16; i++) {
            arr[i] = toByte(i^(0xffffffb9 ^ (arr[i-3]^arr[i-2])));
        }
        if(constant < 2 && constant > -2){
            constant = 0xfffffffffffb389dL + 0xd2dfaf * constant;
        }

        int i = 0;
        long k = -7;
        int larr = arr.length;
        for (int j = 0; j < larr; j++) {
            int k1 = (i+1) & (larr - 1);
            long l1 = constant * arr[k1] +k;
            k = toByte(l1 >> 32);
            long i2 = l1 + k;
            if(i2 < k){
                i2 += 1;
                k+=1;
            }
            arr[k1] = toByte(-2-i2);
            i = k1;
        }

        byte[] res = new byte[arr.length];
        for (int j = 0; j < arr.length; j++) {
            String hex = Long.toHexString(arr[j]);
            res[j] = (byte)Integer.parseInt(hex, 16);
        }


        return res;
    }

    public static long toByte(long n){
        return n & 0xff;
    }

    public static void main(String[] args) {
        String authKeyValue = "4BT54NxufgRSuR1XMQ5ABmp6Xjv6DWg0yqeoUrniNTDM5+Xp7Wsf8mEK+Qtv7AP2+CbJxTLW2qza7Zp39eeRrQ==";
//        int key = bruteForceKey(authKeyValue);
        int key = 238;
        String plainText = decrypt_setting(authKeyValue, key);
        System.out.println(plainText);
//        String[] splits = plainText.split(":");
//        String mid = splits[0];
//        String auth_key = splits[1];

//        String aesKey = crazy_operation(key, 0xec4ba7);
//        System.out.println(aesKey);

//        String plainText= AESDecode(new byte[]{188, 20, 94, 121, 104, 85, 196, 177, 102, 227, 110, 93, 230, 185, 166, 35}, authKeyValue);
    }
}
