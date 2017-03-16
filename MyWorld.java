import greenfoot.*; 
import java.util.*;
public class MyWorld extends World
{
    private long startTime;
    private long runningTime;
    private int level = 1;
    private int score = 0;
    public MyWorld()
    {
        super(600, 400, 1); 
        init(level);
    }

    public void init(int level){
        List<Actor> actors = getObjects(null);
        for(Actor a: actors){
            removeObject(a);
        }
        for(int i=0; i<level; i++){
            addObject(new Rat(), 50, Greenfoot.getRandomNumber(400));
        }
        addObject(new Exterminator(), 520, 200);
        addObject(new Trap(), 550, 200);
        startTime = System.currentTimeMillis();
        
    }
    
    public void act(){
        runningTime = System.currentTimeMillis() - startTime;

        if(getObjects(Rat.class).size() == 0){
            init(level++);
        }
        showText(score+"  "+(int)(15 -(runningTime/1000.0)), 50, 50);

    }
    

    public void addCheese(int x, int y){
        addObject(new Cheese(), x, y);
    }



    public void score(){
        score++;
    }

    public void lose(){
        showText("Game Over!!", 300, 200);
        Greenfoot.stop();
    }
}
