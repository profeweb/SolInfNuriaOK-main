package balletApp.oldstaff.balletPantalles;

import processing.core.PApplet;

public class Trivio004 extends PApplet {
////
    GUI gui;

    public static void main(String[] args) {
        PApplet.main("trivioApp.trivioPantalles.Trivio004", args);
    }

    public void settings() {
        fullScreen();
        smooth(10);
    }

    public void setup() {
        noStroke();
        textAlign(CENTER);
        textSize(18);
        gui = new GUI();
    }

    public void draw() {

        // Dibuixa la pantalla corresponent
        switch (gui.pantallaActual) {
            case registro:
                gui.dibuixaPantallaRegistro(this);
                break;

            case Favoritos:
                gui.dibuixaPantallaFavoritos(this);
                break;

            case TusCanciones:
                gui.dibuixaPantallaTusCanciones(this);
                break;

            case agregarCanción:
                gui.dibuixaPantallaAgregarCancion(this);
                break;

            case TusListas:
                gui.dibuixaPantallaTusListas(this);
                break;

            case cancionesTusListas:
                gui.dibuixaPantallaCancionesTusListas(this);
                break;

            case agregarLista:
                gui.dibuixaPantallaAgregarLista(this);
                break;

            case Canciones:
                gui.dibuixaPantallaCanciones(this);
                break;

            case Calentamiento:
                gui.dibuixaPantallaCalentamiento(this);
                break;

            case Coreografia:
                gui.dibuixaPantallaCoreografia(this);
                break;

            case subListas:
                gui.dibuixaPantallaSubListas(this);
                break;

            case subListasCanciones:
                gui.dibuixaPantallaSubListasCanciones(this);
                break;
        }
    }

    public void keyPressed(){
        if(key=='0'){
            gui.pantallaActual = GUI.PANTALLA.registro;
        }
        else if(key=='1'){
            gui.pantallaActual = GUI.PANTALLA.Favoritos;
        }
        else if(key=='2'){
            gui.pantallaActual = GUI.PANTALLA.TusCanciones;
        }
        else if(key=='3'){
            gui.pantallaActual = GUI.PANTALLA.agregarCanción;
        }
        else if(key=='4'){
            gui.pantallaActual = GUI.PANTALLA.TusListas;
        }
        else if(key=='5'){
            gui.pantallaActual = GUI.PANTALLA.cancionesTusListas;
        }
        else if(key=='6'){
            gui.pantallaActual = GUI.PANTALLA.agregarLista;
        }
        else if(key=='7'){
            gui.pantallaActual = GUI.PANTALLA.Canciones;
        }
        else if(key=='8'){
            gui.pantallaActual = GUI.PANTALLA.Calentamiento;
        }
        else if(key=='9'){
            gui.pantallaActual = GUI.PANTALLA.Coreografia;
        }
        else if(key=='q'|| key=='Q'){
            gui.pantallaActual = GUI.PANTALLA.subListas;
        }
        else if(key=='w'|| key=='W'){
            gui.pantallaActual = GUI.PANTALLA.subListasCanciones;
        }
    }
}
