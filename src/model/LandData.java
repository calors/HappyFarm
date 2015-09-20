package model;

public class LandData {
	private int landID;// 每一块的土地编号
	private int cropID = -1;// 此土地上种植的农作物编号(-1代表没有种植农作物)
	private long startTime;// 开始种植的时间
	// 当前所处的阶段(种子阶段为0，最大生长阶段+1为枯萎状态)
	private int nowStage;// 当前的生长阶段
	private int stealCount;// 被偷次数
	private int isEndStage;// 是否是枯萎状态
	private int fruitNum;// 成熟时果实数字
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
