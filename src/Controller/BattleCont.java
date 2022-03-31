package Controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import engine.Player;
import exceptions.FriendlyFireException;
import units.Archer;
import units.Army;
import units.Cavalry;
import units.Infantry;
import units.Status;
import units.Unit;
import view.BattleView;

public class BattleCont implements ActionListener{

	private BattleView view;
	private Unit me;
	private Unit his;
	private ArrayList<Unit> myUnits;
	private ArrayList<Unit> hisUnits;
	private ArrayList<String> myUnitsStr;
	private ArrayList<String> hisUnitsStr;
	private String yourUnit;
	private String enemyUnit;
	private JButton nextattack;
	private JButton attack;
	private ArrayList<JButton> mybuttons;
	private ArrayList<JButton> hisbuttons;
	private Army meArmy;
	private Army heArmy;
	private int mycurr;
	private int hiscurr;
	
	public void refreshmine(Army mine) {
		for(int i=0;i<mybuttons.size();i++) {
			mybuttons.get(i).setToolTipText("<html>"+"Level: "+mine.getUnits().get(i).getLevel()+"<br>"+"Current Soldier Count: "+mine.getUnits().get(i).getCurrentSoldierCount()+"</html>");
			}
	}
	public void refreshhis(Army enemy) {
		for(int i=0;i<hisbuttons.size();i++) {
			hisbuttons.get(i).setToolTipText("<html>"+"Level: "+enemy.getUnits().get(i).getLevel()+"<br>"+"Current Soldier Count: "+enemy.getUnits().get(i).getCurrentSoldierCount()+"</html>");
		}
	}

	public static StartCont getS() {
		return s;
	}

	public static void setS(StartCont s) {
		BattleCont.s = s;
	}

	private static StartCont s;


