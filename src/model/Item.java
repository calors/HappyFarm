package model;

public class Item {
	private String userName;// 用户名
	private String picPath;// 用户头像地址

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public Item(String userName, String picPath) {
		super();
		this.userName = userName;
		this.picPath = picPath;
	}

	public Item() {
		super();
	}

	@Override
	public String toString() {
		return "Item [userName=" + userName + ", picPath=" + picPath + "]";
	}

}
