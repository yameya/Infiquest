package com.myapps.infiquest.core;

import org.apache.logging.log4j.core.util.Throwables;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA512;

/**
 * Created by AYellapurkar on 06-09-2017.
 */
public class UserService
{

    private  byte[] secretKey = null;
    private static UserService userService = null;

    public static UserService getInstance()
    {
        return userService;
    }

    public static void createInstance(byte[] secretKey)
    {
        if(userService == null)
        {
            userService = new UserService(secretKey);
        }
    }

    private UserService()
    {

    }

    private UserService(byte[] secretKey)
    {
        this.secretKey = secretKey;
    }

    public String generateJtokenForUser(Long userId)
    {
        JwtClaims jwtClaim = getClaimsForUser(String.valueOf(userId));
        return toToken(secretKey,jwtClaim);
    }

    private JwtClaims getClaimsForUser(String userId) {
        final JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(5);
        claims.setSubject(userId);
        //claims.setIssuer("Infiquest");
        //claims.setAudience("Users");
        return claims;
    }

    private String toToken(byte[] key, JwtClaims claims) {
        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA512);
        jws.setKey(new HmacKey(key));
        jws.setDoKeyValidation(false);

        try {
            return jws.getCompactSerialization();
        }
        catch (JoseException e)
        {
           throw new WebApplicationException(e);
        }
    }
}
