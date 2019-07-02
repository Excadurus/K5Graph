package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int size;

    public static int getSize() {
        return size;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        size=n;

        //Scanning array cells
        int graph[][]= new int[n][n];
        for(int i =0 ; i<n;i++){
            for(int j =0 ; j<n; j++){
                graph[i][j]= scanner.nextInt();
            }
        }
        //Filling needless cells with Integer.Max
        for(int i=0;i<n;i++){
            graph[i][i] = Integer.MAX_VALUE;
        }

        //Adding all vertices to the unChosen array
        ArrayList<Integer> unChosenVertices = new ArrayList<>();
        for(int i=0;i<n;i++){
            unChosenVertices.add(i);
        }


        //Starting vertex permutation, Arrays and Lists are deep copied
        for(int i=0;i<n;i++){
            ArrayList<Integer> chosenVertices = new ArrayList<>();
            chosenVertices.add(unChosenVertices.get(i));

            ArrayList newUnChosenVertices = MSTPrime.arrayListDeepCopy(unChosenVertices);
            newUnChosenVertices.remove(unChosenVertices.get(i));

            int[][] newGraph = MSTPrime.arrayDeepCopy(graph);

            MSTPrime.stepTwoRecursive(newGraph,0,chosenVertices,newUnChosenVertices);
        }

        System.out.println(Database.getMinimumCost() + Database.getMinimumEdge());

    }
}
