import processing.video.Movie;
import processing.core.*;
import ddf.minim.*;
public class mainmenu extends PApplet {
    PImage Cover,cq,cn,chtp;
    Minim minim;
    public static Movie bg;
    AudioPlayer mainaudio,press;
    private boolean Start_Game = false;
    private boolean soundPlayed = false,clicked=false;
    public static void main(String[] args) {
        PApplet.main("mainmenu", args);
    }

    public void settings() {
        size(1920, 1080,P3D);
    }

    public void setup() {
        Cover = loadImage("Backgroundm.jpg");
        cq = loadImage("Backgroundquit.jpg");
        cn = loadImage("Backgroundn.jpg");
        chtp = loadImage("Backgroundhtp.jpg");
        Cover.resize(width, height);
        bg = new Movie(this, "Background3.mp4");
        bg.loop(); // Start playing the video in a loop
        minim = new Minim(this);
        mainaudio = minim.loadFile("select.wav");
        press = minim.loadFile("press.wav");
    }
    public void draw() {
        if (!Start_Game) {
            if((mouseX <= 1275) && (mouseX >= 595) && (mouseY <= 449) && (mouseY >= 217)){
                image(cn,0, 0, width, height);
                if (!soundPlayed) {
                    mainaudio.play();
                    mainaudio.rewind();
                    soundPlayed = true;
                }
                if(mouseButton == LEFT){
                    Cover = null;
                    chtp= null;
                    cn=null;
                    cq=null ;
                    press.play();
                    Start_Game=true;
                    Game.main(args);
                }
            }
            else if((mouseX <= 1478) && (mouseX >= 442) && (mouseY <= 746) && (mouseY >= 546)){
                image(chtp,0, 0, width, height);
                if (!soundPlayed) {
                    mainaudio.play();
                    mainaudio.rewind();
                    soundPlayed = true;
                }
            }
            else if ((mouseX <= 1169) && (mouseX >= 750) && (mouseY <= 1034) && (mouseY >= 824)) {
                image(cq,0, 0, width, height);
                if (!soundPlayed) {
                    mainaudio.play();
                    mainaudio.rewind();
                    soundPlayed = true;
                }
                if(mouseButton == LEFT){
                    Cover = null;
                    chtp= null;
                    cn=null;
                    cq=null ;
                    press.play();
                    delay(76);
                    System.exit(0);
                }
            } else {
                image(Cover,0, 0, width, height);
                soundPlayed = false;
            }
        }
        else {
            background(0);
            textAlign(CENTER, CENTER);
            fill(255);
            textSize(150);
            text("Loading...", width/2, height/2);
        }
    }

}


