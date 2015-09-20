package view.store;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import model.FruitBean;
import view.farm.Farm;
import view.store.SaleFromStore;

public class AddToStore extends javax.swing.JPanel {

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		// frame.getContentPane().add(new AddToStore());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	private FruitBean fruit;
	private StoreJDialog storeJDialog;
	private JPanel panelEvery;
	private JLabel pic;
	private JLabel name;
	private JLabel labelNum;
	private JLabel num;
	private Farm farm;

	public AddToStore(Farm farm, StoreJDialog storeJDialog, FruitBean fruit) {
		super();
		this.fruit = fruit;
		this.storeJDialog = storeJDialog;
		this.farm = farm;
		AddToStore.this.addMouseListener(new ClickAdapter());// 当前小面板添加监听
		initGUI();
	}

	public class ClickAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			SaleFromStore saleFromStore = new SaleFromStore(farm, storeJDialog,
					fruit);
			saleFromStore.setLocationRelativeTo(null);
			saleFromStore.setVisible(true);
		}
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(150, 125));
			this.setLayout(null);
			{
				panelEvery = new JPanel();
				panelEvery.setLayout(null);
				this.add(panelEvery);
				panelEvery.setBounds(0, 0, 150, 125);
				panelEvery.setBorder(new LineBorder(
						new java.awt.Color(0, 0, 0), 1, false));
				panelEvery.setBackground(new java.awt.Color(255, 255, 255));
				this.add(panelEvery);
				{
					pic = new JLabel();
					panelEvery.add(pic);
					pic.setBounds(18, 22, 48, 48);
					int cropID = fruit.getCrop().getCropId();
					pic.setIcon(new ImageIcon(fruit.getCrop().getCropFruitPic(
							cropID)));
				}
				{
					name = new JLabel();
					panelEvery.add(name);
					name.setText(fruit.getCrop().getName());
					name.setBounds(72, 38, 58, 17);
				}
				{
					labelNum = new JLabel();
					panelEvery.add(labelNum);
					labelNum.setText("\u6570\u91cf\uff1a");
					labelNum.setBounds(30, 82, 48, 21);
				}
				{
					num = new JLabel();
					panelEvery.add(num);
					num.setText(String.valueOf(fruit.getCount()));
					num.setBounds(78, 82, 52, 21);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
