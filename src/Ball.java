import javafx.scene.image.ImageView;

public class Ball {
    public static final String BImage = "ball.gif";
    private double XVel;
    private double YVel;
    public int BDiameter = 25;
    public static final int PHeight = 10;
    public ImageView imageView;
    private int SIZE = 600;
    private int LifeCount = 10;


    public Ball (){
        this.imageView = new ImageView(BImage);
    }

    public void initialise(){
        this.imageView.setFitWidth(BDiameter);
        this.imageView.setFitHeight(BDiameter);
        this.imageView.setX(300-BDiameter/2);
        this.imageView.setY(480-BDiameter/2-PHeight);
        XVel = 200;
        YVel = 200;
    }

    public int getLifeCount(){
        return LifeCount;
    }

    public void addLife(){
        LifeCount ++;
    }

    public void minusLife(){
        LifeCount --;
    }

    public ImageView getBImage() {
        return this.imageView;
    }

    public void setX(double elapsedTime) {
        this.imageView.setX(this.imageView.getX() + XVel * elapsedTime);
    }

    public void setY(double elapsedTime) {
        this.imageView.setY(this.imageView.getY() - YVel * elapsedTime);
    }

    public double getXVel() {
        return this.XVel;
    }

    public double getYVel() {
        return this.YVel;
    }

    public void changeXVel(double Vel) {
        this.XVel = Vel;
    }

    public void changeYVel(double Vel) {
        this.YVel = Vel;
    }

    public boolean hitTop(){
        return(this.imageView.getY() <= 0);
    }
    public boolean hitBottom(){
        return (this.imageView.getY() + BDiameter>= SIZE);
    }

    public boolean hitLeft(){
        return (this.imageView.getX()  <= 0 );
    }
    public boolean hitRight(){
        return (this.imageView.getX() + BDiameter >= SIZE);
    }

}
