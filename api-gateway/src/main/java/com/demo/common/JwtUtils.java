package com.demo.common;

import com.demo.common.exception.BizException;
import com.demo.common.exception.ErrorCode;
import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtUtils {
	
	private static final String jwtSecurit = "81212df7fc3a34e26a123234dq1245daa";

	public static String createJWT(String id, String subject, long ttlMillis,String issuer) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey secretKey = generalKey();
		JwtBuilder builder = Jwts.builder()
				.setId(id)
				.setSubject(subject)   // 主题
				.setIssuer(issuer)     // 签发者
				.setIssuedAt(now)      // 签发时间
				.signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date expDate = new Date(expMillis);
			builder.setExpiration(expDate); // 过期时间
		}
		return builder.compact();
	}
	/**
	 * 验证JWT
	 * 
	 * @param jwtStr
	 * @return
	 */
	public static Claims validateJWT(String jwtStr) {
		Claims claims ;
		try {
			claims = parseJWT(jwtStr);
			return claims;
		} catch (ExpiredJwtException e) {
			throw new BizException(ErrorCode.TOKEN_EXPIRED,e.getMessage());
		} catch (SignatureException e) {
			throw new BizException(ErrorCode.TOKEN_CHECK_FAILED,e.getMessage());
		} catch (Exception e) {
			throw new BizException(ErrorCode.TOKEN_CHECK_FAILED,e.getMessage());
		}
	}
	
	public static SecretKey generalKey() {
		byte[] encodedKey = Base64.decode(jwtSecurit);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
	    return key;
	}
	
	/**
	 * 
	 * 解析JWT字符串
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public static Claims parseJWT(String jwt)  {
		SecretKey secretKey = generalKey();
		return Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(jwt)
			.getBody();
	}
	
	public static String getUserName(String jwt) {
		Claims claims;
		try {
			claims = parseJWT(jwt);
			return claims.getSubject();
		} catch (ExpiredJwtException e) {
			throw new BizException(ErrorCode.TOKEN_EXPIRED,e.getMessage());
		} catch (SignatureException e) {
			throw new BizException(ErrorCode.TOKEN_CHECK_FAILED,e.getMessage());
		} catch (Exception e) {
			throw new BizException(ErrorCode.TOKEN_CHECK_FAILED,e.getMessage());
		}
	}

}
