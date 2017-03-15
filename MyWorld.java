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
        removeRottenCheese();
        if(Math.random() > 0.999){
            dropCheeseBomb();
        }
        spawnRat();
        if(getObjects(Rat.class).size() == 0){
            init(level++);
        }
        showText(score+"  "+(int)(15 -(runningTime/1000.0)), 50, 50);

    }

    public void removeRottenCheese(){
        /* implement this */
        for(Actor c: getObjects(Cheese.class)){
            Cheese cheese = (Cheese) c;
            if( cheese.isRotten() ){
                removeObject(c);
            }
        }
    }

    public void spawnRat(){
        /* implement this */
        if(runningTime / 1000.0 > 15){
            startTime = System.currentTimeMillis();
            addObject(new Rat(), 0, Greenfoot.getRandomNumber(400));
        }
    }
    
    public void spawnRat(int x, int y){
        addObject(new Rat(), x, y);
    }

    public void addCheese(int x, int y){
        addObject(new Cheese(), x, y);
    }

    public void dropCheeseBomb(){
        /* implement this */
        CheeseBomb cb = new CheeseBomb();
        int x, y;
        int side = Greenfoot.getRandomNumber(4);
        if(side == 0){
            x = Greenfoot.getRandomNumber(600);
            y = 50;
        } else if (side == 1){
            x = Greenfoot.getRandomNumber(600);
            y = 400 - 50;
        } else if (side == 2){
            x = 50;
            y = Greenfoot.getRandomNumber(400);
        } else {
            x = 600 - 50;
            y = Greenfoot.getRandomNumber(400);
        }
        addObject(cb, x, y);
    }

    public void score(){
        score++;
    }

    public void lose(){
        showText("Game Over!!", 300, 200);
        Greenfoot.stop();
    }
}
