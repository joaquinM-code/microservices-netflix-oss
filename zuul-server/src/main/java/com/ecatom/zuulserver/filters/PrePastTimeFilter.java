package com.ecatom.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PrePastTimeFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PrePastTimeFilter.class);

    @Override
    public String filterType() {
        //https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372986#notes
        //Specifies that the filter will trigger before the communication with the microservice is made ("pre" , "post" , "route:)
        return "pre";
    }

    @Override
    public int filterOrder() {
        //Alter the order that if many filters are applied
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        //If true executes the run method(may be used to filter request)
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //We need to intercept the request
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //Logging to see what we are doing
        log.info(String.format("%s request routed to %s" , request.getMethod(), request.getRequestURL().toString()));
        //Adding the time to the request
        Long receivedAt = System.currentTimeMillis();
        request.setAttribute("receivedAt", receivedAt);

        return null;
    }
}
