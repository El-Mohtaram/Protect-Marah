import processing.core.PApplet;
import processing.core.PImage;
public class GreenCircle {
    static float x, y;
    static int size = 100;
    static boolean hitMarah;
    GreenCircle(float x, float y) {
        GreenCircle.x = x;
        GreenCircle.y = y + 200 ;
    }
    static void render(PApplet app,PImage marah) {
        app.image(marah,x, y, size, size); // Green circle
    }
    static boolean isCollidedWithArrow(ArrowInstance arrow) {
        float dx = arrow.x - x;
        float dy = arrow.y - y;
        float distance = PApplet.sqrt(dx * dx + dy * dy);
        if (distance < size / 2 + arrow.size / 2)
            hitMarah = true ;
        return distance < size / 2 + arrow.size / 2;
    }
}