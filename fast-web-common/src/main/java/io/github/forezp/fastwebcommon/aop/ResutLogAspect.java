package io.github.forezp.fastwebcommon.aop;


import com.alibaba.fastjson.JSON;

import io.github.forezp.fastwebcommon.dto.RespDTO;

import io.github.forezp.fastwebcommon.exception.FastwebException;
import io.github.forezp.fastwebcommon.request.RequestHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static io.github.forezp.fastwebcommon.request.RequestHolder.RESP_CODE;
import static io.github.forezp.fastwebcommon.request.RequestHolder.RESP_DTO;


/**
 * Created by fangzhipeng on 2017/8/25.
 */
@Component
@Aspect
public class ResutLogAspect {

    private Logger logger = LoggerFactory.getLogger(ResutLogAspect.class);

    @Pointcut("execution(public io.github.forezp.fastwebcommon.dto.RespDTO *(..))")
    public void loggerPointCut() {

    }

    @Around("loggerPointCut()")
    public RespDTO handleRespResult(ProceedingJoinPoint joinPoint) throws Exception {

        RespDTO respDTO = null;
        try {
            respDTO = (RespDTO) joinPoint.proceed();
        } catch (Throwable throwable) {
            if (throwable instanceof FastwebException) {
                FastwebException e = (FastwebException) throwable;
                throw e;
            }

            if (throwable instanceof Exception) {
                Exception e = (Exception) throwable;
                throw e;
            }
        }
        if (respDTO != null) {
//            logger.debug("user:{} | result: {} ", UserUtils.getCurrentUserWithDefault(), respDTO.toString());
            RequestHolder.get().putIfAbsent(RESP_DTO, JSON.toJSONString(respDTO));
            RequestHolder.get().putIfAbsent(RESP_CODE, respDTO.code);
        }
        return respDTO;

    }


}
