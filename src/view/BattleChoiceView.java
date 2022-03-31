package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.BattleCont;
import Controller.StartCont;
import engine.City;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class BattleChoiceView extends JFrame implements ActionListener{

	
	private JPanel Button;
	private JPanel Text;
	private BattleCont bc;
	private Army my;
	private Army he;
	private StartCont s;
	private City c;
	
	
	public BattleChoiceView(Army mine,Army his,StartCont s1,City c1) {
		c=c1;
		this.setBounds(400, 150, 550, 200);   //change position
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));
		this.setResizable(false);
		this.setUndecorated(true);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setVisible(true);

		my=mine;
		s=s1;
		he=his;
		Text = new JPanel();
		Text.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()/2));
		Text.setLayout(new FlowLayout());
		JLabel text= new JLabel("Please Choose the method you want to use to attack with!");
		Button = new JPanel();
		Button.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()/2));
		Button.setLayout(new FlowLayout());
		
		JButton AutoResolve= new JButton("Auto Resolve");
		JButton Manually= new JButton("Manually Attack");
		AutoResolve.addActionListener(this);
		Manually.addActionListener(this);
		Text.add(text);
		Button.add(AutoResolve);
		Button.add(Manually);
		
		this.add(Text);
		this.add(Button);
		this.revalidate();
		this.repaint();
		}
	public void resolve() {
		if(my.getUnits().size()==0){
			JOptionPane.showMessageDialog(null,"You lost the battle,retreat immediately!","Battle Notification", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			s.getW().setVisible(true);
		}
		if(he.getUnits().size()==0){
			JOptionPane.showMessageDialog(null,"You Won the battle,go collect your new city!","Battle Notification", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			s.getW().setVisible(true);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b.getActionCommand().equals("Manually Attack")) {
			this.setVisible(false);
			new BattleCont(my,he, s);
			c.setTurnsUnderSiege(-1);
			s.getW1().setFlag(true);
		}
		else {
			if(b.getActionCommand().equals("Auto Resolve")) {
				try {
					s.getG().autoResolve(my, he);
					c.setTurnsUnderSiege(-1);
					s.getW1().setFlag(true);
					if(my.getUnits().size()==0) {
						s.playSound("Sound/lose.wav");
						JOptionPane.showMessageDialog(null,"You have lost the battle, what a shame!","Battle Notification", JOptionPane.INFORMATION_MESSAGE);
				//		my.setCurrentLocation(s.getPlayercity().getName());
						this.dispose();
					}
					else {
						if(he.getUnits().size()==0) {
							s.playSound("Sound/Victory.wav");
							JOptionPane.showMessageDialog(null,"You have won the battle, and you conquered this city!","Battle Notification", JOptionPane.INFORMATION_MESSAGE);
							this.dispose();
						}
					}
				} catch (FriendlyFireException e1) {
					JOptionPane.showMessageDialog(null,"You are trying to attack your friend, PLEASE STOP!","Friendly Fire Warning", JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		Army x=new Army("Cairo");
		Unit u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
		//Unit u2 = (new Cavalry(2, 45, 0.6, 0.7, 0.75));
		//Unit u3 = (new Infantry(1, 45, 0.6, 0.7, 0.75));
		//Unit u4 = (new Archer(3, 45, 10, 10, 10));
		u.setParentArmy(x);
		//u2.setParentArmy(x);
		//u3.setParentArmy(x);
		//u4.setParentArmy(x);

		x.getUnits().add(u);
		//x.getUnits().add(u2);
		//x.getUnits().add(u3);
		//x.getUnits().add(u4);

		Army x2=new Army("BlaBla");
		Unit u5 = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
		Unit u6 = (new Cavalry(2, 45, 0.6, 0.7, 0.75));
		Unit u7 = (new Infantry(1, 45, 0.6, 0.7, 0.75));
		Unit u8 = (new Archer(3, 45, 0.6, 0.7, 0.75));
		u5.setParentArmy(x2);
		u6.setParentArmy(x2);
		u7.setParentArmy(x2);
		u8.setParentArmy(x2);

		//x2.getUnits().add(u5);
		//x2.getUnits().add(u6);
		//x2.getUnits().add(u7);
		x2.getUnits().add(u8);
	//	new BattleChoiceView(x,x2,null);
	}
}
