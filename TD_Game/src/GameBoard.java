import java.awt.Graphics;
public class GameBoard {
    private Tile[][] tiles ;
    private int width,height ;
    
    public GameBoard(int width,int height){
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        initializeBoard();
    }
    public Tile getTile(int x, int y){
        return tiles[x][y];
    }
    public void initializeBoard(){
        for (int x = 0 ; x < width; x++){
            for (int y = 0 ; y < height ; y ++){
                tiles[x][y] = new Tile(x,y,TileType.GRASS);
            }
        }
    }
    public void update (){

    }
    public void render (Graphics g ){
        for (int x = 0 ; x < width ; x ++ ){
            for (int y = 0 ; y < height ; y ++){
                tiles[x][y].render(g);
            }
        }
    }
    public int getWidth(){ return width;}
    public int getHeight(){ return height;}

}