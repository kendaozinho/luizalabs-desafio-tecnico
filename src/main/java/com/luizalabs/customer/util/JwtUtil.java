package com.luizalabs.customer.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;

public final class JwtUtil {
  private JwtUtil() {
  }

  public static String getEncodedJwt(String jwtSecretKey) {
    return "Bearer " +
        Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .addClaims(new HashMap<String, Object>() {
              private static final long serialVersionUID = 1L;

              {
                put("foo", "bar");
              }
            }).signWith(SignatureAlgorithm.HS256, jwtSecretKey.getBytes())
            .compact();
  }

  public static Boolean isValidJwt(String jwtSecretKey, String authorization) {
    try {
      Jwts.parser()
          .setSigningKey(jwtSecretKey.getBytes())
          .parseClaimsJws(authorization.replace("Bearer ", ""));
      return true;
    } catch (Throwable t) {
      return false;
    }
  }
}
