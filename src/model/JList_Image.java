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
		// ������Ⱦ��������Ⱦ�����ڻ����б���ʹ� JList �ֶε��б���ѡ����
		setCellRenderer(new MyListCellRenderer());
	}

	private class MyListCellRenderer extends JLabel implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if (value instanceof Item) {
				Item item = (Item) value;
				setIcon(new ImageIcon(item.getPicPath()));// ������ʾ��ͼƬ
				setText("  " + item.getUserName());// ������ʾ������
			}
			// ��ѡ��ʱ�ı���ɫ��ǰ��ɫ
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
