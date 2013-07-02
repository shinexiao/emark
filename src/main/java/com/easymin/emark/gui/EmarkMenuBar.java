/**
 * 
 */
package com.easymin.emark.gui;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu.Separator;

import com.easymin.emark.gui.listener.EditMenuActionListener;
import com.easymin.emark.gui.listener.FileMenuActionListener;
import com.easymin.emark.gui.listener.HelpMenuActionListener;
import com.easymin.emark.gui.listener.InsertMenuActionListener;
import com.easymin.emark.gui.listener.ToolMenuActionListener;
import com.easymin.emark.gui.listener.ViewMenuActionListener;

/**
 * @author Shine
 *
 */
public class EmarkMenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3196322274433850606L;
	
	private EmarkGui emarkGui;

	private ResourceBundle bundle;
	
	private ResourceBundle confBundle;
	
	public EmarkMenuBar(EmarkGui emarkGui) {
		super();
		bundle = ResourceBundle.getBundle("language", Locale.getDefault()); 
		this.emarkGui = emarkGui;
		this.confBundle = ResourceBundle.getBundle("conf");
		
		//初始化菜单组件
		initComponents();
		
		this.emarkGui.setJMenuBar(this);
	}

	private void initComponents() {
		
		JMenu fileMenu = new JMenu(); //文件菜单
		fileMenu.setMnemonic('F');
        fileMenu.setText(bundle.getString("menubar.file")); // NOI18N
        
		JMenu editMenu = new JMenu(); //编辑菜单
		editMenu.setMnemonic('E');
        editMenu.setText(bundle.getString("menubar.edit")); // NOI18N
        
		JMenu insertMenu = new JMenu(); //插入菜单
		insertMenu.setMnemonic('I');
        insertMenu.setText(bundle.getString("menubar.insert")); // NOI18N
        
		JMenu viewMenu = new JMenu(); //查看菜单
		viewMenu.setMnemonic('V');
        viewMenu.setText(bundle.getString("menubar.view")); // NOI18N
        
		JMenu toolMenu = new JMenu(); //工具菜单
		toolMenu.setMnemonic('T');
        toolMenu.setText(bundle.getString("menubar.tool")); // NOI18N
        
		JMenu helpMenu = new JMenu(); //帮助菜单
		
		FileMenuActionListener   fileMenuActionListener   = new FileMenuActionListener(this.emarkGui);
		EditMenuActionListener   editMenuActionListener   = new EditMenuActionListener(this.emarkGui);
		InsertMenuActionListener insertMenuActionListener = new InsertMenuActionListener(this.emarkGui);
		ViewMenuActionListener   viewMenuActionListener   = new ViewMenuActionListener(this.emarkGui);
		ToolMenuActionListener   toolMenuActionListener   = new ToolMenuActionListener();
		HelpMenuActionListener   helpMenuActionListener   = new HelpMenuActionListener(this.emarkGui);
        
        JMenuItem newFileMenu = new JMenuItem();
        JMenuItem openFileMenu = new JMenuItem();
        JMenuItem saveFileMenu = new JMenuItem(); 
        JMenuItem saveAsMenu = new JMenuItem();
        JMenuItem saveAllMenu = new JMenuItem();
        JMenuItem closeFileMenu = new JMenuItem();
        JMenuItem closeAllMenu = new JMenuItem();
        JMenuItem exitMenu = new JMenuItem();
        
        
        JMenuItem undoMenu = new JMenuItem();
        JMenuItem redoMenu = new JMenuItem();
        JMenuItem cutMenu = new  JMenuItem();
        JMenuItem copyMenu = new JMenuItem();
        JMenuItem pasteMenu = new JMenuItem();
  
        JMenuItem boldMenu = new JMenuItem();
        JMenuItem italicMenu = new JMenuItem();
        JMenuItem quoteMenu = new JMenuItem();
        JMenuItem codeMenu = new JMenuItem();
        
        
        JMenuItem h1Menu = new JMenuItem();
        JMenuItem h2Menu = new JMenuItem();
        JMenuItem h3Menu = new JMenuItem();
        JMenuItem h4Menu = new JMenuItem();
        JMenuItem hyperlinkMenu = new JMenuItem();
        JMenuItem imageMenu = new JMenuItem();
        JMenuItem unorderedListMenu = new JMenuItem();
        JMenuItem orderedListMenu = new JMenuItem();
        JMenuItem horizontalLineMenu = new JMenuItem();      
        JMenuItem timestampMenu = new JMenuItem();

        JMenuItem previewMenu = new JCheckBoxMenuItem();
        previewMenu.setSelected(Boolean.valueOf(confBundle.getString("livePreview")));
        
        JMenuItem fullScreenMenu = new JMenuItem();
        
        JMenuItem lineNumberMenu = new JCheckBoxMenuItem();
        lineNumberMenu.setSelected(Boolean.valueOf(confBundle.getString("showLineNumber")));
        
        JMenuItem linewarpMenu = new JCheckBoxMenuItem();
        linewarpMenu.setSelected(Boolean.valueOf(confBundle.getString("lineWarp")));
        
        JMenuItem toolbarMenu = new JCheckBoxMenuItem();
        toolbarMenu.setSelected(Boolean.valueOf(confBundle.getString("showToolBar")));
        
        JMenuItem statusBarMenu = new JCheckBoxMenuItem();
        statusBarMenu.setSelected(Boolean.valueOf(confBundle.getString("showStatusBar")));
        
        JMenuItem optionsMenu = new JMenuItem();

        JMenuItem aboutMenu = new JMenuItem();
     
        

        newFileMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newFileMenu.setText(bundle.getString("menubar.file.newdoc")); // NOI18N
        newFileMenu.setToolTipText(bundle.getString("menubar.file.newdoc.tooltip")); // NOI18N
        newFileMenu.setActionCommand("newFile");
        newFileMenu.addActionListener(fileMenuActionListener);
        fileMenu.add(newFileMenu);

        openFileMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openFileMenu.setText(bundle.getString("menubar.file.opendoc")); // NOI18N
        openFileMenu.setActionCommand("openFile");
        openFileMenu.addActionListener(fileMenuActionListener);
        fileMenu.add(openFileMenu);
        fileMenu.add(new Separator());

        saveFileMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveFileMenu.setText(bundle.getString("menubar.file.savedoc")); // NOI18N
        saveFileMenu.setActionCommand("saveFile");
        saveFileMenu.addActionListener(fileMenuActionListener);
        fileMenu.add(saveFileMenu);

        saveAsMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenu.setText(bundle.getString("menubar.file.savedocto")); // NOI18N
        saveAsMenu.setActionCommand("saveAs");
        saveAsMenu.addActionListener(fileMenuActionListener);
        fileMenu.add(saveAsMenu);

        saveAllMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAllMenu.setText(bundle.getString("menubar.file.saveall")); // NOI18N
        saveAllMenu.setActionCommand("saveAll");
        saveAllMenu.addActionListener(fileMenuActionListener);
        fileMenu.add(saveAllMenu);
        fileMenu.add(new Separator());

        closeFileMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        closeFileMenu.setText(bundle.getString("menubar.file.closedoc")); // NOI18N
        closeFileMenu.setActionCommand("closeFile");
        closeFileMenu.addActionListener(fileMenuActionListener);
        fileMenu.add(closeFileMenu);

        closeAllMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        closeAllMenu.setText(bundle.getString("menubar.file.closeall")); // NOI18N
        closeAllMenu.setActionCommand("closeAll");
        closeAllMenu.addActionListener(fileMenuActionListener);
        fileMenu.add(closeAllMenu);
        fileMenu.add(new Separator());

        exitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenu.setText(bundle.getString("menubar.file.exit")); // NOI18N
        exitMenu.setActionCommand("exit");
        exitMenu.addActionListener(fileMenuActionListener);
        fileMenu.add(exitMenu);
        
        this.add(fileMenu);


        undoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenu.setText(bundle.getString("menubar.edit.undo")); // NOI18N
        undoMenu.setActionCommand("undo");
        undoMenu.addActionListener(editMenuActionListener);
        editMenu.add(undoMenu);

        redoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenu.setText(bundle.getString("menubar.edit.redo")); // NOI18N
        redoMenu.setActionCommand("redo");
        redoMenu.addActionListener(editMenuActionListener);
        editMenu.add(redoMenu);
        editMenu.add(new Separator());

        cutMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cutMenu.setText(bundle.getString("menubar.edit.cut")); // NOI18N
        cutMenu.setActionCommand("cut");
        cutMenu.addActionListener(editMenuActionListener);
        editMenu.add(cutMenu);

        copyMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyMenu.setText(bundle.getString("menubar.edit.copy")); // NOI18N
        copyMenu.setActionCommand("copy");
        copyMenu.addActionListener(editMenuActionListener);
        editMenu.add(copyMenu);

        pasteMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        pasteMenu.setText(bundle.getString("menubar.edit.paste")); // NOI18N
        pasteMenu.setActionCommand("paste");
        pasteMenu.addActionListener(editMenuActionListener);
        editMenu.add(pasteMenu);

        this.add(editMenu);

        

        boldMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        boldMenu.setText(bundle.getString("menubar.insert.b")); // NOI18N
        boldMenu.setActionCommand("bold");
        boldMenu.addActionListener(insertMenuActionListener);
        insertMenu.add(boldMenu);

        italicMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        italicMenu.setText(bundle.getString("menubar.insert.i")); // NOI18N
        italicMenu.setActionCommand("italic");
        italicMenu.addActionListener(insertMenuActionListener);
        insertMenu.add(italicMenu);

        quoteMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quoteMenu.setActionCommand("quote");
        quoteMenu.setText(bundle.getString("menubar.insert.q")); // NOI18N
        quoteMenu.addActionListener(insertMenuActionListener);
        insertMenu.add(quoteMenu);

        codeMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        codeMenu.setText(bundle.getString("menubar.insert.k")); // NOI18N
        codeMenu.setActionCommand("code");
        codeMenu.addActionListener(insertMenuActionListener);
        insertMenu.add(codeMenu);
        insertMenu.add(new Separator());

        h1Menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        h1Menu.setText(bundle.getString("menubar.insert.h1")); // NOI18N
        h1Menu.setActionCommand("h1");
        h1Menu.addActionListener(insertMenuActionListener);
        insertMenu.add(h1Menu);

        h2Menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        h2Menu.setText(bundle.getString("menubar.insert.h2")); // NOI18N
        h2Menu.setActionCommand("h2");
        h2Menu.addActionListener(insertMenuActionListener);
        insertMenu.add(h2Menu);

        h3Menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        h3Menu.setText(bundle.getString("menubar.insert.h3")); // NOI18N
        h3Menu.setActionCommand("h3");
        h3Menu.addActionListener(insertMenuActionListener);
        insertMenu.add(h3Menu);

        h4Menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        h4Menu.setText(bundle.getString("menubar.insert.h4")); // NOI18N
        h4Menu.setActionCommand("h4");
        h4Menu.addActionListener(insertMenuActionListener);
        insertMenu.add(h4Menu);
        insertMenu.add(new Separator());

        hyperlinkMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        hyperlinkMenu.setText(bundle.getString("menubar.insert.a")); // NOI18N
        hyperlinkMenu.setActionCommand("hyperlink");
        hyperlinkMenu.addActionListener(insertMenuActionListener);
        insertMenu.add(hyperlinkMenu);

        imageMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        imageMenu.setText(bundle.getString("menubar.insert.img")); // NOI18N
        imageMenu.setActionCommand("image");
        imageMenu.addActionListener(insertMenuActionListener);
        insertMenu.add(imageMenu);
        insertMenu.add(new Separator());

        unorderedListMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        unorderedListMenu.setText(bundle.getString("menubar.insert.ul")); // NOI18N
        unorderedListMenu.setActionCommand("unorderedList");
        unorderedListMenu.addActionListener(insertMenuActionListener);
        insertMenu.add(unorderedListMenu);

        orderedListMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        orderedListMenu.setText(bundle.getString("menubar.insert.ol")); // NOI18N
        orderedListMenu.setActionCommand("orderedList");
        orderedListMenu.addActionListener(insertMenuActionListener);
        insertMenu.add(orderedListMenu);

        horizontalLineMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        horizontalLineMenu.setText(bundle.getString("menubar.insert.hr")); // NOI18N
        horizontalLineMenu.setActionCommand("horizontalLine");
        horizontalLineMenu.addActionListener(insertMenuActionListener);
        insertMenu.add(horizontalLineMenu);
        insertMenu.add(new Separator());

        timestampMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        timestampMenu.setText(bundle.getString("menubar.insert.time")); // NOI18N
        timestampMenu.setActionCommand("timestamp");
        timestampMenu.addActionListener(insertMenuActionListener);
        insertMenu.add(timestampMenu);

        this.add(insertMenu);

        

        previewMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        previewMenu.setText(bundle.getString("menubar.view.preview")); // NOI18N
        previewMenu.setActionCommand("preview");
        previewMenu.addActionListener(viewMenuActionListener);
        viewMenu.add(previewMenu);

        fullScreenMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        fullScreenMenu.setText(bundle.getString("menubar.view.fullscreen")); // NOI18N
        fullScreenMenu.setActionCommand("fullScreen");
        fullScreenMenu.addActionListener(viewMenuActionListener);
        viewMenu.add(fullScreenMenu);
        viewMenu.add(new Separator());

        lineNumberMenu.setText(bundle.getString("menubar.view.linenumber")); // NOI18N
        lineNumberMenu.setActionCommand("lineNumber");
        lineNumberMenu.addActionListener(viewMenuActionListener);
        viewMenu.add(lineNumberMenu);

        linewarpMenu.setText(bundle.getString("menubar.view.linewarp")); // NOI18N
        linewarpMenu.setActionCommand("linewarp");
        linewarpMenu.addActionListener(viewMenuActionListener);
        viewMenu.add(linewarpMenu);
        viewMenu.add(new Separator());

        toolbarMenu.setText(bundle.getString("menubar.view.toolbar")); // NOI18N
        toolbarMenu.setActionCommand("toolbar");
        toolbarMenu.addActionListener(viewMenuActionListener);
        viewMenu.add(toolbarMenu);

        statusBarMenu.setText(bundle.getString("menubar.view.statusbar")); // NOI18N
        statusBarMenu.setActionCommand("statusbar");
        statusBarMenu.addActionListener(viewMenuActionListener);
        viewMenu.add(statusBarMenu);

        this.add(viewMenu);

        

        optionsMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        optionsMenu.setText(bundle.getString("menubar.tool.options")); // NOI18N
        optionsMenu.setActionCommand("options");
        openFileMenu.addActionListener(toolMenuActionListener);
        toolMenu.add(optionsMenu);

        this.add(toolMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText(bundle.getString("menubar.help")); // NOI18N

        aboutMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        aboutMenu.setText(bundle.getString("menubar.help.about")); // NOI18N
        aboutMenu.setActionCommand("about");
        aboutMenu.addActionListener(helpMenuActionListener);
        helpMenu.add(aboutMenu);

        
        this.add(helpMenu);
        
		
	}
	
	
	
	
}
