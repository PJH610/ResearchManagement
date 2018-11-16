package util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Test {

    private static final int hashIterations = 3; // 加密次数

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        String credentials = "123456";
        String salt = "user2-2";
        Object obj = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(((SimpleHash) obj).toHex());
    }

}
