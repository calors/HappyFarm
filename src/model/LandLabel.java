package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import tools.CropRW;
import tools.FruitRW;
import tools.LandRW;
import view.farm.Farm;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class LandLabel extends JLabel {
	private JLabel labelBackground;// �����صı���ͼ
	private JLabel labelCrop;// �Ժ��ÿ��ũ����ĳɳ��׶�ͼƬ
	private int nowCropID;// ������������ֲ��ũ�����ID��������ʱ�ɵõ�
	private int nowCropFruitCount = 0;// �ջ�ʱ�Ĺ�ʵ�������տ�ʼ����ʱֵΪ��
	private int landNum;// �������صı��
	private Timer timer;// ��������ϵĶ�ʱ��
	private int nowStage;// ���������Ľ׶�
	private Farm farm;// ũ��������
	private CropBean nowCrop;// �����ϵ�ũ����
	private LandData landData;// ��������
	private long leavetime;// �뿪��ʱ��
	private boolean ishadbo = false;// �Ƿ��Ѿ�����
	private boolean isPick = false;// �Ƿ��Ѿ���ժ
	private boolean isRipe = false;// �Ƿ����
	private boolean isSteal = false;// �Ƿ�͵��

	public LandLabel() {
		super();
	}

	public LandLabel(Farm farm, LandData landData, int landNum) {
		super();
		this.farm = farm;
		this.landData = landData;
		this.landNum = landNum;
		initGUI();
		LandRW.readLandMsg(farm.nowUserID);// ��ȡ�����������ݵ�map��
		this.landData = LandRW.landDataMap.get(landNum);// ��ȡָ����ŵ�������Ϣ
		nowCropID = this.landData.getCropID();
		if (nowCropID != -1) {// �������в���
			load(this.landData.getStartTime());// ����������Ϣ,���뿪ʼʱ��
		}
		this.addMouseListener(new LandMouseAdapter());
		// ��Ϊ��label�ؼ� settooltiptext֮�� crop�ؼ����ڵ����أ�
		// �������ü������������ص��¼�����
		this.labelCrop.addMouseListener(new CropMouseAdapter());
	}

	/*
	 * ũ����label������
	 */
	public class CropMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			LandMouseAdapter landAdapter = new LandMouseAdapter();
			landAdapter.mouseClicked(e);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			labelCrop.setToolTipText("��" + nowCropFruitCount + "����ʵ");
		}
	}

	/*
	 * �������������
	 */
	public class LandMouseAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			int mode = e.getModifiers();
			if ((mode & InputEvent.BUTTON1_MASK) != 0) {// ������
				// �жϲ��ֻ��ǲ�ժ
				if (farm.getMouseCursorICon() == 1) {// ����
					bozhong();
				} else if (farm.getMouseCursorICon() == 2) {// ��ժ
					pick();
				} else if (farm.getMouseCursorICon() == 3) {// ����
					shovel();
				}
			} else {
				farm.setMouseCursorICon(0);
				farm.setCursor(null);
			}
		}
	}

	/*
	 * ��������
	 */
	private void load(long startTime) {
		landData.setCropID(nowCropID);
		ishadbo = true;
		nowCrop = CropRW.getCrop(nowCropID);
		isRipe = true;// �Ƿ����
		isPick = landData.getIsEndStage() == 1 ? true : false;// �Ƿ��Ѿ���ժ
		if (isPick) {// �Ѿ���ժ��ˢ�¿�ήͼƬ����
			flushPic(5);// ������0���� ˢ��ͼƬ��isPick �ж��Ƿ��ή
		} else {
			long nowTime = System.currentTimeMillis();
			long dTime = nowTime - startTime;// ���ʱ���
			int i;// �����׶�
			for (i = 0; i < nowCrop.getStageCount(); i++) {
				dTime = dTime - (i + 1) * nowCrop.getUnitTime() * 1000;
				if (dTime <= 0) {// ����һ�׶�ʱ�䲻�����Ȼ�δ����
					isRipe = false;
					break;
				} else {
					this.leavetime = dTime;// ʣ���ʱ��
				}
			}
			nowStage = i;
			flushPic(nowStage);
			if (!isRipe) {// ��δ����
				myTimer((int) leavetime);// �ѿ۳��굱ǰ�����׶����µ�ʱ�����ø���ʱ��
				timer.start();
			} else {
				isRipe = true;// ��ʵ����
				int fruitNum = landData.getFruitNum();// ����ʱ�Ĺ�ʵ��
				int stealNum = landData.getStealCount();// ��͵����
				if (fruitNum == 0) {// �ϴ��˳�ʱ��δ����
					fruitNum = (int) (Math.random() * 20) + 10;// ���10~30����ʵ
					landData.setFruitNum(fruitNum);
				}
				LandRW.landDataMap.put(landNum, landData);
				nowCropFruitCount = fruitNum - stealNum;// ʵ�ʹ�ʵ��=����ʱ�Ĺ�ʵ-��͵����
				return;
			}
		}

	}

	/*
	 * ����
	 */
	private void shovel() {
		if (isPick) {
			farm.flushUserMsg(0, 3);// ���Ӿ���
			labelCrop.setIcon(null);// ���ͼƬ
			// ��ʼ��������Ϣ
			landData.setCropID(-1);
			landData.setFruitNum(0);
			landData.setIsEndStage(0);
			landData.setStartTime(0);
			landData.setStealCount(0);
			landData.setStealName(" ");
			LandRW.landDataMap.put(landData.getLandID(), landData);
			LandRW.saveLandMsg(farm.nowUserID);// ����������Ϣ
			// ��ʼ����Ǳ���
			ishadbo = false;// �Ƿ��Ѿ�����
			isPick = false;// �Ƿ��Ѿ���ժ
			isRipe = false;// �Ƿ����
		} else if (landData.getCropID() == -1) {
			JOptionPane.showMessageDialog(this, "����û�ж��������ܲ���Ŷ");
			return;
		} else {
			JOptionPane.showMessageDialog(this, "��û��ժ�����ܲ���Ŷ");
			return;
		}
	}

	/*
	 * ��ժ
	 */
	private void pick() {
		if (landData.getCropID() == -1) {
			JOptionPane.showMessageDialog(this, "����ɶ��û��");
		} else if (isPick) {
			JOptionPane.showMessageDialog(this, "ũ�����Ѿ���ή��");
		} else if (!isRipe) {// δ����
			JOptionPane.showMessageDialog(this, "��Ҫ�ż�����û����");
		} else {// ����
			nowCrop = CropRW.getCrop(nowCropID);
			if (farm.nowUserID == Farm.USERID) {// �Լ���ũ��
				FruitBean fruit = new FruitBean(nowCrop, nowCropFruitCount);
				FruitRW.addTOStoreList(fruit);
				farm.flushUserMsg(0, 1);
				landData.setIsEndStage(1);
				LandRW.landDataMap.put(landNum, landData);
				LandRW.saveLandMsg(Farm.USERID);
				isPick = true;
				flushPic(nowStage);
				JOptionPane.showMessageDialog(this, "�ɹ���ժ" + nowCropFruitCount
						+ "��" + nowCrop.getName());
			} else {// ���ѵ�ũ��
				if (isSteal || landData.getStealName().contains(Farm.USERID)) {// ͵����
					JOptionPane.showMessageDialog(this, "���Ѿ�͵����~");
				} else {// û͵��
					if (nowCropFruitCount > 10) {// ��ʵ����10�ſ���͵
						int stealNum = (int) (Math.random() * 2 + 1);
						FruitBean fruit = new FruitBean(nowCrop, stealNum);
						FruitRW.addTOStoreList(fruit);
						int stealCount = landData.getStealCount() + stealNum;
						String stealName = landData.getStealName()
								+ Farm.USERID + " ";
						landData.setStealCount(stealCount);
						landData.setStealName(stealName);
						LandRW.landDataMap.put(landNum, landData);
						LandRW.saveLandMsg(farm.nowUserID);
						isSteal = true;
						nowCropFruitCount=0;
						JOptionPane.showMessageDialog(this, "�ɹ�͵ȡ" + stealNum
								+ "��" + nowCrop.getName());
					} else {
						JOptionPane.showMessageDialog(this, "�Ѿ���Ҫ��͵���ˣ����������~");
					}

				}

			}

		}
	}

	/*
	 * ����
	 */
	private void bozhong() {
		nowCropID = farm.getNowCropID();
		nowCrop = CropRW.getCrop(nowCropID);
		if (isHaveSeed()) {
			if (ishadbo == false) {// ���ػ�û���ֹ�
				landData.setCropID(nowCropID);
				landData.setLandID(landNum);
				landData.setStartTime(System.currentTimeMillis());
				FruitRW.delFromBagList(nowCropID);// ���������Ӽ�ȥһ
				FruitRW.saveBagList(farm.nowUserID);
				nowStage = 0;
				ishadbo = true;
				flushPic(nowStage);// ˢ��ͼƬ
				LandRW.landDataMap.put(landNum, landData);
				LandRW.saveLandMsg(farm.nowUserID);// ����������Ϣ
				farm.flushUserMsg(0, 5);// ���Ӿ���
				myTimer(nowCrop.getUnitTime() * 1000);
				timer.start();
			} else {
				JOptionPane.showMessageDialog(farm, "��������Ѿ����ֹ���");
			}
		} else {
			farm.setCursor(null);// ���ָ�
			JOptionPane.showMessageDialog(farm, "û��������");
			farm.setMouseCursorICon(0);
			return;
		}
	}

	/*
	 * �Զ��嶨ʱ�� ��������һ��ˢ��ʱ��
	 */
	private void myTimer(int nextTime) {
		timer = new Timer(nextTime, new ActionListener() {
			// ʱ�䵽�ˣ��׶�++��ˢ��ͼƬ
			@Override
			public void actionPerformed(ActionEvent e) {
				nowStage++;// ��û����������׶Σ���׶���++
				flushPic(nowStage);// ˢ��ͼƬ
				if (nowStage != nowCrop.getStageCount()) {
					timer.setDelay((nowStage + 1) * nowCrop.getUnitTime()
							* 1000);// �����ӳ�,��ǰ�׶ε���һ�׶�����ʱ��Ϊ��һ�׶γ��Ե�λʱ��
				} else {
					timer.stop();
					isRipe = true;// ��ʵ����
					int fruitNum = (int) (Math.random() * 20) + 10;// ���10~30����ʵ
					landData.setFruitNum(fruitNum);
					nowCropFruitCount = fruitNum;
					return;
				}
			}
		});
	}

	/*
	 * �ж��Ƿ�������
	 */
	private boolean isHaveSeed() {
		Iterator<FruitBean> it = FruitRW.bagList.iterator();
		while (it.hasNext()) {
			FruitBean nowFruit = it.next();
			if (nowFruit.getCrop().getCropId() == nowCropID) {
				return true;
			}
		}
		return false;
	}

	/*
	 * ˢ��ͼƬ �����������׶�
	 */
	private void flushPic(int stage) {
		if (stage == 0) {// ������ȥ
			labelCrop
					.setIcon(new ImageIcon(nowCrop.getCropStartPic(nowCropID)));
		} else if (isPick) {// �Ѿ���ժ
			labelCrop.setIcon(new ImageIcon(nowCrop.getCropEndPic(nowCropID)));
		} else {
			labelCrop.setIcon(new ImageIcon(nowCrop.getNowStagePic(nowCropID,
					stage)));
		}
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(200, 200));
			this.setLayout(null);
			{
				labelBackground = new JLabel();
				this.add(labelBackground);
				labelBackground.setBounds(0, 0, 200, 200);
				labelBackground.setIcon(new ImageIcon(
						"image\\guiRes\\tilled.png"));// �������ر���ͼƬ
			}
			{
				labelCrop = new JLabel();
				labelBackground.add(labelCrop);
				labelCrop.setBounds(0, 20, 200, 103);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
