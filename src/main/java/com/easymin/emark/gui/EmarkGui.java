package com.easymin.emark.gui;

import java.awt.Dimension;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * @author Shine
 */
public class EmarkGui extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3017476186023761320L;

	private ResourceBundle bundle;

	private Dimension rawDimension;

	private boolean isFullScreen = false;

	private EmarkToolBar toolbar;
	
	
	/**
	 * Creates new form EmarkGui
	 */
	public EmarkGui() {
		this.bundle = ResourceBundle.getBundle("language", Locale.getDefault());
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Emark");
		setSize(800, 600);
		setLocationRelativeTo(null);// 居中

		new EmarkMenuBar(this);

		toolbar = new EmarkToolBar(this);
		getContentPane().add(toolbar, java.awt.BorderLayout.PAGE_START);

		statusBar = new EmarkStatusBar();
		getContentPane().add(statusBar, java.awt.BorderLayout.SOUTH);

		tabPanel = new EmarkTabbedPane();

		EmarkEditor editor = new EmarkEditor(this);
		editor.setName(bundle.getString("tab.newdoc"));
		tabPanel.addTab(editor.getName(), editor);

		getContentPane().add(tabPanel, java.awt.BorderLayout.CENTER);
	}

	private EmarkStatusBar statusBar;

	private EmarkTabbedPane tabPanel;

	/**
	 * 获取状态栏
	 * 
	 * @return
	 */
	public EmarkStatusBar getStatusBar() {
		return statusBar;
	}

	/**
	 * 获取tabpanel
	 * 
	 * @return
	 */
	public EmarkTabbedPane getTabPanel() {
		return tabPanel;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public Dimension getRawDimension() {
		return rawDimension;
	}

	public void setRawDimension(Dimension rawDimension) {
		this.rawDimension = rawDimension;
	}

	public boolean isFullScreen() {
		return isFullScreen;
	}

	public void setFullScreen(boolean isFullScreen) {
		this.isFullScreen = isFullScreen;
	}

	public EmarkToolBar getToolbar() {
		return toolbar;
	}

}
