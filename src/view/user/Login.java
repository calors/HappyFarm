package view.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import tools.Check;
import tools.UserRW;
import view.farm.Farm;

import model.UserBean;

public class Login extends javax.swing.JFrame {
	private JPanel panelLogin;
	private JLabel labelNewUser;
	private JLabel logo;
	private JPasswordField pass;
	private JLabel labPass;
	private JTextField ID;
	private JLabel labUserID;
	private JButton btnExit;
	private JButton btnLogin;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Login inst = new Login();
				inst.setTitle("用户登录");
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public Login() {
		super();
		this.setTitle("用户登录");
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setResizable(false);
			{
				panelLogin = new JPanel();
				getContentPane().add(panelLogin);
				panelLogin.setLayout(null);
				panelLogin.setBounds(0, 0, 394, 271);
				panelLogin.setBackground(new java.awt.Color(255, 255, 255));
				{
					btnExit = new JButton();
					panelLogin.add(btnExit);
					btnExit.setText("\u5173 \u95ed");
					btnExit.setLayout(null);
					btnExit.setBounds(215, 186, 66, 33);
					btnExit.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
				}
				{
					btnLogin = new JButton();
					panelLogin.add(btnLogin);
					btnLogin.setText("\u767b \u9646");
					btnLogin.setLayout(null);
					btnLogin.setBounds(100, 186, 68, 33);
					btnLogin.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							String userID = ID.getText();
							if (!Check.checkName(userID)) {
								JOptionPane.showMessageDialog(Login.this,
										"用户名输入错误。");
							} else {
								char[] userPassChar = pass.getPassword();
								File userFile = new File("user\\" + userID);
								String userPass = String.valueOf(userPassChar);
								// String userPass = userPassChar.toString();
								// 如果没有复写toString 方法，输出结果Object的toString方法，
								// 结果为[类型@哈希值]。数组类，仅仅是重载(overload)为类的静态方法
								// ,所以此处不可用toString()
								if (userFile.exists()) {
									UserBean user = new UserBean();
									user = UserRW.readUserMsg(userID);

									if (user.getPass().equals(userPass)) {
										Farm farm = new Farm(userID);
										farm.setLocationRelativeTo(null);
										farm.setVisible(true);
										Login.this.dispose();
									} else {
										JOptionPane.showMessageDialog(
												Login.this, "密码错了啦。");
									}
								}// END IF exists
								else {
									int result = JOptionPane.showConfirmDialog(
											Login.this, "用户不存在，是否注册？", "ERROR",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.ERROR_MESSAGE);
									if (result == JOptionPane.YES_OPTION) {
										ResNewUser newUser = new ResNewUser(
												Login.this);
										newUser.setLocationRelativeTo(null);
										newUser.setVisible(true);
									}
								}
							}
						}
					});
				}
				{
					labUserID = new JLabel();
					panelLogin.add(labUserID);
					labUserID.setText("\u7528\u6237\u540d\uff1a");
					labUserID.setLayout(null);
					labUserID.setBounds(73, 95, 61, 17);
				}
				{
					ID = new JTextField();
					panelLogin.add(ID);
					ID.setBounds(146, 92, 172, 24);
				}
				{
					labPass = new JLabel();
					panelLogin.add(labPass);
					labPass.setLayout(null);
					labPass.setBounds(73, 136, 61, 18);
					labPass.setText("\u5bc6 \u7801\uff1a");
				}
				{
					pass = new JPasswordField();
					panelLogin.add(pass);
					pass.setBounds(146, 134, 172, 24);
				}
				{
					logo = new JLabel();
					logo.setIcon(new ImageIcon("image\\guiRes\\logo.jpg"));
					panelLogin.add(logo);
					logo.setLayout(null);
					logo.setBounds(112, 12, 184, 68);

				}
				{
					labelNewUser = new JLabel();
					panelLogin.add(labelNewUser);
					labelNewUser
							.setText("<HTML><U>\u6ce8\u518c\u65b0\u7528\u6237</U></HTML>");
					labelNewUser.setBounds(258, 230, 98, 24);
					labelNewUser
							.setHorizontalAlignment(SwingConstants.TRAILING);
					labelNewUser.setFont(new java.awt.Font(
							"Microsoft YaHei UI", 0, 14));
					labelNewUser.setForeground(new java.awt.Color(0, 0, 255));
					labelNewUser.addMouseListener(new MouseAdapter() {

						@Override
						public void mouseClicked(MouseEvent e) {
							new Thread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									ResNewUser newUser = new ResNewUser(Login.this);
									newUser.setLocationRelativeTo(null);
									newUser.setVisible(true);
								}
							}).start();
							
						}

					});
				}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
