package de.moonbridge;

import java.util.Arrays;

public class Simulator {

    int w1seiten;
    int w2seiten;
    int[] w1;
    int[] w2;
    int[] kegel1 = {1,0,0,0};
    int[] kegel2 = {21,0,0,0};

    int winner = 0;

    public Simulator(int[] wuerfel1, int[] wuerfel2){

        w1seiten = wuerfel1[0];
        w2seiten = wuerfel2[0];

        w1 = Arrays.copyOfRange(wuerfel1, 1, wuerfel1.length);
        w2 = Arrays.copyOfRange(wuerfel2, 1, wuerfel2.length);

    }

    public int startGame(){

        if(winner == 1){

            return 1;

        } else {

            return 2;

        }

    }

}
