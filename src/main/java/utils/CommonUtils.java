package utils;

public class CommonUtils {
    // return null 还是 empty, return null 的话可以代表大部分情况，但是调用的地方可能要加一层判断， 目前先return null
    public static byte[] convertHexToByte(String hexString){
        if(isNullEmpty(hexString))
            return null;
        if(hexString.length() %2 ==1)
            return null;
        byte[] res = new byte[hexString.length()/2];
        for (int i = 0; i < hexString.length(); i+=2) {
            res[i/2] = (byte)Integer.parseInt(hexString.substring(i, i+2), 16);
        }
        return res;
    }

    public static boolean isNullEmpty(String s){
        return s == null || s.length() == 0;
    }
}
