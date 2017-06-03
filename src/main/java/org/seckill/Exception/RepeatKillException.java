package org.seckill.Exception;

/**
 * 重复秒杀异常（运行期异常）
 * Created by Eddie on 2017/6/3.
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
