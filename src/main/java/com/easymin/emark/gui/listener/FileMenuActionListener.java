/**
 * 
 */
package com.easymin.emark.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.easymin.emark.gui.EmarkEditor;
import com.easymin.emark.gui.EmarkGui;
import com.easymin.emark.gui.EmarkTabbedPane;
import com.easymin.emark.utils.Strings;

/**
 * 文件菜单事件监听器
 * 
 * @author Shine
 * 
 */
public class FileMenuActionListener implements ActionListener {

	private static final Logger LOGGER = Logger.getLogger(EmarkGui.class
			.getName());

	private EmarkGui emarkGui;

	private ResourceBundle bundle;

	public FileMenuActionListener(EmarkGui emarkGui) {
		super();
		this.emarkGui = emarkGui;
		this.bundle = emarkGui.getBundle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("newFile".equals(command)) {
			this.newFile();
		} else if ("openFile".equals(command)) {
			this.openFile();
		} else if ("saveFile".equals(command)) {
			this.saveFile();
		} else if ("saveAs".equals(command)) {
			this.saveAs();
		} else if ("saveAll".equals(command)) {
			this.saveAll();
		} else if ("closeFile".equals(command)) {
			this.closeFile();
		} else if ("closeAll".equals(command)) {
			this.closeAll();
		} else if ("exit".equals(command)) {
			this.exit();
		}

	}

	/**
	 * 退出系统
	 */
	private void exit() {
		this.emarkGui.dispose();
		System.exit(0);

	}

	/**
	 * 关闭所有文件
	 */
	private void closeAll() {
		EmarkTabbedPane tab = this.emarkGui.getTabPanel();
		List<EmarkEditor> editors = tab.getEditors();
		for (EmarkEditor textEditorGui : editors) {
			closeFile(tab, textEditorGui);
		}

	}

	/**
	 * 关闭文档
	 */
	private void closeFile() {

		EmarkTabbedPane tab = this.emarkGui.getTabPanel();

		EmarkEditor editor = tab.getSelectedEditor();

		closeFile(tab, editor);

	}

	/**
	 * 保存所有
	 */
	private void saveAll() {

		EmarkTabbedPane tab = this.emarkGui.getTabPanel();

		EmarkEditor editor;
		int size = tab.getComponentCount();
		;

		for (int i = 0; i < size; i++) {

			editor = (EmarkEditor) tab.getComponentAt(i);

			if (Strings.isBlank(editor.getPath())) {
				// 还没有保存过文件
				saveAS(editor, i);
			} else {
				// 文件已经保存过
				File file = new File(editor.getPath());
				if (!file.exists()) { // 如果中途把文件已经删除则重新创建文件
					try {
						file.createNewFile();
					} catch (IOException ex) {
						LOGGER.log(Level.SEVERE, null, ex);
					}
				}
				// 获取文件内容
				saveFile(file, editor.getText());
			}
		}
	}

	/**
	 * 另存为
	 */
	private void saveAs() {
		EmarkEditor editor = this.emarkGui.getTabPanel().getSelectedEditor();
		saveAS(editor, -1);

	}

	/**
	 * 保存文件
	 */
	private void saveFile() {

		EmarkTabbedPane tab = this.emarkGui.getTabPanel();

		EmarkEditor editor = tab.getSelectedEditor();
		if (null == editor) {
			return;
		}

		if (Strings.isBlank(editor.getPath())) {
			// 还没有保存过文件
			saveAS(editor, -1);
		} else {
			// 文件已经保存过
			File file = new File(editor.getPath());
			if (!file.exists()) { // 如果中途把文件已经删除则重新创建文件
				try {
					file.createNewFile();
				} catch (IOException ex) {
					LOGGER.log(Level.SEVERE, null, ex);
				}
			}
			// 获取文件内容
			saveFile(file, editor.getText());
		}

	}

	/**
	 * 创建 tab
	 */
	private void newFile() {
		EmarkTabbedPane tab = this.emarkGui.getTabPanel();
		EmarkEditor editor = new EmarkEditor(this.emarkGui);
		int size = tab.getEditors().size();
		editor.setName(String.format("%s%d", bundle.getString("tab.newdoc"),
				size));
		tab.addTabAndSelected(editor.getName(), editor);
	}

