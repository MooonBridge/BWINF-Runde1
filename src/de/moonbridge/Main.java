package de.moonbridge;

import java.util.Arrays;

public class Main {

    public Main(){

        int[] w1 = {6,1,2,3,4,5,6};
        int[] w2 = {6,1,2,3,4,5,6};
        Simulator sim = new Simulator(w1, w2, 1);

        System.out.println(sim.startGame());

    }

    public static void main(String[] args){
        new Main();
    }
}
