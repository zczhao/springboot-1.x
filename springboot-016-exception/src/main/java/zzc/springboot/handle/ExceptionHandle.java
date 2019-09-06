package zzc.springboot.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import zzc.springboot.enums.ResultEnum;
import zzc.springboot.exception.GirlException;
import zzc.springboot.utils.ResultUtil;
import zzc.springboot.vo.Result;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if(e instanceof GirlException) {
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        }
        logger.error("[系统异常]", e);
        return ResultUtil.error(ResultEnum.UNKONW_ERROR);
    }

}
