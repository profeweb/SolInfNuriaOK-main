
//     this.colors[0] = p5.color(0x802326);
                //     this.colors[1] = p5.color(0x8D3901);
                //     this.colors[2] = p5.color(0xF7A1A4);
                //    this.colors[3] = p5.color(0xFBBCA9);
                //    this.colors[4] = p5.color(0xFDC9AB);
                //   this.colors[5] = p5.color(0xFFD6AD);
                //this.colors[6] = p5.color(0xFFE1C2);
        //this.colors[7] = p5.color(0xFFECD6);

        package balletApp.oldstaff.balletApp.balletColors;

        import processing.core.PApplet;

        public class Colors {

            int[] colors;

            public Colors(PApplet p5){
                this.setColors(p5);
            }

            // Estableix colors de l'App
            void setColors(PApplet p5){
                  this.colors = new int[8];
                  this.colors[0] = p5.color(0xFF802326);
                  this.colors[1] = p5.color(0xFF8D3901);
                  this.colors[2] = p5.color(0xFFF7A1A4);
                  this.colors[3] = p5.color(0xFFFBBCA9);
                  this.colors[4] = p5.color(0xFFFDC9AB);
                  this.colors[5] = p5.color(0xFFFFD6AD);
                  this.colors[6] = p5.color(0xFFFFE1C2);
                  this.colors[7] = p5.color(0xFFFFECD6);
        }

            // Getter del número de colors
            int getNumColors(){
                return this.colors.length;
            }

            // Getter del color primari
            int getFirstColor(){
                return  this.colors[0];
            }

            // Getter del color secundari
            int getSecondColor(){
                return  this.colors[1];
            }

            // Getter del color terciari
            int getThirdColor(){
                return  this.colors[2];
            }

            // Getter del color i-èssim
            int getColorAt(int i){
                return this.colors[i];
            }


            // Dibuixa paleta de colors
            void displayColors(PApplet p5, float x, float y, float w){
                p5.pushStyle();
                //Llegenda
                p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(36);
                p5.text("Colors:", x, y-10);

                // Paleta de colors
                float wc = w / getNumColors();
                for(int i=0; i<getNumColors(); i++){
                    p5.fill(getColorAt(i)); p5.stroke(0); p5.strokeWeight(3);
                    p5.rect(x + i*wc, y, wc, wc);
                }
                p5.popStyle();
            }
        }