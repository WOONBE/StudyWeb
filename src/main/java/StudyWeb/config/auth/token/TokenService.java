package StudyWeb.config.auth.token;

import StudyWeb.config.auth.UserDetailsImpl;
import StudyWeb.config.auth.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application-auth")
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.app.auth.accessTokenExpiry}")
    private long accessTokenValidTime;

    @Value("${jwt.app.auth.refreshTokenExpiry}")
    private long refreshTokenValidTime;

    private final UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String email, long time) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createTestToken(String key, String email, long time) {
        Claims claims = Jwts.claims().setSubject(email);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public String createAccessToken(String email) {
        return createToken(email, accessTokenValidTime);
    }

    public String createRefreshToken(String email) {
        return createToken(email, refreshTokenValidTime);
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Authentication getTestAuthentication(String token, String key) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getTestUserEmail(token, key));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserEmail(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch(ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    public String getTestUserEmail(String token, String key) {
        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
        } catch(ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    public String resolveToken(HttpServletRequest req, String name) {
        return req.getHeader("X-AUTH-" + name + "-TOKEN");
    }

    public Boolean isTokenExpired(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    public Boolean isTestTokenExpired(String token, String key) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    public boolean validateToken(String token, UserDetailsImpl userDetailsimpl) {
        try {
            String email = getUserEmail(token);

            return email.equals(userDetailsimpl.getUsername())&& isTokenExpired(token);
        } catch(Exception e) {
            return false;
        }
    }

    public boolean validateTestToken(String token, UserDetailsImpl userDetailsimpl, String key) {
        try {
            String email = getTestUserEmail(token, key);

            return email.equals(userDetailsimpl.getUsername())&& isTestTokenExpired(token, key);
        } catch(Exception e) {
            return false;
        }
    }
}