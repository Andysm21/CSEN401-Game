package Controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.Farm;
import buildings.Market;
import buildings.Stable;
import engine.City;
import engine.Player;
import engine.PlayerListener;
import exceptions.NotEnoughGoldException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;
import view.BattleChoiceArrived;
import view.BattleChoiceView;
import view.BreakSiege;
import view.CityView;

public class CityCont implements ActionListener {

	private CityView c;
	private JButton army;
	private ArrayList<Army> armies;
	private ArrayList<JButton> militB;
	private ArrayList<JButton> ecoB;
	private ArrayList<JButton> buttons;
	private StartCont s;
	private City c1;
	private static Player andrew; //REMOVE THIS LATER
	private JButton selected;
	private ArmyCont acont;
	private BuildCont buildcont;
	private JButton bar;
	private JButton arc;
	private JButton far;
	private JButton mark;
	private JButton sta;
	private UpgradeRecCont Upgreccont;
	private String targ;
	private Army mine;
	private boolean flag=true;
	


	public ArrayList<Army> getArmies() {
		return armies;
	}

	public void setArmies(ArrayList<Army> armies) {
		this.armies = armies;
	}

	public ArrayList<JButton> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<JButton> buttons) {
		this.buttons = buttons;
	}

	public JButton getSelected() {
		return selected;
	}

	public void setSelected(JButton selected) {
		this.selected = selected;
	}

	public CityView getC() {
		return c;
	}

	public void setC(CityView c) {
		this.c = c;
	}


	public City getC1() {
		return c1;
	}

	public void setC1(City c1) {
		this.c1 = c1;
	}



	public JButton getBar() {
		return bar;
	}

	public void setBar(JButton bar) {
		this.bar = bar;
	}

	public JButton getArc() {
		return arc;
	}

	public void setArc(JButton arc) {
		this.arc = arc;
	}

	public JButton getFar() {
		return far;
	}

	public void setFar(JButton far) {
		this.far = far;
	}

	public JButton getMark() {
		return mark;
	}

	public void setMark(JButton mark) {
		this.mark = mark;
	}

	public JButton getSta() {
		return sta;
	}

	public void setSta(JButton sta) {
		this.sta = sta;
	}

	public CityCont(City city,StartCont s1) {
		s=s1;
		c1=city;
		c=new CityView();
		armies = new ArrayList<Army>();
		targ=s.getW1().getTarg();
		mine=s.getW1().getMine();
		bar=new JButton("Barracks");
		arc=new JButton("Archery Range");
		sta=new JButton("Stable");
		far=new JButton("Farm");
		mark=new JButton("Market");
		bar.setEnabled(false);
		arc.setEnabled(false);
		sta.setEnabled(false);
		far.setEnabled(false);
		mark.setEnabled(false);
		JButton info=new JButton("Get Info"); 
		this.getC().getEndturn().add(info);
		info.addActionListener(this); 
		bar.addActionListener(this);
		sta.addActionListener(this);
		arc.addActionListener(this);
		far.addActionListener(this);
		mark.addActionListener(this);
		bar.setActionCommand("building");
		sta.setActionCommand("building");
		arc.setActionCommand("building");
		far.setActionCommand("building");
		mark.setActionCommand("building");
		c.getBuildings().add(bar);
		c.getBuildings().add(arc);
		c.getBuildings().add(sta);
		c.getBuildings().add(far);
		c.getBuildings().add(mark);
		JButton end=new JButton("End Turn");
		end.addActionListener(this);
		this.getC().getEndturn().add(end);


		//JButton up=new JButton("Upgrade");
		JButton build=new JButton("Build");
		//JButton rec=new JButton("Recruit");
		//up.setEnabled(false);
		//rec.setEnabled(false);
		c.getBoptions().add(build);
		//c.getBoptions().add(up);
		//c.getBoptions().add(rec);
		build.addActionListener(this);
		JButton b=new JButton("Defending Army");
		b.addActionListener(this);
		c.getDefending().add(b);
		this.refresh(city, s1);
	}
	public void refresh(City city,StartCont s1) {
		s=s1;
		c1=city;
//		Unit u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
//		Unit u2 = (new Cavalry(2, 45, 0.6, 0.7, 0.75));
//		Unit u3 = (new Infantry(1, 45, 0.6, 0.7, 0.75));
//		Unit u4 = (new Archer(3, 45, 0.6, 0.7, 0.75));
//		c1.getDefendingArmy().getUnits().add(u);
//		c1.getDefendingArmy().getUnits().add(u2);
//		c1.getDefendingArmy().getUnits().add(u3);
//		c1.getDefendingArmy().getUnits().add(u4);
		militB = new ArrayList<JButton>();
		ecoB = new ArrayList<JButton>();
		buttons = new ArrayList<JButton>();
		refreshtext();
		
		for(int i=0;i<c1.getEconomicalBuildings().size();i++) { 
			if(c1.getEconomicalBuildings().get(i) instanceof Market) {
				this.getMark().setEnabled(true);
			} 
			if(c1.getEconomicalBuildings().get(i) instanceof Farm) {
				this.getFar().setEnabled(true);
			} 
		}

		for(int i=0;i<c1.getMilitaryBuildings().size();i++) { 
			if(c1.getMilitaryBuildings().get(i) instanceof Stable) {
				this.getSta().setEnabled(true);
			} 
			if(c1.getMilitaryBuildings().get(i) instanceof ArcheryRange) {
				this.getArc().setEnabled(true);
			} 
			if(c1.getMilitaryBuildings().get(i) instanceof Barracks) {
				this.getBar().setEnabled(true); 
			} 

		}	



		for(int i =0; i < s.getG().getPlayer().getControlledArmies().size();i++) {//s.getG().getPlayer()
			if(s.getG().getPlayer().getControlledArmies().get(i).getCurrentLocation().equals(city.getName())) { 
				JButton b1 = new JButton ("Army"+(i+1));
				c.getArmies().add(b1);
				b1.addActionListener(this);
				buttons.add(b1); 
				c.getArmies().add(b1);
				armies.add(s.getG().getPlayer().getControlledArmies().get(i));
			} 
		}



		c.revalidate();
		c.repaint();
	}


