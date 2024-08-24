package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.common.code.ErrorCode;
import com.example.demo.common.exception.CustomException;
import com.example.demo.common.constrants.Constants;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.apache.commons.lang3.StringUtils.removeStart;

@Slf4j
@Component
public class JWTProvider {
    private static String secretKey;
    private static String refreshSecretKey;
    private static long tokenTimeForMinute;
    private static long refreshTokenTimeForHour;

    @Value("${token.secret-key}")
    public void setSecretKey(String secretKey) {
        JWTProvider.secretKey = secretKey;
    }

    @Value("${token.refresh-secret-key}")
    public void setRefreshSecretKey(String refreshSecretKey) {
        JWTProvider.refreshSecretKey = refreshSecretKey;
    }

    @Value("${token.token-time}")
    public void setTokenTime(long tokenTime) {
        JWTProvider.tokenTimeForMinute = tokenTime;
    }

    @Value("${token.refresh-token-time}")
    public void setRefreshTokenTime(long refreshTokenTime) {
        JWTProvider.refreshTokenTimeForHour = refreshTokenTime;
    }

    public static String createToken(String userId) {
        return JWT.create()
                .withSubject(userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenTimeForMinute * Constants.ONE_MINUTE_TO_MILLIS))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public static String createRefreshToken(String userId) {
        return JWT.create()
                .withSubject(userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenTimeForHour * Constants.ONE_HOUR_TO_MILLIS))
                .sign(Algorithm.HMAC256(refreshSecretKey));
    }

    public static DecodedJWT checkAccessTokenForRefresh(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
            log.error("access token is not expired. must be expired when request to get new token, userId => {}", decodedJWT.getSubject());
            throw new CustomException(ErrorCode.ACCESS_TOKEN_IS_NOT_EXPIRED);
        } catch (AlgorithmMismatchException | SignatureVerificationException | InvalidClaimException e) {
            throw new CustomException(ErrorCode.TOKEN_IS_INVALID);
        } catch (TokenExpiredException e) {
            return JWT.decode(token);
        }
    }

    public static DecodedJWT decodeAccessTokenAfterVerifying(String token) {
        return decodeTokenAfterVerifying(token, secretKey);
    }

    public static DecodedJWT decodeRefreshTokenAfterVerifying(String token) {
        return decodeTokenAfterVerifying(token, refreshSecretKey);
    }

    public static DecodedJWT decodeTokenAfterVerifying(String token, String secretKey) {
        try {
            return JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
        } catch (AlgorithmMismatchException | SignatureVerificationException | InvalidClaimException e) {
            throw new CustomException(ErrorCode.TOKEN_IS_INVALID);
        } catch (TokenExpiredException e) {
            throw new CustomException(ErrorCode.TOKEN_IS_EXPIRED);
        }
    }

    public static DecodedJWT decodedJWT(String token) {
        return JWT.decode(token);
    }

    public static String getToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        return removeStart(authorization, "Bearer ");
    }
}
