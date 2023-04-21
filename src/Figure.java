public class Figure {

    private FiguresTypes figureType;
    private Sides side;
    private boolean isClicked;

    public Figure(FiguresTypes figureType, Sides side) {
        this.figureType = figureType;
        this.side = side;
        isClicked = false;
    }

    public FiguresTypes getFigureType() {
        return figureType;
    }

    public void setFigureType(FiguresTypes figureType) {
        this.figureType = figureType;
    }

    public Sides getSide() {
        return side;
    }

    public void setSide(Sides side) {
        this.side = side;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
