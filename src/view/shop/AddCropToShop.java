package view.shop;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.farm.Farm;

import model.CropBean;

public class AddCropToShop extends javax.swing.JPanel {
	private JPanel panelEveryCrop;
	private JLabel pic;
	private JLabel level;
	private JLabel seedMoney;
	private JLabel labelLevel;
	private JLabel labelMoney;
	private JLabel name;
	private ShopJDialog shopJDialog;
	private CropBean crop;
	private Farm farm;

	/*
	 * 添加商品到面板
	 */
	public AddCropToShop(Farm farm, ShopJDialog shopjDialog, CropBean crop) {
		super();
		this.shopJDialog = shopjDialog;
		this.crop = crop;
		this.farm = farm;
		initGUI();
		panelEveryCrop.addMouseListener(new ClickAdapter());
	}

	/*
	 * 初始化界面
	 */
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(150, 125));
			this.setLayout(null);
			{
				panelEveryCrop = new JPanel();
				panelEveryCrop.setLayout(null);
				this.add(panelEveryCrop);
				panelEveryCrop.setBounds(0, 0, 150, 125);
				panelEveryCrop.setBorder(new LineBorder(new java.awt.Color(0,
						0, 0), 1, false));
				panelEveryCrop.setBackground(new java.awt.Color(255, 255, 255));
				{
					pic = new JLabel();
					panelEveryCrop.add(pic);
					pic.setBounds(12, 12, 48, 48);
					int cropID = crop.getCropId();
					pic.setIcon(new ImageIcon(crop.getPic()));
				}
				{
					name = new JLabel();
					panelEveryCrop.add(name);
					name.setText(crop.getName());
					name.setBounds(78, 28, 58, 17);
				}
				{
					labelMoney = new JLabel();
					panelEveryCrop.add(labelMoney);
					labelMoney.setText("\u5355\u4ef7\uff1a");
					labelMoney.setBounds(13, 72, 48, 17);
				}
				{
					labelLevel = new JLabel();
					panelEveryCrop.add(labelLevel);
					labelLevel.setText("\u7b49\u7ea7\uff1a");
					labelLevel.setBounds(13, 95, 48, 17);
				}
				{
					seedMoney = new JLabel();
					panelEveryCrop.add(seedMoney);
					seedMoney.setText(crop.getSeedMoney() + "");
					seedMoney.setBounds(73, 71, 64, 17);
				}
				{
					level = new JLabel();
					panelEveryCrop.add(level);
					level.setText(crop.getBuyLevel() + "");
					level.setBounds(73, 94, 58, 17);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class ClickAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			BuyFromShop buyForm = new BuyFromShop(farm, shopJDialog, crop);
			buyForm.setLocationRelativeTo(null);
			buyForm.setVisible(true);
		}

	}
}
