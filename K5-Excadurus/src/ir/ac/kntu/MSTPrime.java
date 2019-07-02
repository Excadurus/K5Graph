package ir.ac.kntu;

import java.util.ArrayList;

public class MSTPrime {

    public static void stepTwoRecursive(int[][] graph,int cost, ArrayList<Integer> chosenVertices, ArrayList<Integer> unChosenVertices){
        //Closing Condition
        if (unChosenVertices.size() == 0){
            Database.setMinimumCost(cost);

            int smallest = findSmallestCycleEdge(graph);
            if(smallest< Database.getMinimumEdge()){
                Database.setMinimumEdge(smallest);
            }
            return;
        }

        //Finding smallest edge(in case there are multiple smallest edges
        int smallest = findSmallestMSTEdge(graph,chosenVertices,unChosenVertices);
        cost+=smallest;

        //Recursion Deep copying and moving one vertex from unchosen array to chosen array
        for(int i = 0 ; i<chosenVertices.size();i++){
            for(int j = 0; j< unChosenVertices.size();j++){
                if (graph[unChosenVertices.get(j)][chosenVertices.get(i)] == smallest){

                    int[][] newGraph = arrayDeepCopy(graph);
                    //These Cells are useless now(because they have been used) so we change them with max value
                    newGraph[unChosenVertices.get(j)][chosenVertices.get(i)]=Integer.MAX_VALUE;
                    newGraph[chosenVertices.get(i)][unChosenVertices.get(j)]=Integer.MAX_VALUE;

                    //new method
                    ArrayList newChosenVertices = arrayListDeepCopy(chosenVertices);
                    newChosenVertices.add(unChosenVertices.get(j));

                    ArrayList newUnChosenVertices = arrayListDeepCopy(unChosenVertices);
                    newUnChosenVertices.remove(unChosenVertices.get(j));

                    stepTwoRecursive(newGraph,cost,newChosenVertices,newUnChosenVertices);
                }
            }
        }

    }

    //Finding the smallest edge after the MST is found to complete the cycle
    public static int findSmallestCycleEdge(int[][] graph){
        int smallest = Integer.MAX_VALUE;
        for(int i = 0 ; i < graph.length;i++){
            for(int j = 0; j < graph[i].length;j++){
                if (graph[i][j] < smallest){
                    smallest = graph[i][j];
                }
            }
        }
        return smallest;
    }

    //Finding the next smallest edge for the mst
    public static int findSmallestMSTEdge(int[][] graph,ArrayList<Integer> chosenVertices, ArrayList<Integer> unChosenVertices){
        int smallest = Integer.MAX_VALUE;
        for(int i = 0 ; i<chosenVertices.size();i++){
            for(int j = 0; j< unChosenVertices.size();j++){
                if (graph[unChosenVertices.get(j)][chosenVertices.get(i)] < smallest){
                    smallest = graph[unChosenVertices.get(j)][chosenVertices.get(i)];
                }
            }
        }
        return smallest;
    }

    //ArrayList Deep Copy
    public static ArrayList<Integer> arrayListDeepCopy(ArrayList<Integer> arrayList) {
        ArrayList<Integer> newArrayList = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            newArrayList.add(arrayList.get(i));
        }
        return newArrayList;
    }

    //Array Deep Copy
    public static int[][] arrayDeepCopy(int[][] array) {
        int[][] newArray = new int[Main.getSize()][Main.getSize()];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }
}
