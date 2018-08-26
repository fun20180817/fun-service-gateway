package com.fun.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

/** 
 * ClassName: GatewayApplication <br/> 
 * Function: 解决跨域问题. <br/> 
 * date: 2018年8月20日 下午5:39:18 <br/> 
 * 
 * @author lishuai11 
 * @version  
 * @since JDK 1.8
 */
@Deprecated
@Component
@WebFilter(filterName = "CORSFilter", urlPatterns = "/*")
public class CORSFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(CORSFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("filter start");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,PUT,DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Authorization, Accept,X-Requested-With,sessionKey");
		if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
			response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
		}
		chain.doFilter(req, res);
	}

	public boolean isLoggable(LogRecord record) {
		return false;
	}

}
