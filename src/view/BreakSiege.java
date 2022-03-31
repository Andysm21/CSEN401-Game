package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.StartCont;
import engine.City;
import units.Army;

public class BreakSiege extends JFrame implements ActionListener{
	
	private StartCont s;
	private JPanel options;
	private Army mine;
	private Army his;
	private City c;
	
	public BreakSiege(Army me, Army he,StartCont s1, City c1) {
		c=c1;
		s=s1;
		mine=me;
		his=he;
		this.setBounds(400, 150, 550, 200);   //change position
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));
		
		options=new JPanel();
		options.setLayout(new FlowLayout());
		this.add(options);
		
		JButton stay=new JButton("Keep Siege");
		stay.addActionListener(this);
		JButton breakSiege=new JButton("Break Siege");
		breakSiege.addActionListener(this);
		
		options.add(breakSiege);
		options.add(stay);
		
		this.revalidate();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b.getActionCommand().equals("Break Siege")) {
			c.setUnderSiege(false);
			c.setTurnsUnderSiege(-1);
			new BattleChoiceView(mine,his,s,c);
			s.getW1().setFlag(true);
			this.dispose();
		}
		else {
			this.dispose();
		}
		
		
	}
	
	

}
