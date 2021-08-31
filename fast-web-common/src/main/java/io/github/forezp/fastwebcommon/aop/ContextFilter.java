package io.github.forezp.fastwebcommon.aop;


import io.github.forezp.fastwebcommon.constant.ApiConstants;
import io.github.forezp.fastwebcommon.request.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

import static io.github.forezp.fastwebcommon.request.RequestHolder.*;


/**
 * Created by forezp on 2018/8/6.
 */

@Component
@WebFilter(urlPatterns = "/*", filterName = "contextFilter")
@Slf4j
public class ContextFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();
        if (ApiConstants.HTTP_METHOD_OPTIONS.equals(method)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            initRequestHolder(httpServletRequest);
            String uri = httpServletRequest.getRequestURI();
            log.info("requst start ," + method + " " + uri);
            long starTime = System.currentTimeMillis();
            filterChain.doFilter(servletRequest, servletResponse);
            Long duration = System.currentTimeMillis() - starTime;
            log.info("requst end ," + uri + " request takes:" + duration + "ms");

            clearRequestHolder();
        }

    }

    private String getRequestId(HttpServletRequest httpServletRequest) {
        String requestId = httpServletRequest.getHeader("requestId");
        if (StringUtils.isEmpty(requestId)) {
            requestId = UUID.randomUUID().toString();
        }
        return requestId;
    }

    private void clearRequestHolder() {
        MDC.clear();
        RequestHolder.remove();
    }

    private void initRequestHolder(HttpServletRequest httpServletRequest) {

        MDC.put(REQUEST_ID, getRequestId(httpServletRequest));
//        String token = httpServletRequest.getHeader(AURHORIZATION);
//        if (StringUtils.isEmpty(token)) {
//            token = httpServletRequest.getHeader(UPPER_AURHORIZATION);
//        }
        RequestHolder.get().putIfAbsent(REQUEST_SERVLET, httpServletRequest);
//        if (!StringUtils.isEmpty(token)) {
//            RequestHolder.get().putIfAbsent(CURRENT_TOKEN, token);
//        }
    }

    @Override
    public void destroy() {

    }


}
