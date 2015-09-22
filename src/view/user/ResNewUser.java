package view.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.UserBean;

import tools.Check;
import tools.LandRW;
import tools.UserRW;
import view.farm.Farm;

public class ResNewUser extends javax.swing.JDialog {
	private JPanel panelNewUser;
	private JButton btnExit;
	private JButton btnRegister;
	private JPasswordField passCheck;
	private JLabel labelPassCheck;
	private JPasswordField pass;
	private JLabel labPass;
	private JTextField ID;
	private JLabel labUserID;
	private JLabel logo;
	private Login login;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public ResNewUser(Login login) {
		super(login, true);
		this.setTitle("用户注册");
		initGUI();
		this.login = login;
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setResizable(false);
				{
					panelNewUser = new JPanel();
					getContentPane().add(panelNewUser);
					panelNewUser.setLayout(null);
					panelNewUser.setBounds(0, 0, 362, 346);
					panelNewUser
							.setBackground(new java.awt.Color(255, 255, 255));
					{
						logo = new JLabel();
						logo.setIcon(new ImageIcon("image\\guiRes\\logo.jpg"));
						logo.setLayout(null);
						logo.setBounds(110, 24, 171, 71);
						panelNewUser.add(logo);
					}
					{
						labUserID = new JLabel();
						panelNewUser.add(labUserID);
						labUserID.setText("\u7528\u6237\u540d\uff1a");
						labUserID.setBounds(45, 118, 72, 22);
					}
					{
						ID = new JTextField();
						panelNewUser.add(ID);
						ID.setBounds(135, 118, 180, 24);
					}
					{
						labPass = new JLabel();
						panelNewUser.add(labPass);
						labPass.setText("\u5bc6 \u7801\uff1a");
						labPass.setBounds(45, 169, 46, 21);
					}
					{
						pass = new JPasswordField();
						panelNewUser.add(pass);
						pass.setBounds(135, 166, 180, 24);
					}
					{
						labelPassCheck = new JLabel();
						panelNewUser.add(labelPassCheck);
						labelPassCheck
								.setText("\u786e\u8ba4\u5bc6\u7801\uff1a");
						labelPassCheck.setBounds(45, 223, 80, 21);
					}
					{
						passCheck = new JPasswordField();
						panelNewUser.add(passCheck);
						passCheck.setBounds(137, 220, 178, 24);
					}
					{
						btnRegister = new JButton();
						panelNewUser.add(btnRegister);
						btnRegister.setText("\u6ce8 \u518c");
						btnRegister.setBounds(82, 286, 72, 33);
						btnRegister.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								String userID = ID.getText().trim();
								if (!Check.checkName(userID)) {// 用户名验证

									JOptionPane.showMessageDialog(
											ResNewUser.this,
											"亲，回去看看，用户名必须为2~15位");
									ID.setText("");
									pass.setText("");
									passCheck.setText("");
								} else {// 用户名检测通过
									String userPass = String.valueOf(pass
											.getPassword());
									String userPassCheck = String
											.valueOf(passCheck.getPassword());
									File file = new File("user\\" + userID);
									if (!file.exists()) {// 用户不存在，可注册
										if (userPass.equals(userPassCheck)
												&& Check.checkPass(userPass)) {// 密码验证
											UserRW.initTxt(userID);// 初始化，为用户创建文件夹和所需文件
											LandRW.initLandData(userID);// 初始化土地
											UserBean user = new UserBean();
											user = new UserBean();
											user.setUserID(userID);
											user.setPass(userPass);
											UserRW.saveUserMsg(user);// 保存用户信息

											int result = JOptionPane
													.showConfirmDialog(
															ResNewUser.this,
															"恭喜！注册成功。马上去完善你的信息吧。",
															"Success",
															JOptionPane.YES_NO_OPTION,
															JOptionPane.INFORMATION_MESSAGE);
											if (result == JOptionPane.YES_OPTION) {// 点确定，显示农场，并显示修改界面
												Farm farm = new Farm(userID);
												farm.setLocationRelativeTo(null);
												farm.setVisible(true);
												ChangeUserMsg changeMsg = new ChangeUserMsg(
														farm);
												changeMsg
														.setLocationRelativeTo(null);
												changeMsg.setVisible(true);
												login.dispose();
											} else {// 点击取消，直接进入农场
												Farm farm = new Farm(userID);
												farm.setLocationRelativeTo(null);
												farm.setVisible(true);
												login.dispose();
											}
										}// end passcheck
										else {
											JOptionPane.showMessageDialog(
													ResNewUser.this,
													"不要着急，密码错了，重新输入吧");
											pass.setText("");
											passCheck.setText("");
										}

									} else {// 用户名重复
										JOptionPane.showMessageDialog(
												ResNewUser.this,
												"这个ID太火啦，已经被人家抢走了");
									}

								}
							}
						});
					}
					{
						btnExit = new JButton();
						panelNewUser.add(btnExit);
						btnExit.setText("\u5173 \u95ed");
						btnExit.setBounds(205, 286, 76, 33);
						btnExit.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								dispose();
							}
						});
					}
				}
			}
			this.setSize(368, 375);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
