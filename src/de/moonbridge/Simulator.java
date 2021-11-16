package de.moonbridge;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

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

        if (wuerfeln(w1) != wuerfeln(w2)){
            if (wuerfeln(w1) > wuerfeln(w2)){
                gameActive(1);
            } else {
                gameActive(2);
            }
        }

        if(winner == 1){
            return 1;
        } else {
            return 2;
        }

    }

    public void gameActive(int firstPlayer){

    }

    public int wuerfeln(int[] w){

        return w[ThreadLocalRandom.current().nextInt(0, w.length)];

    }

}
