import processing.core.PApplet;
public class Enemy {
    private float x, y;
    private final float speed,size;
    public Enemy( float x, float y ,float speed) {
        this.x = x ;
        this.y = y ;
        this.speed = speed;
        this.size = 150; // Adjust size as needed
    }

    public void update() { // Move the enemy towards the green circle
        float dx = GreenCircle.x - x;
        float dy = GreenCircle.y - y;
        float distance = PApplet.sqrt(dx * dx + dy * dy);
        if (distance > 1) { // Ensure the enemy moves at least 1 unit
            x += dx * speed / distance;
            y += dy * speed / distance;
        }
    }

    public void render(PApplet app) {
        app.pushMatrix(); // Save the current transformation matrix
        app.translate(x, y); // Translate to the position of the enemy
        float angle = PApplet.atan2(-GreenCircle.y + y, GreenCircle.x + x); // Calculate the angle to rotate
        app.rotate(angle); // Rotate by 90 degrees
        if (x > GreenCircle.x)
            Game.game.scale(-1, 1);
        app.imageMode(app.CENTER);
        app.image(Game.enemeyy, 0, 0, size, size); // Draw the image
        app.popMatrix(); // Restore the previous transformation matrix
    }

    public boolean isCollidedWithPlayer() {
        // Calculate the distance between the enemy and the player
        float dx = Player.x - x;
        float dy = Player.y - y;
        float distance = PApplet.sqrt(dx * dx + dy * dy);
        // Check if the distance is less than the sum of their radii (assuming circular shapes)
        return distance < (size / 2 + Player.size / 2);
    }
    public boolean isCollidedWithGreenCircle() {
        boolean hasCollided= false;
        float dx = GreenCircle.x - x;
        float dy = GreenCircle.y - y;
        float distance = PApplet.sqrt(dx * dx + dy * dy);
        boolean collided = distance < size / 2 + GreenCircle.size / 2.f;
        if (collided) {
            hasCollided = true;// Set flag to true if collision occurred
        }
            return  hasCollided;
    }
    public boolean isCollidedWithArrow(ArrowInstance arrow) {
        float dx = arrow.x - x;
        float dy = arrow.y - y;
        float distance = PApplet.sqrt(dx * dx + dy * dy);
        return distance < size / 2 + arrow.size / 2;
    }
    static void spawnEnemy(float x, float y , float speed) {
        // Create a new enemy with a random speed between 1 and 3
        boolean overlapping = false;
        for (Enemy enemy : Game.enemies) {
            float distance = PApplet.dist(x, y, enemy.x, enemy.y);
            if (distance < enemy.size) {
                overlapping = true;
                break;
            }
        } // If the new enemy doesn't overlap with any existing enemies, add it to the list
        if (!overlapping) {
                Game.enemies.add(new Enemy(x, y,speed));
            }
    }
    static float rendertime= Game.game.millis();
    static void enemyAttack(){
        for (int i = Game.enemies.size() - 1; i >= 0; i--) {
            Enemy enemy = Game.enemies.get(i);
            ArrowInstance arrow ;
            enemy.update();
            enemy.render(Game.game);
            Game.playerArrow.update();
            Game.playerArrow.render(Game.game);
            if (enemy.isCollidedWithPlayer()) {
                Game.enemies.remove(i);
                HUD.shutter();
                Game.lives -= 0.5f; // Increment collision cou// nt
            }
            if (enemy.isCollidedWithGreenCircle()) {
                Game.enemies.remove(i);
                HUD.shutter();
                Game.lives--; // Increment collision count
            }
            for (int j = Arrow.activeArrows.size() - 1; j >= 0; j--) {
                if (Game.enemies.isEmpty()){
                    break;
                }
                if (Arrow.activeArrows.isEmpty()){
                    break;
                }
                arrow = Arrow.activeArrows.get(j);
                if(GreenCircle.isCollidedWithArrow(arrow))
                {
                    Game.score=0;
                    Game.gameOver=true;
                }
                if (enemy.isCollidedWithArrow(arrow)) {
                    Game.score++;
                    Game.BonusLives++;
                    Arrow.activeArrows.remove(j);
                    Game.enemies.remove(i);
                    Game.eleminateed.add(new Eliminated(enemy.x, enemy.y));
                    break; // Exit the inner loop since the arrow has been removed
                }
                for (int k = Game.eleminateed.size() - 1; k >= 0; k--) {
                    Eliminated eliminated = Game.eleminateed.get(k);
                    // Check if the Eliminated object was added more than 1 minute ago
                    if (Game.game.millis() - rendertime > 500) { // 60000 milliseconds = 1 minute
                        // Remove the Eliminated object from the list
                        Game.eleminateed.remove(k);
                        rendertime= Game.game.millis();
                    }
                }

            }
        }
    }

}
