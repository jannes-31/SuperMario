import sas.*;
import java.awt.Color;

public class Mario
{
    public float x, y;
    public float geschwY = 0;
    int breite =150, hoehe =150;
    boolean aufBoden = false;

    Rectangle hitbox;
    Picture mario;
    Sprite mario1;


    Mario(float x,float y, int breite, int hoehe)
    {
        mario = new Picture(x,y,breite,hoehe,"Mario-Stehend.png");
        hitbox = new Rectangle(x+50,y+50,breite - breite*0.9,hoehe - hoehe*0.9);
        mario1 = new Sprite(hitbox);
            mario1.add(mario);


    }
}
