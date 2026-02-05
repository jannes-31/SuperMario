import sas.*;
import java.awt.Color;

public class Platform
{
    float x, y;
    int breite, hoehe;
    Picture platform;

    Platform(float x, float y, int breite, int hoehe)
    {
        platform = new Picture(x,y,breite,hoehe,"Platform.png");



    }
}
