import greenfoot.*; 
public class Trap extends Actor
{
    private int deviousness;
    public Trap(){
        GreenfootImage img = getImage();
        img.scale(img.getWidth()/2, img.getHeight()/2);    
    }
}