	/**
	 * 打开文件
	 */
	private void openFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);// 只能选择文件
		chooser.setDialogTitle(bundle.getString("filechooser.open"));
		chooser.setFileFilter(new FileNameExtensionFilter("Markdown Files",
				new String[] { "md", "txt", "markdown", "mdown" }));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter(
				"Text Files", new String[] { "txt" }));
		int state = chooser.showOpenDialog(this.emarkGui);

		EmarkTabbedPane tab = this.emarkGui.getTabPanel();

		if (state == JFileChooser.APPROVE_OPTION) {
			File chooseFile = chooser.getSelectedFile();
			// 判断打开的文件是否已经存在在tab中
			String path = chooseFile.getAbsolutePath();
			boolean found = false;
			EmarkEditor gui = null;
			List<EmarkEditor> editors = tab.getEditors();
			for (EmarkEditor editor : editors) {
				if (path.equals(editor.getPath())) {
					found = true;
					gui = editor;
					break;
				}
			}

			if (!found) {// 没有找到
				String content = readFile(chooseFile);
				gui = tab.getSelectedEditor();
				if (null != gui && Strings.isBlank(gui.getText())) {
					// 可以直接写入文件到这里
					gui.setText(content);
					tab.setTitleAt(tab.getSelectedIndex(), chooseFile.getName()); // 设置tab显示文件名
				} else {
					// 创建新的tab
					gui = new EmarkEditor(this.emarkGui);
					gui.setText(content);
					tab.addTabAndSelected(chooseFile.getName(), gui);
				}
				gui.setPath(chooseFile.getAbsolutePath()); // 保存文件的绝对路径
			} else { // 找到
				tab.setSelectedComponent(gui);
			}
		}

	}

	// 从文件读取数据
	private String readFile(File file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			StringBuilder sb = new StringBuilder();
			String str;
			while ((str = reader.readLine()) != null) {
				sb.append(str);
				sb.append("\r\n");
			}
			return sb.toString();
		} catch (FileNotFoundException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException ex) {
					LOGGER.log(Level.SEVERE, null, ex);
				}
			}
		}

		return null;
	}

	private void saveFile(File file, String content) {
		OutputStreamWriter writer = null;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(file));
			writer.write(content);
			writer.flush();
		} catch (FileNotFoundException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException ex) {
					LOGGER.log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	private void saveAS(EmarkEditor gui, int index) {
		if (null == gui) {
			return;
		}
		String content = gui.getText();
		// 弹出文件选择框保存
		EmarkTabbedPane tab = this.emarkGui.getTabPanel();

		JFileChooser chooser = new JFileChooser();
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);// 只能选择文件
		chooser.setDialogTitle(bundle.getString("filechooser.saveas"));
		chooser.setFileFilter(new FileNameExtensionFilter("Markdown Files",
				new String[] { "md", "txt", "markdown", "mdown" }));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter(
				"Text Files", new String[] { "txt" }));
		int state2 = chooser.showSaveDialog(this.emarkGui);
		if (state2 == JFileChooser.APPROVE_OPTION) {
			File chooseFile = chooser.getSelectedFile();
			if (!chooseFile.exists()) { // 文件不存在就创建文件
				try {
					chooseFile.createNewFile();
				} catch (IOException ex) {
					LOGGER.log(Level.SEVERE, null, ex);
				}
			}
			gui.setPath(chooseFile.getAbsolutePath()); // 保存文件的绝对路径
			if (-1 == index) {
				tab.setTitleAt(tab.getSelectedIndex(), chooseFile.getName()); // 设置tab显示文件名
			} else {
				tab.setTitleAt(index, chooseFile.getName());
			}
			saveFile(chooseFile, content);
		}
	}

	/**
	 * 关闭文档
	 * 
	 * @param tab
	 * @param editor
	 */
	private void closeFile(EmarkTabbedPane tab, EmarkEditor editor) {
		if (null != editor) {
			String content = editor.getText();
			if (!Strings.isBlank(content)) {// 有内容
				// 弹出提示框提示是否需要保存
				int state = JOptionPane.showConfirmDialog(this.emarkGui,bundle.getString("document.closeConfirm.message"));
				if (state == JOptionPane.OK_OPTION) {
					// 点击确认,执行保存文件
					if (!Strings.isBlank(editor.getPath())) {
						File file = new File(editor.getPath());
						saveFile(file, content);
					} else {
						saveAS(editor, -1);
					}
					tab.remove(editor);
				} else if (state == JOptionPane.CANCEL_OPTION) {
					// 点击取消,什么都不做
				} else if (state == JOptionPane.NO_OPTION) {
					// 不保存退出
					tab.remove(editor);
				}
			}else{//没有内容直接关闭
				tab.remove(editor);
			}
		}
	}


}
