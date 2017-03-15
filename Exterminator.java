import greenfoot.*;
import java.util.*;
public class Exterminator extends Actor
{
    private int cheeseCount;
    private int delay;
    private boolean haveTrap;
    public Exterminator(){
        haveTrap = true;
        cheeseCount = 5;
        delay = 0;
        getImage().mirrorVertically();
        turn(180);
    }

    public void act() 
    {
        die();
        if(Greenfoot.isKeyDown("w")){
            move(1);
        }
        if(Greenfoot.isKeyDown("a")){
            turn(-3);
        }
        if(Greenfoot.isKeyDown("d")){
            turn(3);
        }
        if(Greenfoot.isKeyDown("j") && delay-- <= 0){
            dropCheese();
            delay = 25;
        }   
        getCheeseBomb();
    }    

    public void dropCheese(){
        if(cheeseCount > 0){
            MyWorld w = (MyWorld)getWorld();
            w.addCheese(getX(), getY());
            cheeseCount--;
        }
    }

    public void die(){
        if(isTouching(Rat.class)){
            MyWorld w = (MyWorld)getWorld();
            w.lose();
        }
    }
    
    public void getCheeseBomb(){
        /* implement this */
        CheeseBomb cb = (CheeseBomb)getOneIntersectingObject(CheeseBomb.class);
        if(cb != null){
            cheeseCount += 5;
            World w = getWorld();
            w.removeObject(cb);
        }
    }
}
