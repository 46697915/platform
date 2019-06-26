package test;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String JWTSECRET = "myScrect";

    /**
     * 生成jwt
     *
     * @param userId
     * @return
     */
//    @GetMapping("/jwt/create/{userId}")
    public static String createToken(String userId) {
        String token = Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .claim("user_name", userId)
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000 * 365))
                .signWith(SignatureAlgorithm.HS512, JWTSECRET)
                .compact();
        return token;
    }

    //解析jwt
//    @GetMapping("/jwt/verify/{token}")
    public static String verifyToken( String token) {
        String user = Jwts.parser()
                .setSigningKey(JWTSECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return user;
    }

    public static void main(String[] args) {
        String token = createToken("abc");
        System.out.println(token);
        System.out.println(verifyToken(token));
    }
}