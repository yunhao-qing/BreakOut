package Brick;

import java.util.ArrayList;

public class Bricks {

    public ArrayList<Brick> myBrick;

    public Bricks(int Level){

        if(Level == 1){
            myBrick = new ArrayList<Brick>();
            myBrick.add(new BlueBrick());
            myBrick.add(new BlueBrick());
            myBrick.add(new RedBrick());
            for(int i = 0; i<5;i++)
                myBrick.add(new BlueBrick());
            myBrick.add(new RedBrick());
            myBrick.add(new BlueBrick());
            myBrick.add(new GreenBrick());
            myBrick.add(new GreenBrick());
            for(int i = 0; i<2;i++)
                myBrick.add(new BlueBrick());
            myBrick.add(new GreenBrick());
        }
        if(Level == 2){
            myBrick = new ArrayList<Brick>();
            myBrick.add(new BlueBrick());
            for(int i = 0; i<3;i++)
                myBrick.add(new RedBrick());
            for(int i = 0; i<5;i++)
                myBrick.add(new BlueBrick());
            myBrick.add(new RedBrick());
            for(int i = 0; i<5;i++)
                myBrick.add(new BlueBrick());
        }
        if(Level == 3){
            myBrick = new ArrayList<Brick>();
            myBrick.add(new BlueBrick());
            for(int i = 0; i<3;i++)
                myBrick.add(new RedBrick());
            myBrick.add(new BlueBrick());
            myBrick.add(new BlueBrick());
            for(int i = 0; i<3;i++)
                myBrick.add(new RedBrick());
            myBrick.add(new BlueBrick());
            myBrick.add(new BlueBrick());
            for(int i = 0; i<3;i++)
                myBrick.add(new RedBrick());
            myBrick.add(new BlueBrick());
        }


    }

    public void initialise(){
        for(int i = 0; i < 5;i++)
            myBrick.get(i).setBrickPos(i*120,50);
        for(int i = 0; i < 5;i++)
            myBrick.get(5+i).setBrickPos(i*120,100);
        for(int i = 0; i < 5;i++)
            myBrick.get(10+i).setBrickPos(i*120,150);
    }
}
