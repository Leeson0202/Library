package cool.leeson.library.exceptions;

import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
    /**
     * 全局异常处理 返回json要加@ResponseBody
     *
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHandler(Exception p) {
        p.printStackTrace();
        return new ResMap().putCode(500).putMsg("服务器错误").build();
    }

    /**
     * 全局异常处理 返回json要加@ResponseBody
     *
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Map<String, Object> MyExceptionHandler(MyException p) {
        return new ResMap().putCode(p.getCode())
                .putMsg(p.getMsg()).build();
    }


}
