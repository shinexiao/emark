/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easymin.emark.gui;

import java.awt.Frame;
import javax.swing.JDialog;

/**
 * 
 * @author Shine
 */
public class AboutUsGui extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6938270437389699849L;

	public AboutUsGui(Frame owner) {
		super(owner, "About", true);
		initCompents();
	}

	// 构造组件
	private void initCompents() {
		// 设置组件大小
		this.setSize(400, 300);
		// 居中
		this.setLocationRelativeTo(null);
		// 设置不能改变大小
		this.setResizable(false);

		AboutUsPanel panel = new AboutUsPanel();
		this.add(panel);
		this.pack();
	}

}