	public BattleCont(Army mine, Army enemy,StartCont s1) {
		JOptionPane.showMessageDialog(null,"Please Prepare yourself for the battle","Battle Notification", JOptionPane.INFORMATION_MESSAGE);
		meArmy=mine;
		mycurr=0;
		hiscurr=0;
		heArmy=enemy;
		s=s1;
		myUnits = new ArrayList<Unit>();
		hisUnits= new ArrayList<Unit>();
		myUnitsStr = new ArrayList<String>();
		hisUnitsStr= new ArrayList<String>();
		mybuttons= new ArrayList<JButton>();
		hisbuttons= new ArrayList<JButton>();
		view=new BattleView();
		Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
		view.setMaximumSize(DimMax);
		view.setExtendedState(JFrame.MAXIMIZED_BOTH);
		int counta=1;
		int countc=1;
		int counti=1;
		for(int i=0;i<mine.getUnits().size();i++) {
			myUnits.add(mine.getUnits().get(i));
			if(mine.getUnits().get(i) instanceof Archer) {
				JButton b=new JButton("Archer "+counta);
				mybuttons.add(b);
				b.setActionCommand("Mine");
				myUnitsStr.add("Archer "+counta);
				view.getUpleft().add(b);
				b.setToolTipText("<html>"+"Level: "+mine.getUnits().get(i).getLevel()+"<br>"+"Current Soldier Count: "+mine.getUnits().get(i).getCurrentSoldierCount()+"</html>");
				b.addActionListener(this);

			}
			if(mine.getUnits().get(i) instanceof Cavalry) {
				JButton b=new JButton("Cavalry "+countc);
				mybuttons.add(b);
				b.setActionCommand("Mine");
				myUnitsStr.add("Cavalry "+countc);
				view.getUpleft().add(b);
				b.setToolTipText("<html>"+"Level: "+mine.getUnits().get(i).getLevel()+"<br>"+"Current Soldier Count: "+mine.getUnits().get(i).getCurrentSoldierCount()+"</html>");

				b.addActionListener(this);

			}
			if(mine.getUnits().get(i) instanceof Infantry) {
				JButton b=new JButton("Infantry "+counti);
				mybuttons.add(b);
				b.setActionCommand("Mine");
				myUnitsStr.add("Infantry "+counti);
				view.getUpleft().add(b);
				b.setToolTipText("<html>"+"Level: "+mine.getUnits().get(i).getLevel()+"<br>"+"Current Soldier Count: "+mine.getUnits().get(i).getCurrentSoldierCount()+"</html>");

				b.addActionListener(this);

			}
			counta++;
			countc++;
			counti++;
		}
		counta=1;
		countc=1;
		counti=1;
		for(int i=0;i<enemy.getUnits().size();i++) {
			hisUnits.add(enemy.getUnits().get(i));
			if(enemy.getUnits().get(i) instanceof Archer) {
				JButton b=new JButton("Archer "+counta);
				hisbuttons.add(b);
				b.setActionCommand("His");
				hisUnitsStr.add("Archer "+counta);
				view.getUpright().add(b);
				b.setToolTipText("<html>"+"Level: "+enemy.getUnits().get(i).getLevel()+"<br>"+"Current Soldier Count: "+enemy.getUnits().get(i).getCurrentSoldierCount()+"</html>");

				b.addActionListener(this);

			}
			if(enemy.getUnits().get(i) instanceof Cavalry) {
				JButton b=new JButton("Cavalry "+countc);
				hisbuttons.add(b);
				b.setActionCommand("His");
				hisUnitsStr.add("Cavalry "+countc);
				view.getUpright().add(b);
				b.setToolTipText("<html>"+"Level: "+enemy.getUnits().get(i).getLevel()+"<br>"+"Current Soldier Count: "+enemy.getUnits().get(i).getCurrentSoldierCount()+"</html>");

				b.addActionListener(this);

			}
			if(enemy.getUnits().get(i) instanceof Infantry) {
				JButton b=new JButton("Infantry "+counti);
				hisbuttons.add(b);
				b.setActionCommand("His");
				hisUnitsStr.add("Infantry "+counti);
				view.getUpright().add(b);
				b.setToolTipText("<html>"+"Level: "+enemy.getUnits().get(i).getLevel()+"<br>"+"Current Soldier Count: "+enemy.getUnits().get(i).getCurrentSoldierCount()+"</html>");

				b.addActionListener(this);

			}
			counta++;
			countc++;
			counti++;
		}

		attack=new JButton("Attack");
		view.getAttack().add(attack);
		attack.addActionListener(this);
		nextattack=new JButton("Next Attack");
		view.getAttack().add(nextattack);
		nextattack.setEnabled(false);
		nextattack.addActionListener(this);
		view.revalidate();
		view.repaint();
	}
	public void resolve() {
		if(myUnits.size()==0){
			s.playSound("Sound/lose.wav");
			JOptionPane.showMessageDialog(null,"You lost the battle,retreat immediately!","Battle Notification", JOptionPane.INFORMATION_MESSAGE);
			view.setVisible(false);
			s.getG().getPlayer().getControlledArmies().remove(meArmy);
			s.getW1().setFlag(false);
			s.getW1().refreshtext();
			s.getW().setVisible(true);
		}
		if(hisUnits.size()==0){
			s.getG().occupy(meArmy, heArmy.getCurrentLocation());
			s.playSound("Sound/Victory.wav");
			JOptionPane.showMessageDialog(null,"You Won the battle,go collect your new city!","Battle Notification", JOptionPane.INFORMATION_MESSAGE);
			view.setVisible(false);
			s.getW1().refreshtext();
			s.getW().setVisible(true);
		}
	}
	public void disabled() {
	for(int i=0; i<myUnits.size();i++) {
		if(myUnits.get(i).getCurrentSoldierCount()==0) {
			mybuttons.get(i).setToolTipText("Dead Unit");
			mybuttons.get(i).setEnabled(false);
			myUnitsStr.remove(i);
			mybuttons.remove(i);
			myUnits.remove(i);
			
		}
	}
	for(int i=0; i<hisUnits.size();i++) {
		if(hisUnits.get(i).getCurrentSoldierCount()==0) {
			hisbuttons.get(i).setToolTipText("Dead Unit");
			hisbuttons.get(i).setEnabled(false);
			hisUnitsStr.remove(i);
			hisbuttons.remove(i);
			hisUnits.remove(i);
		}
	}
	me=null;
	his=null;
	}
	public void manualAttack(JButton b) {
		try {
			int random = (int) (Math.random()*hisUnits.size());
			int random2 = (int) (Math.random()*myUnits.size());
			his=hisUnits.get(random);
			me=myUnits.get(random2);
			enemyUnit=hisUnitsStr.get(hisUnits.indexOf(his));
			yourUnit=myUnitsStr.get(myUnits.indexOf(me));
			mycurr=me.getCurrentSoldierCount();
			hiscurr=his.getCurrentSoldierCount();
			his.attack(me);
			view.getLog().setText(""+enemyUnit+" attacked your "+ yourUnit+"\n"+ "You lost : "+ (mycurr-me.getCurrentSoldierCount())+" Soldiers");
			disabled();
			refreshmine(meArmy);
			refreshhis(heArmy);
			his=null;
			me=null; 
		} catch (FriendlyFireException e) {
			JOptionPane.showMessageDialog(null,"You are trying to attack your friend, PLEASE STOP!","Friendly Fire Warning", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(e.getActionCommand().equals("Attack")&&me!=null&&his!=null) {
			try {
				mycurr=me.getCurrentSoldierCount();
				hiscurr=his.getCurrentSoldierCount();
				me.attack(his);
				view.getLog().setText(""+yourUnit+" attacked "+ enemyUnit+"\n"+ "The enemy lost "+ (hiscurr-his.getCurrentSoldierCount())+" Soldiers");
				disabled();
				attack.setEnabled(false);
				nextattack.setEnabled(true);
				refreshmine(meArmy);
				refreshhis(heArmy);
				me=null;
				his=null;
				s.playSound("Sound/attackme.wav");
				resolve();
			} catch (FriendlyFireException e1) {
				JOptionPane.showMessageDialog(null,"You are trying to attack your friend, PLEASE STOP!","Friendly Fire Warning", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
		}
		else {
			if(e.getActionCommand().equals("Attack")&&me==null) {
				JOptionPane.showMessageDialog(null,"You didn't choose your unit","Warning", JOptionPane.WARNING_MESSAGE);
			}
			else {
				if(e.getActionCommand().equals("Attack")&&his==null) {
					JOptionPane.showMessageDialog(null,"You didn't choose the unit that you want to attack","Warning", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(e.getActionCommand().equals("Next Attack")) {
						nextattack.setEnabled(false);
						attack.setEnabled(true);
						s.playSound("Sound/attackhim.wav");
						manualAttack(b);
						resolve();

					}
					else {
						if(e.getActionCommand().equals("Mine")) {
							me=myUnits.get(myUnitsStr.indexOf(b.getText()));
							yourUnit=b.getText();
						}
						else {
							if(e.getActionCommand().equals("His")) {
								his=hisUnits.get(hisUnitsStr.indexOf(b.getText()));
								
								enemyUnit=b.getText();
							}

						}
					}
				}
			}
		}
	}
	public static void main(String[] args) {
//		Player p = new Player("Cairo");
//		Player p1 = new Player("Cairo1");
//
//		Army x=new Army("Cairo");
//        x.setCurrentStatus(Status.MARCHING);
//        x.setTarget("fjwd");
//        Unit     u = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
//        Unit u2 = (new Cavalry(2, 45, 0.6, 0.7, 0.75));
//        Unit u3 = (new Infantry(1, 45, 0.6, 0.7, 0.75));
//        Unit u4 = (new Archer(3, 45, 0.6, 0.7, 0.75));
//        x.getUnits().add(u);
//        x.getUnits().add(u2);
//        x.getUnits().add(u3);
//        x.getUnits().add(u4);
//        p.getControlledArmies().add(x);
//        
//        Army x1=new Army("Cairo1");
//        x1.setCurrentStatus(Status.MARCHING);
//        Unit     u11 = (new Cavalry(2, 40, 0.6, 0.7, 0.75));
//
//        x1.getUnits().add(u11);
//        p1.getControlledArmies().add(x);
//
//		new BattleCont(x,x1,null);
	}

}