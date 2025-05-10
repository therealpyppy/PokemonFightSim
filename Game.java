import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Game {
	private int screenWidth;
	private int screenHeight;
	
	private List<Object[]> originalPokemonData = new ArrayList<>();
	private DefaultTableModel model;
	
	public Game(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;
		
		JFrame f = new JFrame("Pokemon Fight Sim");
		
		JPanel pokemonSelection = new JPanel();
		pokemonSelection.setLayout(null);
		pokemonSelection.setSize(this.screenWidth, this.screenHeight);
		pokemonSelection.setBackground(Color.WHITE);
		pokemonSelection.setVisible(true);
		
		JLabel pSLabel = new JLabel("Please select your pokemon and your \"opponents\" pokemon:");
		pSLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pSLabel.setPreferredSize(new Dimension(400, 20));
		pSLabel.setBounds((screenWidth - pSLabel.getPreferredSize().width) / 2, 10, pSLabel.getPreferredSize().width, pSLabel.getPreferredSize().height);
		pokemonSelection.add(pSLabel);
		
		JTextField searchBar = new JTextField();
		searchBar.setPreferredSize(new Dimension(screenWidth - 280, 25));
		searchBar.setBounds((screenWidth - searchBar.getPreferredSize().width) / 2, 40, searchBar.getPreferredSize().width, searchBar.getPreferredSize().height);
		pokemonSelection.add(searchBar);
		
		try (BufferedReader br = new BufferedReader(new FileReader("./Info/Pokemon.csv"))) {
			String line = br.readLine();
			
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				String id = parts[0].replace("\"", "");
				String name = parts[1].replace("\"", "");
				String fullName = name + "    ||    " + id;
				
				String imgPath = "./Info/Art/" + String.format("%03d", Integer.parseInt(id)) + ".png";
				ImageIcon rawIcon = new ImageIcon(imgPath);
				Image rawImage = rawIcon.getImage();
				Image scaledImage = getScaledImage(rawImage, 80);
				ImageIcon icon = new ImageIcon(scaledImage);
				
				Object[] row = {icon, fullName};
				originalPokemonData.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] columnNames = {"Image", "Name || ID"};
		
		DefaultTableModel model1 = new DefaultTableModel(columnNames, 0) {
			public Class<?> getColumnClass(int column) {
				return (column == 0) ? ImageIcon.class : String.class;
			}
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		JTable table1 = new JTable(model1);
		table1.setRowHeight(80);
		table1.getColumnModel().getColumn(0).setPreferredWidth(100);
		table1.getColumnModel().getColumn(1).setPreferredWidth(screenWidth - 350);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		DefaultTableModel model2 = new DefaultTableModel(columnNames, 0) {
			public Class<?> getColumnClass(int column) {
				return (column == 0) ? ImageIcon.class : String.class;
			}
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		JTable table2 = new JTable(model2);
		table2.setRowHeight(80);
		table2.getColumnModel().getColumn(0).setPreferredWidth(100);
		table2.getColumnModel().getColumn(1).setPreferredWidth(screenWidth - 350);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		for (Object[] row : originalPokemonData) {
			model1.addRow(row);
			model2.addRow(row);
		}
		
		JScrollPane scroll1 = new JScrollPane(table1);
		scroll1.setBounds(15, 70, screenWidth / 2 - 30 - 16, screenHeight - 90);
		
		JScrollPane scroll2 = new JScrollPane(table2);
		scroll2.setBounds(screenWidth / 2 + 15 + 16, 70, screenWidth - (screenWidth / 2 + 15) - 15 - 16, screenHeight - 90);
		
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		pokemonSelection.add(scroll1);
		pokemonSelection.add(scroll2);
		
		for (Object[] row : originalPokemonData) {
			model1.addRow(row);
		}
		
		searchBar.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				search(searchBar.getText());
			}
			
			public void removeUpdate(DocumentEvent e) {
				search(searchBar.getText());
			}
			
			public void changedUpdate(DocumentEvent e) {
				search(searchBar.getText());
			}
		});
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLayout(null);
		pokemonSelection.setPreferredSize(new Dimension(screenWidth, screenHeight));
		f.setContentPane(pokemonSelection);
		f.pack();
	}

	private Image getScaledImage(Image srcImg, int targetHeight) {
		int width = srcImg.getWidth(null);
		int height = srcImg.getHeight(null);
	
		if (width <= 0 || height <= 0) return srcImg;
	
		double scale = (double) targetHeight / height;
		int newWidth = (int) (width * scale);
	
		return srcImg.getScaledInstance(newWidth, targetHeight, Image.SCALE_SMOOTH);
	}
	

	private void search(String text) {
		text = text.toLowerCase();
		model.setRowCount(0);
		
		boolean found = false;
		for (Object[] row : originalPokemonData) {
			String full = (String) row[1];
			String name = full.split("\\|\\|")[0].trim().toLowerCase();
			if (name.contains(text)) {
				model.addRow(row);
				found = true;
			}
		}
		
		if (!found) {
			for (Object[] row : originalPokemonData) {
				String full = (String) row[1];
				String id = full.split("\\|\\|")[1].trim().toLowerCase();
				if (id.contains(text)) {
					model.addRow(row);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Game(700, 400);
	}
}
