package org.zzd.utils;

import org.springframework.stereotype.Component;

/**
 * @author :zzd
 * @apiNote :线程全局
 * @date : 2023-03-05 16:50
 */
@Component
public class ThreadLocalUtil {
    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void setUsername(String username) {
        THREAD_LOCAL.set(username);
    }

    public static String getUsername() {
        return THREAD_LOCAL.get();
    }
}
