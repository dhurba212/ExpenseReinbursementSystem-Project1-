package dev.ghimire.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {

    private static final String secret = "";
    private static final Algorithm algorithm = Algorithm.HMAC256(secret);
    public static String generateToken(String role,String username, int id)
    {
        String token = JWT.create()
                .withClaim("role",role)
                .withClaim("username",username)
                .withClaim("id",id)
                .sign(algorithm);

        return token;
    }

    public static DecodedJWT verifyJwtToken(String token)
    {
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
        return jwt;

    }

}
