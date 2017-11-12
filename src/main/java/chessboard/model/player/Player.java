package chessboard.model.player;

public class Player {

    int pId;
    String name;

    public Player(int id){
        this.pId = id;
    }

    public int getID()
    {
        return pId;
    }

    public void setID(int id){
        this.pId = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
