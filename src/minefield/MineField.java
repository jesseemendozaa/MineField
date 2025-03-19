package minefield;

import mvc.Model;
import mvc.Utilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class MineField extends Model implements Serializable{
    public class Patch implements Serializable {

        private int x;
        private int y;
        private boolean isMined;
        private String display;

        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
        public String getDisplay() {
            return display;
        }
        public void setDisplay(String display) {
            this.display = display;
        }
        public boolean getPatchStatus() {
            return isMined;
        }
        public void setPatchStatus() {
            this.isMined = isMined;
        }

        public Patch(int x, int y, boolean isMined, String display){
            this.x = x;
            this.y = y;
            this.isMined = isMined;
            this.display = display;
        }

    }

    public static Integer world_size = 20;
    private static Integer percentMined = 20;

    private boolean isMined;
    private boolean isOver;

    private int maxX = world_size-1;
    private int minX = 0;
    private int maxY = world_size-1;
    private int minY = 0;

    private Patch location;
    private Patch goal;

    private Patch[][] map = new Patch[world_size][world_size];
    private ArrayList<Patch> path = new ArrayList<Patch>();

    public MineField(){

        isOver = false;

        for (int i = 0; i < world_size; i++) {
            for (int j = 0; j < world_size; j++){
                Random mined = Utilities.rng;
                int num = mined.nextInt(100);
                if (num < percentMined){
                    map[i][j] = new Patch(i,j, true, "?");
                } else {
                    map[i][j] = new Patch(i,j, false, "?");
                }
            }
        }

        location = map[0][0];
        map[0][0].isMined = false;
        int numMined = 0;
        if (map[1][0].isMined){numMined++;}
        if (map[1][1].isMined){numMined++;}
        if (map[0][1].isMined){numMined++;}
        map[0][0].display = String.valueOf(numMined);
        path.add(location);
        goal = map[world_size-1][world_size-1];
        changed();
    }

    public ArrayList<Patch> getPath(){
        return path;
    }

    public Patch getLocation(){
        return location;
    }

    public Patch getGoal(){
        return goal;
    }

    public Patch[][] getMap(){
        return map;
    }

    public boolean getGameStatus() {
        return isOver;
    }

    public MineField(int x, int y){
        isOver = false;
    }

    public void gameOver(){
        isOver = true;
    }

    private Boolean checkOver(){
        if (location.getX() == goal.getX() && location.getY() == goal.getY()) {
            gameOver();
            return true;
        }
        else {return false;}
    }

    // TODO Change display to number of surrounding mines
    public void checkValid(int newX, int newY) throws Exception{
        if(isOver){
            throw new Exception("Game Over. You cannot move again");
        }
        if ((newX > maxX || newX < minX) || (newY > maxY || newY < minY)){
            throw new Exception("Player out of Bounds");
        } else {
            location = map[newX][newY];
            path.add(location);
            if (location.isMined){
                gameOver();
                throw new Exception("Player stepped on mine. Game Over");
            }
            if (checkOver()){
                throw new Exception("Congratulations! You won the game");
            }
            int numMined = 0;
            try {

                if (newX - 1 >= minX){
                    if (map[newX-1][newY].isMined){numMined++;}
                    if (newY - 1 >= minY){
                        if (map[newX-1][newY - 1].isMined){numMined++;}
                        if (map[newX][newY - 1].isMined){numMined++;}
                    }
                    if (newY + 1 <= maxY){
                        if (map[newX-1][newY + 1].isMined){numMined++;}
                        if (map[newX][newY + 1].isMined){numMined++;}
                    }
                }
                if (newX + 1 <= maxX){
                    if (map[newX+1][newY].isMined){numMined++;}
                    if (newY - 1 >= minY){
                        if (map[newX+1][newY - 1].isMined){numMined++;}
                        if (!(newX - 1 >= minX) && (map[newX][newY - 1].isMined)){numMined++;}
                    }
                    if (newY + 1 <= maxY){
                        if (map[newX+1][newY + 1].isMined){numMined++;}
                        if (!(newX - 1 >= minX) &&(map[newX][newY + 1].isMined)){numMined++;}
                    }
                }
            }
            catch (Exception e){}
            location.setDisplay(String.valueOf(numMined));
        }
    }

    public void moveEast() throws Exception{
        int currX = location.getX();
        int currY = location.getY();
        int newX = currX + 0;
        int newY = currY + 1;

        checkValid(newX, newY);
        changed();
    }

    public void moveWest() throws Exception{
        int currX = location.getX();
        int currY = location.getY();
        int newX = currX + 0;
        int newY = currY - 1;

        checkValid(newX, newY);
        changed();
    }

    public void moveSouth() throws Exception{
        int currX = location.getX();
        int currY = location.getY();
        int newX = currX + 1;
        int newY = currY + 0;

        checkValid(newX, newY);
        changed();
    }

    public void moveNorth() throws Exception{
        int currX = location.getX();
        int currY = location.getY();
        int newX = currX - 1;
        int newY = currY + 0;

        checkValid(newX, newY);
        changed();
    }

    public void moveNortheast() throws Exception{
        int currX = location.getX();
        int currY = location.getY();
        int newX = currX - 1;
        int newY = currY + 1;

        checkValid(newX, newY);
        changed();
    }

    public void moveNorthwest() throws Exception{
        int currX = location.getX();
        int currY = location.getY();
        int newX = currX - 1;
        int newY = currY - 1;

        checkValid(newX, newY);
        changed();
    }

    public void moveSoutheast() throws Exception{
        int currX = location.getX();
        int currY = location.getY();
        int newX = currX + 1;
        int newY = currY + 1;

        checkValid(newX, newY);
        changed();
    }

    public void moveSouthwest() throws Exception{
        int currX = location.getX();
        int currY = location.getY();
        int newX = currX + 1;
        int newY = currY - 1;

        checkValid(newX, newY);
        changed();
    }

}
