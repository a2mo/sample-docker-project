package ir.a2mo.sample.filter;

import ir.a2mo.sample.filter.wrapper.CustomHttpServletRequestWrapper;
import ir.a2mo.sample.util.HttpLogUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Ali Alimohammadi
 * @since 7/31/2022
 */
@Component
@Order(2)
@Slf4j
@RequiredArgsConstructor
public class LoggingFilter extends OncePerRequestFilter {
    private final HttpLogUtil httpLogUtil;

    private static CustomHttpServletRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof CustomHttpServletRequestWrapper) {
            return (CustomHttpServletRequestWrapper) request;
        } else {
            return new CustomHttpServletRequestWrapper(request);
        }
    }

    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }

    protected void doFilterWrapped(CustomHttpServletRequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {

        StringBuilder requestMessage = new StringBuilder();
        StringBuilder responseMessage = new StringBuilder();

        try {
            httpLogUtil.logRequest(request, requestMessage);
            log.info(requestMessage.toString());
            filterChain.doFilter(request, response);
        } finally {
            httpLogUtil.logResponse(response, responseMessage);
            log.info(responseMessage.toString());
            response.copyBodyToResponse();
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
        }
    }


}
