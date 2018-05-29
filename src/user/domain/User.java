package user.domain;

public class User {
	//数据库字段
	private String userId;	//	唯一ID
	private String userName;	//用户名
	private String userPwd;		//登录密码
	private String email;	//注册邮箱
	private String headPortrait;	//头像图片路径
	private String registerTime;	//注册时间
	private String introduction;	//简介
	private boolean status;	//状态，true表示已激活
	private String activationCode;	//激活码，唯一值
	
	//注册表单
	private String reloginpass;	//确认密码
	private String verifyCode;	//验证码
	
	//修改密码表单
	private String newloginpass;	//新密码

	//方法
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getReloginpass() {
		return reloginpass;
	}

	public void setReloginpass(String reloginpass) {
		this.reloginpass = reloginpass;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getNewloginpass() {
		return newloginpass;
	}

	public void setNewloginpass(String newloginpass) {
		this.newloginpass = newloginpass;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPwd=" + userPwd + ", email=" + email
				+ ", headPortrait=" + headPortrait + ", registerTime="
				+ registerTime + ", introduction=" + introduction + ", status="
				+ status + ", activationCode=" + activationCode
				+ ", reloginpass=" + reloginpass + ", verifyCode=" + verifyCode
				+ ", newloginpass=" + newloginpass + "]";
	}
	
	
}
