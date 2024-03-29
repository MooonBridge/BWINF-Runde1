package de.moonbridge;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AnalyzeWuerfel {

    List<int[]> arrayListe;
    int[][] sortierteWuerfel;

    public AnalyzeWuerfel(){

        arrayListe = new ArrayList<>();

    }

    public void startAnalyis(String pURL){

        readData(pURL);
        testWuerfel();

    }

    public int[][] getErgebnis(){

        return sortierteWuerfel;

    }

    public void readData(String path) {

        try {
            int[] tempArray;
            URL url = new URL(path);
            Scanner s = new Scanner(url.openStream());

            int wuerfelAnzahl = s.nextInt();

            for (int i = 0; i < wuerfelAnzahl; i++){
                int wuerfelAugen = s.nextInt();
                tempArray = new int[wuerfelAugen + 2];
                tempArray[0] = wuerfelAugen;
                for (int j = 1; j <= wuerfelAugen; j++){
                    tempArray[j] = s.nextInt();
                }
                tempArray[wuerfelAugen+1] = 0;
                arrayListe.add(tempArray);
            }

        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void testWuerfel(){

        Simulator simulator;

        for (int i = 0; i < arrayListe.size(); i++){
            int[] tempWuerfel = arrayListe.get(i);
            for (int j = (i + 1); j < arrayListe.size(); j++){
                int win1 = 0;
                int win2 = 0;
                for (int k = 0; k < 500; k++){
                    simulator = new Simulator(tempWuerfel, arrayListe.get(j), 1);
                    if (simulator.startGame() == 1){
                        win1++;
                    } else if (simulator.startGame() == 2){
                        win2++;
                    } else if (simulator.startGame() == 3){
                        win1++;
                        win2++;
                    }
                }
                for (int k = 0; k < 500; k++){
                    simulator = new Simulator(tempWuerfel, arrayListe.get(j), 2);
                    if (simulator.startGame() == 1){
                        win1++;
                    } else if (simulator.startGame() == 2){
                        win2++;
                    } else if (simulator.startGame() == 3){
                        win1++;
                        win2++;
                    }
                }
                if (win1 > win2){
                    tempWuerfel[tempWuerfel.length - 1] = tempWuerfel[tempWuerfel.length - 1] + 1;
                } else if (win2 > win1) {
                    arrayListe.get(j)[arrayListe.get(j).length - 1] = arrayListe.get(j)[arrayListe.get(j).length - 1] + 1;
                } else {
                    tempWuerfel[tempWuerfel.length - 1] = tempWuerfel[tempWuerfel.length - 1] + 1;
                    arrayListe.get(j)[arrayListe.get(j).length - 1] = arrayListe.get(j)[arrayListe.get(j).length - 1] + 1;
                }
            }
        }

        sortierteWuerfel = new int[arrayListe.size()][2];
        for (int i = 0; i < arrayListe.size(); i++){
            sortierteWuerfel[i][0] = i + 1;
            sortierteWuerfel[i][1] = arrayListe.get(i)[arrayListe.get(i).length-1];
        }

        Arrays.sort(sortierteWuerfel, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[1], o1[1]);
            }
        });

    }

}
