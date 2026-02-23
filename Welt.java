import sas.*;
import java.awt.Color;

public class Welt
{
    View fenster;
    Picture mario, coin, pipe, brick, luckyBlock, bush, superStar;
    Picture[] platformen, groundblock, stair;
    Color hintergrund;

    double pGeschwX = 2, pX = 700, pY = 350;
    double mGeschwX = 5, mGeschwY = 0, mX = 100, mY = 700, mW = 50, mH = 50;

    boolean mStands = false;

    Welt()
    {
        hintergrund = new Color(99, 149, 238);

        fenster = new View(1200, 800);
        fenster.setBackgroundColor(hintergrund);

        groundblock = new Picture[24];

        for(int i =0; i < groundblock.length; i++)
        {
            new Picture(i * 50, 750, 50, 50,  "MarioGroundBlock.png");
        }

        platformen = new Picture[4];

        platformen[0] = new Picture(650,550,150,20,"Platform.png");
        platformen[1] = new Picture(50,500,150,20,"Platform.png");
        platformen[2] = new Picture(pX,pY,150,20,"Platform.png");
        platformen[3] = new Picture(950,250,150,20,"Platform.png");

        luckyBlock = new Picture(450, 600, 50, 50, "LuckyBlock.png");
        luckyBlock = new Picture(500, 400, 50, 50, "LuckyBlock.png");
        luckyBlock = new Picture(550, 400, 50, 50, "LuckyBlock.png");

        pipe = new Picture(950,600,120,150,"Pipe.png");

        bush = new Picture(1010,635,120,120,"Busch.png");

        for (int i = 1; i < 4;i++)
        {
            bush = new Picture(i * 300,655,100,100,"Busch.png");
            bush = new Picture(i * 290,705,50,50,"Busch.png");
        }

        for (int i = 5; i < 9; i++)
        {
            brick = new Picture(i * 50, 600, 50, 50, "Brick.png");
        }

        for (int i = 6; i < 10; i++)
        {
            brick = new Picture(i * 50, 400, 50, 50, "Brick.png");
        }

        for (int i = 15; i < 18; i++)
        {
            brick = new Picture(i * 50, 170, 50, 50, "Brick.png");
        }

        for (int i = 10; i < 13; i++)
        {
            brick = new Picture(i * 50, 170, 50, 50, "Brick.png");
        }

        for (int i = 5; i < 8; i++)
        {
            brick = new Picture(i * 50, 170, 50, 50, "Brick.png");
        }

        for (int i = 0; i < 3; i++)
        {
            brick = new Picture(i * 50, 170, 50, 50, "Brick.png");
        }

        superStar = new Picture(50,85,50,50,"SuperStar.png");

        mario = new Picture(mX, mY, mW, mH, "Mario-Stehend.png");


    }

    public static void main(String[] args)
    {
        new Welt();
    }
}


