/**
 * 
 */
package com.easymin.emark.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.easymin.emark.gui.EmarkEditor;
import com.easymin.emark.gui.EmarkGui;
import com.easymin.emark.gui.EmarkTabbedPane;
import com.easymin.emark.gui.HyperlinkDialog;
import com.easymin.emark.gui.HyperlinkDialog.ButtonActionListener;
import com.easymin.emark.gui.ImagelinkDialog;
import com.easymin.emark.utils.Strings;

/**
 * 编辑菜单事件监听
 * @author Shine
 *
 */
public class InsertMenuActionListener implements ActionListener {

	private EmarkGui emarkGui;
	
	
	
	public InsertMenuActionListener(EmarkGui emarkGui) {
		super();
		this.emarkGui = emarkGui;
	}



	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		EmarkTabbedPane tab = emarkGui.getTabPanel();
		
		final EmarkEditor editor = tab.getSelectedEditor();
		
		if(null == editor){
			return;
		}
		
		if("bold".equals(command)){
			editor.insert("****", editor.getCaretPosition());
			editor.setCaretPosition(editor.getCaretPosition()-2);
		}else if("italic".equals(command)){
			editor.insert("**", editor.getCaretPosition());
			editor.setCaretPosition(editor.getCaretPosition()-1);
		}else if("quote".equals(command)){
			if(editor.getCurrentColumn() > 0){
				editor.insert("\r\n>", editor.getCaretPosition());
			}else{
				editor.insert(">", editor.getCaretPosition());
			}
		}else if("code".equals(command)){
			if(editor.getCurrentColumn() > 0){
				editor.insert("``", editor.getCaretPosition());
				editor.setCaretPosition(editor.getCaretPosition()-1);
			}else{
				editor.insert("	", editor.getCaretPosition());
			}
			
		}else if("h1".equals(command)){
			editor.insert("##", editor.getCaretPosition());
			editor.setCaretPosition(editor.getCaretPosition()-1);
		}else if("h2".equals(command)){
			editor.insert("####", editor.getCaretPosition());
			editor.setCaretPosition(editor.getCaretPosition()-2);
		}else if("h3".equals(command)){
			editor.insert("######", editor.getCaretPosition());
			editor.setCaretPosition(editor.getCaretPosition()-3);
		}else if("h4".equals(command)){
			editor.insert("########", editor.getCaretPosition());
			editor.setCaretPosition(editor.getCaretPosition()-4);
		}else if("hyperlink".equals(command)){
			
			HyperlinkDialog dialog = new HyperlinkDialog(emarkGui,new ButtonActionListener() {
				
				public void invoke(String url, String title) {
					if(Strings.isBlank(url)){
						return;
					}
					StringBuilder sb = new StringBuilder();
					if(Strings.isBlank(title)){
						sb.append("[").append(url).append("]").append("(").append(url).append(")");
					}else{
						sb.append("[").append(title).append("]").append("(").append(url).append(")");
					}
					
					editor.insert(sb.toString(), editor.getCaretPosition());
				}
			});
			dialog.setVisible(true);
			
			
		}else if("image".equals(command)){
			ImagelinkDialog dialog = new ImagelinkDialog(emarkGui, new ImagelinkDialog.ButtonActionListener() {
				
				public void invoke(String url, String alt) {
					
					if(Strings.isBlank(url)){
						return;
					}
					StringBuilder sb = new StringBuilder();
					if(Strings.isBlank(alt)){
						sb.append("![]").append("(").append(url).append(")");
					}else{
						sb.append("![").append(alt).append("]").append("(").append(url).append(")");
					}
					
					editor.insert(sb.toString(), editor.getCaretPosition());
				}
			});
			
			dialog.setVisible(true);
			
		}else if("unorderedList".equals(command)){
			if(editor.getCurrentColumn() > 0){
				editor.insert("\r\n- ", editor.getCaretPosition());
			}else{
				editor.insert("- ", editor.getCaretPosition());
			}
		}else if("orderedList".equals(command)){
			if(editor.getCurrentColumn() > 0){
				editor.insert("\r\n1. ", editor.getCaretPosition());
			}else{
				editor.insert("1. ", editor.getCaretPosition());
			}
		}else if("horizontalLine".equals(command)){
			editor.insert("\n----------\n", editor.getCaretPosition());
		}else if("timestamp".equals(command)){
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formtDate = format.format(new Date());
			editor.insert(formtDate, editor.getCaretPosition());
			
		}else if("lowercase".equals(command)){
			String selected = editor.getSelectedText();
			if(!Strings.isBlank(selected)){
				selected = selected.toLowerCase();
				editor.replaceRange(selected, editor.getSelectionStart(), editor.getSelectionEnd());
			}
		}else if("uppercase".equals(command)){
			String selected = editor.getSelectedText();
			if(!Strings.isBlank(selected)){
				selected = selected.toUpperCase();
				editor.replaceRange(selected, editor.getSelectionStart(), editor.getSelectionEnd());
			}
		}
		
		
	}

}
