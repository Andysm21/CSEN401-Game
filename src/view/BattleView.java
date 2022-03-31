package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BattleView extends JFrame{
private JTextArea log;
private JPanel up;
private JPanel down;
private JPanel upleft;
private JPanel upright;
private JPanel attack;
	
	
	public BattleView() {
		this.setBounds(0, 0, 2100, 1100);   
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
		log= new JTextArea();
		up = new JPanel();
		down= new JPanel();
		upright= new JPanel();
		upleft= new JPanel();
		attack = new JPanel();
		attack.setBackground(Color.cyan);
		attack.setPreferredSize(new Dimension(2100,100));
		attack.setLayout(new FlowLayout());
		down.setLayout(new GridLayout(1,1));
		up.setPreferredSize(new Dimension(2100,500));
		down.setPreferredSize(new Dimension(2100,250));
		upleft.setPreferredSize(new Dimension(up.getWidth()/2,up.getHeight()));
		upright.setPreferredSize(new Dimension(up.getWidth()/2,up.getHeight()));
		upright.setLayout(new FlowLayout());
		upleft.setLayout(new FlowLayout());
		

		log.setEditable(false);
		up.setLayout(new GridLayout(1,2));
		log.setPreferredSize(new Dimension(down.getWidth(),down.getHeight()));
		up.add(upleft);
		up.add(upright);
		down.add(log);
		upleft.setBackground(Color.blue);
		upright.setBackground(Color.magenta);
		down.setBackground(Color.green);
		this.add(up,BorderLayout.NORTH);
		this.add(attack,BorderLayout.CENTER);
		this.add(down,BorderLayout.SOUTH);
		
		this.revalidate();
		this.repaint();
		
		
	}
	
	
	
	public JPanel getAttack() {
		return attack;
	}



	public void setAttack(JPanel attack) {
		this.attack = attack;
	}



	public JTextArea getLog() {
		return log;
	}



	public void setLog(JTextArea log) {
		this.log = log;
	}



	public JPanel getUp() {
		return up;
	}



	public void setUp(JPanel up) {
		this.up = up;
	}



	public JPanel getDown() {
		return down;
	}



	public void setDown(JPanel down) {
		this.down = down;
	}



	public JPanel getUpleft() {
		return upleft;
	}



	public void setUpleft(JPanel upleft) {
		this.upleft = upleft;
	}



	public JPanel getUpright() {
		return upright;
	}



	public void setUpright(JPanel upright) {
		this.upright = upright;
	}



	public static void main(String[] args) {
		new BattleView();
		}
}
