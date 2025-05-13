import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PokemonList {
	private List<Object[]> originalPokemonData = new ArrayList<>();
	private DefaultTableModel model;
	private int screenWidth;
	//private int screenHeight;
	private Rectangle position;
	private JTable table;

	public PokemonList(int screenWidth, int screenHeight, Rectangle position) {
		this.screenWidth = screenWidth;
		//this.screenHeight = screenHeight;
		this.position = position;

		String[] columnNames = {"Image", "Name || ID"};
		
		this.model = new DefaultTableModel(columnNames, 0) {
			public Class<?> getColumnClass(int column) {
				return (column == 0) ? ImageIcon.class : String.class;
			}
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	public JScrollPane getScroll() {
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
		
		this.table = new JTable(this.model);
		this.table.setRowHeight(80);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(100);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(this.screenWidth - 350);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.getTableHeader().setResizingAllowed(false);
		this.table.getTableHeader().setReorderingAllowed(false);

		for (Object[] row : originalPokemonData) {
			this.model.addRow(row);
		}
		
		JScrollPane scroll = new JScrollPane(this.table);
		scroll.setBounds(this.position);

		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		return scroll;
	}

	public void search(String text) {
		text = text.toLowerCase();
		this.model.setRowCount(0);
		
		boolean found = false;
		for (Object[] row : originalPokemonData) {
			String full = (String) row[1];
			String name = full.split("\\|\\|")[0].trim().toLowerCase();
			if (name.contains(text)) {
				this.model.addRow(row);
				found = true;
			}
		}
		
		if (!found) {
			for (Object[] row : originalPokemonData) {
				String full = (String) row[1];
				String id = full.split("\\|\\|")[1].trim().toLowerCase();
				if (id.contains(text)) {
					this.model.addRow(row);
				}
			}
		}
	}

	public JTable getTable() {
		return this.table;
	}

	public List<Object[]> originalPokemonData() {
		return this.originalPokemonData;
	}

	private Image getScaledImage(Image srcImg, int targetHeight) {
		int width = srcImg.getWidth(null);
		int height = srcImg.getHeight(null);
	
		if (width <= 0 || height <= 0) return srcImg;
	
		double scale = (double) targetHeight / height;
		int newWidth = (int) (width * scale);
	
		return srcImg.getScaledInstance(newWidth, targetHeight, Image.SCALE_SMOOTH);
	}
}
