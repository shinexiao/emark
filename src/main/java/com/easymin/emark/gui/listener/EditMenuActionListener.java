/**
 * 
 */
package com.easymin.emark.gui.listener;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.easymin.emark.gui.EmarkEditor;
import com.easymin.emark.gui.EmarkGui;

/**
 * 编辑菜单事件监听
 * 
 * @author Shine
 * 
 */
public class EditMenuActionListener implements ActionListener {
	
	private static final Logger LOGGER = Logger.getLogger(EmarkGui.class.getName());
	
	private EmarkGui emarkGui;

	private Clipboard clipboard;

	public EditMenuActionListener(EmarkGui emarkGui) {
		super();
		this.emarkGui = emarkGui;
		this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("undo".equals(command)) {
			this.undo();
		} else if ("redo".equals(command)) {
			this.redo();
		} else if ("cut".equals(command)) {	
			this.cut();
		} else if ("copy".equals(command)) {
			this.copy();
		} else if ("paste".equals(command)) {
			this.paste();
		}

	}
	/**
	 * 粘贴
	 */
	private void paste() {
		EmarkEditor editor = this.emarkGui.getTabPanel().getSelectedEditor();
		if( null != editor ){
			Transferable contents =this.clipboard.getContents(this);
			DataFlavor flavor = DataFlavor.stringFlavor;
			if (contents.isDataFlavorSupported(flavor)) {
				try {
					String string = (String) contents.getTransferData(flavor);
					int pos = editor.getCaretPosition();
					editor.insert(string, pos);
				} catch (Exception ex) {
					LOGGER.log(Level.SEVERE,null, ex);
				}
			}
		}
	}

	/**
	 * 拷贝
	 */
	private void copy() {
		
		EmarkEditor editor =this.emarkGui.getTabPanel().getSelectedEditor();
		if(null != editor){
			String selectedText =editor.getSelectedText();
			StringSelection ss = new StringSelection(selectedText);
			this.clipboard.setContents(ss, null);
		}
		
	}
	
	

	/**
	 * 剪切
	 */
	private void cut() {
		EmarkEditor editor = this.emarkGui.getTabPanel().getSelectedEditor();
		if(null != editor){
			String selectedText = editor.getSelectedText();
			StringSelection ss = new StringSelection(selectedText);
			this.clipboard.setContents(ss, null);
			int start = editor.getSelectionStart();
			int end = editor.getSelectionEnd();
			editor.replaceRange("", start, end);
		}
	}

	/**
	 * 重做
	 */
	private void redo() {
		EmarkEditor editor = this.emarkGui.getTabPanel().getSelectedEditor();
		if(null != editor){
			if (editor.canRedo()) {
				editor.redo();
			}
		}
	}

	/**
	 * 撤销
	 */
	private void undo() {
		EmarkEditor editor = this.emarkGui.getTabPanel().getSelectedEditor();
		if(null != editor){
			if (editor.canUndo()) {
				editor.undo();
			}
		}
	}

}
