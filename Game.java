import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Game {
    private int screenWidth;
    private int screenHeight;

    public Game(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;

        JFrame f = new JFrame("Pokemon Fight Sim");
        f.setLayout(null);
        f.setSize(width, height);

        Color blue = new Color(0, 20, 255);

        JPanel pokemonSelection = new JPanel();
        pokemonSelection.setLayout(null);
        pokemonSelection.setSize(this.screenWidth, this.screenHeight);
        pokemonSelection.setBackground(Color.WHITE);
        pokemonSelection.setVisible(true);

        JLabel pSLabel = new JLabel("Please select your pokemon and your \"opponents\" pokemon:");
        pSLabel.setBounds(screenWidth / 2 - 200, 20, 400, 20);
        pokemonSelection.add(pSLabel);

        Object[][] data = {
            {new ImageIcon("./Info/Art/001.png"), "Pikachu"},
            {new ImageIcon("./Info/Art/002.png"), "Charizard"},
            {new ImageIcon("./Info/Art/003.png"), "Bulbasaur"},
            {new ImageIcon("./Info/Art/004.png"), "Squirtle"},
            {new ImageIcon("./Info/Art/005.png"), "Eevee"}
        };

        String[] columnNames = {"Image", "Name"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public Class<?> getColumnClass(int column) {
                return (column == 0) ? ImageIcon.class : String.class;
            }

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        int rowHeight = 80;
        table.setRowHeight(rowHeight);

        table.getColumnModel().getColumn(0).setCellRenderer((table1, value, isSelected, hasFocus, row, column) -> {
            JLabel label = new JLabel();
            if (value instanceof ImageIcon) {
                ImageIcon icon = (ImageIcon) value;
                Image img = icon.getImage();
                int imgWidth = img.getWidth(null);
                int imgHeight = img.getHeight(null);

                int cellWidth = table.getColumnModel().getColumn(column).getWidth();
                int cellHeight = table.getRowHeight();

                float scale = Math.min((float) cellWidth / imgWidth, (float) cellHeight / imgHeight);
                int newW = Math.round(imgWidth * scale);
                int newH = Math.round(imgHeight * scale);

                label.setIcon(new ImageIcon(img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH)));
            }
            return label;
        });

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBounds(0, 50, screenWidth - 250, screenHeight - 50);

        pokemonSelection.add(tableScroll);

        f.add(pokemonSelection);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.getContentPane().setBackground(blue);
    }

    public static void main(String[] args) {
        new Game(700, 450);
    }
}
