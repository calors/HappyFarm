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
	// ��ȡ�����û���ÿ��item����������ͷ���ַ
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

	// ��ȡ����ͷ��
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
	 * �����û���Ϣ��user.txt�ļ���
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
					properties = new Properties();// �����ļ��࣬����̳���
													// Hashtable�����ݸ�ʽ��"��=ֵ"
					fin = new FileInputStream(userFile);// �ļ���
					isr = new InputStreamReader(fin, "GBK");// ���ñ��룬������������
					properties.load(isr);// �ȶ�ȡ�������ݻ��嵽�ַ���isr��
					fout = new FileOutputStream(userFile);// �����

					properties.setProperty("userID", user.getUserID());// �������Ե����ö���
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

					properties.store(fout, "�û���Ϣ");
				} else {// �ļ�������
					userFile.createNewFile();// �������ļ�
				}
			} else {// Ŀ¼������
				userFileFolder.mkdir();// ������Ŀ¼
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
	 * ��user.txt�ļ��ж�ȡ����
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
	 * ��ʼ���û����ֱ��������õ�txt�ļ�
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
