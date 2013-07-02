/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easymin.emark.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.UndoManager;

import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleSheet;

import com.github.rjeschke.txtmark.Processor;
import com.steadystate.css.parser.CSSOMParser;

/**
 * markdown 编辑器
 * 
 * @author Shine
 */
public class EmarkEditor extends javax.swing.JPanel {

	private static final Logger LOGGER = Logger.getLogger(EmarkEditor.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = -2401984687659496641L;

	private ResourceBundle bundle;

	private JTextArea editor;
	private JScrollPane editorScrollPane;
	private JScrollPane previewScrollPane;
	private JSplitPane split;
	private JEditorPane preview;
	private UndoManager undoManager = new UndoManager();
	
	private EmarkGui emarkGui;
	
	/**
	 * Creates new form TextEditorGui
	 */
	public EmarkEditor(EmarkGui emarkGui) {
		this.emarkGui = emarkGui;
		this.bundle = ResourceBundle.getBundle("conf");
		initComponents();
		
	}

	private void initComponents() {

		this.setLayout(new BorderLayout());
		// 分割器
		split = new JSplitPane();
		split.setDividerSize(5);
		split.setOneTouchExpandable(true);
		split.setContinuousLayout(true);
		split.setDividerLocation(400);
		split.setPreferredSize(new Dimension(800, 400));
		
		// 代码编辑器
		editor = new JTextArea();
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 13);
		editor.setFont(font);
		editor.setTabSize(4);
		editor.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				preview(editor.getText());
			}
		});

		// 代码编辑器滚动面板
		editorScrollPane = new JScrollPane();
		
		LineNumberHeaderView view = new LineNumberHeaderView();
		view.setFont(font);
		
		editorScrollPane.setRowHeaderView(view);
		
		
		editorScrollPane.setViewportView(editor);

		split.setLeftComponent(editorScrollPane);

		// 预览界面
		preview = new JEditorPane();
		preview.setEditable(false);
		preview.setBorder(null);
		preview.setContentType("text/html"); // NOI18N
		preview.setEditorKit(new HTMLEditorKit());

		// 预览界面滚动面板
		previewScrollPane = new JScrollPane();
		previewScrollPane.setViewportView(preview);

		split.setRightComponent(previewScrollPane);

		this.add(split, java.awt.BorderLayout.CENTER);

		// 设置编辑器界面滚动事件
		AdjustmentListener adjustmentListener = new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent evt) {
				int value = evt.getValue();
				previewScrollPane.getVerticalScrollBar().setValue(value);
			}
		};
		editorScrollPane.getVerticalScrollBar().addAdjustmentListener(
				adjustmentListener);

		// 初始化样式
		initCss((HTMLEditorKit) this.preview.getEditorKit());
		
		if(!Boolean.valueOf(bundle.getString("showLineNumber"))){
			setShowLineNumber(false);
		}
		
		if(Boolean.valueOf(bundle.getString("lineWarp"))){
			setLineWarp(true);
		}
		
		if(!Boolean.valueOf(bundle.getString("livePreview"))){
			setLivePreview(false);
		}
		
		this.editor.addCaretListener(new CaretListener() {
			
			public void caretUpdate(CaretEvent e) {
			    try {
                    int row = editor.getLineOfOffset(e.getDot());
                    int column = e.getDot() - editor.getLineStartOffset(row);
                    
                    EmarkEditor.this.row = row+1;
                    EmarkEditor.this.col = column;
                    
                    emarkGui.getStatusBar().setRowAndCol(EmarkEditor.this.row ,  EmarkEditor.this.col);
                } catch (Exception ex) {
                   LOGGER.log(Level.SEVERE,null,ex);
                }
				
			}
		});
		
	}

	private void initCss(HTMLEditorKit kit) {

		String style = bundle.getString("defaultStyleSheet");

		File css = new File(System.getProperty("user.dir"), "css/" + style);

		CSSOMParser parser = new CSSOMParser();
		try {
			InputSource inputSource = new InputSource(css.toURI().toURL()
					.toString());
			CSSStyleSheet cssStyleSheet = parser.parseStyleSheet(inputSource,
					null, null);
			CSSRuleList rules = cssStyleSheet.getCssRules();
			int size = rules.getLength();
			for (int i = 0; i < size; i++) {
				CSSRule rule = rules.item(i);
				kit.getStyleSheet().addRule(rule.getCssText());
			}
		} catch (MalformedURLException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}

		this.editor.getDocument().addUndoableEditListener(
				new UndoableEditListener() {
					public void undoableEditHappened(UndoableEditEvent e) {
						undoManager.addEdit(e.getEdit());
					}
				});

	}

	private String path;

	/**
	 * 获取文件保存路径
	 * 
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 设置文件保存路径
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获取编辑器内容
	 * 
	 * @return
	 */
	public String getText() {

		return this.editor.getText();
	}

	/**
	 * 设置编辑器内容
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.editor.setText(text);
		// 设置内容的时候进行预览
		preview(text);
	}

	/**
	 * 判断是否可以撤销
	 * 
	 * @return
	 */
	public boolean canUndo() {

		return undoManager.canUndo();
	}

	/**
	 * 执行撤销
	 */
	public void undo() {
		undoManager.undo();
	}

	/**
	 * 判断是否可以重做
	 * 
	 * @return
	 */
	public boolean canRedo() {

		return undoManager.canRedo();
	}

	/**
	 * 重做
	 */
	public void redo() {
		undoManager.redo();
	}

	/**
	 * 获取选中的内容
	 * 
	 * @return
	 */
	public String getSelectedText() {

		return this.editor.getSelectedText();
	}

	/**
	 * 获得鼠标的偏移位置
	 * 
	 * @return
	 */
	public int getCaretPosition() {

		return this.editor.getCaretPosition();
	}

	/**
	 * 向编辑器插入内容
	 * 
	 * @param content
	 * @param pos
	 */
	public void insert(String content, int pos) {
		this.editor.insert(content, pos);
		preview(getText());
	}
	/**
	 * 设置鼠标位置
	 * @param pos
	 */
	public void setCaretPosition(int pos){
		
		this.editor.setCaretPosition(pos);
	}
	
	public int getSelectionStart(){
		
		return this.editor.getSelectionStart();
	}
	
	public int getSelectionEnd(){
		
		return this.editor.getSelectionEnd();
	}
	
	public void replaceRange(String str,int start,int end){
		
		this.editor.replaceRange(str, start, end);
	}
	
	public int getCurrentColumn(){
		
		return col;
	}
	
	public int getCurrentRow(){
		
		return row;
	}
	
	private int row = 1;
	
	private int col = 0;
	
	/**
	 * 设置是否显示行号
	 * @param isShow
	 */
	public void setShowLineNumber(boolean isShow){
		
		this.editorScrollPane.getRowHeader().setVisible(isShow);
	}
	/**
	 * 设置是否自动换行
	 * @param autoLine
	 */
	public void setLineWarp(boolean autoLine){
		
		this.editor.setLineWrap(autoLine);
		
	}
	/**
	 * 设置是否开启实时预览
	 * @param selected
	 */
	public void setLivePreview(boolean selected) {
		int width = this.getWidth();
		this.split.setPreferredSize(new Dimension(width, this.getHeight()));
		this.previewScrollPane.setVisible(selected);
		if(selected){
			this.split.setDividerLocation(width/2);
		}else{
			this.split.setDividerLocation(width);
			this.split.setOneTouchExpandable(true);
			this.split.setContinuousLayout(true);
		}
	}
	
	// inner helper
	private void preview(String text) {
		String html = Processor.process(text);
		this.preview.setText(html);
	}

	

}
