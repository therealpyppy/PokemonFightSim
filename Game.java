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
		
		JLabel pSLabel = new JLabel("Please select your pokemon and your \"opponents\" pokemon:");
		pSLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pSLabel.setPreferredSize(new Dimension(400, 20));
		pSLabel.setBounds((screenWidth - pSLabel.getPreferredSize().width) / 2, 10, pSLabel.getPreferredSize().width, pSLabel.getPreferredSize().height);
		pokemonSelection.add(pSLabel);
		
		JTextField searchBar1 = new JTextField();
		searchBar1.setPreferredSize(new Dimension(screenWidth / 2 - 30 - 16, 25));
		searchBar1.setBounds(15, 40, screenWidth / 2 - 30 - 16, 25);
		pokemonSelection.add(searchBar1);
		
		PokemonList PokemonList1 = new PokemonList(screenWidth, screenHeight, new Rectangle(15, 70, screenWidth / 2 - 30 - 16, screenHeight - 90));
		JScrollPane scroll1 = PokemonList1.getScroll();
		pokemonSelection.add(scroll1);

		JTextField searchBar2 = new JTextField();
		searchBar2.setPreferredSize(new Dimension(screenWidth - (screenWidth / 2 + 15) - 15 - 16, 25));
		searchBar2.setBounds(screenWidth / 2 + 15 + 16, 40, screenWidth - (screenWidth / 2 + 15) - 15 - 16, 25);
		pokemonSelection.add(searchBar2);

		PokemonList PokemonList2 = new PokemonList(screenWidth, screenHeight, new Rectangle(screenWidth / 2 + 15 + 16, 70, screenWidth - (screenWidth / 2 + 15) - 15 - 16, screenHeight - 90));
		JScrollPane scroll2 = PokemonList2.getScroll();
		pokemonSelection.add(scroll2);
		
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
		new Game(700, 400);
	}
}
