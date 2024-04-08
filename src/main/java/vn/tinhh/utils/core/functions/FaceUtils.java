package vn.tinhh.utils.core.functions;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FaceUtils {
    public static String getContextPath() {
        return getRequest().getContextPath();
    }

    public static HttpServletRequest getRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (null == facesContext) return null;
        ExternalContext externalContext = facesContext.getExternalContext();
        if (null == externalContext) return null;
        return (HttpServletRequest) externalContext.getRequest();
    }

    public static HttpServletResponse getResponse() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (null == facesContext) return null;
        ExternalContext externalContext = facesContext.getExternalContext();
        if (null == externalContext) return null;
        return (HttpServletResponse) externalContext.getResponse();
    }

    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        if (null != request) return getRequest().getSession();
        return null;
    }

    public static String getCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) return cookie.getValue();
            }
        }
        return null;
    }

    public static String getCookie(String name) {
        return getCookie(name, FaceUtils.getRequest());
    }

    public static void setCookie(String name, String value, HttpServletResponse response) {
        HttpServletRequest request = getRequest();
        Cookie cookie = new Cookie(name, value);
        setCookie(cookie, response);
    }

    public static void setCookie(Cookie cookie, HttpServletResponse response) {
        response.addCookie(cookie);
    }

    public static void setCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 12 * 30);
        setCookie(cookie, getResponse());
    }

    public static void setCookie(String name, String value, String domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 12 * 30);
        cookie.setDomain(domain);
        setCookie(cookie, getResponse());
    }

    public static void setCookie(Cookie cookie) {
        getResponse().addCookie(cookie);
    }

    public static void removeCookies(HttpServletRequest request,
                                     HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            removeCookie(response, cookie.getName());
        }
    }

    public static void removeCookie(HttpServletResponse response,
                                    String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
