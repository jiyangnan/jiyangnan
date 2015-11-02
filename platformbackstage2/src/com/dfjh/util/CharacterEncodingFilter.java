package com.dfjh.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author liukq
 */
//用于设置 HTTP 请求字符编码的过滤器,过滤器参数encoding指明使用何种字符编码,用于处理Html Form请求参数的中文乱码 
public class CharacterEncodingFilter implements Filter {
	protected FilterConfig filterConfig = null;
	protected String encoding = "";

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (encoding != null){
//			System.out.println(encoding);
			servletRequest.setCharacterEncoding(encoding);}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {
		filterConfig = null;
		encoding = null;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
	}
}
