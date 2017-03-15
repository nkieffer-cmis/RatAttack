import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
public class Cheese extends Actor
{
    private int freshness;
    private GreenfootImage img;
    public Cheese(){
        img = new GreenfootImage(20, 20);
        freshness = 500 + Greenfoot.getRandomNumber(500);
        setImage(img);
    }

    public void act(){
        putrify();
    }

    public boolean isRotten(){
        return freshness < 0;
    }

    public void putrify(){
        freshness--;
        GreenfootImage img = getImage();
        Color color;
        if(freshness > 500){
            color = Color.yellow;
        } else if (freshness > 250){
            color = Color.green;
        } else if (freshness > 120){
            color = Color.green.darker();
        } else {
            color = Color.black;
        }
        img.setColor(color);
        img.fill();
    }
}
