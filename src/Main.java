import Brick.Bricks;
import javafx.animation.KeyFrame;
import javafx.scene.text.*;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    public static final String TITLE = "BreakOut";
    public static final int SIZE = 600;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;
    public static final int INPUT_SPEED = 30;
    public Ball myBall;
    public Paddle myPaddle;
    public PowerUp myPowerUp1;
    public PowerUp myPowerUp2;
    public Stage stage;
    private Scene myScene;
    private int Level;
    private int Score;
    private Text LivesCount;
    private Text ScoreBoard;
    private Text printLevel;
    private Bricks myBricks;
    private Text WinMessage;
    private Text LostMagges;
    private Text instruction;
    private Group root;


    @Override
    public void start (Stage stage) throws Exception{
        Level = 1;
        Score = 0;
        myScene = setupGame(SIZE, SIZE, BACKGROUND, Level);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> {
                    try {
                        step(SECOND_DELAY);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }


    public void NextLevel(Stage stage) throws Exception{
        Level ++;
        myBricks = new Bricks(Level);
        myBricks.initialise();
        for(int i = 0; i< 15; i++)
            root.getChildren().add(myBricks.myBrick.get(i).getBImage());
        myBall.initialise();
    }

    private Scene setupGame (int width, int height, Paint background, int Level) throws Exception {
        root = new Group();
        Scene scene = new Scene(root, width, height, background);
        initialiseObjects();
        LivesCount = printText(LivesCount, 500, 30, "Lives: " + myBall.getLifeCount(), 18,true);
        ScoreBoard = printText(ScoreBoard, 20, 30, "Score: " + Score, 18,true);
        printLevel =  printText(printLevel, 250,30,"LEVEL " + Level, 25,true);
        instruction = printText(instruction, 100,530,
                "Blue bricks are worth 30 points.\n" +
                "Green bricks are worth 10 points but give out power ups.\n" +
                "Red bricks are worth 10 points.\n" +
                "Press L to get another life.Press E to extand the paddle.\n" +
                "Press S to shorten the paddle.Press R to relocate/normal speed the ball.\n", 12,true);
        WinMessage = printText(WinMessage, 300, 300, "You win", 18,false);
        LostMagges = printText(LostMagges, 300, 300, "You lose ", 18,false);
        addToRoot(root);
        scene.setOnKeyPressed(e -> {
            try {
                handleKeyInput(e.getCode());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        return scene;
    }

    public void addToRoot(Group root){
        root.getChildren().add(myBall.getBImage());
        root.getChildren().add(myPaddle.getPImage());
        for(int i = 0; i< 15; i++)
            root.getChildren().add(myBricks.myBrick.get(i).getBImage());
        root.getChildren().add(myPowerUp1.getPImage());
        root.getChildren().add(myPowerUp2.getPImage());
        root.getChildren().add(LivesCount);
        root.getChildren().add(ScoreBoard);
        root.getChildren().add(printLevel);
        root.getChildren().add(instruction);
        root.getChildren().add(WinMessage);
        root.getChildren().add(LostMagges);

    }

    public void initialiseObjects(){
        myBall = new Ball();
        myBall.initialise();
        myPaddle = new Paddle();
        myPaddle.initialise();
        myBricks = new Bricks(Level);
        myBricks.initialise();
        myPowerUp1 = new PowerUp();
        myPowerUp1.setPowerUpPos(60,175);
        myPowerUp2 = new PowerUp();
        myPowerUp2.setPowerUpPos(540, 175);}


    private void step (double elapsedTime) throws Exception {
        if(Level==3 && myBricks.myBrick.isEmpty())
            WinMessage.setVisible(true);
        if(myBall.getLifeCount() == 0){
            LostMagges.setVisible(true);
            myBall.changeXVel(0);
            myBall.changeYVel(0);
        }
        if(myBricks.myBrick.isEmpty())
            NextLevel(stage);
        LivesCount.setText("Lives: " + myBall.getLifeCount());
        ScoreBoard.setText("Score: " + Score);
        printLevel.setText("Level " + Level);
        if(myPowerUp1.getActivation())
            myPowerUp1.setY(elapsedTime);
        if(myPowerUp2.getActivation())
            myPowerUp2.setY(elapsedTime);
        myBall.setX(elapsedTime);
        myBall.setY(elapsedTime);
        checkBallWallCollision();
        checkPowerUpPaddleCollision();
        checkBallBrickCollision();
    }

    private void checkPowerUpPaddleCollision(){
        if(myPowerUp1.imageView.getBoundsInParent().intersects(myPaddle.getPImage().getBoundsInParent())){
            myPowerUp1.imageView.setImage(null);
            myPowerUp1.checkActivation = false;
            myPaddle.changePHeight(15);
        }
        if(myPowerUp2.imageView.getBoundsInParent().intersects(myPaddle.getPImage().getBoundsInParent())){
            myPowerUp2.imageView.setImage(null);
            myPowerUp2.checkActivation = false;
            myPaddle.changePWidth(200);
        }
    }

    private void checkBallWallCollision(){
        if (myBall.hitTop())
            myBall.changeYVel(myBall.getYVel()*-1);
        if (myBall.hitRight() || myBall.hitLeft())
            myBall.changeXVel(myBall.getXVel()*-1);
        if (myBall.imageView.getBoundsInParent().intersects(myPaddle.imageView.getBoundsInParent()))
            myBall.changeYVel(myBall.getYVel()*-1);
        if(myBall.hitBottom()){
            myBall.initialise();
            myBall.minusLife();
        }
    }

    private void checkBallBrickCollision(){
        for(int i = 0; i<myBricks.myBrick.size();i++){
            if (myBall.imageView.getBoundsInParent().intersects
                    (myBricks.myBrick.get(i).getBImage().getBoundsInParent())){
                if(myBall.imageView.getX()+myBall.BDiameter > myBricks.myBrick.get(i).getBImage().getX()){
                    myBall.changeYVel(myBall.getYVel()*-1);
                    CollisionEffect(i);
                }
                else if(myBall.imageView.getX()< myBricks.myBrick.get(i).getBImage().getX() +
                        myBricks.myBrick.get(i).BHeight){
                    myBall.changeYVel(myBall.getYVel()*-1);
                    CollisionEffect(i);
                }
                else {
                    myBall.changeXVel(myBall.getXVel()*-1);
                    CollisionEffect(i);
                }
            }
        }
    }

    private Text printText(Text t, double x, double y, String message, int fontSize,boolean visible) {
        t = new Text();
        t.setX(x);
        t.setY(y);
        t.setFont(Font.font("Verdana", fontSize));
        t.setText(message);
        t.setFill(Color.BLACK);
        t.setVisible(visible);
        return t;
    }


    public void CollisionEffect(int i){
        if (myBricks.myBrick.get(i).getBType().equals("green")){
            if (myBricks.myBrick.get(i).getBImage().getX() < 50 )
                myPowerUp1.activate();
            else myPowerUp2.activate();
        }
        myBricks.myBrick.get(i).minusLives();
        if (myBricks.myBrick.get(i).getBLives()==0){
            if (myBricks.myBrick.get(i).getBType().equals("blue"))
                Score +=20;
            myBricks.myBrick.get(i).getBImage().setImage(null);
            myBricks.myBrick.remove(i);
        }
        Score +=10;

    }

    private void handleKeyInput (KeyCode code) throws Exception{
        if (code == KeyCode.RIGHT && myPaddle.imageView.getX()+myPaddle.getPWidth()+ INPUT_SPEED <= SIZE)
            myPaddle.imageView.setX(myPaddle.imageView.getX() + INPUT_SPEED);
        if (code == KeyCode.L)
            myBall.addLife();
        if (code == KeyCode.R)
            myBall.initialise();
        if (code == KeyCode.E)
            myPaddle.changePWidth(200);
        if (code == KeyCode.S)
            myPaddle.changePWidth(50);
        else if (code == KeyCode.LEFT && myPaddle.imageView.getX()- INPUT_SPEED>= 0)
            myPaddle.imageView.setX(myPaddle.imageView.getX() - INPUT_SPEED);

    }


    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
