import java.util.LinkedList;

public class PandemicPlayer {
    private LinkedList<String[]> hand;
    private int actionsLeft;
    private String currentCity;
    
    public PandemicPlayer(int startingHandSize,int actionsPerTurn, String startingCity) {
        hand = new LinkedList<String[]>();
        actionsLeft = actionsPerTurn;
        currentCity = startingCity;
    }
    public LinkedList<String[]> getHand() {
        return hand;
    }
    
    public int getActionsLeft() {
        return actionsLeft;
    }
    
    public void setActionsLeft(int actionsLeft) {
        this.actionsLeft = actionsLeft;
    }
    
    public String getCurrentCity() {
        return currentCity;
    }
    
    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }
    public void decreaseActionsLeft() {
        actionsLeft--;
    }
    public void addToHand(String[] card) {
        hand.add(card);
    }
    public void takeFromHand(String[] card) {
        hand.remove(card);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
