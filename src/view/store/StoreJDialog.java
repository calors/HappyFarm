package view.store;

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
public class StoreJDialog extends javax.swing.JDialog {

	private Farm farm;
	private String userID;
	public static JPanel panelStore;

	public StoreJDialog(Farm farm, String userID) {
		super(farm, true);
		initGUI();
		this.farm = farm;
		this.userID = userID;

		if (FruitRW.storeList.isEmpty()) {// 首次加载
			FruitRW.readStoreList(this.userID);// 获取用户果实信息到FruitRW的storelist中
		}

		for (FruitBean fruit : FruitRW.storeList) {// 遍历列表，添加到仓库面板上
			StoreJDialog.panelStore.add(new AddToStore(this.farm,
					StoreJDialog.this, fruit));
		}
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("我的仓库");
				this.setResizable(false);
			}
			{
				panelStore = new JPanel();
				FlowLayout panelBagLayout = new FlowLayout();
				panelStore.setLayout(panelBagLayout);
				getContentPane().add(panelStore, "Center");
				panelStore.setBounds(0, 0, 694, 571);
				panelStore.setBackground(new java.awt.Color(255, 255, 255));
			}
			setSize(700, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel getPanelStore() {
		return panelStore;
	}

}
