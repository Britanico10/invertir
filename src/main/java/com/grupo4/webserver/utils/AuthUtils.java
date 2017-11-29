package com.grupo4.webserver.utils;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.grupo4.inversiones.Usuario;

public class AuthUtils {
	
	private static String SECRET = "chinasuarez";
	
	public static Long validarToken(String token) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(SECRET);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    return Long.valueOf(jwt.getSubject());
		    
		} catch (UnsupportedEncodingException exception){
		    //UTF-8 encoding not supported
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
		}
		return -1L;
	}
	
	/**
	 * este metodo retorna un token basado en el userid del usuario usando jwt:
	 * @see https://github.com/auth0/java-jwt/blob/master/README.md 
	 * @param user
	 * @return
	 */
	
	public static String generarToken(Usuario user) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(SECRET);
		    return JWT.create()
		        .withSubject(user.getId().toString())
		        .sign(algorithm);
		} catch (UnsupportedEncodingException exception){
		    //UTF-8 encoding not supported
			return "";
		} catch (JWTCreationException exception){
		    //Invalid Signing configuration / Couldn't convert Claims.
			return "";
		}
	}

}
