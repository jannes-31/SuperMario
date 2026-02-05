import sas.*;
import java.awt.Color;

public class Welt
{
    View fenster;
    Picture marioStehend, coin, pipe, groundBlock, luckyBlock, stair, brick, marioPic;
    Sprite mario;
    Platform platform, platform1;
    Rectangle hitbox;

    float geschwY = 0;
    float mX, mY,mBreite = 50,mHoehe = 50;

    Welt()
    {
        fenster = new View(1200, 800);
        fenster.setBackgroundColor(Color.blue);

        platform = new Platform(700,600,100,20);
        platform1 = new Platform(50,500,100,20);

        luckyBlock = new Picture(450, 600, 50, 50, "LuckyBlock.png");

        marioPic = new Picture(mX,mY,mBreite,mHoehe,"Mario-Stehend.png");
        hitbox = new Rectangle(mX+50,mY+50,mBreite - mBreite*0.9,mHoehe - mHoehe*0.9);
        mario = new Sprite(hitbox);
        mario.add(marioPic);

        for (int i = 0; i < 20; i++)
        {
            if(geschwY < 5)
            {
                geschwY += 0.5;
            }
            mY += geschwY;
            mario.move(0,mY);
            fenster.wait(10);
        }

        for (int i = 0; i < 25; i++)
        {
            groundBlock = new Picture(i * 50, 750, 50, 50, "MarioGroundBlock.png");
        }

        for (int b = 5; b < 9; b++)
        {
            brick = new Picture(b * 50, 600, 50, 50, "Brick.png");
        }


    }


    public static void main(String[] args)
    {
        new Welt();
    }
}

