import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
public class Test {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] decryptAES(String ciphertext, String key) {
        try {
//
            // 获取解密密钥
            SecretKeySpec keySpec = new SecretKeySpec(Hex.decodeHex(key.toCharArray()), "AES");
            // 获取Cipher实例
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");

            // 初始化Cipher实例。设置执行模式以及加密密钥
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            // 执行
            byte[] clearTextBytes = cipher.doFinal(Hex.decodeHex(ciphertext.toCharArray()));

            return clearTextBytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String key = "bc145e796855c4b166e36e5de6b9a623";
        String cipertext = "e014f9e0dc6e7e0452b91d57310e40066a7a5e3bfa0d6834caa7a852b9e23530cce7e5e9ed6b1ff2610af90b6fec03f6f826c9c532d6daacdaed9a77f5e791ad";

        System.out.println(new String(decryptAES(cipertext, key)));
    }
}

