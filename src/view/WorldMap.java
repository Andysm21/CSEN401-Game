package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.StartCont;

public class WorldMap extends JFrame{
	
	public JPanel getCity() {
		return city;
	}

	public void setCity(JPanel city) {
		this.city = city;
	}

	public JPanel getIdle() {
		return idle;
	}

	public void setIdle(JPanel idle) {
		this.idle = idle;
	}

	public JPanel getBeseiging() {
		return beseiging;
	}

	public void setBeseiging(JPanel beseiging) {
		this.beseiging = beseiging;
	}

	public JPanel getMarching() {
		return marching;
	}

	public void setMarching(JPanel marching) {
		this.marching = marching;
	}
	private JPanel endturn;
	private JPanel city;
	private JPanel idle;
	private JPanel beseiging;
	private JPanel marching;
	private JPanel GetInfo;
	private JTextArea info;
	private StartCont s;

	public JPanel getEndturn() {
		return endturn;
	}

	public void setEndturn(JPanel endturn) {
		this.endturn = endturn;
	}

	public JPanel getGetInfo() {
		return GetInfo;
	}
 
	public void setGetInfo(JPanel getInfo) {
		GetInfo = getInfo;
	}

	public WorldMap() {

		this.setTitle("World Map");
		this.setBounds(0, 0, 2000, 1050);   
		//this.setBounds(400, 150, 1680, 1050); 
		this.setVisible(false);  
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
       // background.setLayout(new GridLayout(7,1));
		this.setExtendedState(MAXIMIZED_BOTH);
        
		this.setResizable(false);
		this.setUndecorated(true);
         
		info=new JTextArea();
		info.setBounds(32,0, 300, 100);
		//info.setPreferredSize(new Dimension(this.getWidth(),50));
		info.setEditable(false);
		info.getSelectedTextColor().brighter();
		info.setFont(new Font(Font.SERIF,Font.BOLD,16));
		info.setForeground(Color.WHITE);
		info.setOpaque(false);
		this.add(info);
		//info.setText("hi");
		
		city=new JPanel();
		//city.setPreferredSize(new Dimension(this.getWidth(),200));
		city.setBounds(300, 50, this.getWidth(), 600);
		city.setLayout(null);//
		city.setOpaque(false);

		this.add(city);
		 
		idle=new JPanel();
		//idle.setPreferredSize(new Dimension(this.getWidth(),200));
		idle.setBounds(40, 110, 260, 200);
		idle.setLayout(new GridLayout(0,2));
		idle.setOpaque(false);

		this.add(idle);
		
		beseiging=new JPanel();
		//beseiging.setPreferredSize(new Dimension(this.getWidth(),200));
		beseiging.setBounds(40, 320, 260, 200);
		beseiging.setLayout(new GridLayout(0,2));
		beseiging.setOpaque(false);

		this.add(beseiging);
		
		marching=new JPanel();
		//marching.setPreferredSize(new Dimension(this.getWidth(),200));
		marching.setBounds(40, 530, 260, 200);
		marching.setLayout(new GridLayout(0,2));
		marching.setOpaque(false);

		this.add(marching);

		GetInfo=new JPanel();
	//	GetInfo.setPreferredSize(new Dimension(this.getWidth(),150));
		GetInfo.setBounds(100,750,100,100);
	//	GetInfo.setLayout(new GridLayout());
	//  GetInfo.setBackground(Color.BLUE);
		GetInfo.setOpaque(false);

		this.add(GetInfo);
		
		
		endturn=new JPanel();
		//endturn.setPreferredSize(new Dimension(this.getWidth()/2,50));
		//endturn.setBackground(Color.GREEN);
		endturn.setBounds(700,800,100,100);
		endturn.setOpaque(false);

		this.add(endturn);
		
		JLabel background=new JLabel(new ImageIcon("Images/worldmap.jpg"));
        background.setLayout(new FlowLayout());
        
		this.add(background);
		this.revalidate();
		this.repaint();
		
		
		
	}
	
	public JTextArea getInfo() {
		return info;
	}

	public void setInfo(JTextArea info) {
		this.info = info;
	}

	public static void main(String[] args) {
		new WorldMap();

	}

}
