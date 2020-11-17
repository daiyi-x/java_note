import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;

public class AES256_2 {

    public static void main(String[] args) {
        try {
            main1(args);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
    public static void main1(String[] args) throws InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        long[] arr = {188, 20, 94, 121, 104, 85, 196, 177, 102, 227, 110, 93, 230, 185, 166, 35};
        char[] password = new char[arr.length];
        for (int i = 0; i < password.length; i++) {
            password[i] = (char)arr[i];
        }

        KeySpec spec = new PBEKeySpec(password, "feacbc02a3a697b0".getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");


//        /* Encrypt the message. */
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, secret);
//        AlgorithmParameters params = cipher.getParameters();
//        byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
//        byte[] ciphertext = cipher.doFinal("Hello, World!".getBytes(StandardCharsets.UTF_8));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        AlgorithmParameters params = cipher.getParameters();
        byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
        String plaintext = new String(cipher.doFinal("4BT54NxufgRSuR1XMQ5ABmp6Xjv6DWg0yqeoUrniNTDM5+Xp7Wsf8mEK+Qtv7AP2+CbJxTLW2qza7Zp39eeRrQ==".getBytes()), StandardCharsets.UTF_8);
        System.out.println(plaintext);
    }
}
