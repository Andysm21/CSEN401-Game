package Controller;

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
import exceptions.NotEnoughGoldException;
import units.Army;
import view.BattleChoiceArrived;
import view.BattleChoiceView;
import view.BreakSiege;
import view.BuildView;

public class BuildCont implements ActionListener{

	private BuildView bv;
	private ArrayList <JButton> buttons;
	private ArrayList <Building> buildings;
	private JButton bsaved ;
	private JButton market;
	private StartCont s;
	private CityCont citycont;
	private City c1;
	private String targ;
	private Army mine;
	private boolean flag=true;
	//private CityView cv;
	public BuildView getBv() {
		return bv;
	}
	public void setBv(BuildView bv) {
		this.bv = bv;
	}
	public StartCont getS() {
		return s;
	}
	public void setS(StartCont s) {
		this.s = s;
	}
	public CityCont getCitycont() {
		return citycont;
	}
	public void setCitycont(CityCont citycont) {
		this.citycont = citycont;
	}

	public BuildCont(StartCont s1,CityCont cc,City c) {
		citycont=cc;
		c1=c;
		s=s1;
		targ=s.getW1().getTarg();
		mine=s.getW1().getMine();
		bv = new BuildView();
		market=new JButton("Market");
		JButton farm=new JButton("Farm");
		JButton Arch=new JButton("Archery Range");
		JButton barrack=new JButton("Barracks");
		JButton stable=new JButton("Stable");
		JButton confirm = new JButton("Confirm Build");
		JButton end=new JButton("End Turn");
		end.addActionListener(this);
		bv.getEndturn().add(end);
		bv.getBuilding().add(Arch);
		bv.getBuilding().add(barrack);
		bv.getBuilding().add(stable);
		bv.getBuilding().add(market);
		bv.getBuilding().add(farm);
		bv.getConfirm().add(confirm);
		market.addActionListener(this);
		farm.addActionListener(this);
		Arch.addActionListener(this);
		barrack.addActionListener(this);
		stable.addActionListener(this);
		confirm.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
//		if(e.getActionCommand().equals("End Turn")) {
//			for(int i = 0; i<s.getG().getPlayer().getControlledArmies().size();i++) {
//
//				if(s.getG().getPlayer().getControlledArmies().get(i).getDistancetoTarget()==1) {
//					targ=s.getG().getPlayer().getControlledArmies().get(i).getTarget();
//				
//				}
//			}
//			s.getG().endTurn();
//			for(int i = 0; i<s.getG().getPlayer().getControlledArmies().size();i++) {
//
//				if(s.getG().getPlayer().getControlledArmies().get(i).getDistancetoTarget()==0) {
//					for(int j =0; j<s.getG().getAvailableCities().size();j++) {
//
//						if(s.getG().getAvailableCities().get(j).getName().equals(targ)) {
//							City x = s.getG().getAvailableCities().get(j);
//							new BattleChoiceArrived(s.getG().getPlayer().getControlledArmies().get(i),x.getDefendingArmy(),s);
//						}
//					}
//				}
//			}
//			s.getW1().refreshtext();
//			this.getBv().dispose();
//			s.getW1().getCitycont().getC().dispose();
//			s.getW().setVisible(true);
//		}
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
			this.getBv().dispose();
			s.getW1().getCitycont().getC().dispose();
			s.getW().setVisible(true);
		}
		if(b.getActionCommand().equals("Confirm Build")) {
			if(bsaved==null) {
				JOptionPane.showMessageDialog(null,"You need to pick a building first!","Build Error", JOptionPane.ERROR_MESSAGE);
			}
				else {
				switch(bsaved.getActionCommand()) {
				case "Market":
				{
					for(int i=0;i<c1.getEconomicalBuildings().size();i++) {
						if(c1.getEconomicalBuildings().get(i) instanceof Market) {
							JOptionPane.showMessageDialog(null,"You already have a Market Stupid","Build Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					try {
						s.getG().getPlayer().build("Market",citycont.getC1().getName());
						citycont.refresh(c1, s);
						citycont.getMark().setEnabled(true);
						citycont.getC().setVisible(true);
						this.getBv().dispose();
					} catch (NotEnoughGoldException e1) {
						JOptionPane.showMessageDialog(null,"You do not have enough gold to build!","Money Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					break;

				}
				case "Farm":
				{
					{
						for(int i=0;i<c1.getEconomicalBuildings().size();i++) {
							if(c1.getEconomicalBuildings().get(i) instanceof Farm) {
								JOptionPane.showMessageDialog(null,"You already have a Farm Stupid","Build Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						try {
							s.getG().getPlayer().build("Farm",citycont.getC1().getName());
							citycont.refresh(c1, s);
							citycont.getFar().setEnabled(true);
							citycont.getC().setVisible(true);
							this.getBv().dispose();
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(null,"You do not have enough gold to build!","Money Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						break;

					}
				}
				case "Barracks":
				{
					{
						for(int i=0;i<c1.getMilitaryBuildings().size();i++) {
							if(c1.getMilitaryBuildings().get(i) instanceof Barracks) {
								JOptionPane.showMessageDialog(null,"You already have a Barracks Stupid","Build Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						try {
							s.getG().getPlayer().build("Barracks",citycont.getC1().getName());
							citycont.refresh(c1, s);
							citycont.getBar().setEnabled(true);
							citycont.getC().setVisible(true);
							this.getBv().dispose();
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(null,"You do not have enough gold to build!","Money Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						break;
					}
				}
				case "Stable":
				{
					{
						for(int i=0;i<c1.getMilitaryBuildings().size();i++) {
							if(c1.getMilitaryBuildings().get(i) instanceof Stable) {
								JOptionPane.showMessageDialog(null,"You already have a Stable Stupid","Build Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						try {
							s.getG().getPlayer().build("Stable",citycont.getC1().getName());
							citycont.refresh(c1, s);
							citycont.getSta().setEnabled(true);
							citycont.getC().setVisible(true);
							this.getBv().dispose();
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(null,"You do not have enough gold to build!","Money Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						break;

					}}

				case "Archery Range":
				{
					{
						for(int i=0;i<c1.getMilitaryBuildings().size();i++) {
							if(c1.getMilitaryBuildings().get(i) instanceof ArcheryRange) {
								JOptionPane.showMessageDialog(null,"You already have a Archery range Stupid","Build Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						try {
							s.getG().getPlayer().build("ArcheryRange",citycont.getC1().getName());
							citycont.refresh(c1, s);
							citycont.getArc().setEnabled(true);
							citycont.getC().setVisible(true);
							this.getBv().dispose();
						} catch (NotEnoughGoldException e1) {
							JOptionPane.showMessageDialog(null,"You do not have enough gold to build!","Money Error", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						break;

					}

				}
				}
				bv.setVisible(false);
				this.getS().getW1().refreshtext();
			}
		}
		

		else {
			if(!b.getActionCommand().equals("Confirm Build")) {
				if(bsaved==null) {
					bsaved=b;
					switch(bsaved.getActionCommand()) {
					case "Market": 
					{
						bv.getInfo().setText("Type : Market"+ "\n"+ "Cost : 1500");
						break;
					}
					case "Farm": 
					{
						bv.getInfo().setText("Type : Farm"+ "\n"+ "Cost : 1000");
						break;
					}
					case "Barracks": 
					{
						bv.getInfo().setText("Type : Barracks"+ "\n"+ "Cost : 2000");
						break;
					}
					case "Archery Range": 
					{
						bv.getInfo().setText("Type : Archery Range"+ "\n"+ "Cost : 1500");
						break;
					}
					case "Stable": 
					{
						bv.getInfo().setText("Type : Stable"+ "\n"+ "Cost : 2500");
						break;
					}
					}
				}
				else {
					if(b==bsaved) {
						bsaved=null;
					}
					else {
						bsaved=b;
					}
				}
				

			}
		}
	}





}
