package view.user;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Item;
import model.JComboBox_Image;
import model.UserBean;

import tools.UserRW;
import view.farm.Farm;

public class ChangeUserMsg extends javax.swing.JDialog {
	private JLabel logo;
	private JLabel labUserID;
	private JButton btnExit;
	private JButton btnSave;
	private JComboBox pic;
	private JLabel labPic;
	private JTextField notice;
	private JLabel labNotice;
	private JTextField nick;
	private JLabel labNick;
	private JLabel changeUserPass;
	private JPanel panelChangeMsg;
	private JTextField userName;
	private JLabel labUserName;
	private JTextField userID;
	private Farm farm;
	private UserBean user;
	private String strNotice = "";
	private String strNick = "";
	private String strPic = "";
	private String strUserName = "";

	public ChangeUserMsg(Farm farm) {
		super(farm, true);
		this.farm = farm;
		this.setTitle("修改用户信息");
		user = farm.LOGINUSER;
		initGUI();

		textAdapter adapter = new textAdapter();
		nick.addMouseListener(adapter);
		notice.addMouseListener(adapter);
		userName.addMouseListener(adapter);
	}

	public class textAdapter extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			JTextField textField = (JTextField) e.getSource();
			textField.setText("");
		}

	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setResizable(false);
				{
					panelChangeMsg = new JPanel();
					getContentPane().add(panelChangeMsg);
					panelChangeMsg.setLayout(null);
					panelChangeMsg.setBounds(0, 0, 381, 405);
					panelChangeMsg.setBackground(new java.awt.Color(255, 255,
							255));
					{
						logo = new JLabel();
						panelChangeMsg.add(logo);
						logo.setIcon(new ImageIcon("image\\guiRes\\logo.jpg"));
						logo.setLayout(null);
						logo.setBounds(103, 19, 176, 69);
					}
					{
						labUserID = new JLabel();
						panelChangeMsg.add(labUserID);
						labUserID.setText("\u767b\u5f55\u7528\u6237\uff1a");
						labUserID.setBounds(56, 107, 69, 21);
					}
					{
						userID = new JTextField();
						panelChangeMsg.add(userID);
						userID.setBounds(143, 106, 183, 24);
						userID.setEditable(false);
						userID.setText(user.getUserID());
					}
					{
						labUserName = new JLabel();
						panelChangeMsg.add(labUserName);
						labUserName.setText("\u7528\u6237\u6635\u79f0\uff1a");
						labUserName.setBounds(56, 148, 69, 21);
					}
					{
						userName = new JTextField();
						panelChangeMsg.add(userName);
						userName.setBounds(143, 147, 183, 24);
						userName.setText(user.getUserName());
					}
					{
						labNick = new JLabel();
						panelChangeMsg.add(labNick);
						labNick.setText("\u4e2a\u6027\u7b7e\u540d\uff1a");
						labNick.setBounds(56, 192, 82, 19);
					}
					{
						nick = new JTextField();
						panelChangeMsg.add(nick);
						nick.setBounds(143, 190, 183, 24);
						nick.setText(user.getNick());
					}
					{
						labNotice = new JLabel();
						panelChangeMsg.add(labNotice);
						labNotice.setText("\u516c\u544a\u724c\uff1a");
						labNotice.setBounds(56, 236, 69, 20);
					}
					{
						notice = new JTextField();
						panelChangeMsg.add(notice);
						notice.setBounds(143, 235, 183, 24);
						notice.setText(user.getNotice());
					}
					{
						labPic = new JLabel();
						panelChangeMsg.add(labPic);
						labPic.setText("\u7528\u6237\u5934\u50cf\uff1a");
						labPic.setBounds(56, 276, 82, 20);
					}
					{
						btnSave = new JButton();
						panelChangeMsg.add(btnSave);
						btnSave.setText("\u4fdd \u5b58");
						btnSave.setBounds(74, 339, 78, 28);
						btnSave.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								strNotice = notice.getText().trim();
								strNick = nick.getText().trim();
								strUserName = userName.getText().trim();
								user.setNick(strNick);
								user.setNotice(strNotice);
								user.setUserName(strUserName);
								UserRW.saveUserMsg(user);// 保存信息
								farm.flushUserMsg(2, 0);// 更新公告
								JOptionPane.showMessageDialog(farm, "保存成功");
								ChangeUserMsg.this.dispose();
							}
						});
					}
					{
						btnExit = new JButton();
						panelChangeMsg.add(btnExit);
						btnExit.setText("\u5173 \u95ed");
						btnExit.setBounds(226, 339, 83, 28);
						btnExit.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								dispose();
							}
						});
					}
					{
						DefaultComboBoxModel picModel = new DefaultComboBoxModel(
								UserRW.getAllFacePic());// 设计数据模型，导入数据
						pic = new JComboBox_Image();// JF_Image继承于JComboBox
						panelChangeMsg.add(pic);
						pic.setModel(picModel);
						pic.setBounds(143, 274, 68, 45);
						pic.addItemListener(new ItemListener() {

							@Override
							public void itemStateChanged(ItemEvent e) {
								if (e.getStateChange() == 1) {
									Item item = (Item) e.getItem();
									strPic = item.getPicPath();
									user.setPic(strPic);
								}

							}
						});
					}
					{
						changeUserPass = new JLabel();
						panelChangeMsg.add(changeUserPass);
						changeUserPass
								.setText("<HTML><U>\u4fee\u6539\u7528\u6237\u5bc6\u7801</U></HTML>");
						changeUserPass.setForeground(Color.blue);
						changeUserPass.setBounds(258, 372, 111, 27);
						changeUserPass.setFont(new java.awt.Font(
								"Microsoft YaHei UI", 0, 14));
						changeUserPass
								.setHorizontalAlignment(SwingConstants.TRAILING);
						changeUserPass.addMouseListener(new MouseAdapter() {

							@Override
							public void mouseClicked(MouseEvent e) {
								ChangeUserPass changePass = new ChangeUserPass(
										farm);
								changePass.setLocationRelativeTo(null);
								changePass.setVisible(true);
							}

						});
					}
				}
			}
			this.setSize(387, 434);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
