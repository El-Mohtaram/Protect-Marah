public class HUD {
    static void drawscore(){
        Game.game.textFont(Game.dashboardf);
        Game.game.textAlign(Game.game.RIGHT, Game.game.TOP);
        Game.game.textSize(50);
        Game.game.fill(255); // White color
        Game.game.text("Score: "+Game.score, Game.game.width - 10, 10); // Print Score
        Game.game.text("Shoots: "+Game.load, Game.game.width - 10, 60); // Print Shoots
        Game.game.text("Level: "+Game.level, Game.game.width - 10, 110); // Print Levels
        Game.game.textFont(Game.shootf);
        Game.game.textAlign(Game.game.CENTER, Game.game.TOP);
        Game.game.textSize(50);
        if(Game.reload) {
            Game.game.fill(3, 255, 19); // White color
            Game.game.text("Shoot" , Game.game.width /2, 120);
        }
        else {
            Game.game.fill(243, 56, 30); // White color
            Game.game.text("Reload" , Game.game.width /2, 120);
        }
    }
    static void drawLives() {
        final int imageSize = 150; // Size of the images
        final int padding = 100; // Padding between images and screen edge
        final int distanceBetweenImages = 200; // Distance between images
        // Draw the images representing lives
        for (int i = 0; i < Game.lives; i++) {
            int x = padding + i * distanceBetweenImages;
            // Draw the appropriate image based on the number of lives
            if (Game.lives > 3) {
                Game.game.image(Game.live4, x, padding, imageSize, imageSize);
                GreenCircle.render(Game.game,Game.live4);// render green circle
            } else if (Game.lives > 2.5) {
                Game.game.image(Game.live3, x, padding, imageSize, imageSize);
                GreenCircle.render(Game.game,Game.live3);// render green circle
            } else if (Game.lives > 1.5) {
                Game.game.image(Game.live2, x, padding, imageSize, imageSize);
                GreenCircle.render(Game.game,Game.live2);// render green circle
            } else if (Game.lives > 0.5) {
                Game.game.image(Game.live1, x, padding, imageSize, imageSize);
                GreenCircle.render(Game.game,Game.live1);// render green circle
            }
            else{
                GreenCircle.render(Game.game,Game.live1);// render green circle
            }
        }
    }
    static void gameOver() {
        // Display "Game Over" text on the screen
       // Game.game.background(Game.backgroundImage);
        Game.game.fill(0,150); // Set Bg Color
        Game.game.rect(0,Game.game.height/2-56,Game.game.width,120); //
        Game.game.textFont(Game.game_overf);
        Game.game.textSize(150); // Adjust text size as needed
        Game.game.textAlign(Game.game.CENTER, Game.game.CENTER); // Center the text horizontally and vertically
        Game.game.fill(243, 56, 30); // Set text color to red
        Game.game.text("Game Over", Game.game.width/2, Game.game.height/2-23);
        if(GreenCircle.hitMarah){
            Game.game.textSize(50); // Adjust text size as needed
            Game.game.fill(255); // Set text color to red
            Game.game.text("You have shooted Marah !", Game.game.width / 2, Game.game.height / 2 + 100);
           // Game.game.noLoop(); // Stop the draw loop
        }
        else {
            Game.game.textSize(50); // Adjust text size as needed
            Game.game.fill(255); // Set text color to red
            Game.game.text("You passed "+ (Game.level-1) + " Levels\nYour score is "+ Game.score+ " with accurcy "+ ((int) ((float) Game.score / (float) Game.load * 100)) + "%", Game.game.width / 2, Game.game.height / 2 + 100);
          //  Game.game.noLoop(); // Stop the draw loop
        }
        Game.game.textFont(Game.dashboardf);
        Game.game.textAlign(Game.game.LEFT, Game.game.BOTTOM);
        Game.game.textSize(50);
        Game.game.fill(255); // White color
        Game.game.text("Press Esc to quit", 10, Game.game.height- 10); // Print Levels
    }
    static void shutter() {
        Game.game.background(255);
        // Pause for a brief moment
        Game.game.delay(50); // Adjust the duration as needed
        // Restore the original background color
        Game.game.background(255);
    }
    static float randomX(){
        float x ;
        // randomly generate initial positions outside the frame boundaries
        if (Game.game.random(1) < 0.5) { // randomly choose whether to spawn on top/bottom or left/right
            if (Game.game.random(1) < 0.5 && randomY() < Game.game.height && randomY() > 0) { // Spawn on top/bottom
                x = Game.game.random(Game.game.width); // random x-coordinate within the frame
            } else { // Spawn on left/right
                x = Game.game.random(-100, 0); // random x-coordinate left of the frame
            }
        } else {
            if (Game.game.random(1) < 0.5 && randomY() < Game.game.height && randomY() > 0) { // Spawn on top/bottom
                x = Game.game.random(Game.game.width); // random x-coordinate within the frame
            } else { // Spawn on left/right
                x = Game.game.random(Game.game.width, Game.game.width + 100); // random x-coordinate right of the frame
            }
        }
        return x ;
    }
    static float randomY(){
        float y ;
        // randomly generate initial positions outside the frame boundaries
        if (Game.game.random(1) < 0.5) { // randomly choose whether to spawn on top/bottom or left/right
            if (Game.game.random(1) < 0.5) { // Spawn on top/bottom
                y = Game.game.random(-100, 0); // random y-coordinate above the frame
            } else { // Spawn on left/right
                y = Game.game.random(-100, 0); // random y-coordinate within the frame
            }
        } else {
            if (Game.game.random(1) < 0.5) { // Spawn on top/bottom
                y = Game.game.random(Game.game.height, Game.game.height + 100); // random y-coordinate below the frame
            } else { // Spawn on left/right
                y = Game.game.random(Game.game.height); // random y-coordinate within the frame
            }
        }
        return y ;
    }
}
