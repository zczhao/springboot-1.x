package zzc.springboot.utils;

import zzc.springboot.enums.ResultEnum;
import zzc.springboot.vo.Result;

public class ResultUtil {

    public static Result success() {
        return new Result(ResultEnum.SUCCESS, null);
    }

    public static Result success(Object object) {
        return new Result(ResultEnum.SUCCESS, object);
    }

    public static Result error(ResultEnum resultEnum) {
        return new Result(resultEnum, null);
    }
    public static Result error(Integer code, String msg) {
        return new Result(code, msg, null);
    }
}
