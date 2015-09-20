package view.shop;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tools.Check;
import tools.FruitRW;
import tools.UserRW;
import view.farm.Farm;

import model.CropBean;
import model.FruitBean;
import model.UserBean;

public class BuyFromShop extends javax.swing.JDialog {
	private JPanel panelBuyForm;
	private JLabel pic;
	private JLabel name;
	private JLabel labelSeedMoney;
	private JLabel seedMoney;
	private JButton btnExit;
	private JButton btnBuy;
	private JTextField num;
	private JLabel labelNum;
	private JLabel level;
	private JLabel labelLevel;
	private CropBean crop;
	private ShopJDialog shopJDialog;
	private Farm farm;
	private static UserBean user;

	public BuyFromShop(Farm farm, ShopJDialog shopJDialog, CropBean crop) {
		super(shopJDialog, true);
		this.crop = crop;
		this.shopJDialog = shopJDialog;
		this.farm = farm;
		user = farm.LOGINUSER;
		initGUI();
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("购买种子");
				getContentPane().setBackground(
						new java.awt.Color(255, 255, 255));
				this.setResizable(false);
			}
			{
				panelBuyForm = new JPanel();
				getContentPane().add(panelBuyForm, "Center");
				panelBuyForm.setLayout(null);
				panelBuyForm.setBounds(7, 0, 227, 169);
				panelBuyForm.setBackground(new java.awt.Color(255, 255, 255));
				{
					pic = new JLabel();
					panelBuyForm.add(pic);
					pic.setText("pic");
					pic.setBounds(17, 18, 48, 48);
					pic.setIcon(new ImageIcon(crop.getCropFruitPic(crop
							.getCropId())));
				}
				{
					name = new JLabel();
					panelBuyForm.add(name);
					name.setText(String.valueOf(crop.getName()));
					name.setBounds(121, 17, 55, 20);
				}
				{
					labelSeedMoney = new JLabel();
					panelBuyForm.add(labelSeedMoney);
					labelSeedMoney.setText("\u79cd\u5b50\u5355\u4ef7\uff1a");
					labelSeedMoney.setBounds(77, 49, 65, 17);
				}
				{
					labelLevel = new JLabel();
					panelBuyForm.add(labelLevel);
					labelLevel.setText("\u6240\u9700\u7b49\u7ea7\uff1a");
					labelLevel.setBounds(77, 72, 65, 17);
				}
				{
					seedMoney = new JLabel();
					panelBuyForm.add(seedMoney);
					seedMoney.setText(String.valueOf(crop.getSeedMoney()));
					seedMoney.setBounds(142, 49, 68, 17);
				}
				{
					level = new JLabel();
					panelBuyForm.add(level);
					level.setText(String.valueOf(crop.getBuyLevel()));
					level.setBounds(142, 72, 68, 17);
				}
				{
					labelNum = new JLabel();
					panelBuyForm.add(labelNum);
					labelNum.setText("\u8d2d\u4e70\u6570\u91cf\uff081~99\uff09\uff1a");
					labelNum.setBounds(12, 101, 130, 21);
				}
				{
					num = new JTextField();
					panelBuyForm.add(num);
					num.setBounds(142, 100, 68, 24);
				}
				{
					btnBuy = new JButton();
					panelBuyForm.add(btnBuy);
					btnBuy.setText("\u8d2d \u4e70");
					btnBuy.setBounds(30, 130, 65, 25);
					btnBuy.addMouseListener(new MouseAdapter() {

						@Override
						public void mouseClicked(MouseEvent e) {
							String numString = num.getText();
							double seedMoney = crop.getSeedMoney();
							double userMoney = user.getMoney();
							int seedNum = 0;
							if (Check.checkISNUM(numString)) {
								seedNum = Integer.valueOf(numString);
								// 输入不符合规则0~99
								if (seedNum > 99 || seedNum < 1) {
									JOptionPane.showMessageDialog(
											BuyFromShop.this, "亲，库存不足啦");
									return;
								}
								if (seedNum * seedMoney <= userMoney) {// 购买
									FruitBean fruit = new FruitBean(crop,
											seedNum);
									FruitRW.addTObagList(fruit);
									user.setMoney(userMoney
											- (seedNum * seedMoney));
									JOptionPane.showMessageDialog(
											BuyFromShop.this,
											"大富豪，购买成功啦，快到背包看看");
									FruitRW.saveBagList(farm.nowUserID);
									farm.flushUserMsg(1, 0);// 刷新金币
									dispose();
								} else {// 钱不够
									JOptionPane.showMessageDialog(
											BuyFromShop.this,
											"哎呀，钱好像不够了，再去挣点钱再来吧");
								}
							} else {

								JOptionPane.showMessageDialog(BuyFromShop.this,
										"哦？小二智商着急，没理解您要买多少");

							}

						}

					});
				}
				{
					btnExit = new JButton();
					panelBuyForm.add(btnExit);
					btnExit.setText("\u5173 \u95ed");
					btnExit.setBounds(130, 130, 65, 25);
					btnExit.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
				}
			}
			this.setSize(250, 210);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
