package model;

import java.awt.Component;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class JList_Image extends JList {

	public JList_Image() {
		this(new Vector<Item>());
	}

	public JList_Image(Vector<Item> items) {
		super(items);
		// 设置渲染器，该渲染器用于绘制列表项和从 JList 字段的列表中选择的项。
		setCellRenderer(new MyListCellRenderer());
	}

	private class MyListCellRenderer extends JLabel implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if (value instanceof Item) {
				Item item = (Item) value;
				setIcon(new ImageIcon(item.getPicPath()));// 设置显示的图片
				setText("  " + item.getUserName());// 设置显示的名字
			}
			// 被选中时的背景色和前景色
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			setEnabled(true);
			setFont(list.getFont());
			setOpaque(true);
			return this;
		}

	}

}
