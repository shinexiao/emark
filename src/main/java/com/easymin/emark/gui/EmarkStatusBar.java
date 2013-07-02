package com.easymin.emark.gui;

import java.awt.FlowLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JToolBar;
/**
 * 状态栏
 * @author Shine
 *
 */
public class EmarkStatusBar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1602749742186487068L;
	
	private JLabel wordCount;
	
	private JLabel colAndRow;
	
	private  ResourceBundle bundle;
	
	public EmarkStatusBar() {
		super();
		this.bundle = ResourceBundle.getBundle("language", Locale.getDefault()); 
		//初始化组件
		initComponents();
	}

	private void initComponents() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setFloatable(false);
	    this.setRollover(true);
	    
	    //字数统计
		this.wordCount = new JLabel();
		this.wordCount.setText(bundle.getString("statusbar.wordcount")+"0");
		this.wordCount.setToolTipText(bundle.getString("statusbar.wordcount.tooltip"));
        this.add(wordCount);
        
        this.add(new Separator());
        
        //行列显示
        this.colAndRow = new JLabel();
        this.setRowAndCol(1, 0);
        this.add(colAndRow);
	}

	/**
	 * 设置字符统计
	 * @param count
	 */
	public void setWordCount(int count){
		this.wordCount.setText(bundle.getString("statusbar.wordcount") + count);
	}
	/**
	 * 设置行列值
	 * @param row
	 * @param col
	 */
	public void setRowAndCol(int row,int col){
		 this.colAndRow.setText("Line: " + row+ ", Column: " +col);
	}
}
