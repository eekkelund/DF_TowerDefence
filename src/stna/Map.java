/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import java.io.*;
import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eetz1
 */
public class Map {

int[][] map =
{
    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 3 },
    {0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
    {0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 }
    
 };


/*    
    for (int x = 0; x < map.length; x++) {
        System.out.println("");
        for (int y = 0; y < map[x].length; y++) {
            System.out.print(map[x][y]);
        }
}*///print map just try

public void setMap(int[][] map){
    this.map=map;
}

public int[][] getMap(){
    return map;
}
//    public static void main(String args[]) {
  //      Map map= new Map();
//}
}