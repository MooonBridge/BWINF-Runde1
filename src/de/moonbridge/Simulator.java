package de.moonbridge;

import javax.swing.*;
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
    int[] kegel2 = {1,0,0,0};
    int[] win = {41,42,43,44};

    int winner = 0;

    public Simulator(int[] wuerfel1, int[] wuerfel2, int pBeginner){
        w1seiten = wuerfel1[0];
        w2seiten = wuerfel2[0];
        w1 = Arrays.copyOfRange(wuerfel1, 1, wuerfel1.length - 1);
        w2 = Arrays.copyOfRange(wuerfel2, 1, wuerfel2.length - 1);
        beginner = pBeginner;
    }

    public int startGame(){

        if (Arrays.stream(w1).noneMatch(i -> i == 6)){
            return 2;
        } else if (Arrays.stream(w2).noneMatch(i -> i == 6)){
            return 1;
        }

        gameActive(beginner);

        return winner;
    }

    public void gameActive(int firstPlayer){

        int activePlayer = firstPlayer;
        int activeNumber;

        int sameCounter1 = 0;
        int sameCounter2 = 0;

        while (winner == 0){
            if (activePlayer == 1){

                activeNumber = wuerfeln(w1);
                int[] tempKegel1 = kegel1;

                if (Arrays.stream(kegel1).anyMatch(i -> i == 1)){

                    for (int i = 3; i >= 0; i--){
                        int prediction = kegel1[i] + activeNumber;
                        if (kegel1[i] == 1){
                            if (Arrays.stream(kegel1).noneMatch(j -> j == prediction)){
                                kegel1[i] = prediction;
                            } else {
                                for (int j = 3; j >= 0; j--){
                                    int prediction2 = kegel1[j] + activeNumber;
                                    if (prediction2 <= 44 && Arrays.stream(kegel1).noneMatch(k -> k == prediction2) && kegel1[j] != 0){
                                        kegel1[j] = prediction2;
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }

                } else {

                    if (activeNumber == 6){
                        if (kegel1[0] == 0){
                            kegel1[0] = 1;
                        } else {
                            for (int i = 3; i >= 0; i--){
                                int prediction = kegel1[i] + activeNumber;
                                if (prediction <= 44 && Arrays.stream(kegel1).noneMatch(j -> j == prediction)){
                                    kegel1[i] = prediction;
                                    break;
                                }
                            }

                        }

                    } else {

                        for (int i = 3; i >= 0; i--){
                            int prediction = kegel1[i] + activeNumber;
                            if (prediction <= 44 && Arrays.stream(kegel1).noneMatch(j -> j == prediction) && kegel1[i] != 0){
                                kegel1[i] = prediction;
                                break;
                            }
                        }

                    }
                }

                Arrays.sort(kegel1);

                if (kegel1 == tempKegel1){
                    sameCounter1++;
                }

                if (sameCounter1 == 200){
                    winner = 3;
                }

                boolean winning = true;
                for (int i = 0; i < 4; i++){
                    if (kegel1[i] != win[i]){
                        winning = false;
                        break;
                    }
                }

                if (winning) {
                    winner = 1;
                }

                if (activeNumber != 6){
                    activePlayer = 2;
                }

                for (int i = 0; i < 4; i++){
                    for (int j = 0; j < 4; j++){
                        if (kegel1[i] <= 40 && kegel1[i] == (kegel2[j] + 20) && kegel1[i] != 0){
                            kegel2[j] = 0;
                        }
                    }
                }

            } else {

                activeNumber = wuerfeln(w2);
                int[] tempKegel2 = kegel2;

                if (Arrays.stream(kegel2).anyMatch(i -> i == 1)){

                    for (int i = 3; i >= 0; i--){
                        int prediction = kegel2[i] + activeNumber;
                        if (kegel2[i] == 1){
                            if (Arrays.stream(kegel2).noneMatch(j -> j == prediction)){
                                kegel2[i] = prediction;
                            } else {
                                for (int j = 3; j >= 0; j--){
                                    int prediction2 = kegel2[j] + activeNumber;
                                    if (prediction2 <= 44 && Arrays.stream(kegel2).noneMatch(k -> k == prediction2) && kegel2[j] != 0){
                                        kegel2[j] = prediction2;
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }

                } else {

                    if (activeNumber == 6){
                        if (kegel2[0] == 0){
                            kegel2[0] = 1;
                        } else {
                            for (int i = 3; i >= 0; i--){
                                int prediction = kegel2[i] + activeNumber;
                                if (prediction <= 44 && Arrays.stream(kegel2).noneMatch(j -> j == prediction)){
                                    kegel2[i] = prediction;
                                    break;
                                }
                            }

                        }

                    } else {

                        for (int i = 3; i >= 0; i--){
                            int prediction = kegel2[i] + activeNumber;
                            if (prediction <= 44 && Arrays.stream(kegel2).noneMatch(j -> j == prediction) && kegel2[i] != 0){
                                kegel2[i] = prediction;
                                break;
                            }
                        }

                    }
                }

                Arrays.sort(kegel2);

                if (kegel2 == tempKegel2){
                    sameCounter2++;
                }

                if (sameCounter2 == 200){
                    winner = 3;
                }

                boolean winning = true;
                for (int i = 0; i < 4; i++){
                    if (kegel2[i] != win[i]){
                        winning = false;
                        break;
                    }
                }
                if (winning) {
                    winner = 2;
                }

                if (activeNumber != 6){
                    activePlayer = 1;
                }

                for (int i = 0; i < 4; i++){
                    for (int j = 0; j < 4; j++){
                        if (kegel1[i] <= 40 && kegel1[i] == (kegel2[j] + 20) && kegel1[i] != 0){
                            kegel1[j] = 0;
                        }
                    }
                }

            }
        }
    }

    public int wuerfeln(int[] w){

        return w[ThreadLocalRandom.current().nextInt(0, w.length)];

    }

}