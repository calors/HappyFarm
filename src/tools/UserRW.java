package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Vector;

import view.farm.Farm;

import model.Item;
import model.UserBean;

public class UserRW {
	// 获取所有用户，每个item包括姓名和头像地址
	public static Vector<Item> getAllUserPic() {
		Vector<Item> items = new Vector<Item>();
		File picFileFolder = new File("user");
		String[] user = picFileFolder.list();
		for (int i = 0; i < user.length; i++) {
			UserBean userBean = readUserMsg(user[i]);
			Item item = new Item(userBean.getUserID(), userBean.getPic());
			if (user[i].equals(Farm.USERID))
				items.add(0, item);
			else {
				items.add(item);
			}
		}
		return items;
	}

	// 获取所有头像
	public static Vector<Item> getAllFacePic() {
		Vector<Item> items = new Vector<Item>();
		File picFileFolder = new File("image\\face");
		int count = picFileFolder.list().length;
		for (int i = 1; i <= count; i++) {
			Item item = new Item("", "image\\face\\" + i + "-1.gif");
			items.add(item);
		}
		return items;
	}

	/*
	 * 保存用户信息到user.txt文件中
	 */
	public static void saveUserMsg(UserBean user) {
		String userID = user.getUserID();
		FileInputStream fin = null;
		InputStreamReader isr = null;
		FileOutputStream fout = null;
		Properties properties = null;
		try {
			File userFileFolder = new File("user\\" + userID);
			if (userFileFolder.exists()) {
				File userFile = new File("user\\" + userID + "\\user.txt");
				if (userFile.exists()) {
					properties = new Properties();// 配置文件类，该类继承自
													// Hashtable，内容格式是"键=值"
					fin = new FileInputStream(userFile);// 文件流
					isr = new InputStreamReader(fin, "GBK");// 设置编码，否则中文乱码
					properties.load(isr);// 先读取，把数据缓冲到字符流isr中
					fout = new FileOutputStream(userFile);// 输出流

					properties.setProperty("userID", user.getUserID());// 设置属性到配置对象
					properties.setProperty("userName", user.getUserName());
					properties.setProperty("pass", user.getPass());
					properties.setProperty("nick", user.getNick());
					properties.setProperty("notice", user.getNotice());
					String pic = user.getPic();
					properties.setProperty("pic",
							pic.substring(pic.lastIndexOf("\\") + 1));
					properties.setProperty("money",
							String.valueOf(user.getMoney()));
					properties.setProperty("exp", user.getExp() + "");
					properties.setProperty("level", user.getLevel() + "");

					properties.store(fout, "用户信息");
				} else {// 文件不存在
					userFile.createNewFile();// 创建新文件
				}
			} else {// 目录不存在
				userFileFolder.mkdir();// 创建新目录
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fout.close();
				properties.clear();
				isr.close();
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * 从user.txt文件中读取数据
	 */
	public static UserBean readUserMsg(String userID) {
		UserBean user = null;
		try {
			Properties properties = new Properties();
			File userFile = new File("user\\" + userID + "\\user.txt");
			FileInputStream fin = new FileInputStream(userFile);
			InputStreamReader isr = new InputStreamReader(fin, "GBK");
			properties.load(isr);
			user = new UserBean();
			user.setUserID(userID);
			user.setUserName(properties.getProperty("userName"));
			user.setPass(properties.getProperty("pass"));
			user.setNick(properties.getProperty("nick"));
			user.setNotice(properties.getProperty("notice"));
			user.setPic("image\\face\\" + properties.getProperty("pic"));
			user.setMoney(Double.parseDouble(properties.getProperty("money")));
			user.setExp(Integer.parseInt(properties.getProperty("exp")));
			user.setLevel(Integer.parseInt(properties.getProperty("level")));
			isr.close();
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return user;
	}

	/*
	 * 初始化用户各种保存数据用的txt文件
	 */
	public static void initTxt(String userID) {
		try {
			File userFileFolder = new File("user\\" + userID);
			File userFile = new File("user\\" + userID + "\\user.txt");
			File landFile = new File("user\\" + userID + "\\land.txt");
			File bagFile = new File("user\\" + userID + "\\bag.txt");
			File storeFile = new File("user\\" + userID + "\\store.txt");
			if (!userFileFolder.exists()) {
				userFileFolder.mkdir();
			}
			if (!userFile.exists()) {
				userFile.createNewFile();
			}
			if (!landFile.exists()) {
				landFile.createNewFile();
			}
			if (!bagFile.exists()) {
				bagFile.createNewFile();
			}
			if (!storeFile.exists()) {
				storeFile.createNewFile();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
