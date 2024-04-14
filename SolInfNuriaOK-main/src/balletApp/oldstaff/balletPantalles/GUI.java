package balletApp.oldstaff.balletPantalles;

import processing.core.PApplet;

import static balletApp.oldstaff.balletLayout.Layout.*;

public class GUI {

    public enum PANTALLA {registro, Favoritos, TusCanciones, agregarCanción,
                          TusListas, cancionesTusListas, agregarLista,
                          Canciones, Calentamiento, Coreografia, subListas,
                          subListasCanciones};

    public PANTALLA pantallaActual;

    public GUI(){
        pantallaActual = PANTALLA.registro;
    }

    public void dibuixaPantallaRegistro(PApplet p5){
        p5.background(255);
        dibuixaPhoto1(p5);
        dibuixaUsuari(p5);
        dibuixaRect1(p5);
    }

    public void dibuixaPantallaFavoritos(PApplet p5){
        p5.background(255);
        dibuixaFullMenu(p5);
        dibuixaRect2(p5);
        dibuixaBotonsTuCuenta(p5);
    }

    public void dibuixaPantallaTusCanciones(PApplet p5){
        p5.background(255);
        dibuixaFullMenu(p5);
        dibuixaRect2(p5);
        dibuixaBotonsTuCuenta(p5);
    }

    public void dibuixaPantallaAgregarCancion(PApplet p5){
        p5.background(255);
        dibuixaFullMenu(p5);
        dibuixaRect2(p5);
        dibuixaBotonsTuCuenta(p5);
    }

    public void dibuixaPantallaTusListas(PApplet p5){
        p5.background(255);
        dibuixaFullMenu(p5);
        dibuixaRect2(p5);
        dibuixaBotonsTuCuenta(p5);
    }

    public void dibuixaPantallaCancionesTusListas(PApplet p5){
        p5.background(255);
        dibuixaFullMenu(p5);
        dibuixaRect2(p5);
        dibuixaBotonsTuCuenta(p5);
    }

    public void dibuixaPantallaAgregarLista(PApplet p5){
        p5.background(255);
        dibuixaFullMenu(p5);
        dibuixaRect2(p5);
        dibuixaBotonsTuCuenta(p5);
    }

    public void dibuixaPantallaCanciones(PApplet p5){
        p5.background(255);
        dibuixaFullMenu(p5);
        dibuixaRect34(p5);
    }

    public void dibuixaPantallaCalentamiento(PApplet p5){
        p5.background(255);
        dibuixaFullMenu(p5);
        dibuixaRect2(p5);
        dibuixaBotonsCanciones(p5);
    }

    public void dibuixaPantallaCoreografia(PApplet p5){
        p5.background(255);
        dibuixaFullMenu(p5);
        dibuixaRect2(p5);
        dibuixaBotonsCanciones(p5);
    }

    public void dibuixaPantallaSubListas(PApplet p5){
        p5.background(255);
        dibuixaFullMenu(p5);
        dibuixaRect2(p5);
        dibuixaBotonsCanciones(p5);
    }

    public void dibuixaPantallaSubListasCanciones(PApplet p5){
        p5.background(255);
        dibuixaFullMenu(p5);
        dibuixaRect2(p5);
        dibuixaBotonsCanciones(p5);
    }


    //  DIBUIX ELEMENTS, zones GUI
    public void dibuixaPhoto1(PApplet p5){                      // 1
        //p5.fill(trivioApp.trivioColors.Colors.colors[3]);
        p5.fill(230);
        p5.rect(margeH, margeV, photo1Width, photo1Height);
    }

    public void dibuixaRect1(PApplet p5){                      // 2
        p5.fill(200);
        p5.rect(margeH+photo1Width+margeH, margeV+usuariRadi+margeV, rect1Width, rect1Height, 30);
    }

    public void dibuixaUsuari(PApplet p5){                    // 3
        p5.fill(200);
        p5.ellipseMode(p5.CENTER);
        p5.ellipse(margeH+photo1Width+margeH+(rect1Width/2), margeV+(usuariRadi/2), usuariRadi, usuariRadi);
    }

    public void dibuixaUsuariMenu(PApplet p5){                // 4
        p5.fill(190);
        p5.ellipse(margeH+(photo2Width/2), margeV+((usuariRadi+10)/2), usuariRadi+10, usuariRadi+10);
    }

    public void dibuixaPhoto2(PApplet p5){                    // 5
        p5.fill(190);
        p5.rect(margeH, menuHeight-photo2Height-margeV-10, photo2Width, photo2Height);
    }

    public void dibuixaMenu(PApplet p5){                      // 6
        p5.fill(230);
        p5.strokeWeight(2); p5.stroke(0);
        p5.rect(-10, -10, menuWidth, menuHeight);
    }

    public void dibuixaRect2(PApplet p5){                      // 7
        p5.fill(230);
        p5.rect(-10+menuWidth+margeH, margeV+60, rect2Width, rect2Height, 30);
    }

    public void dibuixaBotonsTuCuenta(PApplet p5){             // 8
        p5.fill(230);
        p5.rect(-10+menuWidth+margeH+150, margeV+60+rect2Height+30, botonsTuCuentaWidth, botonsTuCuentaHeight, 30);
    }

    public void dibuixaRect34(PApplet p5){                     // 9 i 10
        p5.fill(215);
        p5.rect(390, margeV, rect34Width, rect34Height, 30);
        p5.rect(390+rect34Width+50, margeV, rect34Width, rect34Height, 30);
    }

    public void dibuixaBotonsCanciones(PApplet p5){           // 11 i 12
        p5.fill(215);
        p5.rect(-10+menuWidth+margeH, margeV+60+rect2Height+25, botonVolverWidth, botonVolverHeight, 30);
        p5.rect(-10+menuWidth+margeH+500, margeV+60+rect2Height+40, botonAñadirWidth, botonAñadirHeight, 30);
    }

    public void dibuixaFullMenu(PApplet p5){
        p5.pushStyle();
            dibuixaMenu(p5);
        p5.popStyle();
            dibuixaPhoto2(p5);
            dibuixaUsuariMenu(p5);
    }
}
