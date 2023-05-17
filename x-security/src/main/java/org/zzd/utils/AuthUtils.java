package org.zzd.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.zzd.exception.ResponseException;
import org.zzd.result.ResultCodeEnum;

/**
 * @author :zzd
 * @date : 2023-02-15 14:25
 */
@Component
public class AuthUtils {
    /**
     * @apiNote 获取系统用户名称
     * @return java.lang.String
     */
    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new ResponseException(ResultCodeEnum.UNAUTHORIZED);
        }
        // 用户名或密码错误的时候，执行doLogin方法，所以没有存入SecurityContextHolder，所以authentication没有值，查出来的的就是anonymousUser
        if ("anonymousUser".equals(authentication.getPrincipal())) {
            return "匿名用户";
        }
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        throw new ResponseException("找不到当前登录信息");
    }

    /**
     * @apiNote 获取当前用户id
     * @return java.lang.Long
     */
    public static Long getUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

}
