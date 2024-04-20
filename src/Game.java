import processing.video.Movie;
import processing.core.PApplet ;
import processing.core.PImage;
import processing.core.PFont;
import java.util.ArrayList;
import ddf.minim.*;
import gifAnimation.*;
public class Game extends PApplet {
    public static Movie video;
    private AudioPlayer Levelup;
    static Gif enemeyy, eleminated, arroww, Mohanedg;
    public static PApplet game;
    static PImage Mohaned, Marah, Enemys, Arrows, live1, live2, live3, live4;
    static PFont dashboardf, game_overf, shootf;  // Declare a variable to hold the font
    static Player player;
    static GreenCircle greenCircle;
    static ArrayList<Enemy> enemies;
    static ArrayList<Eliminated> eleminateed;
    static Arrow playerArrow;
    static int score = 0, load = 0, level = 1, BonusLives = 0;
    static float lives = 3.F;
    static boolean reload = false;
    private int minspeed = 1, maxspeed = 3, Levelfactor = 10, spawnInterval = 1500, lastSpawnTime;
    static boolean gameOver;

    public static void main(String[] args) {
        PApplet.main("Game", args);
    }
    public void settings() {
        size(1920, 1080, P3D);
        fullScreen();
        game = this;
    }

    public void setup() {
        frameRate(90);
        video = new Movie(this, "Background3.mp4");
        video.loop(); // Start playing the video in a loop
        Mohanedg = new Gif(this, "Mohaned.gif");
        enemeyy = new Gif(this, "enemyy.gif");
        arroww = new Gif(this, "arroww.gif");
        eleminated = new Gif(this, "elimi.gif");
        arroww.play();
        eleminated.play();
        enemeyy.play();
        Minim minim = new Minim(this);
        AudioPlayer bgMusic = minim.loadFile("GTA.wav");
        Levelup = minim.loadFile("levelup.wav");
        bgMusic.loop();
        greenCircle = new GreenCircle(width / 2, height / 2);
        playerArrow = new Arrow();
        player = new Player(100);
        enemies = new ArrayList<>();
        eleminateed = new ArrayList<>();
        gameOver = false;
        Mohaned = loadImage("Mohaned.png");
        Marah = loadImage("live4.png");
        Enemys = loadImage("Enemy.gif");
        Arrows = loadImage("Arrow.png");
        live1 = loadImage("live1.png");
        live2 = loadImage("live2.png");
        live3 = loadImage("live3.png");
        live4 = loadImage("live4.png");
        game_overf = createFont("binxchr.ttf", 150);
        dashboardf = createFont("JetBrainsMono-SemiBold.ttf", 150);
        shootf = createFont("Bates Shower.ttf", 150);
        enemies = new ArrayList<>();
        lastSpawnTime = millis();
        for (int i = 0; i < 4; i++) {
            float speed = random(minspeed, maxspeed); // Random speed between 1 and 3
            Enemy.spawnEnemy(HUD.randomX(), HUD.randomY(), speed);
        }
    }

    public void draw() {
        try {
            if (video != null && video.available()) {
                video.read();
                video.speed(2);
                image(video, width / 2, height / 2, width, height);
                if (video.time() == 21.805554) {
                    video.jump(0); // Rewind the video to the beginning
                }
                if (!gameOver) {
                    HUD.drawLives();
                    HUD.drawscore();
                    player.update(gameOver);
                    player.render();// Update and render player
                    if (millis() - lastSpawnTime >= spawnInterval) {
                        float speed = random(minspeed, maxspeed);
                        Enemy.spawnEnemy(HUD.randomX(), HUD.randomY(), speed);
                        lastSpawnTime = millis(); // Update lastSpawnTime
                    }
                    Enemy.enemyAttack();
                    Eliminated.render();
                    if (BonusLives % Levelfactor == 0 && BonusLives != 0) {
                        Levelup.rewind();
                        Levelup.play();
                        lives++;
                        level++;
                        spawnInterval *= 0.7f;
                        Levelfactor *= 2;
                        minspeed *= 1.5f;
                        maxspeed *= 1.5f;
                        BonusLives = 0;
                    }
                    if (lives <= 0) { // Check if collision count exceeds threshold
                        gameOver = true;
                    }
                } else {
                    HUD.gameOver();
                }
            }
        }catch (Exception e) {}
    }

    public void mouseMoved() {
        player.aim(mouseX, mouseY);
    }
    public void mousePressed() {
        if (!gameOver) {
            if (mouseButton == RIGHT) {
                reload = true;
            }
            if (mouseButton == LEFT && reload) {
                player.shootArrow();
                load++;
                reload = false;
            }
        }
    }
}
