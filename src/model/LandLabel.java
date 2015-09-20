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
	private JLabel labelBackground;// 放土地的背景图
	private JLabel labelCrop;// 以后放每个农作物的成长阶段图片
	private int nowCropID;// 现在土地上种植的农作物的ID，当播种时可得到
	private int nowCropFruitCount = 0;// 收获时的果实数量，刚开始播种时值为零
	private int landNum;// 现在土地的编号
	private Timer timer;// 这块土地上的定时器
	private int nowStage;// 现在生长的阶段
	private Farm farm;// 农场主界面
	private CropBean nowCrop;// 土地上的农作物
	private LandData landData;// 土地数据
	private long leavetime;// 离开的时间
	private boolean ishadbo = false;// 是否已经播种
	private boolean isPick = false;// 是否已经采摘
	private boolean isRipe = false;// 是否成熟
	private boolean isSteal = false;// 是否偷过

	public LandLabel() {
		super();
	}

	public LandLabel(Farm farm, LandData landData, int landNum) {
		super();
		this.farm = farm;
		this.landData = landData;
		this.landNum = landNum;
		initGUI();
		LandRW.readLandMsg(farm.nowUserID);// 读取所有土地数据到map中
		this.landData = LandRW.landDataMap.get(landNum);// 获取指定编号的土地信息
		nowCropID = this.landData.getCropID();
		if (nowCropID != -1) {// 土地上有播种
			load(this.landData.getStartTime());// 加载土地信息,传入开始时间
		}
		this.addMouseListener(new LandMouseAdapter());
		// 因为给label控件 settooltiptext之后 crop控件会遮挡土地，
		// 所以设置监听，调用土地的事件处理
		this.labelCrop.addMouseListener(new CropMouseAdapter());
	}

	/*
	 * 农作物label适配器
	 */
	public class CropMouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			LandMouseAdapter landAdapter = new LandMouseAdapter();
			landAdapter.mouseClicked(e);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			labelCrop.setToolTipText("有" + nowCropFruitCount + "个果实");
		}
	}

	/*
	 * 土地鼠标适配器
	 */
	public class LandMouseAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			int mode = e.getModifiers();
			if ((mode & InputEvent.BUTTON1_MASK) != 0) {// 左键点击
				// 判断播种还是采摘
				if (farm.getMouseCursorICon() == 1) {// 播种
					bozhong();
				} else if (farm.getMouseCursorICon() == 2) {// 采摘
					pick();
				} else if (farm.getMouseCursorICon() == 3) {// 铲除
					shovel();
				}
			} else {
				farm.setMouseCursorICon(0);
				farm.setCursor(null);
			}
		}
	}

	/*
	 * 加载土地
	 */
	private void load(long startTime) {
		landData.setCropID(nowCropID);
		ishadbo = true;
		nowCrop = CropRW.getCrop(nowCropID);
		isRipe = true;// 是否成熟
		isPick = landData.getIsEndStage() == 1 ? true : false;// 是否已经采摘
		if (isPick) {// 已经采摘，刷新枯萎图片即可
			flushPic(5);// 参数非0即可 刷新图片用isPick 判断是否枯萎
		} else {
			long nowTime = System.currentTimeMillis();
			long dTime = nowTime - startTime;// 算出时间差
			int i;// 生长阶段
			for (i = 0; i < nowCrop.getStageCount(); i++) {
				dTime = dTime - (i + 1) * nowCrop.getUnitTime() * 1000;
				if (dTime <= 0) {// 到下一阶段时间不够，既还未成熟
					isRipe = false;
					break;
				} else {
					this.leavetime = dTime;// 剩余的时间
				}
			}
			nowStage = i;
			flushPic(nowStage);
			if (!isRipe) {// 还未成熟
				myTimer((int) leavetime);// 把扣除完当前生长阶段余下的时间设置给定时器
				timer.start();
			} else {
				isRipe = true;// 果实成熟
				int fruitNum = landData.getFruitNum();// 成熟时的果实数
				int stealNum = landData.getStealCount();// 被偷个数
				if (fruitNum == 0) {// 上次退出时还未成熟
					fruitNum = (int) (Math.random() * 20) + 10;// 随机10~30个果实
					landData.setFruitNum(fruitNum);
				}
				LandRW.landDataMap.put(landNum, landData);
				nowCropFruitCount = fruitNum - stealNum;// 实际果实数=成熟时的果实-被偷个数
				return;
			}
		}

	}

	/*
	 * 铲除
	 */
	private void shovel() {
		if (isPick) {
			farm.flushUserMsg(0, 3);// 增加经验
			labelCrop.setIcon(null);// 清空图片
			// 初始化土地信息
			landData.setCropID(-1);
			landData.setFruitNum(0);
			landData.setIsEndStage(0);
			landData.setStartTime(0);
			landData.setStealCount(0);
			landData.setStealName(" ");
			LandRW.landDataMap.put(landData.getLandID(), landData);
			LandRW.saveLandMsg(farm.nowUserID);// 保存土地信息
			// 初始化标记变量
			ishadbo = false;// 是否已经播种
			isPick = false;// 是否已经采摘
			isRipe = false;// 是否成熟
		} else if (landData.getCropID() == -1) {
			JOptionPane.showMessageDialog(this, "地里没有东西，不能铲除哦");
			return;
		} else {
			JOptionPane.showMessageDialog(this, "还没采摘，不能铲除哦");
			return;
		}
	}

	/*
	 * 采摘
	 */
	private void pick() {
		if (landData.getCropID() == -1) {
			JOptionPane.showMessageDialog(this, "地里啥都没有");
		} else if (isPick) {
			JOptionPane.showMessageDialog(this, "农作物已经枯萎了");
		} else if (!isRipe) {// 未成熟
			JOptionPane.showMessageDialog(this, "不要着急，还没熟呢");
		} else {// 成熟
			nowCrop = CropRW.getCrop(nowCropID);
			if (farm.nowUserID == Farm.USERID) {// 自己的农场
				FruitBean fruit = new FruitBean(nowCrop, nowCropFruitCount);
				FruitRW.addTOStoreList(fruit);
				farm.flushUserMsg(0, 1);
				landData.setIsEndStage(1);
				LandRW.landDataMap.put(landNum, landData);
				LandRW.saveLandMsg(Farm.USERID);
				isPick = true;
				flushPic(nowStage);
				JOptionPane.showMessageDialog(this, "成功采摘" + nowCropFruitCount
						+ "个" + nowCrop.getName());
			} else {// 好友的农场
				if (isSteal || landData.getStealName().contains(Farm.USERID)) {// 偷过了
					JOptionPane.showMessageDialog(this, "你已经偷过啦~");
				} else {// 没偷过
					if (nowCropFruitCount > 10) {// 果实大于10才可以偷
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
						JOptionPane.showMessageDialog(this, "成功偷取" + stealNum
								+ "个" + nowCrop.getName());
					} else {
						JOptionPane.showMessageDialog(this, "已经快要被偷光了，手下留情吧~");
					}

				}

			}

		}
	}

	/*
	 * 播种
	 */
	private void bozhong() {
		nowCropID = farm.getNowCropID();
		nowCrop = CropRW.getCrop(nowCropID);
		if (isHaveSeed()) {
			if (ishadbo == false) {// 土地还没播种过
				landData.setCropID(nowCropID);
				landData.setLandID(landNum);
				landData.setStartTime(System.currentTimeMillis());
				FruitRW.delFromBagList(nowCropID);// 背包里种子减去一
				FruitRW.saveBagList(farm.nowUserID);
				nowStage = 0;
				ishadbo = true;
				flushPic(nowStage);// 刷新图片
				LandRW.landDataMap.put(landNum, landData);
				LandRW.saveLandMsg(farm.nowUserID);// 保存土地信息
				farm.flushUserMsg(0, 5);// 增加经验
				myTimer(nowCrop.getUnitTime() * 1000);
				timer.start();
			} else {
				JOptionPane.showMessageDialog(farm, "这块土地已经播种过啦");
			}
		} else {
			farm.setCursor(null);// 光标恢复
			JOptionPane.showMessageDialog(farm, "没有种子了");
			farm.setMouseCursorICon(0);
			return;
		}
	}

	/*
	 * 自定义定时器 参数：下一次刷新时间
	 */
	private void myTimer(int nextTime) {
		timer = new Timer(nextTime, new ActionListener() {
			// 时间到了，阶段++，刷新图片
			@Override
			public void actionPerformed(ActionEvent e) {
				nowStage++;// 还没到最大生长阶段，则阶段数++
				flushPic(nowStage);// 刷新图片
				if (nowStage != nowCrop.getStageCount()) {
					timer.setDelay((nowStage + 1) * nowCrop.getUnitTime()
							* 1000);// 设置延迟,当前阶段到下一阶段所需时间为下一阶段乘以单位时间
				} else {
					timer.stop();
					isRipe = true;// 果实成熟
					int fruitNum = (int) (Math.random() * 20) + 10;// 随机10~30个果实
					landData.setFruitNum(fruitNum);
					nowCropFruitCount = fruitNum;
					return;
				}
			}
		});
	}

	/*
	 * 判断是否还有种子
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
	 * 刷新图片 参数：生长阶段
	 */
	private void flushPic(int stage) {
		if (stage == 0) {// 刚种下去
			labelCrop
					.setIcon(new ImageIcon(nowCrop.getCropStartPic(nowCropID)));
		} else if (isPick) {// 已经采摘
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
						"image\\guiRes\\tilled.png"));// 设置土地背景图片
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
