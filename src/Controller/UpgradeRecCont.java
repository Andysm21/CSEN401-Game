package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.MilitaryBuilding;
import buildings.Stable;
import engine.City;
import exceptions.BuildingInCoolDownException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import view.BattleChoiceArrived;
import view.UpgradeRecView;

public class UpgradeRecCont implements ActionListener{
	
	private UpgradeRecView ur;
	private Building building;
	private StartCont s;
	private City city;
	private String targ;
	
	
	public UpgradeRecView getUr() {
		return ur;
	}


	public void setUr(UpgradeRecView ur) {
		this.ur = ur;
	}


	public Building getBuilding() {
		return building;
	}


	public void setBuilding(Building building) {
		this.building = building;
	}


	public StartCont getS() {
		return s;
	}


	public void setS(StartCont s) {
		this.s = s;
	}


	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public UpgradeRecCont(Building b,StartCont s1,City c1) {
		city=c1;
		s=s1;
		building =b ;
		ur =new UpgradeRecView();
		JButton upgrade=new JButton("Upgrade");
		ur.getButtons().add(upgrade);
		upgrade.addActionListener(this);
		ur.getTextinfo().setText("Level: "+ b.getLevel()+"\n"+"Upgrade Cost: "+b.getUpgradeCost());

		
		if(b instanceof MilitaryBuilding) {
		JButton recruit=new JButton("Recruit");
		recruit.addActionListener(this);
		ur.getButtons().add(recruit);
		if(b instanceof ArcheryRange) {
			ArcheryRange a=(ArcheryRange )b;
			ur.getTextinfo().setText("Level: "+ b.getLevel()+"\n"+"Upgrade Cost: "+b.getUpgradeCost()+"\n"+"Recruitment Cost: "+a.getRecruitmentCost());

		}
		if(b instanceof Barracks) {
			Barracks ba=(Barracks )b;
			ur.getTextinfo().setText("Level: "+ b.getLevel()+"\n"+"Upgrade Cost: "+b.getUpgradeCost()+"\n"+"Recruitment Cost: "+ba.getRecruitmentCost());

		}
		if(b instanceof Stable) {
			Stable s=(Stable )b;
			ur.getTextinfo().setText("Level: "+ b.getLevel()+"\n"+"Upgrade Cost: "+b.getUpgradeCost()+"\n"+"Recruitment Cost: "+s.getRecruitmentCost());

		}
		

		}
		

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b.getActionCommand().equals("Upgrade")) {
			try {
				s.getG().getPlayer().upgradeBuilding(building);
				s.getW1().getCitycont().refreshtext();
				this.getUr().dispose();
			} catch (NotEnoughGoldException e1) {
				  JOptionPane.showMessageDialog(null,"You do not have enough gold to upgrade!","Money Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (BuildingInCoolDownException e1) {
				  JOptionPane.showMessageDialog(null,"Building is in cool down you can not upgrade!","Building Cooldown Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (MaxLevelException e1) {
				  JOptionPane.showMessageDialog(null,"Building can not be upgraded, maximum level of 3 already reached!","Building Level Error", JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		if(b.getActionCommand().equals("Recruit")) {
			if(building instanceof ArcheryRange) {
					try {
						s.getG().getPlayer().recruitUnit("archer", city.getName());
						s.getW1().getCitycont().refreshtext();
						this.getUr().dispose();
					} catch (BuildingInCoolDownException e1) {
						  JOptionPane.showMessageDialog(null,"Building is in cool down you can not recruit!","Building Cooldown Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (MaxRecruitedException e1) {
						  JOptionPane.showMessageDialog(null,"Maximum number of units of this building reached in this turn","Recruitment Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (NotEnoughGoldException e1) {
						  JOptionPane.showMessageDialog(null,"You do not have enough gold to recruit!","Money Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					};
			}
			if(building instanceof Barracks) {
				try {
					s.getG().getPlayer().recruitUnit("infantry", city.getName());
					s.getW1().getCitycont().refreshtext();
					this.getUr().dispose();
				} catch (BuildingInCoolDownException e1) {
					  JOptionPane.showMessageDialog(null,"Building is in cool down you can not recruit!","Building Cooldown Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (MaxRecruitedException e1) {
					  JOptionPane.showMessageDialog(null,"Maximum number of units of this building reached in this turn","Recruitment Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (NotEnoughGoldException e1) {
					  JOptionPane.showMessageDialog(null,"You do not have enough gold to recruit!","Money Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				};
			}
			if(building instanceof Stable) {
				try {
					s.getG().getPlayer().recruitUnit("cavalry", city.getName());
					s.getW1().getCitycont().refreshtext();
					this.getUr().dispose();
				} catch (BuildingInCoolDownException e1) {
					  JOptionPane.showMessageDialog(null,"Building is in cool down you can not recruit!","Building Cooldown Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (MaxRecruitedException e1) {
					  JOptionPane.showMessageDialog(null,"Maximum number of units of this building reached in this turn","Recruitment Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (NotEnoughGoldException e1) {
					  JOptionPane.showMessageDialog(null,"You do not have enough gold to recruit!","Money Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
		}
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
			this.getUr().dispose();
			s.getW().setVisible(true);
		
		
	}
		
	}
	

}

