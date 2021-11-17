package de.moonbridge;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntPredicate;

public class Simulator {

    int w1seiten;
    int w2seiten;
    int beginner;
    int[] w1;
    int[] w2;
    int[] kegel1 = {1,0,0,0};
    int[] kegel2 = {21,0,0,0};
    int[] win = {41,42,43,44};

    int winner = 0;

    public Simulator(int[] wuerfel1, int[] wuerfel2, int pBeginner){
        w1seiten = wuerfel1[0];
        w2seiten = wuerfel2[0];
        w1 = Arrays.copyOfRange(wuerfel1, 1, wuerfel1.length);
        w2 = Arrays.copyOfRange(wuerfel2, 1, wuerfel2.length);
        beginner = pBeginner;
    }

    public int startGame(){

        if (!Arrays.stream(w1).anyMatch(i -> i == 6)){
            return 2;
        } else if (!Arrays.stream(w2).anyMatch(i -> i == 6)){
            return 1;
        }

        gameActive(beginner);

        if(winner == 1){
            return 1;
        } else {
            return 2;
        }

    }

    public void gameActive(int firstPlayer){

        int activePlayer = firstPlayer;
        int activeNumber;

        while (winner == 0){
            if (activePlayer == 1){
                activeNumber = wuerfeln(w1);
                if (activeNumber == 6 && !Arrays.stream(kegel1).anyMatch(i -> i == 0)){
                    for (int i = 0; i < 4; i++){
                        int prediction = kegel1[i] + 6;
                        if (prediction <= 44 && !Arrays.stream(kegel1).anyMatch(j -> j == prediction)){
                            kegel1[i] = prediction;
                            break;
                        }
                    }
                } else if (activeNumber == 6 && Arrays.stream(kegel1).anyMatch(i -> i == 0) && Arrays.stream(kegel1).anyMatch(i -> i == 1)){
                    for (int i = 0; i < 4; i++){
                        int prediction = kegel1[i] + 6;
                        if (kegel1[i] == 1){
                            kegel1[i] = prediction;
                            break;
                        }
                    }
                } else if (activeNumber == 6 && Arrays.stream(kegel1).anyMatch(i -> i == 0) && !Arrays.stream(kegel1).anyMatch(i -> i == 1)){
                    for (int i = 0; i < 4; i++){
                        if (kegel1[i] == 0){
                            kegel1[i] = 1;
                            break;
                        }
                    }
                }

                if (activeNumber != 6){
                    for (int i = 0; i < 4; i++){
                        int prediction = kegel1[i] + i;
                        if (prediction <= 44 && !Arrays.stream(kegel1).anyMatch(j -> j == prediction)){
                            kegel1[i] = prediction;
                            break;
                        }
                    }
                }

                for (int i = 0; i < 4; i++){
                    for (int j = 0; j < 4; j++){
                        if (kegel1[i] == kegel2[j] && kegel1[i] <= 40){
                            kegel2[j] = 0;
                        }
                    }
                }

            } else {

            }

            if (kegel1 == win){
                winner = 1;
            } else {
                winner = 2;
            }

        }

    }

    public int wuerfeln(int[] w){

        return w[ThreadLocalRandom.current().nextInt(0, w.length)];

    }

}