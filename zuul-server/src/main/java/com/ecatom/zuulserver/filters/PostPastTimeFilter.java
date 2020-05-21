package com.ecatom.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostPastTimeFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(PostPastTimeFilter.class);

    @Override
    public String filterType() {
        return "post";
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
        log.info("Post calculation");
        //Adding the time to the request
        Long passedTime = System.currentTimeMillis() - (Long) request.getAttribute("receivedAt");

        log.info(String.format("%s seconds to process the request", passedTime.doubleValue()/1000.00));
        log.info(String.format("%s milliseconds to process the request", passedTime.doubleValue()));


        return null;
    }
}
