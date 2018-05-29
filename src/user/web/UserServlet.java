package user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tools.commons.CommonUtils;
import tools.servlet.BaseServlet;
import user.domain.User;
import user.service.UserService;
import user.service.exception.UserException;



/*
 * Servlet继承BaseServelt,请求处理方法格式要求，返回值为String,自定义方法名，其余一律相同。
 * 访问特定请求处理方法时，需加以method参数，值为调用的方法名
 */
public class UserServlet extends BaseServlet {
	
	private UserService userService = new UserService();
	/**
	 * aboutme模块显示
	 */
	/**
	 * 加载用户资料
	 */
	public String showAboutme(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/text");
		resp.setCharacterEncoding("UTF-8");
		String userId = "67500104";
		User user = userService.findByUserId(userId);
		String Introduction = user.getIntroduction();
		System.out.println(Introduction);
		resp.getWriter().print(Introduction);
		return null;
	}
	
	/**
	 * 登录功能
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/*
		 * 1.封装表单数据到User中
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		
		/*
		 * 2.校验表单数据
		 */
		Map<String,String> errors = validateLogin(formUser, req.getSession());
		if(errors.size() > 0){
			req.setAttribute("user", formUser);	//返回到页面，设置value="${form.XXX }"获取
			req.setAttribute("errors", errors);//返回到页面错误信息栏目，用${errors.XXX}获取信息
			return "f:/jsps/user/log.jsp";
		}
		/*
		 * 3.把登录工作交给service ，返回user对象；
		 */
		User user = userService.login(formUser);
		/*
		 * 4.若user不存在
		 * 		> 保存错误信息
		 * 		> 保存表单信息(回显)
		 * 5.若user存在
		 * 		> 用户状态是否为true
		 * 			*若状态为false,说明用户未激活，保存错误信息，转发到login.jsp
		 *  6.登录成功：
		 * 		保存当前查询出的user到session中
		 * 		保存当前用户的名称到cookie中，注意中文需要编码处理！！！
		 */
		
		if(user == null){
			req.setAttribute("msg", "用户名或密码错误！");
			req.setAttribute("user", formUser);
			return "f:/jsps/user/log.jsp";
		}else{ 
			if(!user.isStatus()){//是否激活
				req.setAttribute("msg", "用户未激活，请先激活！");
				req.setAttribute("user", formUser);
				return "f:/jsps/user/log.jsp";
			}else{
				//登录成功,保存用户到session
				req.getSession().setAttribute("sessionUser", user);
				req.getSession().setAttribute("userName", user.getUserName());
				//保存用户到cookie中
				String userName = user.getUserName();
				userName = URLEncoder.encode(userName,"utf-8");	//???
				Cookie cookie = new Cookie("userame",userName);
				cookie.setMaxAge(60*60*24*10);	//保存10天
				
				return "r:/index.jsp";	//重定向到主页
			}
		}	
	}
	/*
	 * 登录表单校验
	 */
	private Map<String,String> validateLogin(User formUser, HttpSession session) {
		Map<String,String> errors = new HashMap<String,String>();
		
		//	1.校验登录名
		String userName = formUser.getUserName();
		if(userName == null || userName.trim().isEmpty()){
			 //loginname.trim() :去掉空格符
			errors.put("userName", "用户名不能为空！");
		} else if(userName.length()<3 || userName.length()>20){
			errors.put("userName", "用户名长度必须在3~20之间！");
		}
		//	2.校验密码
		String userPwd = formUser.getUserPwd();
		if(userPwd == null || userPwd.trim().isEmpty()){
			
			errors.put("userPwd", "密码不能为空！");
		} 
		//	3.校验验证码
		String verifyCode = formUser.getVerifyCode();
		String vCode = (String)session.getAttribute("vCode");
		if(verifyCode == null || verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空！");
		}else{
			if(!verifyCode.equalsIgnoreCase(vCode)){
				errors.put("verifyCode", "验证码错误！");
			}
		} 
		
		return errors;
	}
	/**
	 * Ajax校验验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateVerifyCode(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1.获取输入的验证码
		 */
		String verifyCode = req.getParameter("verifyCode");
		/*
		 * 2.获取图片上的验证码
		 */
		String vCode = (String)req.getSession().getAttribute("vCode");
		/*
		 * 3.忽略大小写比较，得到结果
		 */
		boolean bool = verifyCode.equalsIgnoreCase(vCode);
		/*
		 * 4.发送客户端
		 */
		resp.getWriter().print(bool);
		return null;
	}

}
