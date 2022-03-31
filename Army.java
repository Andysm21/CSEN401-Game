package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Army extends JFrame
{
public Army() {
	this.setBounds(0, 0, 2000, 2000);   //change position
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	ArrayList<String> s = new ArrayList<String>();
	s.add("ad");
	s.add("asdasd");
	String s1[] = { "Jalpaiguri", "Mumbai", "Noida", "Kolkata", "New Delhi" };
	JComboBox petList = new JComboBox((ComboBoxModel) s);
	petList.setBounds(EXIT_ON_CLOSE, ABORT, 10, 10);
	JButton x= new JButton("get Info");
	this.add(x,BorderLayout.SOUTH);
	this.add(petList,BorderLayout.NORTH);
	this.revalidate();
	this.repaint();
	
	
	
}
public static void main(String args[]) {
	new Army();
}
}
