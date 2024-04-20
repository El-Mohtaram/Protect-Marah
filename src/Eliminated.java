
    class Eliminated{
        static float x,y;
        public Eliminated(float x,float y){
            this.x=x;
            this.y=y;
        }
        public static void render(){
            Game.game.image(Game.eleminated, x, y, 150, 150); // Draw the image
        }
    }

