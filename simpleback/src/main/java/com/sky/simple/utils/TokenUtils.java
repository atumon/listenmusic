package com.sky.simple.utils;

import com.sky.simple.domain.UserToken;
import com.sky.simple.utils.Consts;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class TokenUtils {

    /**
     * 生成token
     * @param userToken
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(UserToken userToken, int expire) throws Exception{
        JwtClaims claims = new JwtClaims();

        claims.setSubject(userToken.getName());
        claims.setClaim(Consts.CONTEXT_USER_ID,userToken.getId());

//        claims.setClaim(Consts.CONTEXT_USER_NAME,userToken.getName());
        claims.setExpirationTimeMinutesInTheFuture(expire == 0 ? 60*24 : expire);

        Key key = new HmacKey(Consts.JWT_PRIVATE_KEY.getBytes("UTF-8"));

        JsonWebSignature jws = new JsonWebSignature();

        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(key);
        jws.setDoKeyValidation(false);

        String token = jws.getCompactSerialization();

        return token;
    }

    /**
     * 解析token
     * @param token
     * @return
     * @throws Exception
     */
    public static UserToken getInfoFromToken(String token) throws Exception {

        if (token == null) {
            return null;
        }

        Key key = new HmacKey(Consts.JWT_PRIVATE_KEY.getBytes("UTF-8"));

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setRequireSubject()
                .setVerificationKey(key)
                .setRelaxVerificationKeyValidation() // relaxes key length requirement
                .build();

        JwtClaims processedClaims = jwtConsumer.processToClaims(token);
        String UserId = processedClaims.getClaimValue(Consts.CONTEXT_USER_ID).toString();

        return new UserToken(
                Long.parseLong(UserId),
                processedClaims.getSubject());
    }


//    //用法
//    public static void main(String[] agars) throws Exception {
//        UserToken userToken=new UserToken("123","admin");
//        String token = generateToken(userToken, 0);
//        System.out.println(token);
//        UserToken infoFromToken = getInfoFromToken(token);
//        System.out.println(infoFromToken.toString());
//    }

}
