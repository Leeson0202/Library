package cool.leeson.library.exceptions;

import cool.leeson.library.util.ResMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
    /**
     * 全局异常处理 返回json要加@ResponseBody
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> defaultHandel(Exception p) {
//        p.printStackTrace();
        return new ResMap().putCode(500).putMsg("服务器错误").build();
    }

    /**
     * 全局异常处理 返回json要加@ResponseBody
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Object handle(MyException p) {
        return new ResMap().putCode(p.getCode())
                .putMsg(p.getMsg()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handle(MethodArgumentNotValidException e) {
        return ResMap.err(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Object handle(BindException e) {
        return ResMap.err(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Object handle(ConstraintViolationException e) {
        StringBuffer sb = new StringBuffer();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            sb.append(violation.getMessage());
        }
        return ResMap.err(sb.toString());

    }

}
