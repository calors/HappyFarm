package view.bag;

import java.awt.FlowLayout;
import javax.swing.JPanel;

import model.FruitBean;

import tools.FruitRW;
import view.farm.Farm;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class BagJDialog extends javax.swing.JDialog {
	private Farm farm;
	private String userID;
	private JPanel panelBag;

	/**
	 * Auto-generated main method to display this JDialog
	 */

	public BagJDialog(Farm farm, String userID) {
		super(farm, true);
		initGUI();
		this.farm = farm;
		this.userID = userID;

		if (FruitRW.bagList.isEmpty()) {// 首次加载
			FruitRW.readBagList(this.userID);// 获取用户种子信息到FruitRW的baglist中
		}

		for (FruitBean fruit : FruitRW.bagList) {
			this.panelBag.add(new AddToBag(this.farm, BagJDialog.this, fruit));
		}

	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("我的背包");
				this.setResizable(false);
			}
			{
				panelBag = new JPanel();
				FlowLayout panelBagLayout = new FlowLayout();
				panelBag.setLayout(panelBagLayout);
				getContentPane().add(panelBag, "Center");
				panelBag.setBounds(0, 0, 694, 571);
				panelBag.setBackground(new java.awt.Color(255, 255, 255));
			}
			setSize(700, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
