package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import engine.City;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;
import view.ArmyView;
import view.BattleChoiceArrived;
import view.IdleView;
import view.TargetCityView;

public class ArmyCont implements ActionListener{

	private static StartCont s;
	private ArmyView view;
	private Unit[] u;
	private Object selected;
	private int selectedIndx;
	private City c1;
	private Army a1;
	private String [] uname;
	private JComboBox box;
	private JButton target;
	private JButton relocate;
	private TargetCityView x;
	private IdleView idleV;
	private String targ;


	public Army getA1() {
		return a1;
	}


	public void setA1(Army a1) {
		this.a1 = a1;
	}


	public City getC1() {
		return c1;
	}


	public void setC1(City c1) {
		this.c1 = c1;
	}


	public JButton getTarget() {
		return target;
	}


	public void setTarget(JButton target) {
		this.target = target;
	}


	public JButton getRelocate() {
		return relocate;
	}
 

	public void setRelocate(JButton relocate) {
		this.relocate = relocate;
	}


	public void refreshText() {
		view.getPlayer().setText("Player Name: "+s.getG().getPlayer().getName()+"\n"+"Turn Number: "+s.getG().getCurrentTurnCount()+"\n"+"Food: "+s.getG().getPlayer().getFood()+"\n"+"Treasury: "+s.getG().getPlayer().getTreasury());
	}


	public void refresh(Army a) {

		uname=new String[a.getUnits().size()];
		u=new Unit[a.getUnits().size()];
		for(int i=0;i<a.getUnits().size();i++) {
			u[i]=a.getUnits().get(i);
			if(a.getUnits().get(i) instanceof Archer) {
				uname[i]="Archer "+(i+1);
			}
			if(a.getUnits().get(i) instanceof Cavalry) {
				uname[i]="Cavalry "+(1+i);
			}
			if(a.getUnits().get(i) instanceof Infantry) {
				uname[i]="Infantry "+(i+1);
			}


		}
		box=new JComboBox(uname);
		box.addActionListener(this);
		box.setBounds(0, 0, 300, 250);

	}
	public ArmyCont(Army a,City c,StartCont s1) {


		a1=a;
		//a1=c.getDefendingArmy();
		s=s1;
		c1=c;
		targ=s.getW1().getTarg();
		view= new ArmyView();
		refreshText();
		refresh(a);
		view.getUnit().add(box);

		//view.getPlayer().setText("Player Name: "+s.getName()+"\n"+"Turn Number: "+s.getG().getCurrentTurnCount()+"\n"+"Food: "+s.getG().getPlayer().getFood()+"\n"+"Treasury: "+s.getG().getPlayer().getTreasury());
		if(a.getCurrentStatus().equals(Status.IDLE)) {
			if(!c.getDefendingArmy().equals(a)) {
				target=new JButton("Set Target");
				target.addActionListener(this);
				view.getButtonss().add(target);
				} 
			relocate=new JButton("Relocate Unit");
			relocate.addActionListener(this);
			view.getButtonss().add(relocate);
			view.remove(view.getArmy());
		}
		if(a.getCurrentStatus().equals(Status.BESIEGING)) {
			for(int i =0; i<s.getG().getAvailableCities().size();i++) {
				if(targ.equals(s.getG().getAvailableCities().get(i).getName())) {
					City C = s.getG().getAvailableCities().get(i);
					view.getArmy().setText("City Under Siege:"+targ+"\n"+"Turns Under Siege:"+C.getTurnsUnderSiege());
				}
			}
		}

		else {
			if(a.getCurrentStatus().equals(Status.MARCHING)) {
				if(!c.getDefendingArmy().equals(a)) {
					target=new JButton("Set Target");
					target.addActionListener(this);
					view.getButtonss().add(target);
					}
				view.getArmy().setText("Targeted City:"+a.getTarget()+"\n"+"Turns till reaching the City:"+a.getDistancetoTarget()+"\n");

			}
		}
		
//		JButton end=new JButton("End Turn");
//		end.addActionListener(this);
//		view.getEndturn().add(end);
//



		view.revalidate();
		view.repaint();


	}

	public StartCont getS() {
		return s;
	}

	public void setS(StartCont s) {
		this.s = s;
	}

	public ArmyView getView() {
		return view;
	}

	public void setView(ArmyView view) {
		this.view = view;
	}

	public Unit[] getU() {
		return u;
	}

	public void setU(Unit[] u) {
		this.u = u;
	}

	public static void main(String [] args) {
		Army x=new Army("Cairo");
		x.setCurrentStatus(Status.MARCHING);
		x.setTarget("fjwd");
		Unit 	u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
		Unit u2 = (new Cavalry(2, 45, 0.6, 0.7, 0.75));
		Unit u3 = (new Infantry(1, 45, 0.6, 0.7, 0.75));
		Unit u4 = (new Archer(3, 45, 0.6, 0.7, 0.75));
		x.getUnits().add(u);
		x.getUnits().add(u2);
		x.getUnits().add(u3);
		x.getUnits().add(u4);
		new ArmyCont(x,null,s);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Initiate Army")) {
			s.getG().getPlayer().initiateArmy(c1,(Unit)u[selectedIndx]); 
			s.getW().getIdle().removeAll();
			s.getW().getMarching().removeAll();
			s.getW().getBeseiging().removeAll();
			this.getView().setVisible(false);
			this.refresh(a1);
			//view.setVisible(true);
			s.getW1().refreshtext(); 

		}
		else {
			if(e.getActionCommand().equals("Relocate Unit")) {
				idleV= new IdleView(s,(Unit)u[selectedIndx],s.getW1(),this);
				idleV.setVisible(true);
				this.getView().dispose();
				s.getW1().refreshtext();
			}	
			else {
				if(e.getActionCommand().equals("Set Target")) {
					new TargetCityView(this,s,c1);
					this.getView().dispose();
				}
				
				else {
					if(e.getActionCommand().equals("End Turn")) {
						for(int i = 0; i<s.getG().getPlayer().getControlledArmies().size();i++) {

							if(s.getG().getPlayer().getControlledArmies().get(i).getDistancetoTarget()==1) {
								targ=s.getG().getPlayer().getControlledArmies().get(i).getTarget();
							
							}
						}
						s.getG().endTurn();
						for(int i = 0; i<s.getG().getPlayer().getControlledArmies().size();i++) {

							if(s.getG().getPlayer().getControlledArmies().get(i).getDistancetoTarget()==0) {
								for(int j =0; j<s.getG().getAvailableCities().size();j++) {

									if(s.getG().getAvailableCities().get(j).getName().equals(targ)) {
										City x = s.getG().getAvailableCities().get(j);
										new BattleChoiceArrived(s.getG().getPlayer().getControlledArmies().get(i),x.getDefendingArmy(),s,x);
									}
								}
							}
						}
						s.getW1().refreshtext();
						this.getView().dispose();
						s.getW1().getCitycont().getC().dispose();
						s.getW().setVisible(true);
					}
					
				
				else {
					JComboBox c= (JComboBox) e.getSource();
					selected=c.getSelectedItem();
					selectedIndx=c.getSelectedIndex();
					int i=c.getSelectedIndex();

					Unit unit=u[i];
					view.getUnitinfo().setText("Level: "+unit.getLevel()+"\n"+"Current Soldier Count: "+unit.getCurrentSoldierCount()+"\n"+"Max Soldier Count: "+unit.getMaxSoldierCount());
					view.getUnitinfo().setVisible(true);
				}
				}
			}
		}





	}

}
