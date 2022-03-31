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
import exceptions.FriendlyCityException;
import exceptions.TargetNotReachedException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class BattleChoiceArrived extends JFrame implements ActionListener{

	private JPanel Button;
	private JPanel Text;
	private BattleCont bc;
	private Army my;
	private Army he;
	private StartCont s;
	private City c;


	public BattleChoiceArrived(Army mine,Army his,StartCont s1,City c1) {
		this.setBounds(400, 150, 550, 200);   //change position

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true); 
		this.setLayout(new GridLayout(2,1));
		this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
c=c1;
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

		JButton Siege= new JButton("Lay Siege");
		JButton Manually= new JButton("Manually Attack");
		Siege.addActionListener(this);
		Manually.addActionListener(this);
		Text.add(text);
		Button.add(Siege);
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
			c.setTurnsUnderSiege(-1);
			new BattleCont(my,he, s);
			s.getW1().setFlag(true);

			
		}
		else {
			if(b.getActionCommand().equals("Lay Siege")) {

				for(int i = 0; i<s.getG().getAvailableCities().size();i++) {
					if(he.getCurrentLocation().equals(s.getG().getAvailableCities().get(i).getName())){
						City x = s.getG().getAvailableCities().get(i);
						try {
						//	System.out.println(my.getCurrentLocation());
							//System.out.println(x.getName());
							s.getG().getPlayer().laySiege(my, x);
							s.getW1().refreshtext();
							
							this.dispose();
						} catch (TargetNotReachedException e1) {
							JOptionPane.showMessageDialog(null,"You are still too far away from the targeted city","Target Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						} catch (FriendlyCityException e1) {
							JOptionPane.showMessageDialog(null,"You are trying to attack a friendly city, PLEASE STOP!","Friendly City Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						};
					

					}
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
		//new BattleChoiceArrived(x,x2,null);
	}

}
