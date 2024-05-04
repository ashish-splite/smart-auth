package com.smart.smartauth.smartauth.common;

import java.util.Date;

public interface JwtService {
    public String generateToken(String userName);
    public String extractUsername(String token);
    public Date extractExpiration(String token);
    public Boolean isTokenExpired(String token);
    public Boolean validateToken(String token);
}
