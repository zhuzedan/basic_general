package org.zzd.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @apiNote http工具
 * @author zzd
 * @date 2023-07-13 16:30  
 */
public class HttpUtils {

    public static String getIpAddress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");

        //多次反向代理后会有多个ip值，第一个ip才是真实ip
        if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            int index = XFor.indexOf(",");
            if (index != -1) {
                return "0:0:0:0:0:0:0:1".equals(XFor.substring(0, index)) ? "127.0.0.1" : XFor.substring(0, index);
            } else {
                return "0:0:0:0:0:0:0:1".equals(XFor) ? "127.0.0.1" : XFor;
            }
        }
        XFor = Xip;
        if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor))
            return "0:0:0:0:0:0:0:1".equals(XFor) ? "127.0.0.1" : XFor;

        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor))
            XFor = request.getHeader("Proxy-Client-IP");

        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor))
            XFor = request.getHeader("WL-Proxy-Client-IP");

        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor))
            XFor = request.getHeader("HTTP_CLIENT_IP");

        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor))
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");

        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor))
            XFor = request.getRemoteAddr();
        return "0:0:0:0:0:0:0:1".equals(XFor) ? "127.0.0.1" : XFor;
    }

    public static HttpServletRequest getHttpServletRequest() {
        // 普通写法
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
        // 极简写法
        // return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}