package com.pop.backend.global.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CookieManager {

  private static final boolean HTTP_ONLY = true;
  private static final String SAME_SITE = "Strict";
  private static final String COOKIE_PATH = "/";

  public static void saveCookie(String key, String value, HttpServletResponse response) {
    ResponseCookie cookie = ResponseCookie.from(key, value)
                                          .maxAge(Duration.ofMinutes(30))
                                          .httpOnly(HTTP_ONLY)
                                          .sameSite(SAME_SITE)
                                          .path(COOKIE_PATH)
                                          .build();
    response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
  }

  public static void removeCookie(String key, HttpServletResponse response) {

    ResponseCookie cookie = ResponseCookie.from(key, null)
                                          .maxAge(0L)
                                          .httpOnly(HTTP_ONLY)
                                          .sameSite(SAME_SITE)
                                          .path(COOKIE_PATH)
                                          .build();
    response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
  }

  public static String getCookie(String key, HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (Objects.isNull(cookies)) {
      return null;
    }
    return Arrays.stream(cookies)
                 .filter(cookie -> cookie.getName().equals(key))
                 .map(Cookie::getValue)
                 .findFirst()
                 .orElse(null);
  }

}