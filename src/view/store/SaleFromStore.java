package view.store;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.CropBean;
import model.FruitBean;
import model.UserBean;
import tools.Check;
import tools.FruitRW;
import tools.UserRW;
import view.farm.Farm;

public class SaleFromStore extends javax.swing.JDialog {
	private JPanel panelSale;
	private JLabel pic;
	private JLabel name;
	private JLabel labelSeedMoney;
	private JLabel saleMoney;
	private JLabel fruitNum;
	private JLabel labelFruitNum;
	private JButton btnExit;
	private JButton btnSale;
	private JTextField num;
	private JLabel labelSaleNum;
	private CropBean crop;
	private FruitBean fruit;
	private StoreJDialog storeJDialog;
	private Farm farm;
	private static UserBean user;

	public SaleFromStore(Farm farm, StoreJDialog storeJDialog, FruitBean fruit) {
		super(storeJDialog, true);
		this.fruit = fruit;
		this.storeJDialog = storeJDialog;
		this.farm = farm;
		user = farm.LOGINUSER;
		crop = fruit.getCrop();
		initGUI();
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("卖出果实");
				getContentPane().setBackground(
						new java.awt.Color(255, 255, 255));
				this.setResizable(false);
			}
			{
				panelSale = new JPanel();
				getContentPane().add(panelSale, "Center");
				panelSale.setLayout(null);
				panelSale.setBounds(7, 0, 227, 169);
				panelSale.setBackground(new java.awt.Color(255, 255, 255));
				{
					pic = new JLabel();
					panelSale.add(pic);
					pic.setText("pic");
					pic.setBounds(17, 18, 48, 48);
					pic.setIcon(new ImageIcon(crop.getCropFruitPic(crop
							.getCropId())));
				}
				{
					name = new JLabel();
					panelSale.add(name);
					name.setText(String.valueOf(crop.getName()));
					name.setBounds(121, 17, 55, 20);
				}
				{
					labelSeedMoney = new JLabel();
					panelSale.add(labelSeedMoney);
					labelSeedMoney.setText("\u679c\u5b9e\u5355\u4ef7\uff1a");
					labelSeedMoney.setBounds(77, 44, 65, 17);
				}
				{
					saleMoney = new JLabel();
					panelSale.add(saleMoney);
					saleMoney.setText(String.valueOf(crop.getSellMoney()));
					saleMoney.setBounds(142, 44, 68, 17);
				}
				{
					labelSaleNum = new JLabel();
					panelSale.add(labelSaleNum);
					labelSaleNum
							.setText("\u5356\u51fa\u6570\u91cf\uff080~999\uff09\uff1a");
					labelSaleNum.setBounds(9, 91, 141, 21);
				}
				{
					num = new JTextField();
					panelSale.add(num);
					num.setBounds(138, 90, 68, 24);
				}
				{
					btnSale = new JButton();
					panelSale.add(btnSale);
					btnSale.setText("\u5356 \u51fa");
					btnSale.setBounds(30, 130, 65, 25);
					btnSale.addMouseListener(new MouseAdapter() {

						@Override
						public void mouseClicked(MouseEvent e) {
							String numString = num.getText();
							double sellMoney = crop.getSellMoney();
							double userMoney = user.getMoney();
							int saleNum = 0;
							if (Check.checkISNUM(numString)) {
								saleNum = Integer.valueOf(numString);
								if (saleNum <= fruit.getCount()) {
									userMoney += saleNum * sellMoney;
									user.setMoney(userMoney);
									FruitRW.delFromStoreList(crop.getCropId(),
											saleNum);
									FruitRW.saveStoreList(farm.nowUserID);
									JOptionPane.showMessageDialog(
											SaleFromStore.this, "收购完了，老板慢走。");
									farm.flushUserMsg(1, 0);
									dispose();
									storeJDialog.dispose();
								} else {// 数量超过仓库数量
									JOptionPane.showMessageDialog(
											SaleFromStore.this,
											"囧。好像没有那么多可以卖呢~");
								}
							}// end check
							else {
								JOptionPane
										.showMessageDialog(SaleFromStore.this,
												"哦？小二智商着急，没理解您要卖多少");
								return;
							}

						}

					});
				}
				{
					btnExit = new JButton();
					panelSale.add(btnExit);
					btnExit.setText("\u5173 \u95ed");
					btnExit.setBounds(130, 130, 65, 25);
					btnExit.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
				}
				{
					labelFruitNum = new JLabel();
					panelSale.add(labelFruitNum);
					labelFruitNum.setText("\u679c\u5b9e\u6570\u91cf\uff1a");
					labelFruitNum.setBounds(76, 67, 65, 17);
				}
				{
					fruitNum = new JLabel();
					panelSale.add(fruitNum);
					fruitNum.setText(String.valueOf(fruit.getCount()));
					fruitNum.setBounds(147, 67, 63, 17);
				}
			}
			this.setSize(250, 210);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
