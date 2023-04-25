package cool.leeson.lib.exceptions;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MyException extends Exception {
    private Integer code;
    private String msg;

    public enum STATUS {
        ok(200, "请求成功"),
        requestErr(400,"请求出现问题"),
        badToken(401, "token失效"),
        noToken(402, "没有token"),
        err(500, "服务器内部错误");
        private final Integer code;
        private final String msg;

        STATUS(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return this.code;
        }

        public String getMsg() {
            return this.msg;
        }

        @Override
        public String toString() {
            return "{code: " + this.code + ", msg: " + msg + "}";
        }
    }

    public MyException(STATUS status) {
        this.code = status.code;
        this.msg = status.msg;
    }

    public MyException(String msg) {
        this.code = 400;
        this.msg = msg;
    }


}
