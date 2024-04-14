package balletApp.balletOK.gui;

import processing.core.PApplet;
import processing.core.PImage;

public class PagedTusListas {
    String[][] resultsData;    // Dades de les Cards
    TusListasCard[] lists;          // Llistes
    int numLists;              // Número total de Llistes
    int numListsPage;          // Número de Cançons en 1 Pàgina
    int numPage;
    int numTotalPages;
    float x, y, w, h;

    // Botons
    public Button b1, b2;

    // Dimensions dels botons
    float buttonW = 60, buttonH = 30;

    // Constructor
    public PagedTusListas (PApplet p5, int ncp, float x, float y, float w, float h) {

        this.numListsPage = ncp;
        this.numPage = 0;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        // Creació dels botons
        b1 = new Button(p5, ">", 390 + 850/2 + 10 + buttonW - buttonW/2, 500+buttonH/2+20, buttonW, buttonH);
        b2 = new Button(p5, "<", 390 + 850/2 - 10 - buttonW - buttonW/2, 500+buttonH/2+20, buttonW, buttonH);
    }

    // Setters
    public void setData(String[][] d) {
        this.resultsData = d;
        this.numTotalPages = d.length / this.numListsPage;
    }

    public void setCards(PApplet p5, PImage img1) {
        lists = new TusListasCard[this.resultsData.length];
        float hc = h / (float) numListsPage;
        int k=0;
        for (int i=0; i<lists.length; i++) {
            float yc = y +(k% numListsPage)*(hc + 10);
            lists[i] = new TusListasCard(x, yc, w, hc, 10, resultsData[i]);
            lists[i].setButtons(p5, img1);
            k++;
        }
    }


    public void nextPage() {
        if (this.numPage<this.numTotalPages) {
            this.numPage++;
        }
    }

    public void prevPage() {
        if (this.numPage>0) {
            this.numPage--;
        }
    }

    // Dibuixa taula
    public void display(PApplet p5) {

        p5.pushStyle();

        // Dibuixa Cards
        int firstCardPage = numListsPage *numPage;
        int lastCardPage  = numListsPage *(numPage+1) - 1;

        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i<lists.length) {
                boolean mouseOver = (i==numCardOver(p5));
                lists[i].display(p5, mouseOver);
            }
        }

        // Informació de la Pàgina
        p5.fill(0);
        p5.text("Pag: "+(this.numPage+1)+" / "+(this.numTotalPages+1), x + w + 50, y+10);

        // Dibuixa els botons
        b1.display(p5);
        b2.display(p5);

        p5.popStyle();
    }


    public int numCardOver(PApplet p5) {

        int firstCardPage = numListsPage *numPage;
        int lastCardPage  = numListsPage *(numPage+1) - 1;

        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i<lists.length) {
                if (lists[i].mouseOnCard(p5)) {
                    return i;
                }
            }
        }
        return -1;
    }


    public TusListasCard checkCardClick(PApplet p5) {

        int firstCardPage = numListsPage *numPage;
        int lastCardPage  = numListsPage *(numPage+1) - 1;

        for (int i = firstCardPage; i <= lastCardPage & i<lists.length; i++) {
            if (lists[i].mouseOnCard(p5)) {
                return lists[i];
            }
        }
        return null;
    }

    public void checkButtons(PApplet p5) {
        if (b1.mouseOverButton(p5)) {
            nextPage();
        } else if (b2.mouseOverButton(p5)) {
            prevPage();
        }
    }
}
