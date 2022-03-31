package view;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BuildView extends JFrame{
	private JPanel building;
	private JPanel confInfo;
	private JTextArea info;
	private JPanel confirm;
	private JPanel endturn;
	
	public BuildView() {
		this.setBounds(0, 0, 2000, 1050);   
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(1,2));
		this.setVisible(true);
		confInfo = new JPanel();
		confirm = new JPanel();
		confirm.setLayout(new FlowLayout());
		confInfo.setLayout(new GridLayout(2,1));
		building = new JPanel();
		building.setLayout(new GridLayout(5,1));
		building.setPreferredSize(new Dimension(this.getWidth()/2,this.getHeight()));
		info = new JTextArea();
		info.setEditable(false);
		confInfo.setPreferredSize(new Dimension(this.getWidth()/2,this.getHeight()));
		info.setSize(new Dimension(confInfo.getWidth(),900));
		confirm.setSize(new Dimension(confInfo.getWidth(),100));
		confInfo.add(info);
		confInfo.add(confirm);
		this.add(building);
		this.add(confInfo);
		endturn=new JPanel();
		endturn.setPreferredSize(new Dimension(this.getWidth()/2,50));
	//	this.add(endturn);

		
		//this.setVisible(false);
		this.revalidate();
		this.repaint();
	}
	
	public JPanel getEndturn() {
		return endturn;
	}

	public void setEndturn(JPanel endturn) {
		this.endturn = endturn;
	}

	public JPanel getConfInfo() {
		return confInfo;
	}
	public void setConfInfo(JPanel confInfo) {
		this.confInfo = confInfo;
	}
	public JPanel getConfirm() {
		return confirm;
	}
	public void setConfirm(JPanel confirm) {
		this.confirm = confirm;
	}
	public JPanel getBuilding() {
		return building;
	}

	public void setBuilding(JPanel building) {
		this.building = building;
	}

	public JTextArea getInfo() {
		return info;
	}

	public void setInfo(JTextArea info) {
		this.info = info;
	}


	public static void main(String[] args) {
		new BuildView();
	}

}
