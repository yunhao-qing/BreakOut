import javafx.scene.image.ImageView;

public class PowerUp {
    private int PWidth = 20;
    public int PHeight = 80;
    public ImageView imageView;
    private int PVel = 50;
    public boolean checkActivation;


    public PowerUp() {
        checkActivation = false;
        this.imageView = new ImageView("powerup.gif");
        imageView.setFitWidth(PWidth);
        imageView.setFitHeight(PHeight);
        imageView.setVisible(false);
    }

    public void activate(){
        checkActivation = true;
        imageView.setVisible(true);
    }

    public boolean getActivation(){
        return checkActivation;
    }


    public void setY(double elapsedTime) {
        this.imageView.setY(this.imageView.getY() + PVel * elapsedTime);
    }

    public ImageView getPImage() {
        return this.imageView;
    }

    public void setPowerUpPos(double x, double y) {
        this.imageView.setX(x);
        this.imageView.setY(y);
    }

}