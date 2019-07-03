package com.offcn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取请求上下文对象
        RequestContext context = RequestContext.getCurrentContext();
        //从上下文获取请求对象
        HttpServletRequest request = context.getRequest();
        System.out.println("发出请求----->"+request.getContextPath());
        //读取请求参数
       String token= request.getParameter("token");
       //判断token是否为空
        if(token==null){
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            return null;
        }
        return null;
    }
}
