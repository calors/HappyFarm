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
	public static ArrayList<FruitBean> bagList = null;// 放包裹中的数据
	public static ArrayList<FruitBean> storeList = null;// 放仓库中的数据

	static {
		// 初始化数据
		bagList = new ArrayList<FruitBean>();
		storeList = new ArrayList<FruitBean>();
	}

	/*
	 * 把果实类对象添加到list中 temp:果实类对象 list:要添加到的list
	 */
	public static void addTOList(FruitBean temp, ArrayList<FruitBean> list) {
		Iterator<FruitBean> it = list.iterator();// 取代迭器
		boolean flag = false;// 判断果实是否存在的标记
		FruitBean cloneFruit = null;
		FruitBean nowFruit = null;
		while (it.hasNext()) {
			nowFruit = it.next();
			if (nowFruit.getCrop().getCropId() == temp.getCrop().getCropId()) {
				flag = true;
				try {
					cloneFruit = (FruitBean) nowFruit.clone();
					it.remove();// 此处不能用list的remove,应该用迭代器的remove
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
	 * 购买时对添加到包裹中的数据进行处理 参数是果实类对象
	 */
	public static void addTObagList(FruitBean temp) {
		addTOList(temp, bagList);
	}

	/*
	 * 收获后的果实和数量放入仓库storeList中 参数是果实类对象
	 */
	public static void addTOStoreList(FruitBean temp) {
		addTOList(temp, storeList);
	}

	/*
	 * 播种时对包裹中的数据进行处理 参数：要删除的种子的id
	 */
	public static void delFromBagList(int cropid) {
		Iterator<FruitBean> it = bagList.iterator();// 取代迭器
		boolean flag = false;// 判断种子是否存在的标记
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
	 * 仓库中卖出果实后减仓库storeList中的果实数量 参数：现在卖出的农作物的id,现在卖出的数量
	 */
	public static void delFromStoreList(int nowsalecropid, int nowsalecount) {
		Iterator<FruitBean> it = storeList.iterator();// 取代迭器
		boolean flag = false;// 判断果实是否存在的标记
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
				it.remove();// 移除
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
	 * 从文本中读取数据到各List中
	 */
	public static void readList(String filepath, ArrayList<FruitBean> list)
			throws IOException {
		FileReader fr = new FileReader(filepath);
		BufferedReader br = new BufferedReader(fr);
		String str = null;// 存放当前读取到的信息
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
	 * 保存数据到各List中
	 */
	public static void saveList(String filepath, ArrayList<FruitBean> list)
			throws IOException {
		FileWriter fw = new FileWriter(filepath);
		BufferedWriter bw = new BufferedWriter(fw);
		String str = null;// 存放当前读取到的信息
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
	 * 读取用户bag文本中的数据
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
	 * 保存数据到用户bag文本中
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
	 * 读取用户store文本中的数据
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
	 * 保存数据到用户store文本中
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
