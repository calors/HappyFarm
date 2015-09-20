package view.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.UserBean;

import tools.Check;
import tools.UserRW;
import view.farm.Farm;

public class ChangeUserPass extends javax.swing.JDialog {
	private JPanel panelChangePass;
	private JPasswordField oldPass;
	private JLabel labUserID;
	private JButton btnExit;
	private JButton btnSave;
	private JPasswordField newPass;
	private JLabel labNewPass;
	private JLabel logo;
	private JTextField userID;
	private JLabel labOldPass;
	private JLabel labelPassCheck;
	private JPasswordField passCheck;
	private UserBean user = null;
	private Farm farm = null;

	public ChangeUserPass(Farm farm) {
		super(farm, true);
		this.setTitle("修改密码");
		this.farm = farm;
		this.user = farm.LOGINUSER;

		initGUI();
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setResizable(false);
				{
					panelChangePass = new JPanel();
					getContentPane().add(panelChangePass);
					panelChangePass.setLayout(null);
					panelChangePass.setBounds(0, 0, 371, 360);
					panelChangePass.setBackground(new java.awt.Color(255, 255,
							255));
					{
						passCheck = new JPasswordField();
						panelChangePass.add(passCheck);
						passCheck.setBounds(135, 257, 178, 24);
					}
					{
						labelPassCheck = new JLabel();
						panelChangePass.add(labelPassCheck);
						labelPassCheck
								.setText("\u786e\u8ba4\u5bc6\u7801\uff1a");
						labelPassCheck.setBounds(43, 258, 80, 23);
					}
					{
						oldPass = new JPasswordField();
						panelChangePass.add(oldPass);
						oldPass.setBounds(135, 166, 180, 24);
					}
					{
						labOldPass = new JLabel();
						panelChangePass.add(labOldPass);
						labOldPass.setText("\u539f\u5bc6\u7801\uff1a");
						labOldPass.setBounds(45, 169, 72, 21);
					}
					{
						userID = new JTextField();
						panelChangePass.add(userID);
						userID.setBounds(135, 118, 180, 24);
						userID.setEditable(false);
						userID.setText(user.getUserID());
					}
					{
						labUserID = new JLabel();
						panelChangePass.add(labUserID);
						labUserID.setText("\u7528\u6237\u540d\uff1a");
						labUserID.setBounds(45, 118, 72, 22);
					}
					{
						logo = new JLabel();
						panelChangePass.add(logo);
						logo.setIcon(new ImageIcon("image\\guiRes\\logo.jpg"));
						logo.setLayout(null);
						logo.setBounds(110, 24, 171, 71);
					}
					{
						labNewPass = new JLabel();
						panelChangePass.add(labNewPass);
						labNewPass.setText("\u65b0\u5bc6\u7801\uff1a");
						labNewPass.setBounds(45, 216, 72, 21);
					}
					{
						newPass = new JPasswordField();
						panelChangePass.add(newPass);
						newPass.setBounds(135, 213, 178, 24);
					}
					{
						btnSave = new JButton();
						panelChangePass.add(btnSave);
						btnSave.setText("\u4fdd \u5b58");
						btnSave.setBounds(85, 293, 75, 34);
						btnSave.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								String userOldPass = user.getPass();
								String oldPassInput = String.valueOf(oldPass
										.getPassword());
								String userNewPass = String.valueOf(newPass
										.getPassword());
								String newPassCheck = String.valueOf(passCheck
										.getPassword());
								if (!Check.checkPass(userNewPass)) {// 密码规则验证
									JOptionPane.showMessageDialog(farm,
											"密码格式错误，请重新输入");
									return;
								}
								if (userOldPass.equals(oldPassInput)) {// 旧密码验证
									if (userNewPass.equals(newPassCheck)) {// 重复密码验证
										JOptionPane.showMessageDialog(farm,
												"修改成功");
										user.setPass(userNewPass);
										UserRW.saveUserMsg(user);// 保存信息
										dispose();
									} else {
										JOptionPane.showMessageDialog(farm,
												"请检查两次输入密码是否一致");
										oldPass.setText("");
										newPass.setText("");
										passCheck.setText("");
									}
								} else {
									JOptionPane.showMessageDialog(farm,
											"原密码输入错误");
									oldPass.setText("");
									newPass.setText("");
									passCheck.setText("");
								}
							}
						});
					}
					{
						btnExit = new JButton();
						panelChangePass.add(btnExit);
						btnExit.setText("\u5173\u95ed");
						btnExit.setBounds(198, 293, 83, 34);
						btnExit.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								dispose();
							}
						});
					}
				}
			}
			this.setSize(377, 389);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
