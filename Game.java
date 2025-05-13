import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Game {
	private int screenWidth;
	private int screenHeight;
	
	public Game(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;
		
		JFrame f = new JFrame("Pokemon Fight Sim");
		
		JPanel pokemonSelection = new JPanel();
		pokemonSelection.setLayout(null);
		pokemonSelection.setSize(this.screenWidth, this.screenHeight);
		pokemonSelection.setBackground(Color.WHITE);
		pokemonSelection.setVisible(true);
		
		JLabel pSLabel = new JLabel("Please select you and your \"opponents\" pokemon:");
		pSLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pSLabel.setPreferredSize(new Dimension(400, 20));
		pSLabel.setBounds((screenWidth - pSLabel.getPreferredSize().width) / 2, 10, pSLabel.getPreferredSize().width, pSLabel.getPreferredSize().height);
		pokemonSelection.add(pSLabel);
		
		JTextField searchBar1 = new JTextField();
		searchBar1.setPreferredSize(new Dimension(screenWidth / 2 - 30 - 16, 25));
		searchBar1.setBounds(15, 40, screenWidth / 2 - 30 - 16, 25);
		pokemonSelection.add(searchBar1);
		
		JTextField searchBar2 = new JTextField();
		searchBar2.setPreferredSize(new Dimension(screenWidth - (screenWidth / 2 + 15) - 15 - 16, 25));
		searchBar2.setBounds(screenWidth / 2 + 15 + 16, 40, screenWidth - (screenWidth / 2 + 15) - 15 - 16, 25);
		pokemonSelection.add(searchBar2);

		PokemonList PokemonList1 = new PokemonList(screenWidth, screenHeight, new Rectangle(15, 70, screenWidth / 2 - 30 - 16, screenHeight - 190));
		JScrollPane scroll1 = PokemonList1.getScroll();
		pokemonSelection.add(scroll1);

		JLabel List1Label = new JLabel("No Pokemon selected");
		Dimension labelSize = List1Label.getPreferredSize();

		double labelXPos = 15 + ((screenWidth / 2.0 - 30 - 16) - labelSize.getWidth()) / 2;
		int labelYPos = 85+(screenHeight - 190);

		List1Label.setBounds((int) labelXPos, (int) labelYPos, (int) labelSize.getWidth(), (int) labelSize.getHeight());
		pokemonSelection.add(List1Label);

		PokemonList1.getTable().getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int indexSelected = PokemonList1.getTable().getSelectedRow();
				//String selectedRow = String.format("%03d", indexSelected);
				String selectedId = String.format("%03d", indexSelected+1);

				System.out.println("left #:" + selectedId);
			}
		});

		PokemonList PokemonList2 = new PokemonList(screenWidth, screenHeight, new Rectangle(screenWidth / 2 + 15 + 16, 70, screenWidth - (screenWidth / 2 + 15) - 15 - 16, screenHeight - 190));
		JScrollPane scroll2 = PokemonList2.getScroll();
		pokemonSelection.add(scroll2);
		
		JLabel List2Label = new JLabel("No Pokemon selected");
		Dimension labelSize2 = List2Label.getPreferredSize();

		int labelYPos2 = 70 + (screenHeight - 190) + 25;
		double labelXPos2 = (screenWidth / 2.0 + 15 + 16) + ((screenWidth - (screenWidth / 2.0 + 15) - 15 - 16) - labelSize2.getWidth()) / 2;

		List2Label.setBounds((int) labelXPos2, labelYPos2, (int) labelSize2.getWidth(), (int) labelSize2.getHeight());
		pokemonSelection.add(List2Label);

		PokemonList2.getTable().getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				int indexSelected = PokemonList2.getTable().getSelectedRow();
				//String selectedRow = String.format("%03d", indexSelected);
				String selectedId = String.format("%03d", indexSelected+1);

				System.out.println("right #:" + selectedId);
			}
		});

		searchBar1.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				PokemonList1.search(searchBar1.getText());
			}
			
			public void removeUpdate(DocumentEvent e) {
				PokemonList1.search(searchBar1.getText());
			}
			
			public void changedUpdate(DocumentEvent e) {
				PokemonList1.search(searchBar1.getText());
			}
		});

		searchBar2.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				PokemonList2.search(searchBar2.getText());
			}
			
			public void removeUpdate(DocumentEvent e) {
				PokemonList2.search(searchBar2.getText());
			}
			
			public void changedUpdate(DocumentEvent e) {
				PokemonList2.search(searchBar2.getText());
			}
		});
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLayout(null);
		pokemonSelection.setPreferredSize(new Dimension(screenWidth, screenHeight));
		f.setContentPane(pokemonSelection);
		f.pack();
	}
	
	public static void main(String[] args) {
		new Game(1024, 600);
	}
}
