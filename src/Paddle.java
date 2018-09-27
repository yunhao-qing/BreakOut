import javafx.scene.image.ImageView;

public class Paddle {
    public static final String BImage = "paddle.gif";
    private double XVel, YVel;
    public int PWidth = 150;
    public int PHeight = 5;
    public ImageView imageView;
    private int SIZE = 600;



    public Paddle (){
        this.imageView = new ImageView(BImage);
    }

    public void initialise(){
        this.imageView.setFitWidth(PWidth);
        this.imageView.setFitHeight(PHeight);
        this.imageView.setX(300 - PWidth/2);
        this.imageView.setY(500);
        XVel = 200;
        YVel = 200;
    }

    public void changePWidth(int Width){
        PWidth = Width;
        this.imageView.setFitWidth(Width);
    }

    public void changePHeight(int Height){
        PHeight = Height;
        this.imageView.setFitHeight(Height);
    }

    public ImageView getPImage() {
        return this.imageView;
    }

    public double getPWidth() {
        return this.PWidth;
    }

}
