package zzc.springboot.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录检查
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

	/**
	 * 目标方法执行之前
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("自定义拦截器......");
		Object loginUser = request.getSession().getAttribute("loginUser");
		if (null == loginUser) {
			// 未登录，返回登录页
			System.out.println("未登录，返回登录页");
			request.setAttribute("msg", "没有权限请先登录");
			request.getRequestDispatcher("/index.html").forward(request, response);
			return false;
		} else {
			// 已登录,放行请求
			System.out.println("已登录,放行请求");
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
