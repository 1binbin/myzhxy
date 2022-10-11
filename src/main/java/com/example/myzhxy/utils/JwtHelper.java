package com.example.myzhxy.utils;

import io.jsonwebtoken.*;

import java.util.Date;

/**
 * token口令生成JwtHelper
 *
 * @Author hongxiaobin
 * @Time 2022/10/11-11:22
 */
public class JwtHelper {
    private static long tokenExpiration = 20 * 60 * 60 * 1000;
    private static String tokenSignKey = "123456";

    /**
     * 生成token字符串
     *
     * @Param: Long userId, Integer userType
     * @Return: String
     */
    public static String createToken(Long userId, Integer userType) {
        String token = Jwts.builder()
                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("userType", userType)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 从Token字符串获取userid
     *
     * @Param: String token
     * @Return: Long
     */
    public static Long getUserId(String token) {
        if (token.isEmpty()) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        Integer userId = (Integer) body.get("userId");
        return userId.longValue();
    }

    /**
     * 从token字符串获取userType
     *
     * @Param: String token
     * @Return: Integer
     */
    public static Integer getUserType(String token) {
        if (token.isEmpty()) {
            return null;
        }
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (Integer) (claims.get("userType"));
    }

    /**
     * 从token字符串获取username
     *
     * @Param: String token
     * @Return: String
     */
    public static String getUserName(String token) {
        if (token.isEmpty()) {
            return "";
        }
        Jws<Claims> claimsJws
                = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("userName");
    }

    /**
     * 判读token是否有效
     *
     * @Param: String token
     * @Return: boolean
     */
    public static boolean isExpiration(String token) {
        try {
            boolean isExpiration = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
            return isExpiration;
        } catch (Exception e) {
//            过期出现异常，返回true
            return true;
        }
    }

    /**
     * 刷新token
     *
     * @Param: String token
     * @Return: String
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody();
            refreshedToken = JwtHelper.createToken(getUserId(token), getUserType(token));
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public static void main(String[] args) {
        String token = JwtHelper.createToken(1L, 1);
        System.out.println(token);
//        System.out.println(JwtHelper.getUserId(token));
//        System.out.println(JwtHelper.getUserName(token));
    }
}
