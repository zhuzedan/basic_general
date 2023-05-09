package org.zzd.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author :zzd
 * @date : 2023-02-15 14:25
 */
@Component
public class AuthUtils {
    /**
     * @apiNote 获取当前登录用户名
     * @return java.lang.String
     */
    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * @apiNote 获取当前用户id
     * @return java.lang.Long
     */
    public static Long getUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}
