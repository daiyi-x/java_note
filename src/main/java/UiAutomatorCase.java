
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

public class UiAutomatorCase {

    private static int[] arr = new int[1];

    public static void main(String[] args) {
//        try{
//            System.out.println(arr[1]);
//        }catch (IndexOutOfBoundsException e){
//            System.out.println(e);
//            System.out.println(arr[1]);
//        }finally {
//            System.out.println("final");
//        }
        int[] arr = new int[]{242, 74, 148, 105, 132, 239, 152, 231, 112, 81, 94, 65, 172, 85, 64, 45};
//        System.out.println(bytesToHex(arr));
        char[] cArr = new char[arr.length];
        for (int i = 0; i < cArr.length; i++) {
            cArr[i] = (char) arr[i];
            System.out.println(cArr[i]);
        }
//        System.out.println(encrypt(cArr.toString(),"NtJODO1bDjQoAntDDG2F"));
        System.out.println(decrypt("NtJODO1bDjQoAntD".getBytes(), getKey(cArr.toString())));

    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(int[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static final String encrypt(String key, String plainText) {
        Key secretKey = getKey(key);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] p = plainText.getBytes("UTF-8");
            byte[] result = cipher.doFinal(p);
            BASE64Encoder encoder = new BASE64Encoder();
            String encoded = encoder.encode(result);
            return encoded;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Key getKey(String keySeed) {
        if (keySeed == null) {
            keySeed = System.getenv("AES_SYS_KEY");
        }
        if (keySeed == null) {
            keySeed = System.getProperty("AES_SYS_KEY");
        }
        if (keySeed == null || keySeed.trim().length() == 0) {
            keySeed = "abcd1234!@#$";// 默认种子
        }
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(keySeed.getBytes());
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String decrypt(byte[] cipherText, Key k) {
        byte[] sourceText = null;

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, k); // 操作模式为解密,key为密钥
            sourceText = cipher.doFinal(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(sourceText);

    }

    // 生成密钥
    public Key keyGenerator() {
        Key key = null;
        try {
            // 初始化密钥key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(56); // 选择DES算法,密钥长度必须为56位
            key = keyGenerator.generateKey(); // 生成密钥
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return key;
    }

}
