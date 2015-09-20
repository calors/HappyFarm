package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

import model.CropBean;

public class CropRW {
	public static ArrayList<CropBean> cropList = null;// �����е�ũ��������

	static {
		cropList = new ArrayList<CropBean>();
		cropList = readCrop();
	}

	/*
	 * ��ȡ���Ӷ�Ӧ��ũ������Ϣ ������Ҫ�ҵ�ũ�����Ӧ��id ����ֵ��CropBean����
	 */
	public static CropBean getCrop(int cropID) {
		for (CropBean tmpCrop : cropList) {
			if (tmpCrop.getCropId() == cropID) {
				try {
					return (CropBean) tmpCrop.clone();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		}// end for
		return null;
	}

	/*
	 * ��ȡ����ũ�������Ϣ��croplist
	 */
	public static ArrayList<CropBean> readCrop() {
		try {
			File cropFileFolder = new File("image\\crops");
			String[] cropFile = cropFileFolder.list();// ��ȡ�ļ����б�
			Properties properties = new Properties();

			for (int i = 1; i <= cropFile.length; i++) {
				String cropPath = cropFileFolder + "\\crop" + i + "\\crop.txt";
				FileInputStream fin = new FileInputStream(cropPath);
				// ָ�����룬�����������
				InputStreamReader isr = new InputStreamReader(fin, "GBK");
				properties.load(isr);
				CropBean tmpCropBean = new CropBean();
				tmpCropBean.setCropId(Integer.valueOf(properties
						.getProperty("cropId")));
				tmpCropBean.setName(properties.getProperty("name"));
				tmpCropBean.setPic(cropFileFolder + "\\crop" + i + "\\"
						+ properties.getProperty("pic"));
				tmpCropBean.setStageCount(Integer.valueOf(properties
						.getProperty("stageCount")));
				tmpCropBean.setUnitTime(Integer.valueOf(properties
						.getProperty("unitTime")));
				tmpCropBean.setSellMoney(Double.valueOf(properties
						.getProperty("sellMoney")));
				tmpCropBean.setSeedMoney(Double.valueOf(properties
						.getProperty("seedMoney")));
				tmpCropBean.setBuyLevel(Integer.valueOf(properties
						.getProperty("buyLevel")));
				cropList.add(tmpCropBean);
				fin.close();
				properties.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cropList;
	}

	public static ArrayList<CropBean> getCropList() {
		return cropList;
	}
}
