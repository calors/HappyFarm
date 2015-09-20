package view.friend;

import javax.swing.DefaultComboBoxModel;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Item;
import model.JList_Image;

import tools.UserRW;
import view.farm.Farm;
import view.user.ChangeUserMsg;


public class FriendList extends javax.swing.JDialog {
	private JScrollPane jScrollPane;
	private JList friendsList;
	private Farm farm;

	public FriendList(Farm farm) {
		super(farm);
		this.farm = farm;
		this.setTitle("好友列表");
		initGUI();
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setResizable(false);
			}
			{
				jScrollPane = new JScrollPane();
				getContentPane().add(jScrollPane, "Center");
				jScrollPane.setBounds(0, 0, 166, 309);
				jScrollPane
						.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				{

					ListModel friendsListModel = new DefaultComboBoxModel(
							UserRW.getAllUserPic());
					friendsList = new JList_Image();// JList_Image继承自JList
													// 重写了渲染器，可以显示头像
					jScrollPane.setViewportView(friendsList);
					friendsList.setModel(friendsListModel);
					friendsList.setPreferredSize(new java.awt.Dimension(160,
							307));
					friendsList
							.addListSelectionListener(new ListSelectionListener() {

								@Override
								public void valueChanged(ListSelectionEvent e) {
									if (e.getValueIsAdjusting() == false) {// 鼠标释放
										JList_Image list = (JList_Image) e
												.getSource();
										Item item = (Item) list
												.getSelectedValue();
										String userID = item.getUserName();
										if (userID.equals(Farm.USERID)) {
											ChangeUserMsg changeMsg = new ChangeUserMsg(
													farm);
											Farm newfarm = new Farm(userID);
											farm.dispose();
											newfarm.setLocationRelativeTo(null);
											newfarm.setVisible(true);
											changeMsg
													.setLocationRelativeTo(null);
											changeMsg.setVisible(true);

										} else {
											Farm newfarm = new Farm(userID);
											farm.dispose();
											newfarm.setLocationRelativeTo(null);
											newfarm.setVisible(true);
										}
									}

								}
							});
				}
			}
			this.setSize(172, 338);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
