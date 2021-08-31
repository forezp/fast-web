package io.github.forezp.fastwebcommon.dto;



import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.io.Serializable;

import static io.github.forezp.fastwebcommon.constant.RequestConstant.REQUEST_ID;


public class RespDTO<T> implements Serializable {


    public int code = 0;
    public String message = "";
    public T data;
    public String requestId;

    public static RespDTO onSuc(Object data) {
        RespDTO resp = new RespDTO();
        String requestId = MDC.get(REQUEST_ID);
        if (!StringUtils.isEmpty(requestId)) {
            resp.requestId = requestId;
        }
        resp.message="sucess";
        resp.data = data;
        return resp;
    }

    @Override
    public String toString() {
        return "RespDTO{" +
                "code=" + code +
                ", error='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
