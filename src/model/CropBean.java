package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CropBean implements Cloneable {

	private int cropId;// ũ������
	private String name;// ũ��������
	private String pic;// ũ�����ʵͼƬ
	private int stageCount;// ũ���������׶���
	private int unitTime;// ũ��������ʱ���׶�ʱ�����
	private double sellMoney;// ��ʵ���۵���
	private double seedMoney;// ���ӹ��򵥼�
	private int buyLevel;// ���ӹ���ȼ�

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

	// ��ũ����id��ȡ��ʵͼƬ������·��
	public String getCropFruitPic(int cropId) {
		return "image\\crops\\crop" + cropId + "\\seed.png";
	}

	// ��ũ����id��ȡ����ͼƬ������·��
	public String getCropStartPic(int cropId) {
		return "image\\crops\\crop" + cropId + "\\cron_start.png";
	}

	public void main(String[] args) throws IOException {
		new CropBean().getCropMaxStageCountPic(1);
	}

	// ��ũ����id��ȡũ�����ʵ����ʱͼƬ������·��
	public String getCropMaxStageCountPic(int cropId) throws IOException {
		int maxStage = 0;

		File file = new File("image\\crops\\crop" + cropId + "\\crop.txt");
		if (file.exists()) {
			FileReader fReader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(fReader);
			String string;// ���ļ��ж�ȡĳһ�д���string��
			while ((string = bReader.readLine()) != null) {
				if (string.contains("stageCount")) {
					String[] tmp = string.split("=");// ���ȺŻ��ֳ�ǰ�����Σ�����һ�μ�Ϊ�����������
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

	// ��ũ����id��ȡũ�����ήͼƬ������·��
	public String getCropEndPic(int cropId) {
		return "image\\crops\\crop" + cropId + "\\cron_end.png";
	}

	// ��ũ����id�����ڵ������׶λ�ȡũ���ﵱǰ�׶�ͼƬ������·��
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
