import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.AttributedString;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;




public class Generate{
static	window frame = new window("New World");
	static boolean init = false;
	static JTextArea environment = new JTextArea();
	static Integer h = 10;
	static String health = h.toString();
	static String[][] build;
	static JMenuItem save = new JMenuItem("Save");
	static JMenuBar bar = new JMenuBar();
	static JMenu file =  new JMenu("Menu");
	static JMenuItem load =  new JMenuItem("Load");
	static JMenuItem newWorld = new JMenuItem("Generate new World");
	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	
	public static void main(String[] args) {
			
		JOptionPane.showMessageDialog(null,"Welcome to world generator! \n A world will be generated for you in a new window now. \n If you want to save your world press ALT + S");
		
		setFrame();
		newWorld.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				environment.setText("");
			setFrame();
				
			}
		});
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);	
				
				int result = fileChooser.showSaveDialog(frame);
				
				if(result == JFileChooser.CANCEL_OPTION){
					return;
				}
				
				File fileName = fileChooser.getSelectedFile();
				
				if(fileName == null || fileName.getName().equals("")){
					JOptionPane.showMessageDialog(frame, "Invalid File Name.");
				}
				else{
					try{
						output = new ObjectOutputStream(new FileOutputStream(fileName));
						output.writeObject(environment.getText());
						output.flush();
						output.close();
					}catch(IOException e1){
						JOptionPane.showMessageDialog(frame, "Error writing file.");
					}
				}	
			}
		});
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				 
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);	
				
				int result = fileChooser.showOpenDialog(frame);
				
				if(result == JFileChooser.CANCEL_OPTION){
					return;
				}
				
				File fileName = fileChooser.getSelectedFile();
				
				if(fileName == null || fileName.getName().equals("")){
					JOptionPane.showMessageDialog(frame, "Invalid File Name.");
				}
				else{
					try{
					
						input = new ObjectInputStream(new FileInputStream(fileName));	
						String e1 = (String) input.readObject();
						input.close();
						environment.setText(e1);
						
					
						
					}catch(IOException e1){
						JOptionPane.showMessageDialog(frame, "Error reading fileIO.");
					} catch (ClassNotFoundException e2) {
					
						e2.printStackTrace();
					}
				}	
				
			}
		});

		
	}
public static void setFrame(){
	environment.setSize(700,700);
	environment.setEditable(false);
	environment.setFont(new Font("monospaced", Font.PLAIN, 12));
	frame.add(environment, BorderLayout.SOUTH);
	frame.add(bar);
	bar.add(file);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	file.add(save);
	file.add(load);
	file.add(newWorld);
	frame.setVisible(true);
	frame.setSize(700,730);
	frame.setResizable(false);
	
	buildLines();
}
public static void buildLines(){
Random r = new Random();
int rand = r.nextInt(5);
build = new String[99][42];
	for(int line = 0; line < 15; line++){
		
		int x = 0;
		int x1 = 0;
		int y1 = 0;
	while(x<15+rand){
		build[x1][y1] = "\n";
		x1++;
		y1++;
		x++;
	}	
	}
	int d2 = 16+rand;
	int air1 = r.nextInt(10);	
	for(int d = 0; d < 99; d++){
		
		if(air1==3){
		build[d][d2] = "X";
		}
		else{
		build[d][d2] = "﻿﻿﻿ ";
		}
		air1 = r.nextInt(10);
		
	}
	d2 = 17+rand;
	air1 = r.nextInt(5);	
	for(int d = 0; d < 99; d++){
		if(build[d][d2-1].contains("X")){
			build[d][d2] = "X";
			if(d>0 && d<42){
				if(build[d-1][d2].contains(" ")){
					build[d-1][d2] = "X";
				}
				if(build[d-1][d2].contains("X")){
				build[d+1][d2] = "X";	
				}
		}
		}
		else{
		
			if(air1==3){
			build[d][d2] = "X";
			}
			else{
			build[d][d2] = "﻿﻿﻿ ";
			}
			air1 = r.nextInt(5);
			
		}
	}
	
	d2 = 18 + rand;
	air1 = r.nextInt(2);	
	for(int t = 0; t < 99; t++){
		if(build[t][d2-1].contains("X")){
			build[t][d2] = "X";
			if(t>0 && t<42){
				if(build[t-1][d2].contains(" ")){
					build[t-1][d2] = "X";
				}
				if(build[t-1][d2].contains("X")){
				build[t+1][d2] = "X";	
				}
			}
			}
			else{
			
				if(air1 == 1){
				build[t][d2] = "X";
				}
				else{
				build[t][d2] = "﻿﻿﻿ ";
				}
				air1 = r.nextInt(2);
			
			}
	}
	int rando;
	for(int line = 0, b = 19 + rand; line < 23-rand; line++, b++){
		rando = r.nextInt(18)+1;
		if(rando < 5){
		build[0][b] = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX \n";	
		}else{
			int start;
			int gap;
			start = r.nextInt(20)+1;
			gap = r.nextInt(35)+4;
			for(int pq = 0; pq < start; pq++){
				build[pq][b] = "X";
			}
			for(int h = 0; h < gap; h++){
				build[h][b] = "0";
			}
			for(int k = 0; k < 99-(gap+start); k++){
				build[k][b] = "X";
			}
		}
	}
	construct(rand,build);

	}

	
public static void construct(int rand, String[][] build){

	int xt = 0;
	int b1 = 0;
	int b2 = 0;
	while(xt<15+rand){
		
			environment.append(build[b1][b2]);
			b1++;
			b2++;
			xt++;
		
}
	xt = 0;
	b1 = 0;
	b2 = 16 + rand;
	while(xt < 99){
		environment.append(build[b1][b2]);
		xt++;
		b1++;
	}
	environment.append("\n");
	xt = 0;
	b1 = 0;
	b2 = 17 + rand;
	while(xt < 99){
		environment.append(build[b1][b2]);
		xt++;
		b1++;
	}
	environment.append("\n");
	xt = 0;
	b1 = 0;
	b2 = 18 + rand;
	while(xt < 99){
		environment.append(build[b1][b2]);
		xt++;
		b1++;
	}
	environment.append("\n");
	xt = 0;
	b1 = 0;
	b2 = 19 + rand;
	while(xt < 99){
		environment.append(build[b1][b2]);
		xt++;
		b1++;
	}
	for(int c = 0; c < 23-rand; c++, b2++){
		for (int r = 0; r < 99; r++){
			environment.append(build[r][b2]);
			
		}
	}
	}	

public static void refresh(MouseEvent e){
	Graphics g = frame.getGraphics();
	g.drawRect(e.getX(), e.getY(), 50, 50);
}


}

