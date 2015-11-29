/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;
/**
 *
 * @author eetz1
 */
public class Map {

private Arena arena;
private ModelPlayer player;
int[][] map1 =
{
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 3 },
    {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    
 };
int[][] map2 =
{
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3 },
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    
 };
public Map(ModelPlayer player){
    this.player=player;
}


public void setMap(int[][] map1, int[][] map2){
    this.map1=map1;
    this.map2=map2;
}

public int[][] getMap(){
    
    
    switch(player.getLevel()){
        case 1:
        return map1;
        case 2:
        //return map2;
    }
    return map1;
}
//    public static void main(String args[]) {
  //      Map map= new Map();
//}
}