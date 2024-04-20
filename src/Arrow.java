import processing.core.PApplet;
import java.util.ArrayList;
public class Arrow {
    static ArrayList<ArrowInstance> activeArrows;// List to store active arrows// Flag to indicate if the arrow is active (i.e., moving)
    public Arrow() {
        activeArrows = new ArrayList<>();
    }
    public void update() {
        for (int i = activeArrows.size() - 1; i >= 0; i--) {
            ArrowInstance arrow = activeArrows.get(i);
            arrow.update();
            if (ArrowInstance.isOffScreen()) {
                activeArrows.remove(i);
            }
        }
    }
    public void render(PApplet app) {
        for (ArrowInstance arrow : activeArrows) {
            arrow.render(app);
        }
    }
    public void shoot(float startX, float startY, float angle, float speed) {
        activeArrows.add(new ArrowInstance(startX, startY, angle, speed));
    }
}
 class ArrowInstance {
    static float x, y;
    private final float dx, dy;
    static final float size = 10;
    public ArrowInstance(float startX, float startY, float angle, float speed ) {
        x = startX;
        y = startY;
        this.dx = PApplet.cos(angle) * speed;
        this.dy = PApplet.sin(angle) * speed;
    }
    public void update() {
        x += dx;
        y += dy;
    }
    public void render(PApplet app) {
        app.image(Game.arroww,x + dx * 5, y + dy * 5, 50,50); // Render arrow head
    }
     static boolean isOffScreen() {
         // Check if the arrow is off-screen
         // Example condition: if the arrow goes beyond the window boundaries
         return x < 0 || x > Game.game.width || y < 0 || y > Game.game.height;
     }
}