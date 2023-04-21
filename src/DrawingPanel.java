import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawingPanel extends JPanel {

    int cellSize = 700 / 8;
    Game game;

    int startY = 30;

    public DrawingPanel(Game game) {
        this.game = game;

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                clickCell(e.getX(), e.getY());
                repaint();

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        drawField(g2);
        try {
            drawChess(g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void drawField(Graphics2D g) {

        g.setColor(new Color(98, 83, 72));
        g.fillRect(0, 0, 750, 760);
        //int cellSize = 600/8; // 800 will be changed on real window size
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {

                if (x % 2 == 0 && y % 2 == 0 || x % 2 == 1 && y % 2 == 1) {
                    g.setColor(new Color(183, 165, 153));
                } else {
                    g.setColor(new Color(112, 93, 81));
                }
                g.fillRect(cellSize * x, cellSize * y + startY, cellSize, cellSize);

                if(game.getClickedField()[y][x]){
                    g.setColor(new Color(79, 177, 105));
                    g.fillRect(cellSize * x, cellSize * y + startY, cellSize, cellSize);
                }
            }
        }
    }

    private void drawChess(Graphics2D g2) throws IOException {

        Figure [][] field =  game.getField();
        int nullX = 0;
        int nullY = 0;
        int coordX = 0;
        int coordY = 0;
        String sideName;
        String figureName = "";

        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {

                if(field[x][y] != null){
                    coordX = nullX + x * cellSize;
                    coordY = nullY + y * cellSize;

                    if(field[x][y].getSide() == Sides.DARK){
                        sideName = "brown";
                    }else{
                        sideName = "white";
                    }

                    switch (field[x][y].getFigureType()){
                        case PAWN -> figureName = "pawn";
                        case KING -> figureName = "king";
                        case ROOK -> figureName = "rook";
                        case QUEEN -> figureName = "queen";
                        case BISHOP -> figureName = "elephant";
                        case KNIGHT -> figureName = "horse";
                    }

                    drawFigure(g2, coordY, coordX, sideName + " " + figureName + ".png");
                }
            }
        }
    }

    private void drawFigure(Graphics2D g2, int x, int y, String imgName) throws IOException {

        int size = 40;

        BufferedImage figure = null;

        figure = ImageIO.read(new File("C:\\Users\\pshev\\Documents\\PET\\Chess\\res\\" + imgName));

        double ratio = (double) figure.getHeight() / figure.getWidth();
        //System.out.println(ratio + " ratio");

        int width = size;
        int height = (int) Math.round (width * ratio);

        y = y + (cellSize - height) - (cellSize / 12);
        //System.out.println(cellSize - height + " cellSize - height");

        g2.drawImage(figure, x + (cellSize - width)/2, y + startY, width, height, null);

    }

    /**
     * Переводит координаты с фактических на формат поля,
     * и вызывает метод в игре
     * @param x
     * @param y
     */
    private void clickCell(int x, int y){
        double realX = (double) (x / cellSize);
        double realY = (double) (y - startY)/cellSize;

        if(realY < 0.0){
            realY = -1;
        }
        game.click((int) realX, (int) realY);

    }

}
