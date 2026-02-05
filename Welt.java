import sas.*;
import java.awt.Color;

class Welt
{
    View fenster;
    Picture marioStehend,coin,platform,pipe,groundBlock;
    Welt()
    {
    fenster = new View(1200,800);
    fenster.setBackgroundColor(Color.blue);
    
    marioStehend = new Picture(0,0,125,125,"Mario-Stehend.png");
    coin = new Picture(200,200,20,20,"Coin.png");
    platform = new Picture(500,500,100,20,"Platform.png");
    pipe = new Picture(300,300,100,100,"Pipe.png");
    groundBlock = new Picture(750,400,30,30,"MarioGroundBlock.png");
    
    
    
    
    
    for(int i = 0; i < 25;i++)
    {
        groundBlock = new Picture(i*50,750,50,50,"MarioGroundBlock.png");
    }
    }
}