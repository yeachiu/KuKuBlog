package user.dao;
/**
 * 用户模块持久层
 * @author DF
 *
 */
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;



import tools.jdbc.TxQueryRunner;
import user.domain.User;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * 修改用户资料
	 * @throws SQLException 
	 */
	public void updateUserMessage(User user) throws SQLException{
		String sql = "update t_user set userName=?,introduction=? where userId=?";
		
		Object[] param = {user.getUserName(),user.getIntroduction(),user.getUserId()};
		qr.update(sql, param);
	}
	
	/**
	 * 根据用户id查询
	 * @throws SQLException 
	 */
	public User findByUserId(String userId) throws SQLException{
		String sql = "select * from t_user where userId=?";
		return qr.query(sql, new BeanHandler<User>(User.class),userId);
	}
	/**
	 * 按userId和userPwd查询
	 * @param userId
	 * @param userPwd
	 * @return
	 * @throws SQLException 
	 */
	public boolean findByUidAndPassword(String userId, String userPwd) throws SQLException {
		String sql = "select count(*) from t_user where userId=? and userPwd=?";
		Number number = (Number)qr.query(sql, new ScalarHandler(), userId, userPwd);
		return number.intValue() > 0;
	}
	/**
	 * 修改密码
	 * @param uid
	 * @param password
	 * @throws SQLException
	 */
	public void updatePassword(String userId, String newloginpass) throws SQLException {
		String sql = "update t_user set userPwd=? where userId=?";
		qr.update(sql, newloginpass, userId);
	}
	/**
	 * 【登录】--按用户名和密码查询
	 * @throws SQLException 
	 */
	public User findByUserNameAndUserPwd(String userName, String userPwd) throws SQLException{
		System.out.println("findByUserNameAndUserPwd()...");
		String sql = "select * from t_user where userName=? and userPwd=?";
//		System.out.println("[denglu]"+qr.query(sql, new BeanHandler<User>(User.class),userName,userPwd));
		User uu = qr.query(sql, new BeanHandler<User>(User.class),userName,userPwd);
		return uu;
	}
	
}
