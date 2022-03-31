package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UpgradeRecView extends JFrame{
private JPanel buttons;
private JTextArea textinfo;
private JPanel infor;
private JPanel image;
private JPanel endturn;
	
	
	
	public UpgradeRecView() {
		this.setBounds(0, 0, 800, 800);   
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(1,2));
		buttons = new JPanel();
		infor = new JPanel();
		image = new JPanel();
		textinfo= new JTextArea();
		textinfo.setEditable(false);
		buttons.setLayout(new FlowLayout());
		buttons.setSize(new Dimension(this.getWidth()/2,this.getHeight()));
		infor.setSize(new Dimension(this.getWidth()/2,this.getHeight()));
		textinfo.setSize(new Dimension(infor.getWidth(),400));
		infor.setLayout(new GridLayout(2,1));
		image.setSize(new Dimension());
		infor.add(textinfo);
		infor.add(image);
		endturn=new JPanel();
		endturn.setPreferredSize(new Dimension(this.getWidth()/2,50));
		this.add(endturn);

		
		this.add(buttons);
		this.add(infor);
		this.setVisible(false);
		this.revalidate();
		this.repaint();
	}
	public JPanel getButtons() {
		return buttons;
	}
	public void setButtons(JPanel buttons) {
		this.buttons = buttons;
	}
	public JTextArea getTextinfo() {
		return textinfo;
	}
	public void setTextinfo(JTextArea textinfo) {
		this.textinfo = textinfo;
	}
	public JPanel getInfor() {
		return infor;
	}
	public void setInfor(JPanel infor) {
		this.infor = infor;
	}
	public JPanel getImage() {
		return image;
	}
	public void setImage(JPanel image) {
		this.image = image;
	}
	public static void main(String[] args) {
		new UpgradeRecView();
	}
}
