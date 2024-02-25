package swing;

import java.awt.BorderLayout;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class HelloWorldGUI extends JFrame implements ActionListener{
	
	JTextArea ta = new JTextArea();
	
	public HelloWorldGUI() {
		setTitle("NotePad by Ankit");
		setSize(500, 400);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}

	private void initComponents() {
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMI = new JMenuItem("New");
		newMI.addActionListener(this);
		newMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		
		JMenuItem openMI = new JMenuItem("Open");
		openMI.addActionListener(this);
		openMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
		
		JMenuItem saveMI = new JMenuItem("Save");
		saveMI.addActionListener(this);
		saveMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		
		JMenuItem saveasMI=new JMenuItem("Save As");
		saveasMI.addActionListener(this);
		saveasMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		
		JMenuItem printMI=new JMenuItem("Print");
		printMI.addActionListener(this);
		printMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
		
		JMenuItem exitMI=new JMenuItem("Exit");
		exitMI.addActionListener(this);
		exitMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
		fileMenu.add(newMI); fileMenu.add(openMI); fileMenu.add(saveMI); fileMenu.add(saveasMI); fileMenu.add(printMI); fileMenu.add(exitMI);
		
		JMenu editMenu = new JMenu("Edit");
		JMenuItem cutMI = new JMenuItem("Cut");
		cutMI.addActionListener(this);
		cutMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		
		JMenuItem copyMI = new JMenuItem("Copy");
		copyMI.addActionListener(this);
		copyMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		
		JMenuItem pasteMI = new JMenuItem("Paste");
		pasteMI.addActionListener(this);
		pasteMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		
		JMenuItem selectallMI=new JMenuItem("Select All");
		selectallMI.addActionListener(this);
		selectallMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		
		editMenu.add(cutMI); editMenu.add(copyMI); editMenu.add(pasteMI); editMenu.add(selectallMI);
		
		JMenu formatMenu=new JMenu("Format");
		/*JMenuItem fontclorMI=new JMenuItem("Font Colour");
		fontclorMI.addActionListener(this);*/
		
		JMenuItem fontstyleMI=new JMenuItem("Font Style");
		fontstyleMI.addActionListener(this);
		
		JMenuItem fontsizeMI=new JMenuItem("Font Size");
		fontsizeMI.addActionListener(this);
		
	
		
		String[] fontFamilyvalue = {"Arial", "Times New Roman", "Verdana", "Tahoma", "Calibri"};
		JList familylistMI = new JList(fontFamilyvalue);
		
		
		familylistMI.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		/*formatMenu.add(fontclorMI)*/; formatMenu.add(fontstyleMI); formatMenu.add(fontsizeMI);
		
		JMenuBar mb = new JMenuBar();
		mb.add(fileMenu); mb.add(editMenu); mb.add(formatMenu);
		
		add(mb, BorderLayout.NORTH);
		
		ta.setBackground(Color.BLACK);
		
		//JScrollBar jb = new JScrollBar(O)
		
		
		//add(ta);
		
		JScrollPane scpane=new JScrollPane(ta);
		
		scpane.setBorder(BorderFactory.createEmptyBorder());
		add(scpane);
		ta.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
		ta.setForeground(Color.WHITE);

		
		
		/*String[][] data = {{"1","A","99"},{"2","B","88"},{"3","C","100"}};
		String[] columns = {"Roll No","Name","Marks"};
		JTable jt = new JTable(data,columns);
		JScrollPane sp = new JScrollPane(jt);
		add(sp);*/
		
		
	}

	public static void main(String[] args) {
		HelloWorldGUI gui = new HelloWorldGUI();
		gui.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("New")) {
			ta.setText("");
		}else if(cmd.equals("Save")) {
			try {
				
				FileWriter fw = new FileWriter(new File("temp.txt"));
				fw.write(ta.getText());
				fw.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}		
		}else if(cmd.equals("Open")) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(this);
			File f = fc.getSelectedFile();
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				while(br.ready()) {
					ta.append(br.readLine()+"\n");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}else if(cmd.equals("Print")) {
			try {
				//printer dialogue will open
				ta.print();
			}catch(Exception e1) {
				
			}
		}//if Exit option is choosen
		//Exit not WOrking condition.........
		else if(cmd.equals("Exit")){
			int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?",
	                "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	        if (response == JOptionPane.YES_OPTION) {
	            System.exit(0);
	        }
		}else if(cmd.equals("Copy")) {
			ta.copy();
			
		}else if(cmd.equals("Paste")) {
			ta.paste();
		}else if(cmd.equals("Cut")) {
			ta.cut();
		}else if(cmd.equals("Select All")) {
			ta.selectAll();
		}else if(cmd.equals("Font Style")) {
			
			 String[] fontStyleValues = { "Regular", "Bold", "Italic", "Bold Italic" };
		        JList<String> fontStyleList = new JList<String>(fontStyleValues);
		        fontStyleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		        JOptionPane.showMessageDialog(this, fontStyleList, "Font Style", JOptionPane.PLAIN_MESSAGE);
		        String selectedStyle = fontStyleList.getSelectedValue().toString();
		        Font currentFont = ta.getFont();
		        Font newFont = null;
		        if (selectedStyle.equals("Regular")) {
		            newFont = new Font(currentFont.getFamily(), Font.PLAIN, currentFont.getSize());
		        } else if (selectedStyle.equals("Bold")) {
		            newFont = new Font(currentFont.getFamily(), Font.BOLD, currentFont.getSize());
		        } else if (selectedStyle.equals("Italic")) {
		            newFont = new Font(currentFont.getFamily(), Font.ITALIC, currentFont.getSize());
		        } else if (selectedStyle.equals("Bold Italic")) {
		            newFont = new Font(currentFont.getFamily(), Font.BOLD + Font.ITALIC, currentFont.getSize());
		        }
		        ta.setFont(newFont);
		}else if(cmd.equals("Font Size")) {
			String[] fontSizeValues = {"12", "14", "16", "18", "20","25","30","35","40","50"};
	        JList fontSizeList = new JList(fontSizeValues);
	        fontSizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        JOptionPane.showMessageDialog(this, fontSizeList, "Font Size", JOptionPane.PLAIN_MESSAGE);
	        int selectedSize = Integer.parseInt(fontSizeList.getSelectedValue().toString());
	        Font currentFont = ta.getFont();
	        Font newFont = currentFont.deriveFont((float) selectedSize);
	        ta.setFont(newFont);
		}else if(cmd.equals("Save As")) {
			 JFileChooser fc = new JFileChooser();
		        fc.setDialogTitle("Save As");
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
		        fc.setFileFilter(filter);
		        int userSelection = fc.showSaveDialog(this);
		        if (userSelection == JFileChooser.APPROVE_OPTION) {
		            File fileToSave = fc.getSelectedFile();
		            try {
		                FileWriter fw = new FileWriter(fileToSave);
		                fw.write(ta.getText());
		                fw.close();
		            } catch (Exception e1) {
		                e1.printStackTrace();
		            }
		        }
	}
	}
}
