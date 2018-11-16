package com.zhuoyue.researchManement.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5Utils {

    private static final String hashAlgorithmName = "MD5";
    private static final int hashIterations = 3; // 加密次数

    public static String encrypt(String uname, String password) {
        return new SimpleHash(hashAlgorithmName, password, uname, hashIterations).toHex();
    }

}
