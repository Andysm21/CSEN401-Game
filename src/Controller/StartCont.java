package Controller;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.City;
import engine.Game;
import view.Start;
import view.WorldMap;

public class StartCont implements ActionListener {
	
	private Start s;
	private Game g;
	private String name;
	private String city;
	private City playercity;
	private JPanel playerinfo;
	private JTextField pname;
	private WorldMap w;
	private World w1;
	public World getW1() {
		return w1;
	}

	public void setW1(World w1) {
		this.w1 = w1;
	}

	private JButton begin;
	private JButton spar;
	private JButton cai;
	private JButton rome;
	private JButton submit;

	public StartCont() {
		s=new Start();
		w=new WorldMap();
	//	w.setVisible(false);
		pname =new JTextField();                //.gettext
		/*
		 * pname.setSize(10,30); pname.setBounds(50, 10, 20, 30);
		 */ 	
		
		submit=new JButton("Submit");
		submit.setOpaque(false);
		//submit.setIcon();
		//submit.setContentAreaFilled(false);
		//submit.setBorderPainted(false);
		submit.addActionListener(this);
		submit.setPreferredSize(new Dimension(100,30));
		playerinfo=new JPanel();
		playerinfo.setOpaque(false);
		playerinfo.setPreferredSize(new Dimension(s.getPlayer().getWidth(),50));
		playerinfo.setLayout(new FlowLayout());        //which layout should it be?
		JLabel l=new JLabel("Player Name:");
		l.setFont(new Font(Font.SERIF,Font.BOLD,30));
		l.setOpaque(false);
		playerinfo.add(l); 
		playerinfo.add(pname);
		playerinfo.add(submit);
		s.getPlayer().add(playerinfo);
		pname.setPreferredSize(new Dimension (200,30));
		pname.setOpaque(false);
		cai=new JButton();
		cai.setActionCommand("Cairo");
		cai.addActionListener(this);    //this class is listening to every button
		cai.setEnabled(false);
		spar=new JButton();
		spar.setOpaque(false);
		spar.setActionCommand("Sparta");
		spar.addActionListener(this);    //this class is listening to every button
		spar.setEnabled(false);
		rome=new JButton();
		rome.setActionCommand("Rome");
		rome.addActionListener(this);    //this class is listening to every button
		rome.setEnabled(false);
		rome.setIcon(new ImageIcon("Images/rsz_1rome.jpg"));
		cai.setIcon(new ImageIcon("Images/rsz_cai.jpg"));
		spar.setIcon(new ImageIcon(("Images/rsz_4sparta.jpg")));
		cai.setContentAreaFilled(false);
		cai.setBorderPainted(false);
		rome.setContentAreaFilled(false);
		rome.setBorderPainted(false);
		spar.setContentAreaFilled(false);
		spar.setBorderPainted(false);

		s.getCities().add(cai);
		s.getCities().add(spar);
		s.getCities().add(rome);
		
		
		 
		begin=new JButton();
		begin.setActionCommand("Start Game");
		begin.addActionListener(this);    //this class is listening to every button
		begin.setEnabled(false);
		begin.setIcon(new ImageIcon("Images/rsz_1start.png"));
		begin.setContentAreaFilled(false);
		begin.setBorderPainted(false);
		s.getStart().add(begin);
		
		s.revalidate();
		s.repaint();
	}
	public void playSound(String filepath) {
		try {
			AudioInputStream audioInputStream=AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
			Clip clip=AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Start getS() {
		return s;
	}

	public void setS(Start s) {
		this.s = s;
	}


	public Game getG() {
		return g;
	}

	public void setG(Game g) {
		this.g = g;
	}

	public JTextField getPname() {
		return pname;
	}

	public void setPname(JTextField pname) {
		this.pname = pname;
	}

	public WorldMap getW() {
		return w;
	}

	public void setW(WorldMap w) {
		this.w = w;
	}

	public JButton getBegin() {
		return begin;
	}

	public void setBegin(JButton begin) {
		this.begin = begin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b= (JButton) e.getSource();
		if(b.getActionCommand().equals("Submit")) {
			if(!pname.getText().toString().equals("")) {
			name=pname.getText().toString();
			cai.setEnabled(true);
			rome.setEnabled(true);
			spar.setEnabled(true);
		}
			
		}
		else {
			if(b.getActionCommand().equals("Cairo")) {
				city="Cairo";
				playSound("Sound/cairo.wav");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				begin.setEnabled(true);

		}
			else {
				if(b.getActionCommand().equals("Rome")) {
				city="Rome";
				playSound("Sound/rome.wav");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				begin.setEnabled(true);
				}
				else {
					if(b.getActionCommand().equals("Sparta")) {
						playSound("Sound/sparta.wav");
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						begin.setEnabled(true);

						city="Sparta";
					}
				}
			}
		}
		if(b.getActionCommand().equals("Start Game") ) { //&& name!=null && city!=null
			try {
				g=new Game(name,city);
				for(int i=0;i<g.getAvailableCities().size();i++) {
					if(g.getAvailableCities().get(i).getName().equals(city)) {
						playercity=g.getAvailableCities().get(i);
					}
				}
				w1 = new World(this);
				playSound("Sound/startgame.wav");
				w.setVisible(true);
				s.dispose();
				
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Please Choose a City To Start The Game!","City Error", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			
		}
		
		
	}
	
	public City getPlayercity() {
		return playercity;
	}

	public void setPlayercity(City playercity) {
		this.playercity = playercity;
	}

	public static void main(String[] args) {
	

		new StartCont();
		
	}

}
