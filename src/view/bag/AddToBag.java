package view.bag;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.FruitBean;
import view.farm.Farm;

public class AddToBag extends javax.swing.JPanel {
	private JLabel pic;
	private JLabel name;
	private JLabel labelNum;
	private JLabel num;
	private JPanel panelEverySeed;
	private FruitBean fruit;
	private BagJDialog bagJDialog;
	private int nowCropID;
	private Farm farm;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */

	public AddToBag(Farm farm, BagJDialog bagJDialog, FruitBean fruit) {
		super();
		this.fruit = fruit;
		this.bagJDialog = bagJDialog;
		this.farm = farm;
		AddToBag.this.addMouseListener(new ClickAdapter());// 当前小面板添加监听
		initGUI();

	}

	public class ClickAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			int mode = e.getModifiers();
			if ((mode & InputEvent.BUTTON1_MASK) != 0) {
				nowCropID = fruit.getCrop().getCropId();// 获取到当前农作物ID
				String iconPath = "image\\crops\\crop" + nowCropID
						+ "\\seed.png";
				ImageIcon cropIcon = new ImageIcon(iconPath);// 获取到当前农作物的icon
				Cursor cursor = farm.getToolkit().createCustomCursor(
						cropIcon.getImage(), new Point(0, 0), "bo");// 创建自定义光标
				farm.setCursor(cursor);// 给农场设上光标
				farm.setNowCropID(nowCropID);// 要播种的种子ID
				farm.setMouseCursorICon(1);// 鼠标标记设为1
				bagJDialog.dispose();
			}
		}
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(150, 125));
			this.setLayout(null);
			{
				panelEverySeed = new JPanel();
				panelEverySeed.setLayout(null);
				this.add(panelEverySeed);
				panelEverySeed.setBounds(0, 0, 150, 125);
				panelEverySeed.setBorder(new LineBorder(new java.awt.Color(0,
						0, 0), 1, false));
				panelEverySeed.setBackground(new java.awt.Color(255, 255, 255));
				this.add(panelEverySeed);
				{
					pic = new JLabel();
					panelEverySeed.add(pic);
					pic.setBounds(18, 22, 48, 48);
					int cropID = fruit.getCrop().getCropId();
					pic.setIcon(new ImageIcon(fruit.getCrop().getCropFruitPic(
							cropID)));
				}
				{
					name = new JLabel();
					panelEverySeed.add(name);
					name.setText(fruit.getCrop().getName());
					name.setBounds(72, 38, 58, 17);
				}
				{
					labelNum = new JLabel();
					panelEverySeed.add(labelNum);
					labelNum.setText("\u6570\u91cf\uff1a");
					labelNum.setBounds(30, 82, 48, 21);
				}
				{
					num = new JLabel();
					panelEverySeed.add(num);
					num.setText(String.valueOf(fruit.getCount()));
					num.setBounds(78, 82, 52, 21);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
