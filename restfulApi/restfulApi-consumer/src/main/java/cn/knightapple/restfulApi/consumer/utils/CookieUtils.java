package cn.knightapple.restfulApi.consumer.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {
    static public String getCookie(String key, HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies==null) {
            return "";
        }else{
            for(int i=0;i<cookies.length;i++)
            {
                if(cookies[i].getName().equals(key))
                {
                    return cookies[i].getValue();
                }
            }
            return "";
        }
    }
}
