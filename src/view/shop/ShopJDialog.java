package view.shop;

import java.awt.FlowLayout;
import javax.swing.JPanel;

import model.CropBean;

import tools.CropRW;
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
public class ShopJDialog extends javax.swing.JDialog {
	private JPanel panelShop;
	private Farm farm;

	public ShopJDialog(Farm farm) {
		super(farm, true);
		this.farm = farm;
		initGUI();
		if (CropRW.cropList == null) {// 首次加载
			CropRW.readCrop();// 获取商品
		}

		for (CropBean crop : CropRW.cropList) {
			this.panelShop.add(new AddCropToShop(farm, ShopJDialog.this, crop));
		}

	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("商店");
				this.setResizable(false);
			}
			{
				panelShop = new JPanel();
				FlowLayout panelShopLayout = new FlowLayout();
				panelShop.setLayout(panelShopLayout);
				getContentPane().add(panelShop, "Center");
				panelShop.setBounds(0, 0, 694, 571);
				panelShop.setBackground(new java.awt.Color(255, 255, 255));
			}
			setSize(700, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel getPanelShop() {
		return panelShop;
	}

}
