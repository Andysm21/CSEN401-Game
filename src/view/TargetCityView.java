package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.ArmyCont;
import Controller.StartCont;
import engine.City;

public class TargetCityView extends JFrame implements ActionListener{

	private StartCont s;
	private City c;
	private ArrayList<JButton> cityButtons;
	private JButton confirm;
	private JPanel cities;
	private JPanel conf;
	private ArmyCont acont;
	private String savedCity;

	public TargetCityView(ArmyCont ac,StartCont s1,City c1){
		acont=ac;
		cityButtons = new ArrayList<JButton>();
		s=s1;
		c=c1;
		this.setBounds(400, 150, 500, 200);   //change position
		this.setVisible(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));
		cities = new JPanel();
		cities.setLayout(new FlowLayout());
		for(int i = 0 ;i<s.getG().getAvailableCities().size();i++) {
			if(!s.getG().getAvailableCities().get(i).equals(c1)) {
				if(!s.getG().getPlayer().getControlledCities().contains(s.getG().getAvailableCities().get(i))) {
					JButton b= new JButton(s.getG().getAvailableCities().get(i).getName());
					b.addActionListener(this);
					cityButtons.add(b);
					cities.add(b);
				}
			}
		}

		conf = new JPanel();
		conf.setLayout(new FlowLayout());
		confirm= new JButton("Confirm Target");
		confirm.addActionListener(this);
		conf.add(confirm);
		this.add(cities);
		this.add(conf);
		this.revalidate();
		this.repaint();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();

		if(b.getActionCommand().equals("Cairo")) {
			savedCity="Cairo";
		}
		else {
			if(b.getActionCommand().equals("Sparta")) {
				savedCity="Sparta";
			}
			else {
				if(b.getActionCommand().equals("Rome")) {
					savedCity="Rome";
				}
				else {
					if(b.getActionCommand().equals("Confirm Target")) {
						if(savedCity==null) {
							JOptionPane.showMessageDialog(null,"Choose a target before confirming!","Target Error", JOptionPane.ERROR_MESSAGE);
						}
						else {
							s.getG().targetCity(acont.getA1(),savedCity); 
							savedCity=null;
							//s.getW1().refreshtext();
							this.dispose();
						}
					}
				}
			}
		}
	}
}



