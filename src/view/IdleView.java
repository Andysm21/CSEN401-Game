package view;

import java.awt.Dimension;
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
import Controller.World;
import exceptions.MaxCapacityException;
import units.Army;
import units.Status;
import units.Unit;

public class IdleView extends JFrame implements ActionListener{

	private JPanel player;
	private ArrayList<JButton> buttons;
	private ArrayList<Army> idleArmies;
	private StartCont s;
	private Army toRelocate;
	private Unit unit;
	private World worldcont;
	private ArmyCont armycont;


	public Army getToRelocate() {
		return toRelocate;
	}



	public void setToRelocate(Army toRelocate) {
		this.toRelocate = toRelocate;
	}



	public IdleView(StartCont s1,Unit u,World w,ArmyCont ac)
	{
		armycont=ac;
		worldcont=w;
		unit=u;
		s=s1;
		buttons = new ArrayList<JButton>();
		idleArmies = new ArrayList<Army>();
		this.setBounds(400, 150, 600, 600);   //change position
		this.setVisible(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));
		JPanel conf = new JPanel();
		conf.setLayout(new FlowLayout());
		JButton confirm = new JButton("Confirm Relocating");
		conf.add(confirm);
		confirm.addActionListener(this);
		player=new JPanel();
		player.setPreferredSize(new Dimension(this.getWidth(),100));
		player.setLayout(new FlowLayout());
		for(int i =0;i<s.getG().getPlayer().getControlledArmies().size();i++) {
			if(s.getG().getPlayer().getControlledArmies().get(i).getCurrentStatus().equals(Status.IDLE)) {
				JButton b = new JButton("Idle Army"+ (i+1));
				b.addActionListener(this);
				player.add(b);
				buttons.add(b);
				idleArmies.add(s.getG().getPlayer().getControlledArmies().get(i));
			}
		}
		JButton def = new JButton("Defending Army");
		def.addActionListener(this);
		player.add(def);
		buttons.add(def);
		idleArmies.add(armycont.getC1().getDefendingArmy());
		this.add(player);
		this.add(conf);



	}



	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b= (JButton) e.getSource();
		for(int i =0 ; i<buttons.size();i++) {
			if(b.getActionCommand().equals(buttons.get(i).getActionCommand())) {
				toRelocate = idleArmies.get(i);
			}
		}
		if(e.getActionCommand().equals("Confirm Relocating")&&toRelocate!=null&&unit!=null) {
			try {
				//if(unit.getParentArmy().getUnits().size()==1) {
				//	s.getW1().getCitycont().getButtons().remove(s.getG().getPlayer().getControlledArmies().indexOf(unit.getParentArmy()));
				//	s.getW1().getCitycont().getArmies().remove(s.getG().getPlayer().getControlledArmies().indexOf(unit.getParentArmy()));
				//	s.getG().getPlayer().getControlledArmies().remove(unit.getParentArmy());
					toRelocate.relocateUnit(unit);
					//worldcont.getW().dispose();
					//s.getW().getIdle().removeAll();
					this.setVisible(false);
					worldcont.refreshtext();

					worldcont.getW().revalidate();
					worldcont.getW().repaint();

				//}
			} catch (MaxCapacityException e1) {
				JOptionPane.showMessageDialog(null,"Cannot add more units, maximum capacity of army reached","Capacity Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}

		}
	}
}
