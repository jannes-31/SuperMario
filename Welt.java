import sas.*;
import java.awt.Color;

public class Welt {


    View fenster;
    Picture mario, pipe, superStar, bush, points;
    Picture[] platformen, groundblock, bricks, luckyBlocks, stairs, coins;
    Color hintergrund;
    Text gewonnen, timerAnzeige, punkteAnzeige, youWin, finalScore, finalTime;

    // Punktestand
    int punkte = 0;

    // Für den Spieltimer
    long startZeit;

    // Merkt sich ob das Spiel bereits gewonnen wurde
    boolean gewonnenBool = false;

    // Bewegungsgeschwindigkeit und Startposition der beweglichen Plattform
    double pGeschwX = 2, pX = 700, pY = 350;

    // Marios Geschwindigkeit und Startposition
    double mGeschwX = 5, mGeschwY = 0, mX = 100, mY = 700, mW = 50, mH = 50;

    // Merkt sich ob Mario gerade auf dem Boden steht und in welche Richtung er schaut
    boolean mStands = false;
    boolean facingRight = true;

    Welt() {


        hintergrund = new Color(99, 149, 238);
        fenster = new View(1200, 800);

        // Timer starten und oben links anzeigen
        startZeit = Tools.getStartTime();
        timerAnzeige = new Text(10, 10, "Zeit: 0.0s");

        // Punkteanzeige oben in der Mitte
        punkteAnzeige = new Text(550, 10, "Punkte: 0");

        fenster.setBackgroundColor(hintergrund);


        groundblock = new Picture[24];
        for (int i = 0; i < groundblock.length; i++) {
            groundblock[i] = new Picture(i * 50, 750, 50, 50, "MarioGroundBlock.png");
        }


        platformen = new Picture[4];
        platformen[0] = new Picture(600, 600, 150, 20, "Platform.png");
        platformen[1] = new Picture(50, 500, 150, 20, "Platform.png");
        platformen[2] = new Picture(pX, pY, 150, 20, "Platform.png"); // bewegliche Plattform
        platformen[3] = new Picture(950, 250, 150, 20, "Platform.png");


        pipe = new Picture(950, 600, 120, 150, "Pipe.png");


        stairs = new Picture[3];
        stairs[0] = new Picture(750, 700, 50, 50, "Stair.png");
        stairs[1] = new Picture(800, 700, 50, 50, "Stair.png");
        stairs[2] = new Picture(800, 650, 50, 50, "Stair.png");


        bricks = new Picture[50];
        int zaehler = 0;

        for (int i = 5; i < 10; i++)
            bricks[zaehler++] = new Picture(i * 50, 600, 50, 50, "Brick.png");

        for (int i = 6; i < 12; i++)
            bricks[zaehler++] = new Picture(i * 50, 400, 50, 50, "Brick.png");

        for (int i = 15; i < 18; i++)
            bricks[zaehler++] = new Picture(i * 50, 170, 50, 50, "Brick.png");

        for (int i = 10; i < 13; i++)
            bricks[zaehler++] = new Picture(i * 50, 170, 50, 50, "Brick.png");

        for (int i = 5; i < 8; i++)
            bricks[zaehler++] = new Picture(i * 50, 170, 50, 50, "Brick.png");

        for (int i = 0; i < 3; i++)
            bricks[zaehler++] = new Picture(i * 50, 170, 50, 50, "Brick.png");


        luckyBlocks = new Picture[3];
        luckyBlocks[0] = new Picture(450, 600, 50, 50, "LuckyBlock.png");
        luckyBlocks[1] = new Picture(500, 400, 50, 50, "LuckyBlock.png");
        luckyBlocks[2] = new Picture(550, 400, 50, 50, "LuckyBlock.png");



        coins = new Picture[5];
        coins[0] = new Picture(100, 400, 40, 40, "Coin.png");
        coins[1] = new Picture(350, 500, 40, 40, "Coin.png");
        coins[2] = new Picture(1100, 100, 40, 40, "Coin.png");
        coins[3] = new Picture(1110, 460, 40, 40, "Coin.png");
        coins[4] = new Picture(760, 460, 40, 40, "Coin.png");



        superStar = new Picture(50, 85, 50, 50, "SuperStar.png");


        bush = new Picture(1010, 635, 120, 120, "Busch.png");
        for (int i = 1; i < 4; i++) {
            bush = new Picture(i * 300, 655, 100, 100, "Busch.png");
            bush = new Picture(i * 290, 705, 50, 50, "Busch.png");
        }

        // Mario an seiner Startposition erstellen
        mario = new Picture(mX, mY, mW, mH, "Mario-Stehend.png");

        if(fenster.keyLeftPressed())
        {
            mario.flipHorizontal();
        }

        // Spielschleife
        Thread wiederholung = new Thread(() -> {

            while (true) {

                // Wenn gewonnen: nichts mehr machen, nur warten
                if (gewonnenBool) {
                    try { Thread.sleep(20); } catch (InterruptedException e) { break; }
                    continue;
                }

                mStands = false;

                // Timer aktualisieren
                timerAnzeige.setText("Zeit: " + Tools.getElapsedTimeString(startZeit));

                // Bewegliche Plattform
                platformen[2].move(pGeschwX, 0);
                pX = platformen[2].getShapeX();
                if (pX >= 1050 || pX <= 700)
                    pGeschwX = -pGeschwX; // Richtung umkehren wenn Rand erreicht

                // Schwerkraft
                mGeschwY += 0.35;

                // Mario bewegen
                if (fenster.keyRightPressed()) {
                    mario.move(mGeschwX, 0);

                    if (!facingRight) {
                        mario.flipHorizontal();
                        facingRight = true;
                    }
                }


                if (fenster.keyLeftPressed()) {
                    mario.move(-mGeschwX, 0);

                    if (facingRight) {
                        mario.flipHorizontal();
                        facingRight = false;
                    }
                }


                mario.move(0, mGeschwY);

                // Stern berührt → Gewinn-Sequenz starten
                if (mario.intersects(superStar)) {
                    gewonnenBool = true;
                    String stoppZeit = Tools.getElapsedTimeString(startZeit);

                    // Stern 2 Sekunden lang blinken (5x an/aus = 200ms pro Blink)
                    for (int i = 0; i < 5; i++) {
                        superStar.setHidden(true);
                        try { Thread.sleep(200); } catch (InterruptedException e) {}
                        superStar.setHidden(false);
                        try { Thread.sleep(200); } catch (InterruptedException e) {}
                    }

                    // Bildschirm schwarz machen
                    fenster.setBackgroundColor(Color.BLACK);

                    // Siegbildschirm aufbauen
                    youWin = new Text(450, 300, "You Win!", new Color(255, 220, 0));
                    youWin.setFontSansSerif(true, 80);
                    youWin.setHidden(true);

                    finalScore = new Text(500, 400, "Score: " + punkte, Color.WHITE);
                    finalScore.setFontSansSerif(true, 40);
                    finalScore.setHidden(true);

                    finalTime = new Text(520, 460, "Zeit: " + stoppZeit, Color.WHITE);
                    finalTime.setFontSansSerif(true, 40);
                    finalTime.setHidden(true);

                    // Siegbildschirm 4x blinken lassen
                    for (int i = 0; i < 4; i++) {
                        youWin.setHidden(false);
                        finalScore.setHidden(false);
                        finalTime.setHidden(false);
                        try { Thread.sleep(400); } catch (InterruptedException e) {}
                        youWin.setHidden(true);
                        finalScore.setHidden(true);
                        finalTime.setHidden(true);
                        try { Thread.sleep(400); } catch (InterruptedException e) {}
                    }

                    // Am Ende dauerhaft sichtbar lassen
                    youWin.setHidden(false);
                    finalScore.setHidden(false);
                    finalTime.setHidden(false);
                }

                if(mario.intersects(luckyBlocks[0]))
                {
                    luckyBlocks[0].setHidden(true);
                    punkte += 500;
                    punkteAnzeige.setText("Punkte: " + punkte);
                    points = new Picture(470,585,20,10,"PunkteMario.png");
                    fenster.wait(180);
                    points.setHidden(true);
                }

                if(mario.intersects(luckyBlocks[1]))
                {
                    luckyBlocks[1].setHidden(true);
                    punkte += 500;
                    punkteAnzeige.setText("Punkte: " + punkte);
                    points = new Picture(515,385,20,10,"PunkteMario.png");
                    fenster.wait(180);
                    points.setHidden(true);
                }

                if(mario.intersects(luckyBlocks[2]))
                {
                    luckyBlocks[2].setHidden(true);
                    punkte += 500;
                    punkteAnzeige.setText("Punkte: " + punkte);
                    points = new Picture(565,385,20,10,"PunkteMario.png");
                    fenster.wait(180);
                    points.setHidden(true);
                }

                // Coins einsammeln, verschwinden und 1000 Punkte geben
                for (int i = 0; i < coins.length; i++) {
                    if (coins[i] != null && mario.intersects(coins[i])) {
                        coins[i].setHidden(true);
                        coins[i] = null; // damit der Coin nicht nochmal gezählt wird
                        punkte += 1000;
                        punkteAnzeige.setText("Punkte: " + punkte);
                    }
                }

                // Kollisionen mit allen Objekten prüfen
                checkCollisionArray(groundblock);
                checkCollisionArray(platformen);
                checkCollision(pipe);
                checkCollisionArray(bricks);
                checkCollisionArray(luckyBlocks);
                checkCollisionArray(stairs);

                // Nur springen wenn Mario auf dem Boden steht
                if (mStands && fenster.keyUpPressed())
                    mGeschwY = -9;


                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        wiederholung.start();
    }

    // Kollision für ein ganzes Array prüfen
    private void checkCollisionArray(Picture[] arr) {
        for (Picture obj : arr) {
            if (obj != null)
                checkCollision(obj);
        }
    }

    // Kollision zwischen Mario und einem Objekt prüfen
    private void checkCollision(Picture obj) {

        if (mario.intersects(obj)) {

            // Kanten von Mario und Objekt
            double marioBottom = mario.getShapeY() + mH;
            double marioTop = mario.getShapeY();
            double objTop = obj.getShapeY();
            double objBottom = obj.getShapeY() + obj.getShapeHeight();
            double marioLeft = mario.getShapeX();
            double marioRight = mario.getShapeX() + mW;
            double objLeft = obj.getShapeX();
            double objRight = obj.getShapeX() + obj.getShapeWidth();

            // Mario steht auf Objekt
            if (mGeschwY >= 0 && marioBottom <= objTop + 15) {

                mStands = true;
                mGeschwY = 0;

                mario.moveTo(mario.getShapeX(), objTop - mH);


                if (obj == platformen[2])
                    mario.move(pGeschwX, 0);

            }

            // Mario gegen Objekt von links
            else if (marioRight >= objLeft && marioLeft < objLeft) {
                mario.moveTo(objLeft - mW, mario.getShapeY());
            }

            // Mario gegen Objekt von rechts
            else if (marioLeft <= objRight && marioRight > objRight) {
                mario.moveTo(objRight, mario.getShapeY());
            }


            else if (mGeschwY < 0 && marioTop >= objBottom - 15) {

                mGeschwY = 2;

                mario.moveTo(
                        mario.getShapeX(),
                        objBottom
                );
            }
        }
    }

    public static void main(String[] args) {
        new Welt();
    }
}