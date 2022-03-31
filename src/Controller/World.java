package Controller;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import engine.City;
import engine.Player;
import units.Army;
import units.Status;
import view.BattleChoiceArrived;
import view.BattleChoiceView;
import view.BreakSiege;
import view.WorldMap;
public class World implements ActionListener{
	private ArrayList<JButton> buttons;
	//	private ArrayList<Army> March;
	private ArrayList<Army> armies;
	private JButton army;
	private StartCont s;
	private ArmyCont acont;
	private CityCont citycont;
	private WorldMap w;
	private Player p;
	private String targ;
	private Army mine;
	private boolean controlled=false;
	private boolean flag=true;


	public JButton getArmy() {
		return army;
	}
	public void setArmy(JButton army) {
		this.army = army;
	}
	public Army getMine() {
		return mine;
	}
	public void setMine(Army mine) {
		this.mine = mine;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getTarg() {
		return targ;
	}
	public void setTarg(String targ) {
		this.targ = targ;
	}
	public ArrayList<Army> getArmies() {
		return armies;
	}
	public void setArmies(ArrayList<Army> armies) {
		this.armies = armies;
	}
	public void refresh() {
		JButton info=new JButton("Get Info"); 
		s.getW().getGetInfo().add(info,FlowLayout.LEFT);
		info.addActionListener(this); 
		//		Army x=new Army("Cairo");
		//		x.setCurrentStatus(Status.BESIEGING);
		//		x.setTarget("fjwd");
		//		Unit 	u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
		//		Unit u2 = (new Cavalry(2, 45, 0.6, 0.7, 0.75));
		//		Unit u3 = (new Infantry(1, 45, 0.6, 0.7, 0.75));
		//		Unit u4 = (new Archer(3, 45, 0.6, 0.7, 0.75));
		//		x.getUnits().add(u);
		//		x.getUnits().add(u2);
		//		x.getUnits().add(u3);
		//		x.getUnits().add(u4);
		//		p.getControlledArmies().add(x);

		JButton end=new JButton("End Turn");
		end.addActionListener(this);
		s.getW().getEndturn().add(end);

		int x=240;
		int y=140;
		int city=0;

		for(int i = 0; i<s.getG().getAvailableCities().size();i++) { 
			JButton b=new JButton(s.getG().getAvailableCities().get(i).getName());
			s.getW().getCity().add(b);

			b.setBounds(x, y, 100, 100);
			if (city==0) {
				x+=570;
				city++; 
			}
			else if(city==1) {
				x-=480;
				y+=320;
			}



			b.addActionListener(this);
		}
		//s.getW().getCity().setLayout(new BorderLayout());

		for(int i=0;i<p.getControlledArmies().size();i++) {      //should we add defending army as an idle?
			int count=1;
			if(p.getControlledArmies().get(i).getCurrentStatus().equals(Status.IDLE)) {
				armies.add(p.getControlledArmies().get(i));
				JButton b=new JButton("Idle Army"+count);
				b.addActionListener(this);
				count++;
				s.getW().getIdle().add(b);
				buttons.add(b);
			}
		}

		for(int i=0;i<p.getControlledArmies().size();i++) {
			int count=1;
			if(p.getControlledArmies().get(i).getCurrentStatus().equals(Status.MARCHING)) {
				armies.add(p.getControlledArmies().get(i));
				JButton b=new JButton("Marching Army"+count);
				b.addActionListener(this);
				count++;
				s.getW().getMarching().add(b);
				buttons.add(b);
			}
		}

		for(int i=0;i<p.getControlledArmies().size();i++) {
			int count=1;
			if(p.getControlledArmies().get(i).getCurrentStatus().equals(Status.BESIEGING)) {
				JButton b=new JButton("Besieging Army"+count);
				armies.add(p.getControlledArmies().get(i));
				b.addActionListener(this);
				count++;
				s.getW().getBeseiging().add(b);
				buttons.add(b);
			}
		}


		s.getW().revalidate();
		s.getW().repaint();

	}
	public World(StartCont s1) {
		w = new WorldMap();
		s = s1;
		armies=new ArrayList<Army>();
		buttons=new ArrayList<JButton>();
		p=s.getG().getPlayer();
		refreshtext();
		JButton info=new JButton("Get Info"); 
		s.getW().getGetInfo().add(info);
		info.addActionListener(this); 
	
		JButton end=new JButton("End Turn");
		end.addActionListener(this);
		s.getW().getEndturn().add(end);

		int x=240;
		int y=140;
		int city=0;
		for(int i = 0; i<s.getG().getAvailableCities().size();i++) { 
			JButton b=new JButton(s.getG().getAvailableCities().get(i).getName()); 
			s.getW().getCity().add(b);
			b.setBounds(x, y, 100, 100);
			if (city==0) {
				x+=570;
				city++;
			}
			else if(city==1) {
				x-=480;
				y+=320;
			}

			b.addActionListener(this);
		}

		x=0;
		y=0;
		for(int i=0;i<p.getControlledArmies().size();i++) {      //should we add defending army as an idle?
			int count=1;
			if(p.getControlledArmies().get(i).getCurrentStatus().equals(Status.IDLE)) {
				armies.add(p.getControlledArmies().get(i));
				JButton b=new JButton("Idle Army"+count);
				//b.setBounds(x, y, 50, 50);
				//x+=100;
				//y+=100;
				b.addActionListener(this);
				count++;
				s.getW().getIdle().add(b);
				buttons.add(b);
			}
		}

		for(int i=0;i<p.getControlledArmies().size();i++) {
			int count=1;
			if(p.getControlledArmies().get(i).getCurrentStatus().equals(Status.MARCHING)) {
				armies.add(p.getControlledArmies().get(i));
				JButton b=new JButton("Marching Army"+count);
				b.addActionListener(this);
				count++;
				s.getW().getMarching().add(b);
				buttons.add(b);
			}
		}

		for(int i=0;i<p.getControlledArmies().size();i++) {
			int count=1;
			if(p.getControlledArmies().get(i).getCurrentStatus().equals(Status.BESIEGING)) {
				JButton b=new JButton("Besieging Army"+count);
				armies.add(p.getControlledArmies().get(i));
				b.addActionListener(this);
				count++;
				s.getW().getBeseiging().add(b);
				buttons.add(b);
			}
		}


		s.getW().revalidate();
		s.getW().repaint();

	}
	public void refreshtext() {
		int count=1;

		s.getW().getInfo().setText("  Player Name: "+s.getG().getPlayer().getName()+"\n"+"  Turn Number: "+s.getG().getCurrentTurnCount()+"\n"+"  Food: "+s.getG().getPlayer().getFood()+"\n"+"  Treasury: "+s.getG().getPlayer().getTreasury());
		s.getW().getIdle().removeAll();
		for(int i=0;i<p.getControlledArmies().size();i++) {      //should we add defending army as an idle?
			if(p.getControlledArmies().get(i).getCurrentStatus().equals(Status.IDLE)) {
				//	if(p.getControlledArmies().get(i).getUnits().size()!=0) {
				armies.add(p.getControlledArmies().get(i));
				JButton b=new JButton("Idle Army"+count);
				b.addActionListener(this);
				count++;
				s.getW().getIdle().add(b);
				buttons.add(b);
				//}
			}
		}		
		s.getW().getMarching().removeAll();
		count=1;
		for(int i=0;i<p.getControlledArmies().size();i++) {
			if(p.getControlledArmies().get(i).getCurrentStatus().equals(Status.MARCHING)) {
				armies.add(p.getControlledArmies().get(i));
				JButton b=new JButton("Marching Army"+count);
				b.addActionListener(this);
				count++;
				s.getW().getMarching().add(b);
				buttons.add(b);
			}
		}
		s.getW().getBeseiging().removeAll();
		count=1;
		for(int i=0;i<p.getControlledArmies().size();i++) {
			if(p.getControlledArmies().get(i).getCurrentStatus().equals(Status.BESIEGING)) {
				JButton b=new JButton("Besieging Army"+count);
				armies.add(p.getControlledArmies().get(i));
				b.addActionListener(this);
				count++;
				s.getW().getBeseiging().add(b);
				buttons.add(b);
			}
		}
		this.getW().revalidate();
		this.getW().repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
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
						//System.out.println(mine);
					} 
				}
				s.getG().endTurn();


