import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

public class GameWindow {

    Game game;
    ImageIcon icon = new ImageIcon("res/icon.png");

    public GameWindow() throws IOException {
        this.game = new Game();
    }

    public void drawField(){

        JFrame frame = new JFrame();
        frame.setTitle("Chess");

        frame.setSize(745, 760);
        frame.setResizable(false);
        frame.setIconImage(icon.getImage());

        DrawingPanel panel = new DrawingPanel(game);


        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);// posice okna centrum
        frame.setVisible(true);

        System.out.println("Vlakno se skoncilo ");
    }
}
