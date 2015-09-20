package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import model.LandData;

public class LandRW {
	public static HashMap<Integer, LandData> landDataMap = null;
	static {
		landDataMap = new HashMap<Integer, LandData>();
	}

	/*
	 * 读取用户六块小土地上的数据 参数：用户ID
	 */
	public static void readLandMsg(String userID) {
		try {
			String landFile = "user\\" + userID + "\\land.txt";
			FileReader fileReader = new FileReader(landFile);
			BufferedReader br = new BufferedReader(fileReader);
			int landID, cropID, isEnd, fruitNum, stealCount;
			long startTime;
			String stealName;
			String str = null;
			while ((str = br.readLine()) != null) {// readLine()自动往后移动光标
				String[] data = str.split(",");
				landID = Integer.valueOf(data[0]);
				cropID = Integer.valueOf(data[1]);
				startTime = Long.valueOf(data[2]);
				isEnd = Integer.valueOf(data[3]);
				fruitNum = Integer.valueOf(data[4]);
				stealCount = Integer.valueOf(data[5]);
				stealName = data[6];
				LandData landData = new LandData();
				landData.setLandID(landID);
				landData.setCropID(cropID);
				landData.setStartTime(startTime);
				landData.setIsEndStage(isEnd);
				landData.setFruitNum(fruitNum);
				landData.setStealCount(stealCount);
				landData.setStealName(stealName);
				landDataMap.put(landID, landData);
			}
			br.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 保存用户六块小土地上的数据 参数：用户ID
	 */
	public static void saveLandMsg(String userID) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter("user\\" + userID + "\\land.txt");
			bw = new BufferedWriter(fw);
			String str = null;// 保存从landDataMap读取的土地信息
			int landID, cropID, isEnd, fruitNum, stealCount;
			String stealName = "";
			long startTime;
			for (LandData landData : LandRW.landDataMap.values()) {
				landID = landData.getLandID();
				cropID = landData.getCropID();
				startTime = landData.getStartTime();
				isEnd = landData.getIsEndStage();
				fruitNum = landData.getFruitNum();
				stealCount = landData.getStealCount();
				stealName = landData.getStealName();
				str = landID + "," + cropID + "," + startTime + "," + isEnd
						+ "," + fruitNum + "," + stealCount + "," + stealName;
				bw.write(str);
				bw.newLine();// write()方法写入数据时，不会写入回车，因此需要使用newLine()方法在每行数据后加入回车
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null && fw != null) {
				try {
					bw.close();
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			bw=null;
			fw=null;
		}
	}

	/*
	 * 初始化用户六块小土地上的数据 参数：用户ID
	 */
	public static void initLandData(String userID) {
		for (int i = 1; i <= 6; i++) {
			LandData landData = new LandData();
			landData.setLandID(i);
			landData.setCropID(-1);
			landData.setStartTime(0);
			landData.setIsEndStage(0);
			landData.setFruitNum(0);
			landData.setStealCount(0);
			landData.setStealName("");
			landDataMap.put(i, landData);
		}
		saveLandMsg(userID);
	}
}
