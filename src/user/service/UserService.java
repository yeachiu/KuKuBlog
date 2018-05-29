package user.service;

import java.sql.SQLException;

import user.dao.UserDao;
import user.domain.User;
import user.service.exception.UserException;
/**
 * 用户模块业务层
 * @author DF
 *
 */
public class UserService {
	private UserDao userDao = new UserDao(); 
	/**
	 * 保存用户修改
	 */
	public void updateUserMessage(User user){
		try{
			userDao.updateUserMessage(user);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查询用户资料
	 */
	public User findByUserId(String userId){
		try{
			return userDao.findByUserId(userId);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 修改密码
	 * @param uid
	 * @param newPass
	 * @param oldPass
	 * @throws UserException 
	 */
	public void updatePassword(String userId, String newloginpass, String userPwd) throws UserException {

		try {
			/*
			 * 1. 校验老密码
			 */
			boolean bool = userDao.findByUidAndPassword(userId, userPwd);
			if(!bool) {//如果老密码错误
				throw new UserException("<span class='glyphicon glyphicon-remove-sign'></span>&nbsp;原密码错误！");
			}
			
			/*
			 * 2. 修改密码
			 */
			userDao.updatePassword(userId, newloginpass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 【登录】
	 * @param user
	 * @return
	 */
	public User login(User user){
		/*
		 * 将表单数据提交到持久层查询是否一致
		 */
		try{
			System.out.println("login()...");
			return userDao.findByUserNameAndUserPwd(user.getUserName(), user.getUserPwd());
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
