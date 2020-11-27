
public class UseWightedGraph {

    public static void main (String args[]) {
    	int n = 6;
        WeightedGraphM G = new WeightedGraphM(n);
        G.addEdge(0,1,2);
        G.addEdge(0,5,9);
        G.addEdge(1,3,8);
        G.addEdge(1,2,6);
        G.addEdge(1,5,5);
        G.addEdge(2,3,1);
        G.addEdge(4,3,3);
        G.addEdge(4,2,7);
        G.addEdge(5,4,3);
        
        G.print();
        System.out.println();
        
  
        WeightedGraphM G1 = Algorithm.reachability(G);
        G1.print();
        System.out.println();

        WeightedGraphM G2 =  Algorithm.floyd(G);
        G2.print();
        System.out.println();

        int[] pred = Algorithm.dijkstra(G, 0);
        for (int k=0; k<n; k++) 
           Algorithm.printPath(G, pred, 0, k);
        
        G.addEdge(1,0,2);
        G.addEdge(5,0,9);
        G.addEdge(3,1,8);
        G.addEdge(2,1,6);
        G.addEdge(5,1,5);
        G.addEdge(3,2,1);
        G.addEdge(3,4,3);
        G.addEdge(2,4,7);
        G.addEdge(4,5,3);
        pred = Algorithm.prim(G, 0);
        for (int k=0; k<n; k++) 
           Algorithm.printPath(G, pred, 0, k);
        

        /*
        double[][] N = G.toMatrix();
        for (int i=0; i<N.length; i++) {
            for (int j=0; j<N.length; j++) 
            	if (N[i][j] < Double.MAX_VALUE)
            		System.out.print(N[i][j] + " ");
            	else
            		System.out.print("    ");
            System.out.println();
        }
       WeightedGraphV G3 = new WeightedGraphV(N);
        System.out.println("================");
       double[][] Q = G.toMatrix();
        WeightedGraphM G4 = new WeightedGraphM(N);
        G4.print();
        System.out.println();
        */
      
     }

}
