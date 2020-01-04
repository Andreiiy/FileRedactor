package redactor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Main implements ActionListener{

	File file = new File("");
	JFrame frame;
	JButton buttonOpen;
	JButton buttonSave; 
	JButton buttonNew;
	JTextArea area;
	JLabel label;
	
	public Main() {
	    frame = new JFrame("File Editor");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	    buttonOpen = new JButton("Open");
		buttonOpen.setLocation(10, 10);
		buttonOpen.setSize(100, 50);
		
	    buttonSave = new JButton("Save");
		buttonSave.setLocation(120, 10);
		buttonSave.setSize(100, 50);
		
	    buttonNew = new JButton("New File");
		buttonNew.setLocation(230, 10);
		buttonNew.setSize(100, 50);
		
		label = new JLabel();
		label.setLocation(350, 25);
		label.setSize(300, 25);
		
	    area = new JTextArea();
		area.setLocation(10, 70);
		area.setSize(770, 480);
		////////////////////////////////////////////////////////////////////
		buttonOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(frame);
				file = chooser.getSelectedFile();
				label.setText("Location File: "+file.getPath());
				try(FileReader fileReader = new FileReader(file)){
					
					char [] buf = new char [(int) file.length()];
					fileReader.read(buf);
					area.setText(new String(buf));
					
				}catch(Exception e2){
					
				}
				
			}
		});
		
		buttonSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try(FileWriter writer = new  FileWriter(file);) {
					
					writer.write(area.getText());
					writer.flush();
				} catch (IOException e1) {
					
				}
				
			}
		});
		
		buttonNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
					file = chooser.getSelectedFile();
					try(FileWriter writer = new  FileWriter(file);) {
						
						writer.write("");
						writer.flush();
					} catch (IOException e1) {
						
					}
				}
				
				
			}
		});
		
		///////////////////////////////////////////////////////////////////
		frame.add(buttonOpen);
		frame.add(buttonSave);
		frame.add(buttonNew);
		frame.add(area);
		frame.add(label);
		frame.add(new JLabel());
	}

	public static void main(String[] args)  {
	
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Main();
				
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