				for(int i = 0; i<s.getG().getPlayer().getControlledArmies().size();i++) {
					if(s.getG().getPlayer().getControlledArmies().get(i).getDistancetoTarget()==0 && flag==true) { //prevents lay seige and manual attack options from showing up again
						//System.out.println(s.getG().getPlayer().getControlledArmies().get(i).getTarget());
						for(int j =0; j<s.getG().getAvailableCities().size();j++) {
							if(s.getG().getAvailableCities().get(j).getName().equals(targ)) {
								City x = s.getG().getAvailableCities().get(j);
								//System.out.println(s.getG().getPlayer().getControlledArmies().get(i).getCurrentLocation());
								//System.out.println(targ);
								//System.out.println(s.getG().getPlayer().getControlledArmies().get(i));
								//this.getW().setVisible(false);
								//current location is an empty string, why????????
								new BattleChoiceArrived(mine,x.getDefendingArmy(),s,x);
								flag=false;
								targ=null;
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
					if(s.getG().getAvailableCities().get(i).isUnderSiege()==false ) { //checks that autoresolve and manual dont appear again
						if(s.getG().getAvailableCities().get(i).getTurnsUnderSiege()==3) {
							City c= s.getG().getAvailableCities().get(i);
							new BattleChoiceView(mine,s.getG().getAvailableCities().get(i).getDefendingArmy(),s,c);
							this.getW().setVisible(false);

							//flag=false;
						}
					}
				}
				s.getW().getCity().removeAll();
				s.getW().getInfo().removeAll();
				s.getW().getIdle().removeAll();
				s.getW().getMarching().removeAll();
				s.getW().getBeseiging().removeAll();
				s.getW().getEndturn().removeAll();
				s.getW().getGetInfo().removeAll();
				s.getW1().refresh();
				s.getW1().refreshtext();
			}
			if(!b.getActionCommand().equals("Get Info")) {
				switch(b.getActionCommand()) {
				case "Cairo":
				{
					for(int i =0;i<s.getG().getPlayer().getControlledCities().size();i++) {
						if (s.getG().getPlayer().getControlledCities().get(i).getName().equals("Cairo")) {
							citycont = new CityCont(s.getG().getPlayer().getControlledCities().get(i),s);
							citycont.getC().setVisible(true);
							controlled=true;
						}
						
					}
					if(!controlled) {
						JOptionPane.showMessageDialog(null,"Stop trying to view your enemy's city CHEATER!","City Error", JOptionPane.ERROR_MESSAGE);
					}
					controlled=false;

					break;
				}
				case "Sparta":
				{
					for(int i =0;i<s.getG().getPlayer().getControlledCities().size();i++) {
						if (s.getG().getPlayer().getControlledCities().get(i).getName().equals("Sparta")) {
							citycont = new CityCont(s.getG().getPlayer().getControlledCities().get(i),s);
							citycont.getC().setVisible(true);
							controlled=true;

						}
					}
					if(!controlled) {
						JOptionPane.showMessageDialog(null,"Stop trying to view your enemy's city CHEATER!","City Error", JOptionPane.ERROR_MESSAGE);
					}
					controlled=false;
				}
				break;

				case "Rome":
				{
					for(int i =0;i<s.getG().getPlayer().getControlledCities().size();i++) {
						if (s.getG().getPlayer().getControlledCities().get(i).getName().equals("Rome")) {
							citycont = new CityCont(s.getG().getPlayer().getControlledCities().get(i),s);
							citycont.getC().setVisible(true);
							controlled=true;
						}
					}
					if(!controlled) {
						JOptionPane.showMessageDialog(null,"Stop trying to view your enemy's city CHEATER!","City Error", JOptionPane.ERROR_MESSAGE);
					}
					controlled=false;

					break;
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

		}
	}

	public WorldMap getW() {
		return w;
	}

	public void setW(WorldMap w) {
		this.w = w;
	}

	public CityCont getCitycont() {
		return citycont;
	}

	public void setCitycont(CityCont citycont) {
		this.citycont = citycont;
	}

}






