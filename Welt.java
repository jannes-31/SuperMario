import sas.*;
import java.awt.Color;

public class Welt
{
    View fenster;
    Picture marioStehend, coin, pipe, groundBlock, luckyBlock, stair, brick, marioPic, busch;
    Sprite mario;
    Platform platform, platform1;
    Rectangle hitbox;
    Color hintergrund;

    float geschwY = 0;
    float mX, mY,mBreite = 50,mHoehe = 50;

    Welt()
    {
        hintergrund = new Color(99,149,238);

        fenster = new View(1200, 800);
        fenster.setBackgroundColor(hintergrund);

        platform = new Platform(650,550,150,20);
        platform1 = new Platform(50,500,150,20);

        luckyBlock = new Picture(450, 600, 50, 50, "LuckyBlock.png");

        pipe = new Picture(950,600,120,150,"Pipe.png");

        busch = new Picture(1010,635,120,120,"Busch.png");

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

        for (int i = 1; i < 4;i++)
        {
            busch = new Picture(i * 300,655,100,100,"Busch.png");
            busch = new Picture(i * 290,705,50,50,"Busch.png");
        }

        for (int i = 0; i < 25; i++)
        {
            groundBlock = new Picture(i * 50, 750, 50, 50, "MarioGroundBlock.png");
        }

        for (int i = 5; i < 9; i++)
        {
            brick = new Picture(i * 50, 600, 50, 50, "Brick.png");
        }



    }


    public static void main(String[] args)
    {
        new Welt();
    }
}

