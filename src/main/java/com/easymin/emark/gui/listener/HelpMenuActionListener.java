/**
 * 
 */
package com.easymin.emark.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.easymin.emark.gui.AboutUsGui;
import com.easymin.emark.gui.EmarkGui;

/**
 * 编辑菜单事件监听
 * @author Shine
 *
 */
public class HelpMenuActionListener implements ActionListener {
	
	private EmarkGui emarkGui;
	
	public HelpMenuActionListener(EmarkGui emarkGui) {
		super();
		this.emarkGui = emarkGui;
	}



	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if( "about".equals(e.getActionCommand()) ){
			AboutUsGui aboutUs = new AboutUsGui(emarkGui);
		    aboutUs.setVisible(true);
		}
	}

}
