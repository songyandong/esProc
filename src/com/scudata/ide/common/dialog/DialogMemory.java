package com.scudata.ide.common.dialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.scudata.common.MessageManager;
import com.scudata.ide.common.GM;
import com.scudata.ide.common.GV;
import com.scudata.ide.common.resources.IdeCommonMessage;
import com.scudata.ide.common.swing.FreeConstraints;
import com.scudata.ide.common.swing.FreeLayout;
import com.scudata.ide.common.swing.MemoryMonitor;

/**
 * �����ڴ�Ի���
 *
 */
public class DialogMemory extends JDialog {
	private static final long serialVersionUID = 1L;

	private JButton jBCancel = new JButton();
	private JButton jBClean = new JButton();
	/**
	 * �����ռ�õ��ڴ�
	 */
	private JLabel jLabel1 = new JLabel();
	/**
	 * ��ʾ��ʽ
	 */
	private DecimalFormat df = new DecimalFormat("###,###");
	/**
	 * ���ڴ��ı���
	 */
	private JFormattedTextField jTFTotal = new JFormattedTextField();
	/**
	 * ���õ�����ڴ���
	 */
	private JLabel jLabel2 = new JLabel();
	/**
	 * ���õ�����ڴ����ı���
	 */
	private JFormattedTextField jTFMax = new JFormattedTextField();
	/**
	 * Ӧ��ʹ���˵��ڴ�
	 */
	private JLabel jLabel3 = new JLabel();
	/**
	 * �����ڴ��ı���
	 */
	private JFormattedTextField jTFFree = new JFormattedTextField();
	/**
	 * �ڴ������
	 */
	private MemoryMonitor jPanel1 = new MemoryMonitor();
	/**
	 * �������е�ӳ���
	 */
	private HashMap<String, ArrayList<String>> wrapStringBuffer;

