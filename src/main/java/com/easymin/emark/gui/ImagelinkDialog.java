/**
 * 
 */
package com.easymin.emark.gui;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 超链接选择对话框
 * 
 * @author Shine
 * 
 */
public class ImagelinkDialog extends Dialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2085189340809530583L;

	private ResourceBundle bundle;
	
	private ButtonActionListener listener;
	
	public ImagelinkDialog(JFrame owner,ButtonActionListener buttonActionListener) {
		super(owner,true);
		this.bundle = ResourceBundle.getBundle("language",Locale.getDefault());
		this.listener = buttonActionListener;
		initComponents();
	}
	
	//初始化界面
	private void initComponents() {
		this.setTitle(bundle.getString("imagelinkDialog.title"));
		
		JLabel urlLabel = new javax.swing.JLabel();
        final JTextField urlField = new javax.swing.JTextField();
        JLabel titleLabel = new javax.swing.JLabel();
        final JTextField titleField = new javax.swing.JTextField();
        JButton okbutton = new javax.swing.JButton();
        JButton cancelbutton = new javax.swing.JButton();

        urlLabel.setText(bundle.getString("imagelinkDialog.url"));

        urlField.setText("http://");

        titleLabel.setText(bundle.getString("imagelinkDialog.alt"));

        okbutton.setText(bundle.getString("imagelinkDialog.ok"));
        cancelbutton.setText(bundle.getString("imagelinkDialog.cancel"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(urlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okbutton)
                        .addGap(60, 60, 60)
                        .addComponent(cancelbutton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(urlField, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                        .addComponent(titleField)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(urlField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelbutton)
                    .addComponent(okbutton))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        
        cancelbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImagelinkDialog.this.dispose();
			}
		});
        
        this.setLocationRelativeTo(null);
        
        this.pack();
        
        okbutton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//点击
				listener.invoke(urlField.getText(),titleField.getText());
				//关闭对话框
				ImagelinkDialog.this.dispose();
			}
		});
        
        this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				ImagelinkDialog.this.dispose();
			}
			
        });
	}

	public interface ButtonActionListener{

		void invoke(String url, String title);
		
	}
	
}
