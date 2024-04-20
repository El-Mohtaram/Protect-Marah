import processing.core.PApplet;
public class Player {
    static float x, y;
    static float size = 80;
    private float angle;
    private final float orbitRadius;
    private final Arrow arrow;
    private final boolean moving;
    private float angle_rotation = 0.0F;

    Player(float orbitRadius) {
        this.orbitRadius = orbitRadius;
        this.angle = 0;
        this.arrow = new Arrow();
        this.moving = true;
        //    this.playerArrow = playerArrow; // Store the playerArrow instance
    }

    void update(boolean gameOver) {
        if (!gameOver && moving) {
            // Calculate the angle based on the cursor's position
            float dx = Game.game.mouseX - GreenCircle.x;
            float dy = Game.game.mouseY - GreenCircle.y;
            angle = PApplet.atan2(dy, dx);
            // Calculate the position of the blue circle based on the angle
            arrow.update();
            // Set the position of the blue circle
        }
    }


    void aim(float targetX, float targetY) {
        angle = PApplet.atan2(targetY - GreenCircle.y, targetX - GreenCircle.y);
    }
    void render() {
        x = GreenCircle.x+ PApplet.cos(angle) * orbitRadius;
        y = GreenCircle.y+ PApplet.sin(angle) * orbitRadius;
        Game.game.pushMatrix();
        Game.game.translate(x, y);
        Game.game.rotate(angle_rotation);
        Game.game.imageMode(Game.game.CENTER);
        if ((x < GreenCircle.x || Game.game.mouseX > GreenCircle.x) && Game.game.mouseX < x)
            Game.game.scale(1, -1);
        if(Game.reload){
            Game.game.image(Game.Mohanedg, 0, 0, size, size);
        }
        else {
            Game.game.image(Game.Mohaned, 0, 0, size, size);
        }
        Game.game.popMatrix();
        angle_rotation = PApplet.atan2(-Game.game.mouseY + y, -Game.game.mouseX + x) + PApplet.PI;
        // Render arrow
        arrow.render(Game.game);
    }

    public void shootArrow() {
        // Get the current position and angle of the player
        float startX = x;
        float startY = y;
        float angle = PApplet.atan2(Game.game.mouseY - startY, Game.game.mouseX - startX);
        float speed = 5; // Adjust arrow speed as needed

        // Shoot the arrow
        arrow.shoot(startX, startY, angle, speed);
    }

}