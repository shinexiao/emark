/**
 * 
 */
package com.easymin.emark.gui.listener;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JCheckBoxMenuItem;

import com.easymin.emark.gui.EmarkEditor;
import com.easymin.emark.gui.EmarkGui;
import com.easymin.emark.gui.EmarkTabbedPane;

/**
 * 文件菜单事件监听器
 * 
 * @author Shine
 * 
 */
public class ViewMenuActionListener implements ActionListener {

	private static final Logger LOGGER = Logger.getLogger(ViewMenuActionListener.class.getName());
	
	private EmarkGui emarkGui;

	private Properties prop;
	
	public ViewMenuActionListener(EmarkGui emarkGui) {
		super();
		this.emarkGui = emarkGui;
		this.prop = new Properties();
		try {
			this.prop.load(getClass().getResourceAsStream("/conf.properties"));
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null,ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("fullScreen".equals(command)) {
			this.fullScreen();
		} else if ("preview".equals(command)) { // 开关实时预览
			JCheckBoxMenuItem checkbox = (JCheckBoxMenuItem) e.getSource(); 
			this.livePreview(checkbox.isSelected()); 
		} else if("lineNumber".equals(command)){
			JCheckBoxMenuItem checkbox = (JCheckBoxMenuItem) e.getSource(); 
			this.showLineNumber(checkbox.isSelected());
		}else if("linewarp".equals(command)){
			JCheckBoxMenuItem checkbox = (JCheckBoxMenuItem) e.getSource(); 
			this.lineWarp(checkbox.isSelected());
		}else if("toolbar".equals(command)){
			JCheckBoxMenuItem checkbox = (JCheckBoxMenuItem) e.getSource(); 
			this.showToolBar(checkbox.isSelected());
		}else if("statusbar".equals(command)){
			JCheckBoxMenuItem checkbox = (JCheckBoxMenuItem) e.getSource(); 
			this.showStatusBar(checkbox.isSelected());
		}

	}
	/**
	 * 是否开启实施预览
	 * @param selected
	 */
	private void livePreview(boolean selected) {
		
		EmarkTabbedPane tab =this.emarkGui.getTabPanel();
		
		List<EmarkEditor> editors = tab.getEditors();
		
		for (EmarkEditor emarkEditor : editors) {
			emarkEditor.setLivePreview(selected);
		}
		
		saveConf("livePreview", String.valueOf(selected));
		
	}

	/**
	 * 设置是否自动换行
	 * @param selected
	 */
	private void lineWarp(boolean selected) {
		
		EmarkTabbedPane tab =this.emarkGui.getTabPanel();
		
		List<EmarkEditor> editors = tab.getEditors();
		
		for (EmarkEditor emarkEditor : editors) {
			emarkEditor.setLineWarp(selected);
		}
		
		saveConf("lineWarp",String.valueOf(selected));
	}

	/**
	 * 设置是否显示行号
	 * @param selected
	 */
	private void showLineNumber(boolean selected) {
		EmarkTabbedPane tab =this.emarkGui.getTabPanel();
		List<EmarkEditor> editors = tab.getEditors();
		for (EmarkEditor emarkEditor : editors) {
			emarkEditor.setShowLineNumber(selected);
		}
		saveConf("showLineNumber",String.valueOf(selected));
		
	}

	/**
	 * 是否显示状态栏
	 * @param selected
	 */
	private void showStatusBar(boolean selected) {
		
		this.emarkGui.getStatusBar().setVisible(selected);
		
		saveConf("showStatusBar", String.valueOf(selected));
		
	}
	/**
	 * 是否显示工具栏
	 * @param selected
	 */
	private void showToolBar(boolean selected) {
		
		this.emarkGui.getToolbar().setVisible(selected);
		
		saveConf("showToolBar", String.valueOf(selected));
		
	}

	/**
	 * 全屏显示
	 */
	private void fullScreen() {
		GraphicsEnvironment environment = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice gd = environment.getDefaultScreenDevice();
		if (gd.isFullScreenSupported()) {
			if (!emarkGui.isFullScreen()) { // 进入全屏模式
				emarkGui.dispose();
				emarkGui.setUndecorated(true);
				emarkGui.setRawDimension(new Dimension(emarkGui.getWidth(),
						emarkGui.getHeight()));
				emarkGui.setFullScreen(true);
				gd.setFullScreenWindow(emarkGui);
			} else { // 退出全屏模式
				emarkGui.dispose();
				emarkGui.setUndecorated(false);
				emarkGui.setSize(emarkGui.getRawDimension());
				emarkGui.setVisible(true);
				emarkGui.setFullScreen(false);
			}
		}
		// 不支持全屏就不处理
	}
	
	//inner helper
	private void saveConf(String key,String value){
		prop.setProperty(key, value);
		OutputStream os = null;
		try{
			os = new FileOutputStream(new File(getClass().getResource("/conf.properties").toURI()));
			prop.store(os, "状态栏显示状态");
			os.flush();
		}catch(Exception ex){
			LOGGER.log(Level.SEVERE, null,ex);
		}finally{
			if(null != os){
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
