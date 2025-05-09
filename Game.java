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
        this.screenHeight = height + 1;

        JFrame f = new JFrame("Pokemon Fight Sim");
        f.setLayout(null);
        f.setSize(width, height + 40);

        JPanel pokemonSelection = new JPanel();
        pokemonSelection.setLayout(null);
        pokemonSelection.setSize(this.screenWidth, this.screenHeight);
        pokemonSelection.setBackground(Color.WHITE);
        pokemonSelection.setVisible(true);

        JLabel pSLabel = new JLabel("Please select your pokemon and your \"opponents\" pokemon:");
        pSLabel.setBounds(screenWidth / 2 - 200, 10, 400, 20);
        pokemonSelection.add(pSLabel);

        JTextField searchBar = new JTextField();
        searchBar.setBounds(20, 40, screenWidth - 280, 25);
        pokemonSelection.add(searchBar);

        try (BufferedReader br = new BufferedReader(new FileReader("./Info/Pokemon.csv"))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0].replace("\"", "");
                String name = parts[1].replace("\"", "");
                String fullName = name + "    ||    " + id;

                String imgPath = "./Info/Art/" + String.format("%03d", Integer.parseInt(id)) + ".png";
                ImageIcon icon = new ImageIcon(imgPath);

                Object[] row = {icon, fullName};
                originalPokemonData.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] columnNames = {"Image", "Name || ID"};
        model = new DefaultTableModel(columnNames, 0) {
            public Class<?> getColumnClass(int column) {
                return (column == 0) ? ImageIcon.class : String.class;
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
		
        table.setRowHeight(80);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(0, 70, screenWidth - 250, screenHeight - 70);
        pokemonSelection.add(scroll);

        for (Object[] row : originalPokemonData) {
            model.addRow(row);
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

        f.add(pokemonSelection);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
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
