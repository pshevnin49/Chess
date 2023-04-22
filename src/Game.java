import java.util.ArrayList;
import java.util.List;

public class Game {

    private Figure[][] field;
    private boolean [][] clickedField;
    private Sides movingSide;
    private Sides gamerSide;
    private boolean isClicked;
    private CoordXY clickedCell;
    private List<Figure> killedFigures;

    public Game(Sides gamerSide, Sides movingSide) {

        //this.gamerSide = gamerSide;
        field = generateField();
        clickedField = new boolean[8][8];
        this.gamerSide = gamerSide;
        this.movingSide = movingSide;
        clickedCell = null;
        killedFigures = new ArrayList<>();

        for(int i = 0; i < clickedField.length; i++){
            for(int j = 0; j < clickedField[i].length; j++){
                clickedField[i][j] = false;
            }
        }

    }

    public Figure[][] generateField() {
        field = new Figure[8][8];
        // заполняем верхний ряд фигурами белых
        field[0][0] = new Figure(FiguresTypes.ROOK, Sides.LIGHT);
        field[0][1] = new Figure(FiguresTypes.KNIGHT, Sides.LIGHT);
        field[0][2] = new Figure(FiguresTypes.BISHOP, Sides.LIGHT);
        field[0][3] = new Figure(FiguresTypes.QUEEN, Sides.LIGHT);
        field[0][4] = new Figure(FiguresTypes.KING, Sides.LIGHT);
        field[0][5] = new Figure(FiguresTypes.BISHOP, Sides.LIGHT);
        field[0][6] = new Figure(FiguresTypes.KNIGHT, Sides.LIGHT);
        field[0][7] = new Figure(FiguresTypes.ROOK, Sides.LIGHT);

        // заполняем второй ряд пешками белых
        for (int i = 0; i < 8; i++) {
            field[1][i] = new Figure(FiguresTypes.PAWN, Sides.LIGHT);
        }

        // заполняем нижний ряд фигурами черных
        field[7][0] = new Figure(FiguresTypes.ROOK, Sides.DARK);
        field[7][1] = new Figure(FiguresTypes.KNIGHT, Sides.DARK);
        field[7][2] = new Figure(FiguresTypes.BISHOP, Sides.DARK);
        field[7][3] = new Figure(FiguresTypes.QUEEN, Sides.DARK);
        field[7][4] = new Figure(FiguresTypes.KING, Sides.DARK);
        field[7][5] = new Figure(FiguresTypes.BISHOP, Sides.DARK);
        field[7][6] = new Figure(FiguresTypes.KNIGHT, Sides.DARK);
        field[7][7] = new Figure(FiguresTypes.ROOK, Sides.DARK);


        // заполняем седьмой ряд пешками черных
        for (int i = 0; i < 8; i++) {
            field[6][i] = new Figure(FiguresTypes.PAWN, Sides.DARK);
        }

        // заполняем оставшиеся ячейки null
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j] = null;
            }
        }
        field[5][5] = new Figure(FiguresTypes.ROOK, Sides.LIGHT);

        return field;
    }

    public void click(int x, int y){
        System.out.println(x + " clicked x");
        System.out.println(y + " clicked y");

        if(!clickedField[y][x]){
            isClicked = false;
            clickedCell = null;
            cleanClicked();
            try {
                clickedField[y][x] = true;
                System.out.println(field[y][x].getSide() + " side");
                if(field[y][x].getSide() == gamerSide){
                    isClicked = true;
                    System.out.println(field[y][x].getFigureType() + " figure type");
                    clickedCell = new CoordXY(x, y);
                    switch (field[y][x].getFigureType()){
                        case PAWN -> pawnCLick(x, y);
                    }
                }
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Out of field!");
            }
        }else{
            if(field[y][x] == null || field[y][x].getSide() != gamerSide){
                moveFigure(x, y);
                isClicked = false;
                clickedCell = null;
                cleanClicked();
            }else{
                isClicked = false;
                clickedCell = null;
                cleanClicked();
            }
        }
    }

    private void moveFigure(int x, int y){

        if(field[y][x] != null){
            killedFigures.add(field[y][x]);
        }
        field[y][x] = field[clickedCell.getY()][clickedCell.getX()];
        field[clickedCell.getY()][clickedCell.getX()] = null;
    }

    private void pawnCLick(int x, int y){
        try{
            if(field[y - 1][x] == null){
                clickedField[y - 1][x] = true;
                System.out.println("Yes");
            }

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("No1");
        }
        try {
            if(field [y - 1][x + 1] != null){
                if(field [y - 1][x + 1].getSide() != gamerSide){
                    clickedField[y - 1][x + 1] = true;
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){

        }

        try {
            if(field [y - 1][x - 1] != null){
                if(field [y - 1][x - 1].getSide() != gamerSide){
                    clickedField[y - 1][x - 1] = true;
                }
            }

        }catch (ArrayIndexOutOfBoundsException e){

        }
    }

    private void cleanClicked(){
        for(int i = 0; i < clickedField.length; i++){
            for(int j = 0; j < clickedField[i].length; j++){
                clickedField[i][j] = false;
            }
        }
    }

    public boolean[][] getClickedField() {
        return clickedField;
    }

    public void setClickedField(boolean[][] clickedField) {
        this.clickedField = clickedField;
    }

    public Figure[][] getField() {
        return field;
    }

    public void setField(Figure[][] field) {
        this.field = field;
    }
}
