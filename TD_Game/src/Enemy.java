public abstract class Enemy {
    protected int health ;
    protected int speed ;
    protected int reward ;
    protected int damage ;
    public abstract void move ();
    public void takeDamage (int dmg){
        health -= dmg;
    }

}
