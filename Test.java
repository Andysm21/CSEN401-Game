package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.Farm;
import buildings.Market;
import buildings.Stable;
import engine.City;
import units.Army;
import units.Status;

public class Test {

	public Test() {
		String [] s= {"a","b","c"};
		JComboBox x=new JComboBox(s);
		x.setVisible(true);
		
		 Army x=armies.get(r);
		  if(x.getCurrentStatus().equals(Status.BESIEGING)) {
			  for(int i =0; i<s.getG().getAvailableCities().size();i++) {
				  if(x.getTarget().equals(s.getG().getAvailableCities().get(i).getName())) {
					 City C = s.getG().getAvailableCities().get(i);
					 JOptionPane.showMessageDialog(null,
					"City Under Siege:"+x.getTarget()+"\n"+"Turns Under Siege:"+C.getTurnsUnderSiege()+"\n","Besieging Army Information",JOptionPane.INFORMATION_MESSAGE);
				  }
			  }
		  		  }
		  else {
			  if(x.getCurrentStatus().equals(Status.MARCHING)) {
						 JOptionPane.showMessageDialog(null,
						"Targeted City:"+x.getTarget()+"\n"+"Turns till reaching the City:"+x.getDistancetoTarget()+"\n","Besieging Army Information",JOptionPane.INFORMATION_MESSAGE);
				  }
			  		  }
		
	
	}
	
	public static void main(String [] args) {
		new Test();
	}
	for(int i=0;i<city.getEconomicalBuildings().size();i++) {
		Building bb= city.getEconomicalBuildings().get(i);
		if(bb instanceof Market) {
			JButton b=new JButton("Market");
			b.addActionListener(this);
			c.getBuildings().add(b);
			ecoB.add(b);
			
		}
		if(bb instanceof Farm) {
			JButton b=new JButton("Farm");
			b.addActionListener(this);
			c.getBuildings().add(b);
			ecoB.add(b);
		}
	
		//b.addActionListener(this); 
	}
	
	for(int i=0;i<city.getMilitaryBuildings().size();i++) {
		Building bb= city.getMilitaryBuildings().get(i);
		if(bb instanceof ArcheryRange) {
			JButton b=new JButton("ArcheryRange");
			b.addActionListener(this);
			c.getBuildings().add(b);
			militB.add(b);
			
		}
		if(bb instanceof Stable) {
			JButton b=new JButton("Stable");
			b.addActionListener(this);
			c.getBuildings().add(b);
			militB.add(b);
		}
		if(bb instanceof Barracks) {
			JButton b=new JButton("Barracks");
			b.addActionListener(this);
			c.getBuildings().add(b);
			militB.add(b);

		}
	
		//b.addActionListener(this); 
	}
}
/*
 * for(int i=0;i<c1.getEconomicalBuildings().size();i++) { Building bb=
 * c1.getEconomicalBuildings().get(i); if(bb instanceof Market) { JButton bz=new
 * JButton("Market"); bz.addActionListener(this); c.getBuildings().add(bz);
 * ecoB.add(bz); } if(bb instanceof Farm) { JButton bz=new JButton("Farm");
 * bz.addActionListener(this); c.getBuildings().add(bz); ecoB.add(bz); }
 * 
 * //b.addActionListener(this); }
 * 
 * for(int i=0;i<c1.getMilitaryBuildings().size();i++) { Building bb=
 * c1.getMilitaryBuildings().get(i); if(bb instanceof ArcheryRange) { JButton
 * bz=new JButton("ArcheryRange"); bz.addActionListener(this);
 * c.getBuildings().add(bz); militB.add(bz);
 * 
 * } if(bb instanceof Stable) { JButton bz=new JButton("Stable");
 * bz.addActionListener(this); c.getBuildings().add(bz); militB.add(bz); } if(bb
 * instanceof Barracks) { JButton bz=new JButton("Barracks");
 * bz.addActionListener(this); c.getBuildings().add(bz); militB.add(bz);
 * 
 * }
 * 
 * //b.addActionListener(this); }
 */
}
