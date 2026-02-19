import sas.*;
import java.awt.Color;

public class Welt
{
    View fenster;
    Picture marioStehend, coin, pipe, groundBlock, luckyBlock, stair, brick, marioPic, busch;
    Picture[] platformen;
    Rectangle pHitbox;
    Sprite mario;
    Rectangle mHitbox;
    Color hintergrund;
    Sprite[] platform;
    
    Text geschwindigkeit;
    
    
    double geschwY  = 0;
    float mX        = 400;
    float mY        = 100;
    float mBreite   = 50;        
    float mHoehe    = 50;  
    double platformX = 700, platformY = 350;
    
    Welt()
    {
        hintergrund = new Color(99,149,238);

        fenster = new View(1200, 800);
        fenster.setBackgroundColor(hintergrund);
        
        
        platformen = new Picture[3];
        platform = new Sprite[3];
        
        platformen[0] = new Picture(650,550,150,20,"Platform.png");
        platformen[1] = new Picture(50,500,150,20,"Platform.png");
        platformen[2] = new Picture(platformX, platformY, 150, 20,"Platform.png");
        
        
        luckyBlock = new Picture(450, 600, 50, 50, "LuckyBlock.png");   
        luckyBlock = new Picture(500, 400, 50, 50, "LuckyBlock.png");

        pipe = new Picture(950,600,120,150,"Pipe.png");

        busch = new Picture(1010,635,120,120,"Busch.png");

            marioPic = new Picture(mX,mY,mBreite,mHoehe,"Mario-Stehend.png");
            mHitbox = new Rectangle(mX,mY,mBreite,mHoehe);
                
            mario = new Sprite(mHitbox);
            mario.add(marioPic);
        
        pHitbox = new Rectangle(platformX,platformY,150,20);
        platform[1] = new Sprite(pHitbox);
        platform[2].add(platformen[2]);
            
            
            
            
        geschwindigkeit = new Text(200,100,Double.toString(geschwY));
        Thread update = new Thread(() -> 
        {
            while(true)
            {
                platformen[2].move(5,0);
                platformX = platformen[2].getShapeX();
                if(platformX > 1000 && platformX < 700)
                {
                    platformen[2].move(-5,0);
                }
                
                try
                {
                    Thread.sleep(100);
                } catch (InterruptedException e) 
                {
                    break;
                }
                }
            
        });
        update.start();

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
        
        for (int i = 7; i < 10; i++)
        {
            brick = new Picture(i * 50, 400, 50, 50, "Brick.png");
        }


    }


    public static void main(String[] args)
    {
        new Welt();
    }
}
