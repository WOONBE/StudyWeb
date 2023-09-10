package StudyWeb.service;

import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class CookieService {

    private int refreshCookieValidTime = 30 * 60 * 2 * 24 * 14 + (30 * 60 * 2 * 9); //14일, (기준 UTC + 9)

    public ResponseCookie createCookie(String cookieName, String value) {
        ResponseCookie cookie = ResponseCookie.from(cookieName, value)
                .path("/")
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .maxAge(refreshCookieValidTime)
                .build();
        return cookie;
    }

    public ResponseCookie deleteCookie(String cookieName) {
        ResponseCookie cookie = ResponseCookie.from(cookieName, null)
                .path("/")
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .maxAge(0)
                .build();
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest req, String cookieName) {
        final Cookie[] cookies = req.getCookies();
        if (cookies == null)
            return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName))
                return cookie;
        }
        return null;
    }
}