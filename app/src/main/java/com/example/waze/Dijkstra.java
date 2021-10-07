package com.example.waze;

import java.io.Serializable;
import java.util.ArrayList;

class areaInfo implements Serializable {
    String area_name;
    double latitude;
    double longitude;
    public areaInfo(String area_name, double latitude, double longitude) {
        this.area_name = area_name;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}

class Result{
    int minCost;
    ArrayList<areaInfo> res;
    public Result(int minCost, ArrayList<areaInfo> res) {
        this.minCost = minCost;
        this.res = res;
    }
}

public class Dijkstra {

    int start;
    int stop;
    ArrayList<areaInfo> resL,area;
    Result dist_path;

    Result dijkstraShotestPathAlgo(String source, String destination) {

        resL=new ArrayList<>();

//        System.out.println(
//                "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "\n" +
//                        "");

        area = new ArrayList<>();
        area.add(new areaInfo("Gondia",21.4549,80.1961));
        area.add(new areaInfo("Bhandara",21.1777,79.6570));
        area.add(new areaInfo("Nagpur",21.1458,79.0882));
        area.add(new areaInfo("Wardha",20.7453,78.6022));
        area.add(new areaInfo("Amravati",20.9320,77.7523));
        area.add(new areaInfo("Yavatmal",20.3899,78.1307));
        area.add(new areaInfo("Chandrapur",19.9615,79.2961));
        area.add(new areaInfo("Akola",20.7002, 77.0082));
        area.add(new areaInfo("Bhusawal",21.0418,75.7876));
        area.add(new areaInfo("Jalgaon",21.0077,75.5626));
        area.add(new areaInfo("Dhule",20.9042,74.7749));
        area.add(new areaInfo("Malegaon",20.5579,74.5089));
        area.add(new areaInfo("Aurangabad",19.8762,75.3433));
        area.add(new areaInfo("Jalna",19.8347,75.8816));
        area.add(new areaInfo("Parbhani",19.2608, 76.7748));
        area.add(new areaInfo("Nanded",19.1383, 77.3210));
        area.add(new areaInfo("Nashik",19.9975, 73.7898));
        area.add(new areaInfo("Ahmednagar",19.0948, 74.7480));
        area.add(new areaInfo("Beed",18.9901, 75.7531));
        area.add(new areaInfo("Latur",18.4088, 76.5604));
        area.add(new areaInfo("Mumbai",19.0760, 72.8777));
        area.add(new areaInfo("Pune",18.5204, 73.8567));
        area.add(new areaInfo("Solapur",17.6599, 75.9064));
        area.add(new areaInfo("Satara",17.6805, 74.0183));
        area.add(new areaInfo("Ratnagiri",16.9902, 73.3120));
        area.add(new areaInfo("Kolhapur",16.7050, 74.2433));

        int s_in=0,d_in=0;
        for(int i=0;i< area.size();i++){
            if(area.get(i).area_name.equals(source)){
                s_in=i;
            }
            if(area.get(i).area_name.equals(destination)){
                d_in=i;
            }
        }

        int startVertex = s_in;
        int stopVertex = d_in;

        start=startVertex;
        stop=stopVertex;

        int[][] adjMat = new int[][]{
                {0,80,0,0,0,0,239,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {80,0,62,0,0,0,209,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,62,0,77,154,0,153,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,77,0,0,72,130,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,154,0,0,93,0,102,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,72,93,0,0,0,0,0,0,0,0,0,245,197,0,0,0,0,0,0,0,0,0,0},
                {239,209,153,130,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,102,0,0,0,155,0,0,0,0,191,204,209,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,155,0,29,0,0,0,173,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,29,0,96,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,96,0,239,218,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,239,0,0,0,0,0,104,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,218,0,0,60,0,0,0,114,125,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,191,173,0,0,0,60,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,245,0,204,0,0,0,0,0,0,0,70,0,0,147,0,0,0,0,0,0,0},
                {0,0,0,0,0,197,0,209,0,0,0,0,0,0,70,0,0,0,0,143,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,104,0,0,0,0,0,157,0,0,167,210,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,114,0,0,0,157,0,128,0,255,121,276,216,0,336},
                {0,0,0,0,0,0,0,0,0,0,0,0,125,0,147,0,0,128,0,136,0,250,180,271,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,143,0,0,136,0,0,0,122,0,0,352},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,167,255,0,0,0,148,0,0,327,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,210,121,250,0,148,0,253,112,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,276,180,122,0,253,0,235,0,231},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,216,271,0,0,112,235,0,0,122},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,327,0,0,0,0,132},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,336,0,352,0,0,231,122,132,0}
        };

        int n = adjMat[0].length;
        int minDistArr[] = new int[n];

        boolean visited[] = new boolean[n];

        // Initialize all distances as INFINITE and visited[] as false
        for (int currIn = 0; currIn < n; currIn++) {
            minDistArr[currIn] = Integer.MAX_VALUE;
            visited[currIn] = false;
        }

        // Dist of start from itself = 0
        minDistArr[startVertex] = 0;

        // stores shortest path tree
        int parents[] = new int[n];

        // starting vertex has no parent
        parents[startVertex] = -1;

        // Find shortest path for all vertices
        for (int i = 1; i < n; i++) {

            // Choose min dist vertex from unvisited nearest vertices
            int nearestVertex = -1;
            int currMinDist = Integer.MAX_VALUE;
            for (int currIn = 0; currIn < n; currIn++) {
                if (visited[currIn] == false && minDistArr[currIn] < currMinDist) {
                    nearestVertex = currIn;
                    currMinDist = minDistArr[currIn];
                }
            }

            // Mark visited
            visited[nearestVertex] = true;

            // Update dist value of the adjacent vertices of the picked vertex.
            for (int currIn = 0; currIn < n; currIn++) {
                int edgeDist = adjMat[nearestVertex][currIn];
                if (edgeDist > 0 && ((currMinDist + edgeDist) < minDistArr[currIn])) {
                    parents[currIn] = nearestVertex;
                    minDistArr[currIn] = currMinDist + edgeDist;
                }
            }
        }
        int value=minDistArr[stop];
        printPath(stop, parents);

        return new Result(value,resL);
    }

    void printPath(int currentVertex,int[] parents){
        if (currentVertex == -1)
            return;
        printPath(parents[currentVertex], parents);
        resL.add(area.get(currentVertex));
    }
}
