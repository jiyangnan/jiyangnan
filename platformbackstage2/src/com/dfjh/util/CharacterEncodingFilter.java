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
//�������� HTTP �����ַ�����Ĺ�����,����������encodingָ��ʹ�ú����ַ�����,���ڴ���Html Form����������������� 
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
