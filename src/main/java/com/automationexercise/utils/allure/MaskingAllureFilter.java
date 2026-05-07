package com.automationexercise.utils.allure;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.Map;

/**
 * Custom Allure filter class responsible for attaching formatted API requests and responses.
 * <p>Masks sensitive fields (e.g. password) in form parameters and builds custom request, and generic response attachments.</p>
 * */
public class MaskingAllureFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        long startTime = System.currentTimeMillis();
        Response response = ctx.next(requestSpec, responseSpec);
        long responseTime = System.currentTimeMillis() - startTime;

        Allure.addAttachment("Request", buildRequest(requestSpec));
        Allure.addAttachment("Response", buildResponse(response, responseTime));
        return response;
    }

    // Helper Methods
    private String buildRequest(FilterableRequestSpecification reqSpec) {
        StringBuilder sb = new StringBuilder();

        sb.append("-- REQUEST --\n");
        sb.append("Method: ").append(reqSpec.getMethod()).append("\n");
        sb.append("URI: ").append(reqSpec.getURI()).append("\n\n");

        if (!reqSpec.getHeaders().asList().isEmpty())
            sb.append("-- HEADERS --\n").append(reqSpec.getHeaders()).append("\n\n");
        if (!reqSpec.getCookies().asList().isEmpty())
            sb.append("-- COOKIES --\n").append(reqSpec.getCookies()).append("\n\n");
        if (!reqSpec.getFormParams().isEmpty())
            sb.append("-- FORM PARAMS --\n").append(formatAndMaskBody(reqSpec.getFormParams())).append("\n\n");
        if (!reqSpec.getQueryParams().isEmpty())
            sb.append("-- QUERY PARAMS --\n").append(reqSpec.getQueryParams()).append("\n\n");

        Object body = reqSpec.getBody();
        if (body != null)
            sb.append("-- REQUEST BODY --\n").append(body).append("\n\n");

        return sb.toString();
    }

    private String buildResponse(Response res, long responseTime) {
        StringBuilder sb = new StringBuilder();

        sb.append("-- RESPONSE --\n");
        sb.append("Status: ").append(res.getStatusLine()).append("\n");
        sb.append("ResponseTime: ").append(responseTime).append("ms \n\n");

        if (!res.getHeaders().asList().isEmpty())
            sb.append("-- HEADERS --\n").append(res.getHeaders()).append("\n\n");
        if (!res.getCookies().isEmpty())
            sb.append("-- COOKIES --\n").append(res.getCookies()).append("\n\n");

        sb.append("-- RESPONSE BODY --\n");
        sb.append("StatusCode: ").append(res.getStatusCode()).append("\n");
        sb.append("Body: ").append(res.getBody().asPrettyString()).append("\n");

        return sb.toString();
    }

    private String formatAndMaskBody(Map<String, ?> params) {
        if (params == null || params.isEmpty()) return "EMPTY";
        StringBuilder sb = new StringBuilder();

        params.forEach((key, value) -> {
            String displayValue = key.toLowerCase().contains("password") ? "[PROTECTED]" : String.valueOf(value);
            sb.append(key).append("=").append(displayValue).append("\n");
        });
        return sb.toString();
    }
}