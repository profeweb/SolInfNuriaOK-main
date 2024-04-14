package balletApp.oldstaff.balletApp.balletFonts;

import balletApp.oldstaff.balletApp.balletFonts.Fonts;
import processing.core.PApplet;

public class ballet002 extends PApplet{
    balletApp.oldstaff.balletApp.balletFonts.Fonts fontsApp;

    public static void main(String[] args){
        PApplet.main("balletApp.oldstaff.balletApp.balletFonts.ballet002", args);
    }

    public void settings(){
        size(900, 900, P2D);
        smooth(10);
    }

    public void setup(){
        fontsApp = new Fonts(this);

    }


    public void draw(){

        background(255);

        fill(0);
        textFont(fontsApp.getFirstFont());
        text("Titol de l'App", 50, 200);

        fill(50);
        textFont(fontsApp.getSecondFont());
        text("Subtitol de l'App", 50, 250);

        fill(55, 0, 0);
        textFont(fontsApp.getThirdFont());
        text("Paràgraf de l'App 1", 50, 300);

        fill(55, 0, 0);
        textFont(fontsApp.getFontAt(3));
        text("Paràgraf de l'App 2", 50, 350);


        fontsApp.displayFonts(this, 540, 550, 50);

    }

    public void keyPressed(){
        println("KEY PRESSED");
    }

    public void mousePressed(){
        println("MOUSE PRESSED");
    }

    public void mouseDragged(){
        println("MOUSE DRAGGED");
    }

    public void mouseReleased() {
        println("MOUSE RELEASED");
    }
}