public void refreshtext() {
	this.getC().getPlayer().setText("Player Name: "+s.getG().getPlayer().getName()+"\n"+"Turn Number: "+s.getG().getCurrentTurnCount()+"\n"+"Food: "+s.getG().getPlayer().getFood()+"\n"+"Treasury: "+s.getG().getPlayer().getTreasury());
}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b= (JButton) e.getSource();
		if(b.getActionCommand().equals("Get Info")) { 
			if(army==null) {
				JOptionPane.showMessageDialog(null,"Please Choose an army first to display the information!","Selection Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				int r=buttons.indexOf(army);
				Army a= armies.get(r);
				acont=new ArmyCont(a,s.getPlayercity(),s);
				acont.getView().setVisible(true);
				army.setIcon(null); 
				army=null; 
			}
		}
		if(b.getActionCommand().equals("Defending Army")) {
			acont = new ArmyCont(c1.getDefendingArmy(),c1,s);
			acont.getView().setVisible(true);
			acont.getView().getArmy().setVisible(true);
			acont.getView().getUnit().setSize(new Dimension(acont.getView().getWidth(),350));
			acont.getView().getArmy().setSize(new Dimension(0,0));
			JButton initiate = new JButton("Initiate Army");
			initiate.addActionListener(acont);
			acont.getView().getUnit().add(initiate);
			//PUT A PICTURE ABOVE THE DETAILS
		}
		else {
			int r=buttons.indexOf(b);

			if(b.getActionCommand().equals("Get Info")) { //ADD GET INFO BUTTON AND MAKE IT WORK
				Army a= s.getG().getPlayer().getControlledArmies().get(r);
				acont=new ArmyCont(a,null,s);
				acont.getView().revalidate();
				acont.getView().setVisible(true); 
			}
			else {
				if(b.getActionCommand().equals("Build")) {
					c.setVisible(false);
					buildcont = new BuildCont(s,this,c1);
					buildcont.getBv().setVisible(true);
					this.getC().dispose();
				}
				else {
					if(b.getActionCommand().equals("building")) {
						switch(b.getText()) {
						case "Market":
						{
							for(int i =0;i<c1.getEconomicalBuildings().size();i++) { 
								if(c1.getEconomicalBuildings().get(i) instanceof Market) {
									Upgreccont = new UpgradeRecCont(c1.getEconomicalBuildings().get(i),s,c1);
									Upgreccont.getUr().setVisible(true);
								}
							}
							break;
						}
						case "Farm":
						{
							for(int i =0;i<c1.getEconomicalBuildings().size();i++) { 
								if(c1.getEconomicalBuildings().get(i) instanceof Farm) {
									Upgreccont = new UpgradeRecCont(c1.getEconomicalBuildings().get(i),s,c1);
									Upgreccont.getUr().setVisible(true);
								}
							}
							break;
						}
						case "Archery Range":
						{
							for(int i =0;i<c1.getMilitaryBuildings().size();i++) { 
								if(c1.getMilitaryBuildings().get(i) instanceof ArcheryRange) {
									Upgreccont = new UpgradeRecCont(c1.getMilitaryBuildings().get(i),s,c1);
									Upgreccont.getUr().setVisible(true);
								}
							}
							break;
						}
						case "Barracks":
						{
							for(int i =0;i<c1.getMilitaryBuildings().size();i++) { 
								if(c1.getMilitaryBuildings().get(i) instanceof Barracks) {
									Upgreccont = new UpgradeRecCont(c1.getMilitaryBuildings().get(i),s,c1);
									Upgreccont.getUr().setVisible(true);
								}
							}
							break;
						}
						case "Stable":
						{
							for(int i =0;i<c1.getMilitaryBuildings().size();i++) { 
								if(c1.getMilitaryBuildings().get(i) instanceof Stable) {
									Upgreccont = new UpgradeRecCont(c1.getMilitaryBuildings().get(i),s,c1);
									Upgreccont.getUr().setVisible(true);
								}
							}
							break;
						}
						}
					}
					else {
						if(e.getActionCommand().equals("End Turn")) {
							
							if(s.getG().isGameOver()) {
								if(s.getG().getPlayer().getControlledCities().size() == s.getG().getAvailableCities().size() ) {
									JOptionPane.showMessageDialog(null,"Congratulations, you have successfully conquered all the cities and you became the World Conquerer","Game Overrrrrrr", JOptionPane.OK_OPTION);	
									System.exit(0);
								}					
								else {
									JOptionPane.showMessageDialog(null,"We have some bad news for you, you have ran out of turns and you couldn't conquerer all the cities","Game Overrrrrrr", JOptionPane.OK_OPTION);	
									System.exit(0);
								}
							}
							
							
							for(int i = 0; i<s.getG().getPlayer().getControlledArmies().size();i++) {

								if(s.getG().getPlayer().getControlledArmies().get(i).getDistancetoTarget()==1) {
									targ=s.getG().getPlayer().getControlledArmies().get(i).getTarget();
									mine=s.getG().getPlayer().getControlledArmies().get(i);
								
								}
							}
							s.getG().endTurn();
						
							for(int i = 0; i<s.getG().getPlayer().getControlledArmies().size();i++) {

								if(s.getG().getPlayer().getControlledArmies().get(i).getDistancetoTarget()==0 && flag==true) {
									for(int j =0; j<s.getG().getAvailableCities().size();j++) {

										if(s.getG().getAvailableCities().get(j).getName().equals(targ)) {
											City x = s.getG().getAvailableCities().get(j);
											//this.getW().setVisible(false);
											new BattleChoiceArrived(s.getG().getPlayer().getControlledArmies().get(i),x.getDefendingArmy(),s,x);
											flag=false;
										}
									}
								}
							}
							for(int i=0;i<s.getG().getAvailableCities().size();i++) {
								if(s.getG().getAvailableCities().get(i).isUnderSiege()) {
									City c= s.getG().getAvailableCities().get(i);
									new BreakSiege(mine, s.getG().getAvailableCities().get(i).getDefendingArmy(),s,c);
								}
							}
							for(int i=0; i<s.getG().getAvailableCities().size();i++) {
								if(s.getG().getAvailableCities().get(i).isUnderSiege()==false) {
									if(s.getG().getAvailableCities().get(i).getTurnsUnderSiege()==3) {
										City c= s.getG().getAvailableCities().get(i);
										new BattleChoiceView(mine,s.getG().getAvailableCities().get(i).getDefendingArmy(),s,c);
										s.getW().setVisible(false);
									}
								}
							}
							
							s.getW1().refreshtext();
							this.refreshtext();
							this.getC().dispose();
						}
//						if(e.getActionCommand().equals("End Turn")) {
//							for(int i = 0; i<s.getG().getPlayer().getControlledArmies().size();i++) {
//
//								if(s.getG().getPlayer().getControlledArmies().get(i).getDistancetoTarget()==1) {
//									targ=s.getG().getPlayer().getControlledArmies().get(i).getTarget();
//								
//								}
//							}
//							s.getG().endTurn();
//							for(int i = 0; i<s.getG().getPlayer().getControlledArmies().size();i++) {
//
//								if(s.getG().getPlayer().getControlledArmies().get(i).getDistancetoTarget()==0) {
//									for(int j =0; j<s.getG().getAvailableCities().size();j++) {
//
//										if(s.getG().getAvailableCities().get(j).getName().equals(targ)) {
//											City x = s.getG().getAvailableCities().get(j);
//											new BattleChoiceArrived(s.getG().getPlayer().getControlledArmies().get(i),x.getDefendingArmy(),s);
//										}
//									}
//								}
//							}
//							s.getW1().refreshtext();
//							this.refreshtext();
//							this.getC().dispose();
//						}
						
					}
				}
			}
		}
		if(army==null) {
			army=b;
		}
		else {
			if(b==army) {
				army=null;
			}
			else {
				army=b;
			}
		}
	}

	public ArmyCont getAcont() {
		return acont;
	}

	public void setAcont(ArmyCont acont) {
		this.acont = acont;
	}


}
