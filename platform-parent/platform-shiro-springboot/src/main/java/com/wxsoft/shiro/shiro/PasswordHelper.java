package com.wxsoft.shiro.shiro;

import com.wxsoft.shiro.business.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    //这些要与Realm中的一致
    private static String algorithmName = "md5";
    //hash次数
    private final static int hashIterations = 2;

    /**
     * 给密码加盐
     * @param user
     */
    static public  void encryptPassword(User user) {
        //加盐
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(algorithmName, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }
    public static void main(String[] args) {
        User user = new User();
        user.setPassword("admin");
        encryptPassword(user);
        System.out.println(user);
    }
}
