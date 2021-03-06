package io.github.forezp.fastwebcommon.exception;


import io.github.forezp.fastwebcommon.dto.RespDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常统一处理
 */
@ControllerAdvice
@ResponseBody
public class FastwebExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(FastwebExceptionHandler.class);


    @ExceptionHandler(FastwebException.class)
    public ResponseEntity<RespDTO> handleException(Exception e) {
        RespDTO resp = new RespDTO();
        FastwebException exception = (FastwebException) e;
        resp.code = exception.getCode();
        resp.message = e.getMessage();
        //logger.info("user:{} | result: {} ", UserUtils.getCurrentPrinciple(), resp.toString());
        return new ResponseEntity(resp, HttpStatus.OK);
    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RespDTO> handleHttpMessageNotReadableException() {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(getRespDTOByException(status), status);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RespDTO> handleHttpRequestMethodNotSupportedException() {

        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        return new ResponseEntity(getRespDTOByException(status), status);
    }


    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<RespDTO> handleHttpMediaTypeNotSupportedException() {

        HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        return new ResponseEntity(getRespDTOByException(status), status);
    }

   
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespDTO> exception(Exception e) {
        e.printStackTrace();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity(getRespDTOByException(status), status);
    }

    private RespDTO getRespDTOByException(HttpStatus status) {

        RespDTO resp = new RespDTO();
        resp.code = status.value();
        resp.message = status.getReasonPhrase();
        return resp;
    }
}
