package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ArmyView extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea player;
	private JTextArea army;
	private JPanel unit;
	private JTextArea unitinfo;
	private JPanel buttonss;
	private JPanel endturn;
	
	

	public ArmyView() { 
		
		this.setBounds(400, 150, 600, 600);   //change position
		this.setVisible(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(new GridLayout(5,1));
		
		

		
		player=new JTextArea();
		player.setPreferredSize(new Dimension(this.getWidth(),100));
		player.setEditable(false);  
		this.add(player);
	
		
		army=new JTextArea();
		army.setPreferredSize(new Dimension(this.getWidth(),100));
		army.setEditable(false);  
		army.setText("");
		this.add(army);
		
		buttonss=new JPanel();
		buttonss.setPreferredSize(new Dimension(this.getWidth(),100));
		this.add(buttonss);
		
		
		unit=new JPanel();
		unit.setPreferredSize(new Dimension(this.getWidth(),250));
		unit.setLayout(new GridLayout(1,2));        
		JLabel l=new JLabel("Unit information:");
		unit.add(l);
		this.add(unit);
		
		unitinfo=new JTextArea();
		unitinfo.setPreferredSize(new Dimension(this.getWidth()/2,100));
		unitinfo.setEditable(false);  
		unitinfo.setVisible(false);
		unit.add(unitinfo);
		
		endturn=new JPanel();
		endturn.setPreferredSize(new Dimension(this.getWidth()/2,50));
		this.add(endturn);

		
		
		this.revalidate();
		this.repaint();
		
		
		
	   
		
	}
	public JPanel getEndturn() {
		return endturn;
	}
	public void setEndturn(JPanel endturn) {
		this.endturn = endturn;
	}
	public JPanel getButtonss() {
		return buttonss;
	}
	public void setButtonss(JPanel buttons) {
		this.buttonss = buttons;
	}
	public JTextArea getUnitinfo() {
		return unitinfo;
	}
	public void setUnitinfo(JTextArea unitinfo) {
		this.unitinfo = unitinfo;
	}
	public JTextArea getPlayer() {
		return player;
	}
	public void setPlayer(JTextArea player) {
		this.player = player;
	}
	public JTextArea getArmy() {
		return army;
	}
	public void setArmy(JTextArea army) {
		this.army = army;
	}
	public JPanel getUnit() {
		return unit;
	}
	public void setUnit(JPanel unit) {
		this.unit = unit;
	}
	public static void main(String [] args) {
		new ArmyView();
	}

}
