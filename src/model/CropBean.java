package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CropBean implements Cloneable {

	private int cropId;// 农作物编号
	private String name;// 农作物名称
	private String pic;// 农作物果实图片
	private int stageCount;// 农作物生长阶段数
	private int unitTime;// 农作物生长时各阶段时间基数
	private double sellMoney;// 果实销售单价
	private double seedMoney;// 种子购买单价
	private int buyLevel;// 种子购买等级

	public CropBean() {
		super();
	}

	public CropBean(int cropId, String name, String pic, int stageCount,
			int unitTime, double sellMoney, double seedMoney, int buyLevel) {
		super();
		this.cropId = cropId;
		this.name = name;
		this.pic = pic;
		this.stageCount = stageCount;
		this.unitTime = unitTime;
		this.sellMoney = sellMoney;
		this.seedMoney = seedMoney;
		this.buyLevel = buyLevel;
	}

	@Override
	public String toString() {
		return cropId + ":" + name + ":" + pic + ":" + stageCount + ":"
				+ unitTime + ":" + sellMoney + ":" + seedMoney + ":" + buyLevel;
	}

	// 按农作物id获取果实图片的完整路径
	public String getCropFruitPic(int cropId) {
		return "image\\crops\\crop" + cropId + "\\seed.png";
	}

	// 按农作物id获取种子图片的完整路径
	public String getCropStartPic(int cropId) {
		return "image\\crops\\crop" + cropId + "\\cron_start.png";
	}

	public void main(String[] args) throws IOException {
		new CropBean().getCropMaxStageCountPic(1);
	}

	// 按农作物id获取农作物果实成熟时图片的完整路径
	public String getCropMaxStageCountPic(int cropId) throws IOException {
		int maxStage = 0;

		File file = new File("image\\crops\\crop" + cropId + "\\crop.txt");
		if (file.exists()) {
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			String string;// 从文件中读取某一行存在string中
			while ((string = bReader.readLine()) != null) {
				if (string.contains("stageCount")) {
					String[] tmp = string.split("=");// 按等号划分成前后两段，后面一段即为最大生长周期
					maxStage = Integer.parseInt(tmp[1]);
					bReader.close();
					break;
				}
			}

		} else {
			// notice error
		}
		return "image\\crops\\crop" + cropId + "\\" + maxStage + ".png";
	}

	// 按农作物id获取农作物枯萎图片的完整路径
	public String getCropEndPic(int cropId) {
		return "image\\crops\\crop" + cropId + "\\cron_end.png";
	}

	// 按农作物id和现在的生长阶段获取农作物当前阶段图片的完整路径
	public String getNowStagePic(int cropId, int stageNow) {
		return "image\\crops\\crop" + cropId + "\\" + stageNow + ".png";
	}

	public int getCropId() {
		return cropId;
	}

	public void setCropId(int cropId) {
		this.cropId = cropId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getStageCount() {
		return stageCount;
	}

	public void setStageCount(int stageCount) {
		this.stageCount = stageCount;
	}

	public int getUnitTime() {
		return unitTime;
	}

	public void setUnitTime(int unitTime) {
		this.unitTime = unitTime;
	}

	public double getSellMoney() {
		return sellMoney;
	}

	public void setSellMoney(double sellMoney) {
		this.sellMoney = sellMoney;
	}

	public double getSeedMoney() {
		return seedMoney;
	}

	public void setSeedMoney(double seedMoney) {
		this.seedMoney = seedMoney;
	}

	public int getBuyLevel() {
		return buyLevel;
	}

	public void setBuyLevel(int buyLevel) {
		this.buyLevel = buyLevel;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
