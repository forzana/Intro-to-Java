package gradetracker;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class GradeTracker extends JFrame {

    private DefaultTableModel tableModel;
    private JTable gradeTable;
    private JTextField nameField;
    private JTextField gradeField;
    private JLabel countLabel;
    private JLabel averageLabel;
    private JLabel highestLabel;
    
    private static final int FRAME_WIDTH = 520;
    private static final int FRAME_HEIGHT = 500;
    private static final int FIELD_WIDTH = 20;
    private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.0");

    public GradeTracker() {
        super("Grade Tracker");
        
        // Menu Bar
        JMenuBar menuBar = new JMenuBar();     
        this.setJMenuBar(menuBar);
        menuBar.add(createFileMenu());
        
        setLayout(new BorderLayout());
        
        // Add Grade Panel
        JPanel addGradePanel = createAddGradePanel();
        add(addGradePanel, BorderLayout.NORTH);
        
        // The Table of Grades
		String[] columns = {"Student Name", "Grade", "Letter Grade"};
		tableModel = new DefaultTableModel(columns, 0) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		gradeTable = new JTable(tableModel);
		add(new JScrollPane(gradeTable), BorderLayout.CENTER);
        
		// Statistics Panel
        JPanel statisticsPanel = createStatisticsPanel();
        add(statisticsPanel, BorderLayout.SOUTH);
        
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    /**
    Creates the fields to input a name and grade and the two buttons to add grade and clear
    @return the panel containing all those elements
	*/
	public JPanel createAddGradePanel() {
        // Input Panel
        JLabel nameLabel = new JLabel("Student Name: ");
        nameField = new JTextField(FIELD_WIDTH);
        
        JLabel gradeLabel = new JLabel("Grade (0-100): ");
        gradeField = new JTextField(FIELD_WIDTH);

        JButton addGradeButton = new JButton("Add Grade");
        addGradeButton.addActionListener((e) -> addGradeAction());
        
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener((e) -> clearAction());
	
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(gradeLabel);
		panel.add(gradeField);
		panel.add(addGradeButton);
		panel.add(clearButton);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Add Grade"));
	
		return panel;
	}

    /**
    Creates the stats panel to display count, highest, and average
    @return the stats panel
	*/
	public JPanel createStatisticsPanel() {
        countLabel = new JLabel("Count: 0");
        averageLabel = new JLabel("Average: 0.0");
        highestLabel = new JLabel("Highest: 0.0");
	
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		panel.add(countLabel);
		panel.add(averageLabel);
		panel.add(highestLabel);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Statistics"));
	
		return panel;
	}
    
    /**
	    Creates the File menu.
	    @return the menu
	 */
	public JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createFileOpenItem());
		menu.add(createFileSaveItem());
		menu.add(createFileExitItem());
		return menu;
	}
	
	/**
		Creates the File->Open menu item and sets its action listener.
		@return the menu item
	 */
	public JMenuItem createFileOpenItem() {
		JMenuItem item = new JMenuItem("Open");
		item.addActionListener((e) -> openAction());
		return item;
	}
	
	/**
		Creates the File->Save menu item and sets its action listener.
		@return the menu item
	 */
	public JMenuItem createFileSaveItem() {
		JMenuItem item = new JMenuItem("Save");
		item.addActionListener((e) -> saveAction());
		return item;
	}
	
	/**
		Creates the File->Exit menu item and sets its action listener.
		@return the menu item
	 */
	public JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		item.addActionListener((e) -> System.exit(0));
		return item;
	}
    
    
    private File chooseFile(boolean save) {
    	JFileChooser chooser= new JFileChooser();

        chooser.setCurrentDirectory(new File("."));
        chooser.setFileFilter(new FileNameExtensionFilter("csv","CSV"));
        int value = -1;
    	if (save) {
    		value = chooser.showSaveDialog(null); 
    	} else {
    		value = chooser.showOpenDialog(null);
    	}
        if(value == JFileChooser.APPROVE_OPTION){
            File selectedFile = chooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();

            File myFile = new File(path);
            System.out.println("selected file " + myFile);
            return myFile;
        } else {
        	return null;
        }
    }
    
    private void saveAction() {
    	File saveFile = chooseFile(true);
    	System.out.println("saving file " +saveFile);
    	if (saveFile == null)  {
    		return;
    	} else {
    		try {
	    		FileWriter fout = new FileWriter(saveFile);
	    		BufferedWriter out = new BufferedWriter(fout);
	    		out.write("Student Name,Grade");
	    		out.newLine();
				for (int i = 0; i < tableModel.getRowCount(); i++) {
					String name = tableModel.getValueAt(i, 0).toString();
					String grade = tableModel.getValueAt(i, 1).toString();
					out.write(name + "," + grade);
					out.newLine();
				}
				out.close();
    		} catch (IOException e) {
    			JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
    
    private void openAction() {
    	File loadFile = chooseFile(false);
    	if (loadFile == null) {
    		return;
    	} else {
    		try {
    			FileReader f = new FileReader(loadFile);
    			BufferedReader in = new BufferedReader(f);
    			
                String line = in.readLine(); // skip header
                while ((line = in.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }

                    String[] parts = line.split(",");
    				if (parts.length < 2) {
    					System.out.println("Warning: skipping line with fewer than 2 fields: " + line);
    					continue;
    				}
    				
    				try {
                        String name = parts[0].trim();
                        double grade = Double.parseDouble(parts[1].trim());
                        String letter = letterGrade(grade);
                        tableModel.addRow(new Object[] { name, DECIMAL_FORMAT.format(grade), letter });
    				} catch (NumberFormatException e) {
    					System.out.println("Warning: skipping malformed line: " + line);
    				}
                }
                
                in.close();
                updateStatistics();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
    	}
    	
    }
    
    private void addGradeAction() {
        String name = nameField.getText().trim();
        String gradeText = gradeField.getText().trim();

        if (name.isEmpty() || gradeText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both a name and a numeric grade.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double grade;
        try {
        	grade = Double.parseDouble(gradeText);
        } catch (NumberFormatException e) {
        	JOptionPane.showMessageDialog(this, "Grade must be a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        if (grade < 0 || grade > 100) {
        	JOptionPane.showMessageDialog(this, "Grade must be between 0 and 100.", "Input Error", JOptionPane.ERROR_MESSAGE);
          return;
        }

        String letter = letterGrade(grade);
        tableModel.addRow(new Object[]{name, DECIMAL_FORMAT.format(grade), letter});
        clearAction();
        updateStatistics();
    }
    
    private void clearAction() {
        nameField.setText("");
        gradeField.setText("");
    }
    
	private void updateStatistics() {
		int count = tableModel.getRowCount();
		if (count == 0) {
			countLabel.setText("Count: 0");
			averageLabel.setText("Average: 0.0");
			highestLabel.setText("Highest: 0.0");
			return;
		}

		double sum = 0.0;
		double highest = 0.0;
		for (int i = 0; i < count; i++) {
			double grade = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
			sum += grade;
			if (grade > highest) {
				highest = grade;
			}
		}

		double average = sum / count;
		countLabel.setText("Count: " + count);
		averageLabel.setText("Average: " + DECIMAL_FORMAT.format(average));
		highestLabel.setText("Highest: " + DECIMAL_FORMAT.format(highest));
	}

    private String letterGrade(double score) {
        if (score >= 97) return "A+";
        if (score >= 93) return "A";
        if (score >= 90) return "A-";
        if (score >= 87) return "B+";
        if (score >= 83) return "B";
        if (score >= 80) return "B-";
        if (score >= 77) return "C+";
        if (score >= 73) return "C";
        if (score >= 70) return "C-";
        if (score >= 67) return "D+";
        if (score >= 63) return "D";
        if (score >= 60) return "D-";
        return "F";
    }

    // -------------------------------------------------------

    public static void main(String[] args) {
        JFrame gradeTracker = new GradeTracker();
        gradeTracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gradeTracker.setVisible(true);  
    }
}
