package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CityView  extends JFrame {
	
	private JPanel buildings;
	private JPanel defending;
	private JPanel boptions;
	private JTextArea player;
	private JPanel endturn;
	public JTextArea getPlayer() {
		return player;
	}

	public void setPlayer(JTextArea player) {
		this.player = player;
	}

	private JPanel armies;

	public JPanel getBuildings() {
		return buildings;
	}

	public void setBuildings(JPanel buildings) {
		this.buildings = buildings;
	}

	public JPanel getDefending() {
		return defending;
	}

	public void setDefending(JPanel defending) {
		this.defending = defending;
	}

	public CityView(){
		
		this.setBounds(0, 0, 2000, 1050);   
		this.setVisible(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(new GridLayout(6,1));
		
		player=new JTextArea();
		player.setPreferredSize(new Dimension(this.getWidth(),30));
		player.setEditable(false);  
		this.add(player);
		player.setText("hello");
		
		boptions=new JPanel();
		boptions.setPreferredSize(new Dimension(this.getWidth(),70));
		boptions.setLayout(new FlowLayout());        //which layout should it be?
		
		this.add(boptions);
		
		buildings=new JPanel();
		buildings.setPreferredSize(new Dimension(this.getWidth(),600));
		buildings.setLayout(new GridLayout(1,5));        //which layout should it be?
		
		this.add(buildings);
		
		defending=new JPanel();
		defending.setPreferredSize(new Dimension(this.getWidth(),100));
		defending.setLayout(new GridLayout(1,1));        //which layout should it be?
		
		this.add(defending);
		
		armies=new JPanel();
		armies.setPreferredSize(new Dimension(this.getWidth(),600));
		armies.setLayout(new GridLayout(1,5));        //which layout should it be?
		
		this.add(armies);
		endturn=new JPanel();
		endturn.setPreferredSize(new Dimension(this.getWidth()/2,50));
		this.add(endturn);

		
	}

	public JPanel getEndturn() {
		return endturn;
	}

	public void setEndturn(JPanel endturn) {
		this.endturn = endturn;
	}

	public JPanel getBoptions() {
		return boptions;
	}

	public void setBoptions(JPanel boptions) {
		this.boptions = boptions;
	}

	public JPanel getArmies() {
		return armies;
	}

	public void setArmies(JPanel armies) {
		this.armies = armies;
	}
	

}
