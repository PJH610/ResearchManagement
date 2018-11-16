package com.zhuoyue.researchManement.util;

import com.zhuoyue.researchManement.annotation.SystemControllerLog;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加解加密工具类
 */
public class RSAUtils {

    public static final String SECURITY = "RSA"; // 加密方式
    public static final String ALGORITHM = "MD5withRSA"; // 加密算法
    public static final String KEYPAIR = "keypair";
    public static final String PUBLIC_KEY = "RSAPublicKey"; // 公钥
    public static final String PRIVATE_KEY = "RSAPrivateKey"; // 私钥

    /**
     * 获取密钥
     * @return
     */
    public static Map<String, Object> getKey() {
        Map<String, Object> map = null;
        try {
            // 生成实现指定算法的KeyPairGenerator对象，用于生成密钥对
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(SECURITY, new BouncyCastleProvider());
            keyPairGenerator.initialize(1024); // 初始化密钥长度
            KeyPair keyPair = keyPairGenerator.generateKeyPair(); // 生成密钥对
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic(); // 获取公钥
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate(); // 获取私钥

            // 保存在map中
            map = new HashMap<String, Object>();
            map.put(PUBLIC_KEY, rsaPublicKey);
            map.put(PRIVATE_KEY, rsaPrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 利用私钥进行解密
     * @param privateKey 私钥
     * @param str 密文
     * @return
     */
    public static String decrypt(RSAPrivateKey privateKey, String str) {
        try {
            System.out.println("密文为：" + str);
            // 获取实现指定转换的Cipher对象
            Cipher cipher = Cipher.getInstance("RSA/NONE/NoPadding", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey); // 用密钥初始化此Cipher对象

            int blockSize = cipher.getBlockSize(); // 返回块的大小
            byte[] bytes = hexStringToBytes(str); // 将十六进制转换为二进制
            int j = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (bytes.length - j * blockSize > 0) { // 将二进制数据分块写入ByteArrayOutputStream中
                baos.write(cipher.doFinal(bytes, j * blockSize, blockSize));
                j++;
            }

            // 将二进制数据转换为字符串
            byte[] bs = baos.toByteArray();
            StringBuilder sb = new StringBuilder();
            sb.append(new String(bs));
            String pwd = sb.reverse().toString();
            System.out.println("明文为：" + pwd);
            return pwd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将十六进制字符串转换为二进制数组
     * @param hexString 十六进制字符串
     * @return
     */
    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }

        hexString = hexString.toUpperCase(); // 全部转换为大写字符
        int length = hexString.length() / 2; // 获取十六进制数据个数
        char[] hexChars = hexString.toCharArray(); // 将十六进制字符串转换为字符数组
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2; // 开始位置
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char ch) {
        return (byte) "0123456789ABCDEF".indexOf(ch);
    }
}
