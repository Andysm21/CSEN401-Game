package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Start extends JFrame{
	
	private JPanel player;
	private JPanel cities;
	private JPanel start;
	private JLabel l;
	private JTextField pname;
	private JPanel playerfiller;
	private JPanel playerfiller1;
	private JPanel playerfiller2;
	private JLabel background;
	private Image backgroundimg;



	public JPanel getPlayer() {
		return player;
	}

	public void setPlayer(JPanel player) {
		this.player = player;
	}

	public JPanel getCities() {
		return cities;
	}

	public void setCities(JPanel cities) {
		this.cities = cities;
	}

	public JPanel getStart() {
		return start;
	}

	public void setStart(JPanel start) {
		this.start = start;
	}

	public JTextField getPname() {
		return pname;
	}

	public void setPname(JTextField pname) {
		this.pname = pname;
	}
	

	public Start() {
		this.setTitle("Empire Building");
		this.setBounds(400, 150, 1680, 1050);   //change position
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
        background=new JLabel(new ImageIcon("Images/StartMenu.jpg"));
        background.setLayout(new FlowLayout());

	 
		player=new JPanel();
		player.setOpaque(false);
		player.setPreferredSize(new Dimension(this.getWidth(),200));
		player.setLayout(new GridLayout(2,1));        //which layout should it be?
		l=new JLabel("Player Name:");
		
		  playerfiller=new JPanel(); 
		  playerfiller.setOpaque(false);
		  playerfiller.setPreferredSize(new Dimension(this.getWidth(),60)); 
		  playerfiller.setLayout(new FlowLayout());
		  player.add(playerfiller); 
		  /* playerfiller1=new JPanel(); playerfiller1.setPreferredSize(new
		 * Dimension(this.getWidth(),60)); playerfiller1.setLayout(new FlowLayout());
		 * playerfiller2=new JPanel(); playerfiller2.setPreferredSize(new
		 * Dimension(this.getWidth(),60)); playerfiller2.setLayout(new FlowLayout());
		 * player.add(playerfiller); player.add(playerfiller1);
		 * player.add(playerfiller2);
		 */
		  background.add(player);
		
		cities=new JPanel();
		cities.setOpaque(false);
		cities.setPreferredSize(new Dimension(this.getWidth(),150));
		cities.setLayout(new FlowLayout());
		cities.setLocation(10, 10);
		//are we supposed to create a controller and loop on available cities in game?
		JLabel l2=new JLabel("Pick your city:");
		l2.setFont(new Font(Font.SERIF,Font.BOLD,30));
		cities.add(l2);
		background.add(cities,BorderLayout.CENTER);
		
		
		start=new JPanel();
		start.setOpaque(false);
		start.setPreferredSize(new Dimension(this.getWidth(),1000));
		start.setLayout(new FlowLayout());
		
		
		background.add(start,BorderLayout.SOUTH);
		
		this.add(background);
		this.revalidate();
		this.repaint();
	}
	 
	 public static void main(String[] args) {
		new Start();
		 
	}

	public JLabel getL() {
		return l;
	}

	public void setL(JLabel l) {
		this.l = l;
	}

}
