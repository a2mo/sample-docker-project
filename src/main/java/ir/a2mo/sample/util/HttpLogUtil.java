package ir.a2mo.sample.util;

import ir.a2mo.sample.filter.wrapper.CustomHttpServletRequestWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Ali Alimohammadi
 * @since 7/31/2022
 */
@Component
public class HttpLogUtil {

    private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(
            MediaType.valueOf("text/*"),
            MediaType.APPLICATION_FORM_URLENCODED,
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.valueOf("application/*+json"),
            MediaType.valueOf("application/*+xml"),
            MediaType.MULTIPART_FORM_DATA
    );

    public void logRequest(CustomHttpServletRequestWrapper request, StringBuilder msg) {
        msg.append("\n-- Request --\n");
        logRequestInfo(request, msg);
    }

    public void logResponse(ContentCachingResponseWrapper response, StringBuilder msg) {
        msg.append("\n-- Response --\n");
        logResponseInfo(response, msg);
    }
    private void logContent(byte[] content, String contentType, StringBuilder msg) {
        MediaType mediaType = MediaType.valueOf(contentType);
        boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
        if (visible) {
            String contentString = new String(content, StandardCharsets.UTF_8);
            Stream.of(contentString.split("\r\n|\r|\n")).forEach(line -> msg.append(line).append("\n"));
        } else {
            msg.append(String.format("[%d bytes content]", content.length)).append("\n");
        }
    }

    private void logRequestBody(CustomHttpServletRequestWrapper request, StringBuilder msg) {
        try {
            byte[] content = request.getInputStream().readAllBytes();
            if (content.length > 0) {
                logContent(content, request.getContentType(), msg);
            }
        } catch (IOException e) {
            msg.append(String.format("[error in request body reading : %s]", e.getMessage())).append("\n");
        }
    }

    private void logRequestInfo(CustomHttpServletRequestWrapper request, StringBuilder msg) {
        msg.append(String.format("Request URI      : %s\n", request.getServletPath()));
        msg.append(String.format("Request Method      : %s\n", request.getMethod()));
        msg.append(String.format("Request Headers      : %s\n", getHeaders(request)));
        msg.append(String.format("Request Parameters      : %s\n", getParameters(request)));
        msg.append("Request Body      : \n");
        logRequestBody(request, msg);
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerMap = request.getHeaderNames();
        while (headerMap.hasMoreElements()) {
            String paramName = headerMap.nextElement();
            String paramValue = request.getParameter(paramName);
            headers.put(paramName, paramValue);
        }
        return headers;
    }

    private Map<String, String> getParameters(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            String paramValue = request.getParameter(paramName);
            parameters.put(paramName, paramValue);
        }
        return parameters;
    }

    private void logResponseInfo(ContentCachingResponseWrapper response, StringBuilder msg) {
        int status = response.getStatus();
        msg.append(String.format("Response status %s (%s)\n", status, HttpStatus.valueOf(status).getReasonPhrase()));
        msg.append("Response Body      : \n");
        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, response.getContentType(), msg);
        }
    }
}
