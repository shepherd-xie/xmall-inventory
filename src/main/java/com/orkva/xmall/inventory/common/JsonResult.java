package com.orkva.xmall.inventory.common;

/**
 * JsonResult
 *
 * @author Shepherd Xie
 * @version 2023/8/8
 */
public record JsonResult<T>(int code, T payloads, String messages) {

    public static <T> JsonResult<T> of(int code, T payloads, String messages) {
        return new JsonResult<>(code, payloads, messages);
    }

    public static <T> JsonResult<T> ok() {
        return new JsonResult<>(0, null, null);
    }

    public static <T> JsonResult<T> ok(T payloads) {
        return new JsonResult<>(0, payloads, null);
    }

}
