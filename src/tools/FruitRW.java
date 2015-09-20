package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import model.FruitBean;

public class FruitRW {
	public static ArrayList<FruitBean> bagList = null;// �Ű����е�����
	public static ArrayList<FruitBean> storeList = null;// �Ųֿ��е�����

	static {
		// ��ʼ������
		bagList = new ArrayList<FruitBean>();
		storeList = new ArrayList<FruitBean>();
	}

	/*
	 * �ѹ�ʵ�������ӵ�list�� temp:��ʵ����� list:Ҫ��ӵ���list
	 */
	public static void addTOList(FruitBean temp, ArrayList<FruitBean> list) {
		Iterator<FruitBean> it = list.iterator();// ȡ������
		boolean flag = false;// �жϹ�ʵ�Ƿ���ڵı��
		FruitBean cloneFruit = null;
		FruitBean nowFruit = null;
		while (it.hasNext()) {
			nowFruit = it.next();
			if (nowFruit.getCrop().getCropId() == temp.getCrop().getCropId()) {
				flag = true;
				try {
					cloneFruit = (FruitBean) nowFruit.clone();
					it.remove();// �˴�������list��remove,Ӧ���õ�������remove
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}// end while
		if (flag) {
			int count = cloneFruit.getCount() + temp.getCount();
			cloneFruit.setCount(count);
			list.add(cloneFruit);
		} else {
			list.add(temp);
		}
	}

	/*
	 * ����ʱ����ӵ������е����ݽ��д��� �����ǹ�ʵ�����
	 */
	public static void addTObagList(FruitBean temp) {
		addTOList(temp, bagList);
	}

	/*
	 * �ջ��Ĺ�ʵ����������ֿ�storeList�� �����ǹ�ʵ�����
	 */
	public static void addTOStoreList(FruitBean temp) {
		addTOList(temp, storeList);
	}

	/*
	 * ����ʱ�԰����е����ݽ��д��� ������Ҫɾ�������ӵ�id
	 */
	public static void delFromBagList(int cropid) {
		Iterator<FruitBean> it = bagList.iterator();// ȡ������
		boolean flag = false;// �ж������Ƿ���ڵı��
		FruitBean cloneFruit = null;
		FruitBean nowFruit = null;
		while (it.hasNext()) {
			nowFruit = it.next();
			if (nowFruit.getCrop().getCropId() == cropid) {
				flag = true;
				try {
					cloneFruit = (FruitBean) nowFruit.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				it.remove();
			}
		}// end while
		if (flag) {
			int count = cloneFruit.getCount() - 1;
			if (count > 0) {
				cloneFruit.setCount(count);
				bagList.add(cloneFruit);
			} else {
				return;
			}
		} else {
			return;
		}
	}

	/*
	 * �ֿ���������ʵ����ֿ�storeList�еĹ�ʵ���� ����������������ũ�����id,��������������
	 */
	public static void delFromStoreList(int nowsalecropid, int nowsalecount) {
		Iterator<FruitBean> it = storeList.iterator();// ȡ������
		boolean flag = false;// �жϹ�ʵ�Ƿ���ڵı��
		FruitBean cloneFruit = new FruitBean();
		FruitBean nowFruit = new FruitBean();
		while (it.hasNext()) {
			nowFruit = it.next();
			if (nowFruit.getCrop().getCropId() == nowsalecropid) {
				flag = true;
				try {
					cloneFruit = (FruitBean) nowFruit.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				it.remove();// �Ƴ�
			}
		}// end while
		if (flag) {
			int count = cloneFruit.getCount() - nowsalecount;
			if (count > 0) {
				cloneFruit.setCount(count);
				storeList.add(cloneFruit);
			} else {
				return;
			}

		} else {
			return;
		}
	}

	/*
	 * ���ı��ж�ȡ���ݵ���List��
	 */
	public static void readList(String filepath, ArrayList<FruitBean> list)
			throws IOException {
		FileReader fr = new FileReader(filepath);
		BufferedReader br = new BufferedReader(fr);
		String str = null;// ��ŵ�ǰ��ȡ������Ϣ
		int cropID, count;
		while ((str = br.readLine()) != null) {
			FruitBean tmpFruit = new FruitBean();
			String[] tmp = str.split(",");
			cropID = Integer.valueOf(tmp[0]);
			count = Integer.valueOf(tmp[1]);
			tmpFruit.setCrop(CropRW.getCrop(cropID));
			tmpFruit.setCount(count);
			list.add(tmpFruit);
		}
		br.close();
		fr.close();

	}

	/*
	 * �������ݵ���List��
	 */
	public static void saveList(String filepath, ArrayList<FruitBean> list)
			throws IOException {
		FileWriter fw = new FileWriter(filepath);
		BufferedWriter bw = new BufferedWriter(fw);
		String str = null;// ��ŵ�ǰ��ȡ������Ϣ
		int cropID, count;
		for (FruitBean fruit : list) {
			cropID = fruit.getCrop().getCropId();
			count = fruit.getCount();
			str = cropID + "," + count;
			bw.write(str);
			bw.newLine();
		}
		bw.close();
		fw.close();
	}

	/*
	 * ��ȡ�û�bag�ı��е�����
	 */
	public static void readBagList(String userID) {
		String file = "user\\" + userID + "\\bag.txt";
		try {
			readList(file, bagList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * �������ݵ��û�bag�ı���
	 */
	public static void saveBagList(String userID) {
		String file = "user\\" + userID + "\\bag.txt";
		try {
			saveList(file, bagList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * ��ȡ�û�store�ı��е�����
	 */
	public static void readStoreList(String userID) {
		String file = "user\\" + userID + "\\store.txt";
		try {
			readList(file, storeList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * �������ݵ��û�store�ı���
	 */
	public static void saveStoreList(String userID) {
		String file = "user\\" + userID + "\\store.txt";
		try {
			saveList(file, storeList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
