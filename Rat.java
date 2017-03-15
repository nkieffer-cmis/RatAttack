import greenfoot.*;
import java.util.*;
public class Rat extends Actor
{
    private int direction;
    private int eaten;
    public Rat(){
        direction = Greenfoot.getRandomNumber(4);
        getImage().scale(50,25);
        eaten = 0;
    }

    public void act() 
    {
        eat();
        Cheese closest = getClosestCheese(200);
        if(closest == null){
            wander();
        } else{
            moveTowardCheese(closest);
        }
        spawnRat();
        die();
    }   

    public void wander(){
        if(Math.random() > 0.95){
            direction = Greenfoot.getRandomNumber(4);
        }
        int dx = 0;
        int dy = 0;
        if ( direction == 0 ){
            dy = -1;
        } else if ( direction == 1 ){
            dy = 1;
        } else if ( direction == 2 ){
            dx = -1;
        } else if ( direction == 3 ){
            dx = 1;
        }

        setLocation(getX() + dx, getY() + dy);
    }

    public void moveTowardCheese(Cheese cheese){
        int dx = 0;
        int dy = 0;
        int cheeseX = cheese.getX();
        int cheeseY = cheese.getY();
        int x = getX();
        int y = getY();
        
        if ( x < cheeseX ){
            dx = 1;
        }else if ( x > cheeseX){
            dx = -1;
        }
        
        if ( y < cheeseY ){
            dy = 1;
        }else if ( y > cheeseY ){
            dy = -1;
        }
        setLocation(x + dx, y + dy);
    }
    
    public Cheese getClosestCheese(int range){
        /* implement this */
        List<Cheese> cheeses = getObjectsInRange(range, Cheese.class);
        if (cheeses.size() == 0){
            return null;
        }else if( cheeses.size() == 1 ){
            return cheeses.get(0);
        } else {
            return getClosestCheese(range - 1);
        }
    }
    
    public void eat(){
        Cheese cheese = (Cheese)getOneIntersectingObject(Cheese.class);
        if( cheese != null){
            World w = getWorld();
            w.removeObject(cheese);
            eaten++;
        }
    }
    
    public void spawnRat(){
        if(eaten == 3){
            MyWorld w = (MyWorld)getWorld();
            w.spawnRat(getX(), getY());
            eaten = 0;
        }
    }
    
    public void die(){
        if(isTouching(Trap.class)){
            MyWorld w = (MyWorld)getWorld();
            w.removeObject(this);
            w.score();
        }
    }
}
