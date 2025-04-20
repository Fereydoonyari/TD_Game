public class ScoreTracker {
    private int score = 100 ;
    public void add(int amount){
        score += amount;
    }
    public int get (){
        return score ;
    }
    public void spend(int amount){
        score -= amount;
    }
}
