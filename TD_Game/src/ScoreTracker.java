public class ScoreTracker {
    private int score = 0 ;
    public void add(int amount){
        score += amount;
    }
    public int get (){
        return score ;
    }
}
