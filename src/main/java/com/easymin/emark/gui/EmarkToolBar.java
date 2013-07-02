/**
 * 
 */
package com.easymin.emark.gui;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import com.easymin.emark.gui.listener.EditMenuActionListener;
import com.easymin.emark.gui.listener.FileMenuActionListener;
import com.easymin.emark.gui.listener.InsertMenuActionListener;

/**
 * 工具栏
 * 
 * @author Shine
 *
 */
public class EmarkToolBar extends JToolBar {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4750903296055796351L;
	
	private EmarkGui emarkGui;

	public EmarkToolBar(EmarkGui emarkGui) {
		super();
		this.emarkGui = emarkGui;
		//初始化组件
		initComponents();
		
		this.setRollover(true);
	}

	private void initComponents() {
		
		InsertMenuActionListener  insertMenuActionListener = new InsertMenuActionListener(emarkGui);
		FileMenuActionListener  fileMenuActionListener = new FileMenuActionListener(emarkGui);
		EditMenuActionListener editMenuActionListener = new EditMenuActionListener(emarkGui);
		
		Commands commands = new Commands();
		//文档功能
		commands.addCommand("newFile", "document_new.png");
		commands.addCommand("openFile", "document_open.png");
		commands.addCommand("saveFile", "document_save.png");
		commands.addCommand("saveAll", "save-all.png");
		
		Iterator<Command> iter = commands.getCommands();
		while(iter.hasNext()){
			try{
				Command command = iter.next();
				
				JButton button = new JButton();
				button.setIcon(command.getImageIcon());
		        button.setBorderPainted(false);
		        button.setFocusable(false);
		        button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		        button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		        button.setActionCommand(command.getCommand());
		        button.addActionListener(fileMenuActionListener);
		        this.add(button);
			}catch(Exception ex){
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		this.add(new Separator());
		commands.clear();
		
		
		//编辑操作
		commands.addCommand("cut", "text_cut.png");
		commands.addCommand("copy", "text_copy.png");
		commands.addCommand("paste", "text_paste.png");
		iter = commands.getCommands();
		while(iter.hasNext()){
			try{
				Command command = iter.next();
				
				JButton button = new JButton();
				button.setIcon(command.getImageIcon());
		        button.setBorderPainted(false);
		        button.setFocusable(false);
		        button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		        button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		        button.setActionCommand(command.getCommand());
		        button.addActionListener(editMenuActionListener);
		        this.add(button);
			}catch(Exception ex){
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
			}
		}
		this.add(new Separator());
		commands.clear();
		
		//插入功能
		commands.addCommand("bold", "text_bold.png");
		commands.addCommand("italic", "text_italic.png");
		commands.addCommand("quote", "text_quote.png");
		commands.addCommand("code", "text_code.png");
		commands.addCommand("h1", "text_h1.png");
		commands.addCommand("h2", "text_h2.png");
		commands.addCommand("hyperlink", "text_hyperlink.png");
		commands.addCommand("image", "media_image.png");
		commands.addCommand("horizontalLine", "text_horizontal_rule.png");
		commands.addCommand("unorderedList", "text_bullets.png");
		commands.addCommand("orderedList", "ordered-list.png");
		commands.addCommand("timestamp", "text_timestamp.png");
		commands.addCommand("lowercase", "text_lowercase.png");
		commands.addCommand("uppercase", "text_uppercase.png");
		
		iter = commands.getCommands();
		while(iter.hasNext()){
			try{
				Command command = iter.next();
				
				JButton button = new JButton();
				button.setIcon(command.getImageIcon());
		        button.setBorderPainted(false);
		        button.setFocusable(false);
		        button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		        button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		        button.setActionCommand(command.getCommand());
		        button.addActionListener(insertMenuActionListener);
		        this.add(button);
			}catch(Exception ex){
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		
        
        
	}
	
	static class Commands{
		
		List<Command> commands = new LinkedList<Command>();
		
		public void addCommand(String command,String icon){
			commands.add(new Command(command,icon));
		}
		
		public Iterator<Command> getCommands(){
			
			return commands.iterator();
		}
		
		public void clear(){
			this.commands.clear();
		}
		
	}
	
	static class Command{
		
		private String command;
		
		private String icon;

		
		public Command(String command, String icon) {
			super();
			this.command = command;
			this.icon = icon;
		}

		public String getCommand() {
			return command;
		}

		public void setCommand(String command) {
			this.command = command;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}
		
		public ImageIcon getImageIcon(){
			
			try {
				return new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/img/"+this.icon)));
			} catch (IOException ex) {
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
			}
			return null;
		}
		
	}
	
}
