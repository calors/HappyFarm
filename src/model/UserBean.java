package model;

public class UserBean {
	private String userID;// 用户ID 登录用
	private String userName = "";// 昵称
	private String pass;// 密码
	private String nick = "";// 签名
	private String notice = "";// 公告
	private String pic = "1-1.gif";// 头像图片
	private double money = 200;// 钱
	private int exp = 0;// 经验
	private int level = 1;// 等级

	public UserBean() {
		super();
	}

	public UserBean(String userID, String userName, String pass, String nick,
			String notice, String pic, double money, int exp, int level) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.pass = pass;
		this.nick = nick;
		this.notice = notice;
		this.pic = pic;
		this.money = money;
		this.exp = exp;
		this.level = level;
	}

	@Override
	public String toString() {
		return userID + ":" + userName + ":" + pass + ":" + nick + ":" + notice
				+ ":" + pic + ":" + money + ":" + exp + ":" + level;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
