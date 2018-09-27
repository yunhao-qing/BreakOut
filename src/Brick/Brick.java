package Brick;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Brick {
    private int BLives;
    private int BWidth = 120;
    public int BHeight = 50;
    private String BType;
    public ImageView BImage;


    public Brick(String BType, int BLives) {
        this.BType = BType;
        this.BLives = BLives;
        Image brickImage = new Image(BType +".gif");
        BImage = new ImageView(brickImage);
        BImage.setFitWidth(BWidth);
        BImage.setFitHeight(BHeight);
    }

    public String getBType() {
        return this.BType;
    }

    public void minusLives() {
        this.BLives--;
    }

    public int getBLives() {
        return this.BLives;
    }

    public ImageView getBImage() {
        return this.BImage;
    }

    public void setBrickPos(double x, double y) {
        this.BImage.setX(x);
        this.BImage.setY(y);
    }

}