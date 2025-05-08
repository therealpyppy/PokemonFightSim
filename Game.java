import javax.swing.*;
import java.awt.*;

public class Game {
    private int screenWidth;
    private int screenHeight;

    public Game(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;

        JFrame f = new JFrame();
        f.setLayout(null);
        f.setSize(width, height);

        Color blackblack = new Color(0, 0, 0);
        Color darkGray = new Color(45, 45, 45);
        Color black = new Color(20, 20, 20);
        Color blue = new Color(0, 20, 255);

        JPanel pokemonSelection = new JPanel();
        pokemonSelection.setLayout(null);
        pokemonSelection.setSize(this.screenWidth, this.screenHeight);
        pokemonSelection.setBackground(Color.WHITE);
        pokemonSelection.setVisible(true);

		JLabel pSLabel = new JLabel("Please select your pokemon and your \"opponents\" pokemon:");
		pSLabel.setBounds(screenWidth/2-200, 20, 400, 20);
		pokemonSelection.add(pSLabel);

        f.add(pokemonSelection);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.getContentPane().setBackground(blue);
    }

    public static void main(String[] args) {
        new Game(700, 450);
    }
}