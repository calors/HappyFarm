package view.farm;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import model.LandData;
import model.LandLabel;
import model.UserBean;
import tools.FruitRW;
import tools.LandRW;
import tools.UserRW;
import view.bag.BagJDialog;
import view.friend.FriendList;
import view.shop.ShopJDialog;
import view.store.StoreJDialog;

public class Farm extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel panelFarm;
	private LandLabel land6;// ����
	private LandData landData6 = new LandData();// ��������
	private LandLabel land5;
	private LandData landData5 = new LandData();
	private LandLabel land4;
	private LandData landData4 = new LandData();
	private LandLabel land3;
	private LandData landData3 = new LandData();
	private LandLabel land2;
	private LandData landData2 = new LandData();
	private LandLabel land1;
	private LandData landData1 = new LandData();
	private JLabel dress;// ������
	private JLabel showUserNotice;
	private JLabel showUserLevel;
	private JLabel showLevel;
	private JLabel showUserExp;
	private JLabel showExp;
	private JLabel showUserMoney;
	private JLabel showMoney;
	private JLabel notice;// ����
	private JLabel background;// ����
	private JLabel shop;// �̵�
	private JLabel store;// �ֿ�
	private JLabel bag;// ����
	private JLabel tool;// ����
	private JLabel pick;// ��ժ
	private JLabel friends;// �����б�
	private JLabel money;// ��Ǯ����
	private JLabel exp;// ��������
	private JLabel level;// �ȼ�����
	public String nowUserID = null;// ��ǰũ���û���ID
	private int mouseCursorICon = 0;// ũ����꣬0Ĭ�ϣ�1���֣�2��ժ��3����
	private int nowCropID = -1;// ��ǰũ����ID
	public static String USERID = null;// ��¼�û���ID
	public UserBean nowUser;
	public static UserBean LOGINUSER;

	public Farm(String userID) {
		super();

		if (Farm.USERID == null || userID.equals(Farm.USERID)) {// ��¼ʱ,���ߴӺ���ũ������
			Farm.USERID = userID;
			Farm.LOGINUSER=UserRW.readUserMsg(Farm.USERID);
			this.nowUserID = userID;
			this.nowUser = UserRW.readUserMsg(nowUserID);
			this.setTitle(nowUserID + "��ũ��");
			initGUI();
			if (FruitRW.bagList.size() == 0) {// �״ε�½�ż���
				FruitRW.readBagList(nowUserID);
			}
			iconAdapter adaper = new iconAdapter();
			dress.addMouseListener(adaper);
			shop.addMouseListener(adaper);
			store.addMouseListener(adaper);
			bag.addMouseListener(adaper);
			tool.addMouseListener(adaper);
			pick.addMouseListener(adaper);
			friends.addMouseListener(adaper);
			this.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					saveData();
				}

			});
		} else {// ������ѵ�ũ��
			this.nowUserID = userID;
			this.nowUser = UserRW.readUserMsg(nowUserID);
			this.setTitle(nowUserID + "��ũ��");
			initGUI();
			updateUI();
			iconAdapter adaper = new iconAdapter();
			dress.addMouseListener(adaper);
			pick.addMouseListener(adaper);
			friends.addMouseListener(adaper);

			this.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					FruitRW.saveStoreList(Farm.USERID);// �����¼�û��Ĳֿ�����
					LandRW.saveLandMsg(nowUserID);// ����ũ���û���������Ϣ
				}

			});
		}

	}

	public void updateUI() {
		this.background.remove(tool);
		this.background.remove(shop);
		this.background.remove(bag);
		this.background.remove(store);
		this.background.revalidate();
	}

	private void saveData() {// ��������
		FruitRW.saveBagList(Farm.USERID);
		FruitRW.saveStoreList(Farm.USERID);
		LandRW.saveLandMsg(Farm.USERID);
		UserRW.saveUserMsg(LOGINUSER);
	}

	public Farm() {
		super();
	}

	public class iconAdapter extends MouseAdapter {

		@Override
		public void mouseExited(MouseEvent e) {// ����˳�����ʾСͼ�꣬ͼƬ��ż�һ
			JLabel jLabel = (JLabel) e.getSource();
			ImageIcon icon = (ImageIcon) jLabel.getIcon();
			String iconPath = (icon.getDescription());
			int num = Integer.valueOf(iconPath.substring(
					iconPath.indexOf('(') + 1, iconPath.indexOf(')')));
			jLabel.setIcon(new ImageIcon("image\\icon\\topbutton (" + (--num)
					+ ").png"));
		}

		@Override
		public void mouseEntered(MouseEvent e) {// �����룬��ʾ��ͼ�꣬ͼƬ��ż�һ
			JLabel jLabel = (JLabel) e.getSource();
			ImageIcon icon = (ImageIcon) jLabel.getIcon();
			String iconPath = (icon.getDescription());
			int num = Integer.valueOf(iconPath.substring(
					iconPath.indexOf('(') + 1, iconPath.indexOf(')')));
			jLabel.setIcon(new ImageIcon("image\\icon\\topbutton (" + (++num)
					+ ").png"));
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel jLabel = (JLabel) e.getSource();// ��ȡ�ؼ�
			int mode = e.getModifiers();
			/*
			 * ���ֲ�ͬ����갴ť���ɽ�getModifiers�ķ���ֵͬBUTTON1_MASK,
			 * BUTTON2_MASK��BUTTON3_MASK��ֵ���жԱ� ��windows����ϵͳ�У�BUTTON1��2��3����Ϊ�����Ҽ���
			 */

			if (jLabel == dress) {// װ��ؼ�
				if ((mode & InputEvent.BUTTON1_MASK) != 0) {// �ж��Ƿ�������
					ImageIcon bgIcon = (ImageIcon) background.getIcon();
					String bgPath = bgIcon.getDescription();
					String numStr = bgPath.substring(
							bgPath.lastIndexOf('\\') + 1, bgPath.indexOf('.'));
					int num = Integer.valueOf(numStr);// ��ǰ�����ļ����
					File file = new File("image\\background");
					int bgCount = file.list().length;// �����ļ�����
					if (num < bgCount) {
						background.setIcon(new ImageIcon("image\\background\\"
								+ (++num) + ".png"));
					} else if (num == bgCount) {
						background.setIcon(new ImageIcon(
								"image\\background\\1.png"));
					}
				}
			}

			if (jLabel == shop) {// �̵�
				if ((mode & InputEvent.BUTTON1_MASK) != 0) {// �ж��Ƿ�������
					ShopJDialog shop = new ShopJDialog(Farm.this);
					shop.setLocationRelativeTo(null);
					shop.setVisible(true);
				}
			}
			if (jLabel == store) {// �ֿ�
				if ((mode & InputEvent.BUTTON1_MASK) != 0) {// �ж��Ƿ�������
					StoreJDialog store = new StoreJDialog(Farm.this, nowUserID);
					store.setLocationRelativeTo(null);
					store.setVisible(true);
				}
			}
			if (jLabel == bag) {// ����
				if ((mode & InputEvent.BUTTON1_MASK) != 0) {// �ж��Ƿ�������
					BagJDialog bag = new BagJDialog(Farm.this, nowUserID);
					bag.setLocationRelativeTo(null);
					bag.setVisible(true);
				}
			}
			if (jLabel == tool) {// ����
				if ((mode & InputEvent.BUTTON1_MASK) != 0) {// ������
					ImageIcon icon = new ImageIcon(
							"image\\icon\\topbutton (6).png");
					Cursor cursor = Farm.this.getToolkit().createCustomCursor(
							icon.getImage(), new Point(0, 0), "");// ʹ��imageicon�����Զ�����
					Farm.this.setCursor(cursor);
					mouseCursorICon = 3;

				}
			}
			if (jLabel == pick) {// ��ժ
				if ((mode & InputEvent.BUTTON1_MASK) != 0) {// ������
					ImageIcon icon = new ImageIcon(
							"image\\icon\\topbutton (4).png");
					Cursor cursor = Farm.this.getToolkit().createCustomCursor(
							icon.getImage(), new Point(0, 0), "");// ʹ��imageicon�����Զ�����
					Farm.this.setCursor(cursor);
					mouseCursorICon = 2;
				}
			}
			if (jLabel == friends) {// �����б�
				if ((mode & InputEvent.BUTTON1_MASK) != 0) {
					FriendList list = new FriendList(Farm.this);
					list.setLocationRelativeTo(level);
					list.setVisible(true);
				}
			}
		}
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setResizable(false);
			{
				panelFarm = new JPanel();
				getContentPane().add(panelFarm);
				panelFarm.setLayout(null);
				panelFarm.setBounds(0, 0, 844, 721);
				{
					background = new JLabel();
					panelFarm.add(background);
					background.setLayout(null);
					background.setBounds(0, 0, 844, 721);
					background
							.setIcon(new ImageIcon("image\\background\\1.png"));
					background.addMouseListener(new MouseAdapter() {

						@Override
						public void mouseClicked(MouseEvent e) {
							int mode = e.getModifiers();
							if ((mode & InputEvent.BUTTON3_MASK) != 0) {
								Farm.this.setCursor(null);
							}
						}
					});
					{// װ��
						dress = new JLabel();
						background.add(dress);
						dress.setBounds(100, 45, 80, 80);
						dress.setIcon(new ImageIcon(
								"image\\icon\\topbutton (10).png"));
					}
					{// �̵�
						shop = new JLabel();
						background.add(shop);
						shop.setBounds(200, 45, 80, 80);
						shop.setIcon(new ImageIcon(
								"image\\icon\\topbutton (2).png"));
					}
					{// �ֿ�
						store = new JLabel();
						background.add(store);
						store.setBounds(300, 45, 80, 80);
						store.setIcon(new ImageIcon(
								"image\\icon\\topbutton (8).png"));
					}
					{// ����
						bag = new JLabel();
						background.add(bag);

						bag.setBounds(400, 45, 80, 80);
						bag.setIcon(new ImageIcon(
								"image\\icon\\topbutton (12).png"));
					}
					{// ����
						tool = new JLabel();
						background.add(tool);
						tool.setBounds(500, 45, 80, 80);
						tool.setIcon(new ImageIcon(
								"image\\icon\\topbutton (6).png"));
					}
					{// ��ժ
						pick = new JLabel();
						background.add(pick);
						pick.setBounds(600, 45, 80, 80);
						pick.setIcon(new ImageIcon(
								"image\\icon\\topbutton (4).png"));
					}
					{// �����б�
						friends = new JLabel();
						background.add(friends);
						friends.setBounds(700, 45, 80, 80);
						friends.setIcon(new ImageIcon(
								"image\\icon\\topbutton (18).png"));
					}
					{// ������
						notice = new JLabel();
						background.add(notice);
						notice.setBounds(50, 130, 250, 155);
						notice.setIcon(new ImageIcon("image\\guiRes\\paizi.png"));
						notice.setText("\u516c\u544a\u724c");
						notice.setLayout(null);
						{
							showUserNotice = new JLabel();
							notice.add(showUserNotice);
							showUserNotice.setText(nowUser.getNotice());
							//showUserNotice.setEditable(false);
							showUserNotice.setBounds(32, 30, 175, 80);
							showUserNotice.setOpaque(false);
						}
					}
					{// ��ҹ�����
						money = new JLabel();
						background.add(money);
						money.setBounds(380, 140, 135, 122);
						money.setIcon(new ImageIcon("image\\guiRes\\jiaren.png"));
						money.setText("money");
						money.setLayout(null);
						{
							showMoney = new JLabel();
							money.add(showMoney);
							showMoney.setText("\u91d1    \u5e01");
							showMoney.setBounds(0, 35, 135, 28);
							showMoney.setFont(new java.awt.Font(
									"Microsoft YaHei UI", 0, 14));
							showMoney
									.setHorizontalAlignment(SwingConstants.CENTER);
						}
						{
							showUserMoney = new JLabel();
							money.add(showUserMoney);
							showUserMoney.setText(String.valueOf(nowUser
									.getMoney()));
							showUserMoney.setBounds(0, 62, 135, 25);
							showUserMoney
									.setHorizontalAlignment(SwingConstants.CENTER);
						}
					}
					{// ���鹫����
						exp = new JLabel();
						background.add(exp);
						exp.setBounds(520, 210, 135, 122);
						exp.setIcon(new ImageIcon("image\\guiRes\\jiaren.png"));
						exp.setText("EXP");
						exp.setLayout(null);
						{
							showExp = new JLabel();
							exp.add(showExp);
							showExp.setText("\u7ecf    \u9a8c");
							showExp.setFont(new java.awt.Font(
									"Microsoft YaHei UI", 0, 14));
							showExp.setBounds(0, 38, 135, 21);
							showExp.setHorizontalAlignment(SwingConstants.CENTER);
						}
						{
							showUserExp = new JLabel();
							exp.add(showUserExp);
							showUserExp
									.setText(String.valueOf(nowUser.getExp()));
							showUserExp.setBounds(0, 65, 135, 22);
							showUserExp
									.setHorizontalAlignment(SwingConstants.CENTER);
						}
					}
					{// �ȼ�������
						level = new JLabel();
						background.add(level);
						level.setBounds(670, 260, 135, 122);
						level.setIcon(new ImageIcon("image\\guiRes\\jiaren.png"));
						level.setLayout(null);
						{
							showLevel = new JLabel();
							level.add(showLevel);
							showLevel.setText("\u7b49   \u7ea7");
							showLevel.setBounds(0, 35, 135, 27);
							showLevel.setFont(new java.awt.Font(
									"Microsoft YaHei UI", 0, 14));
							showLevel
									.setHorizontalAlignment(SwingConstants.CENTER);
						}
						{
							showUserLevel = new JLabel();
							level.add(showUserLevel);
							showUserLevel.setText(String.valueOf(nowUser
									.getLevel()));
							showUserLevel.setBounds(0, 65, 135, 18);
							showUserLevel
									.setHorizontalAlignment(SwingConstants.CENTER);
						}
					}
					{
						land1 = new LandLabel(Farm.this, landData1, 1);
						background.add(land1);
						land1.setText("jLabel1");
						land1.setBounds(115, 315, 200, 200);
					}
					{
						land2 = new LandLabel(Farm.this, landData2, 2);
						background.add(land2);
						land2.setText("jLabel2");
						land2.setBounds(285, 235, 200, 200);
					}
					{
						land3 = new LandLabel(Farm.this, landData3, 3);
						background.add(land3);
						land3.setText("jLabel3");
						land3.setBounds(305, 410, 200, 200);
					}
					{
						land4 = new LandLabel(Farm.this, landData4, 4);
						background.add(land4);
						land4.setText("jLabel4");
						land4.setBounds(450, 315, 200, 200);
					}
					{
						land6 = new LandLabel(Farm.this, landData5, 5);
						background.add(land6);
						land6.setText("jLabel6");
						land6.setBounds(630, 400, 200, 200);
					}
					{
						land5 = new LandLabel(Farm.this, landData6, 6);
						background.add(land5);
						land5.setText("jLabe5");
						land5.setBounds(485, 500, 200, 200);
					}
				}// end background

			}// end panel
			pack();
			this.setSize(850, 750);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getMouseCursorICon() {
		return mouseCursorICon;
	}

	public void setMouseCursorICon(int mouseCursorICon) {
		this.mouseCursorICon = mouseCursorICon;
	}

	public int getNowCropID() {
		return nowCropID;
	}

	public void setNowCropID(int nowCropID) {
		this.nowCropID = nowCropID;
	}

	/*
	 * ˢ���û���Ϣ������ �ȼ������飬��ҵȡ� ���� type :���ͣ�ʶ����Ӿ��飬����������0�����Ӿ��飬�ȼ� 1��ˢ�½�ң�2��ˢ����ʾ
	 * ����num Ҫ��ӵľ���ֵ
	 */
	public void flushUserMsg(int type, int num) {
		if (type == 0) {
			int exp = LOGINUSER.getExp() + num;
			int level = LOGINUSER.getLevel();
			if (exp >= level * 200) {
				level++;
				exp = exp - (level - 1) * 200;
			}
			LOGINUSER.setExp(exp);
			LOGINUSER.setLevel(level);
			showUserExp.setText(exp + "");
			showUserLevel.setText(level + "");

		} else if (type == 1) {
			showUserMoney.setText(LOGINUSER.getMoney()
					+ "");
		} else {//���¹���
			showUserNotice.setText(LOGINUSER.getNotice());
		}
	}

	public JPanel getPanelFarm() {
		return panelFarm;
	}

}
