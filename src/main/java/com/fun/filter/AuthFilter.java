package com.fun.filter;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.fun.jedis.JedisService;
import com.fun.util.BaseUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * ClassName: AuthFilter <br/>
 * date: 2018年4月18日 上午11:27:57 <br/>
 * 
 * @author lishuai11
 * @version
 * @since JDK 1.8
 */
@Component
public class AuthFilter extends ZuulFilter {

	@Autowired
	JedisService jedisService;

	private static final String AUTHORIZATION = "auth";

	public static final String F_SPACE_AUTH = "F-space-auth:";

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

//	appName = CacheManager.getData(appKey, new CacheManager.Load<String>() {
//		@Override
//		public String load(String key) {
//			List<App> list = appRepository.findByAppKeyInfoAppKey(key);
//			if (list == null || list.size() == 0) {
//				list = appRepository.findByAppId(key);
//			}
//			if (list != null && list.size() > 0) {
//				App app = list.get(0);
//				return app.getName();
//			}
//			return "-";
//		}
//
//	}, 120);
	
	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String requestURI = request.getRequestURI().toString();
		requestURI = requestURI.toLowerCase();
		if (Pattern.matches(".*/register/.*", requestURI) || Pattern.matches(".*/login$", requestURI)
				|| Pattern.matches(".*/loginwx$", requestURI)) {
			ctx.setSendZuulResponse(true);
			ctx.setResponseStatusCode(200);
			return null;
		}
		Object auth = request.getHeader(AUTHORIZATION);
		if (auth == "" || auth == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", null);
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("status", "fail");
			jsonObject2.put("msg", "没有权限，请登陆！");
			jsonObject.put("result", jsonObject2);
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(200);
			ctx.setResponseBody(jsonObject.toJSONString());
			HttpServletResponse response = ctx.getResponse();
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.setLocale(new java.util.Locale("zh", "CN"));
			return null;
		}
		String authorization = String.valueOf(auth);
		String token = BaseUtils.decode(authorization);
        try {
        	token = token.split(";")[1];
		} catch (Exception e) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", null);
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("status", "fail");
			jsonObject2.put("msg", "请求失败！非法Authorization");
			jsonObject.put("result", jsonObject2);
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(200);
			ctx.setResponseBody(jsonObject.toJSONString());
			HttpServletResponse response = ctx.getResponse();
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.setLocale(new java.util.Locale("zh", "CN"));
			return null;
		}
		
		if (jedisService.isExist(F_SPACE_AUTH + token)) {
			ctx.setSendZuulResponse(true);
			ctx.setResponseStatusCode(200);
			return null;
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data", null);
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("status", "fail");
			jsonObject2.put("msg", "token已过期，请重新登陆！");
			jsonObject.put("result", jsonObject2);
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(200);
			ctx.setResponseBody(jsonObject.toJSONString());
			HttpServletResponse response = ctx.getResponse();
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.setLocale(new java.util.Locale("zh", "CN"));
			return null;

		}
	}

}