	/**
	 * ���캯��
	 */
	public DialogMemory() {
		super(GV.appFrame, "�ڴ����� - ��λ��[�ֽ�]", true);
		try {
			initUI();
			pack();
			GM.setDialogDefaultButton(this, jBCancel, jBCancel);
			refreshMemory();

			WindowListener l = new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					jPanel1.surf.stop();
				}

				public void windowDeiconified(WindowEvent e) {
					jPanel1.surf.start();
				}

				public void windowIconified(WindowEvent e) {
					jPanel1.surf.stop();
				}
			};
			this.addWindowListener(l);
			jPanel1.surf.start();
			resetLangText();
		} catch (Exception ex) {
			GM.showException(ex);
		}
	}

	/**
	 * ���û������е�ӳ��
	 * 
	 * @param wrapStringBuffer
	 */
	public void setWrapStringBuffer(
			HashMap<String, ArrayList<String>> wrapStringBuffer) {
		this.wrapStringBuffer = wrapStringBuffer;
	}

	/**
	 * ����������Դ
	 */
	private void resetLangText() {
		MessageManager mm = IdeCommonMessage.get();
		setTitle(mm.getMessage("dialogmemory.title"));
		jBCancel.setText(mm.getMessage("button.close"));
		jBClean.setText(mm.getMessage("dialogmemory.button.clean"));
		jLabel1.setText(mm.getMessage("dialogmemory.label1"));
		jLabel2.setText(mm.getMessage("dialogmemory.label2"));
		jLabel3.setText(mm.getMessage("dialogmemory.label3"));
	}

	/**
	 * ��ʼ���ؼ�
	 * 
	 * @throws Exception
	 */
	private void initUI() throws Exception {
		JPanel panel1 = new JPanel();
		FreeLayout freeLayout1 = new FreeLayout();
		panel1.setLayout(freeLayout1);
		jBCancel.setMnemonic('C');
		jBCancel.setText("�ر�(C)");
		jBCancel.addActionListener(new DialogMemory_jBCancel_actionAdapter(this));
		jBClean.setMnemonic('E');
		jBClean.setText("����(E)");
		jBClean.addActionListener(new DialogMemory_jBClean_actionAdapter(this));
		jLabel1.setText("�����ռ�õ��ڴ�:");
		jLabel2.setText("���õ�����ڴ���:");
		jLabel3.setText("Ӧ��ʹ���˵��ڴ�:");
		jTFTotal.setBackground(UIManager.getColor("Button.background"));
		jTFTotal.setBorder(null);
		jTFTotal.setToolTipText("");
		jTFTotal.setDisabledTextColor(Color.black);
		jTFTotal.setEditable(false);
		jTFTotal.setEnabled(true);
		jTFTotal.setForeground(Color.blue);
		jTFTotal.setHorizontalAlignment(SwingConstants.LEFT);
		jTFMax.setBackground(UIManager.getColor("Button.background"));
		jTFMax.setBorder(null);
		jTFMax.setDisabledTextColor(Color.magenta);
		jTFMax.setEditable(false);
		jTFMax.setEnabled(true);
		jTFMax.setForeground(Color.blue);
		jTFMax.setText("");
		jTFMax.setHorizontalAlignment(SwingConstants.LEFT);
		jTFFree.setBackground(UIManager.getColor("Button.background"));
		jTFFree.setBorder(null);
		jTFFree.setDisabledTextColor(Color.blue);
		jTFFree.setEditable(false);
		jTFFree.setForeground(Color.blue);
		jTFFree.setText("");
		jTFFree.setHorizontalAlignment(SwingConstants.LEFT);
		this.setResizable(false);
		this.addWindowListener(new DialogMemory_this_windowAdapter(this));
		getContentPane().add(panel1);
		panel1.add(jTFFree, new FreeConstraints(140, 54, 150, -1));
		panel1.add(jLabel1, new FreeConstraints(15, 14, -1, -1));
		panel1.add(jLabel2, new FreeConstraints(15, 35, -1, -1));
		panel1.add(jLabel3, new FreeConstraints(15, 56, -1, -1));
		panel1.add(jBCancel, new FreeConstraints(301, 13, 85, -1));
		panel1.add(jTFMax, new FreeConstraints(140, 33, 150, -1));
		panel1.add(jTFTotal, new FreeConstraints(140, 14, 150, -1));
		panel1.add(jPanel1, new FreeConstraints(12, 89, 375, 197));
		panel1.add(jBClean, new FreeConstraints(301, 49, 85, -1));
	}

	/**
	 * ȡ����ť
	 * 
	 * @param e
	 */
	void jBCancel_actionPerformed(ActionEvent e) {
		GM.setWindowDimension(this);
		this.setVisible(false);
	}

	/**
	 * ������ť
	 * 
	 * @param e
	 */
	void jBClean_actionPerformed(ActionEvent e) {
		if (wrapStringBuffer != null) {
			wrapStringBuffer.clear();
		}
		System.gc();
		refreshMemory();
	}

	/**
	 * ˢ���ڴ�
	 */
	void refreshMemory() {
		long total, tmp;
		long unit = 1024;// *1024;
		total = Runtime.getRuntime().totalMemory();
		jTFTotal.setValue(df.format(total / unit) + " KB");

		tmp = Runtime.getRuntime().maxMemory();
		jTFMax.setValue(df.format(new Long(tmp / unit)) + " KB");

		tmp = Runtime.getRuntime().freeMemory();
		jTFFree.setValue(df.format(new Long((total - tmp) / unit)) + " KB");
	}

	/**
	 * ���ڹر��¼�
	 * 
	 * @param e
	 */
	void this_windowClosed(WindowEvent e) {
		GM.setWindowDimension(this);
		dispose();
	}
}

class DialogMemory_jBCancel_actionAdapter implements
		java.awt.event.ActionListener {
	DialogMemory adaptee;

	DialogMemory_jBCancel_actionAdapter(DialogMemory adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jBCancel_actionPerformed(e);
	}
}

class DialogMemory_jBClean_actionAdapter implements
		java.awt.event.ActionListener {
	DialogMemory adaptee;

	DialogMemory_jBClean_actionAdapter(DialogMemory adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.jBClean_actionPerformed(e);
	}
}

class DialogMemory_this_windowAdapter extends java.awt.event.WindowAdapter {
	DialogMemory adaptee;

	DialogMemory_this_windowAdapter(DialogMemory adaptee) {
		this.adaptee = adaptee;
	}

	public void windowClosed(WindowEvent e) {
		adaptee.this_windowClosed(e);
	}
}