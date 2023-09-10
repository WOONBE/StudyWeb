package StudyWeb.config.auth.token;


import StudyWeb.config.auth.UserDetailsImpl;
import StudyWeb.config.auth.UserDetailsServiceImpl;
import StudyWeb.repository.RefreshTokenRepository;
import StudyWeb.service.CookieService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CookieService cookieService;
    private final UserDetailsServiceImpl userDetailsServiceimpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = tokenService.resolveToken(request, "ACCESS");
        String refreshToken = null;

        try {
            if (accessToken != null && !accessToken.equals("undefined") && !accessToken.equals("null")) {
                UserDetailsImpl userDetailsimpl = (UserDetailsImpl) userDetailsServiceimpl.loadUserByUsername(tokenService.getUserEmail(accessToken));
                if (tokenService.validateToken(accessToken, userDetailsimpl)) {
                    log.info("유효");
                    getAuthentication(accessToken);
                } else {
                    log.info("유효x");
                    refreshToken = existRefreshToken(request, refreshToken);
                }
            }else{
                log.info("accessToken x");
                refreshToken = existRefreshToken(request, refreshToken);
            }
        } catch (ExpiredJwtException e) {
            log.info(e.getMessage());
        }

        try {
            if (refreshToken != null) {
                log.info("refresh");
                RefreshToken dbRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken);
                if (dbRefreshToken != null && tokenService.isTokenExpired(dbRefreshToken.getRefreshToken())) {
                    Authentication auth = getAuthentication(dbRefreshToken.getRefreshToken());
                    createAccessToken(response, auth);
                }
            }
        } catch (ExpiredJwtException e) {
            log.info(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(String accessToken) {
        Authentication auth = tokenService.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }

    private void createAccessToken(HttpServletResponse response, Authentication auth) {
        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
        (response).setHeader("X-AUTH-ACCESS-TOKEN", tokenService.createAccessToken(principal.getUsername()));
    }

    private String existRefreshToken(HttpServletRequest request, String refreshToken) {
        Cookie cookie = cookieService.getCookie(request, "X-AUTH-REFRESH-TOKEN");
        if (cookie != null) {
            refreshToken = cookie.getValue();
        }
        return refreshToken;
    }
}