/**
 * 
 */
package com.easymin.emark.gui;

import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTabbedPane;

/**
 * tab panel
 * 
 * @author Shine
 * 
 */
public class EmarkTabbedPane extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5409683256746667795L;

	public EmarkTabbedPane() {
		super();
	}

	/**
	 * 添加tab
	 * 
	 * @param title
	 * @param editor
	 */
	public void addTab(String title, EmarkEditor editor) {
		super.addTab(title, editor);
	}

	/**
	 * 添加tab并且设置选中
	 * 
	 * @param title
	 * @param editor
	 */
	public void addTabAndSelected(String title, EmarkEditor editor) {
		addTab(title, editor);
	}

	/**
	 * 获取编辑器
	 * 
	 * @return
	 */
	public List<EmarkEditor> getEditors() {

		List<EmarkEditor> editors = new LinkedList<EmarkEditor>();
		Component[] components = getComponents();
		if (null != components) {
			for (Component component : components) {
				editors.add((EmarkEditor) component);
			}
		}

		return editors;
	}

	/**
	 * 获取选中的编辑器
	 * 
	 * @return
	 */
	public EmarkEditor getSelectedEditor() {
		Component component = this.getSelectedComponent();
		return (EmarkEditor) component;
	}

}
