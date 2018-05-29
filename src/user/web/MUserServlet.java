package user.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tools.commons.CommonUtils;
import tools.servlet.BaseServlet;
import user.domain.User;
import user.service.UserService;
import user.service.exception.UserException;

/**
 * Servlet implementation class MUserServlet
 */
public class MUserServlet extends BaseServlet {
	
	private UserService userService = new UserService();
	
	/**
	 * 修改资料功能
	 */
	public String updateUserMessage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 准备事件：加载用户资料(selete * from t_user where userId=?),setAttribute显示在编辑表单上
		 * 1.提交表单数据
		 * 2.步骤与添加功能一致，区别在于sql执行更新语句
		 */
		/*
		 * 1.封装表单数据
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		String userName = formUser.getUserName();
		/*
		 * 2.校验
		 */
		if(userName.equals("")||userName==null){// 非空校验
			req.setAttribute("error", "用户名不能为空！");
			req.setAttribute("user", formUser);
			return "f:/jsps/manage/user/editmess.jsp";
		}
		if(userName.length()<3 || userName.length()>20) {//长度校验
			req.setAttribute("error", "用户名不能为空！");
			req.setAttribute("user", formUser);
			return "f:/jsps/manage/user/editmess.jsp";
		}
		/*
		 * 3.提交service处理
		 */
		
		userService.updateUserMessage(formUser);
		/*
		 * 4.保存成功信息，返回到编辑页面
		 */
		req.setAttribute("msg", "修改保存成功！");
		req.setAttribute("user", formUser);
		return "f:/jsps/manage/user/editmess.jsp";
	}
	/**
	 * 加载用户资料
	 */
	public String loadUser(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User)req.getSession().getAttribute("sessionUser");
		String userId = user.getUserId();
		User getuser = userService.findByUserId(userId);
		
		req.setAttribute("getuser", getuser);
		
		return "f:/jsps/manage/user/editmess.jsp";
	}
	
	/**
	 * 修改密码　
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updatePassword(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1. 封装表单数据到user中
		 * 2. 从session中获取uid
		 * 3. 使用uid和表单中的oldPass和newPass来调用service方法
		 *   > 如果出现异常，保存异常信息到request中，转发到pwd.jsp
		 * 4. 保存成功信息到rquest中
		 * 5. 转发到msg.jsp
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		User user = (User)req.getSession().getAttribute("sessionUser");

		System.out.println(formUser.getReloginpass());
		System.out.println(formUser.getUserPwd());
		System.out.println(formUser.getNewloginpass());
		if(formUser.getReloginpass().equals("")||formUser.getUserPwd().equals("")||formUser.getNewloginpass().equals("")){
			req.setAttribute("msg", "<span class='glyphicon glyphicon-remove-sign'></span>&nbsp;密码不能为空！");
			req.setAttribute("user", formUser);
			return "f:/jsps/manage/user/editpass.jsp";
		}
		if(!formUser.getNewloginpass().equals(formUser.getReloginpass())){
			req.setAttribute("msg", "<span class='glyphicon glyphicon-remove-sign'></span>&nbsp;两次密码输入不一致！");
			req.setAttribute("user", formUser);
			return "f:/jsps/manage/user/editpass.jsp";
		}
		try {
			userService.updatePassword(user.getUserId(), formUser.getNewloginpass(), 
					formUser.getUserPwd());
			req.setAttribute("msg", "<span class='glyphicon glyphicon-remove-sign'></span>&nbsp;修改密码成功");
			return "f:/jsps/user/editpass.jsp";
		} catch (UserException e) {
			req.setAttribute("msg", e.getMessage());//保存异常信息到request
			req.setAttribute("user", formUser);//为了回显
			return "f:/jsps/manage/user/editpass.jsp";
		}
	}
	
	/**
	 * 注销功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getSession().invalidate();
		return "r:/index.jsp";
	}
	
}
