package com.orkva.xmall.inventory.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * JsonResult
 *
 * @author Shepherd Xie
 * @version 2023/8/8
 */
public record JsonResult<T>(boolean success, Status status, T payloads, String messages) {

    @Getter
    @ToString
    @AllArgsConstructor
    public enum Status {
        OK(200, "ok", "ok"),
        FAIL(400, "fail", "fail"),
        ERROR(500, "error", "error");

        private final int code;
        private final String name;
        private final String value;
    }

    public static <T> JsonResult<T> of(boolean success, Status status, T payloads, String messages) {
        return new JsonResult<>(success, status, payloads, messages);
    }

    public static <T> JsonResult<T> ok(Status status, T payloads, String messages) {
        return of(true, status, payloads, messages);
    }

    public static <T> JsonResult<T> ok(T payloads) {
        return ok(Status.OK,payloads, null);
    }

    public static <T> JsonResult<T> ok() {
        return ok(null);
    }

    public static <T> JsonResult<T> fail(Status status, T payloads, String messages) {
        return of(false, status, payloads, messages);
    }

    public static <T> JsonResult<T> fail(T payloads, String messages) {
        return of(false, Status.FAIL, payloads, messages);
    }

}
