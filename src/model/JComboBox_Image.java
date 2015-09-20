package model;

import java.awt.Component;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class JComboBox_Image extends JComboBox {
	public JComboBox_Image() {
		this(new Vector<Item>());

	}

	public void setSelectedItem(Item item) {

		super.setSelectedItem(item);
	}

	public JComboBox_Image(Vector<Item> items) {
		super(items);
		// 设置渲染器，该渲染器用于绘制列表项和从 JComboBox 字段的列表中选择的项。
		setRenderer(new MyListCellRenderer());
		setMaximumRowCount(3);// 设置 JComboBox 选择时显示的最大行数。
	}

	/**
	 * 该渲染器用于绘制列表项和从 JComboBox 字段的列表中选择的项(显示图片和名字)。 列表单元格渲染器，
	 * Component类是所有组件的父类。
	 * 
	 */
	private class MyListCellRenderer extends JLabel implements ListCellRenderer {
		// 要把图像显示在JLable上，只要设置JLable的setIcon即可
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {

			if (value instanceof Item) {
				Item item = (Item) value;
				setIcon(new ImageIcon(item.getPicPath()));// 设置显示的图片
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
