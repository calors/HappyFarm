package model;

public class LandData {
	private int landID;// ÿһ������ر��
	private int cropID = -1;// ����������ֲ��ũ������(-1����û����ֲũ����)
	private long startTime;// ��ʼ��ֲ��ʱ��
	// ��ǰ�����Ľ׶�(���ӽ׶�Ϊ0����������׶�+1Ϊ��ή״̬)
	private int nowStage;// ��ǰ�������׶�
	private int stealCount;// ��͵����
	private int isEndStage;// �Ƿ��ǿ�ή״̬
	private int fruitNum;// ����ʱ��ʵ����
	private String stealName="";

	public LandData() {
		super();
	}

	public LandData(int landID, int cronid, long startTime, int nowStage,
			int stealCount, int isendStage, int fruitNum) {
		super();
		this.landID = landID;
		this.cropID = cronid;
		this.startTime = startTime;
		this.nowStage = nowStage;
		this.stealCount = stealCount;
		this.isEndStage = isendStage;
		this.fruitNum = fruitNum;
	}

	@Override
	public String toString() {
		return landID + ":" + cropID + ":" + startTime + ":" + nowStage + ":"
				+ stealCount + ": " + isEndStage;
	}

	public int getLandID() {
		return landID;
	}

	public void setLandID(int landID) {
		this.landID = landID;
	}

	public int getCropID() {
		return cropID;
	}

	public void setCropID(int cronid) {
		this.cropID = cronid;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getNowStage() {
		return nowStage;
	}

	public void setNowStage(int nowStage) {
		this.nowStage = nowStage;
	}

	public int getStealCount() {
		return stealCount;
	}

	public void setStealCount(int stealCount) {
		this.stealCount = stealCount;
	}

	public int getIsEndStage() {
		return isEndStage;
	}

	public void setIsEndStage(int isEndStage) {
		this.isEndStage = isEndStage;
	}

	public int getFruitNum() {
		return fruitNum;
	}

	public void setFruitNum(int fruitNum) {
		this.fruitNum = fruitNum;
	}

	public String getStealName() {
		return stealName;
	}

	public void setStealName(String stealName) {
		this.stealName = stealName;
	}
	
}
