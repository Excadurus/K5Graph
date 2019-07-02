package ir.ac.kntu;

public class Database {
    //this class just saves the minimum costs, that's it
    private static int minimumCost=0;
    private static int minimumEdge= Integer.MAX_VALUE;

    public static int getMinimumCost() {
        return minimumCost;
    }

    public static void setMinimumCost(int minimumCost) {
        Database.minimumCost = minimumCost;
    }

    public static int getMinimumEdge() {
        return minimumEdge;
    }

    public static void setMinimumEdge(int minimumEdge) {
        Database.minimumEdge = minimumEdge;
    }
}
