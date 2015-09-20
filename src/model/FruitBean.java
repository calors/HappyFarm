package model;

public class FruitBean implements Cloneable {
	private CropBean crop; // ũ����
	private int count;// ũ��������

	public FruitBean() {
		super();
	}

	public FruitBean(CropBean crop, int count) {
		super();
		this.crop = crop;
		this.count = count;
	}

	@Override
	public String toString() {
		return crop.getName() + ":" + count;
	}

	public CropBean getCrop() {
		return crop;
	}

	public void setCrop(CropBean crop) {
		this.crop = crop;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
