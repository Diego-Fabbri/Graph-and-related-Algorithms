import java.util.Vector;

public class WeightedGraphV {
	/*RAPPRESENTAZIONE DI UN GRAFO PESATO CON LE LISTE DI ADIACENZA USANDO VECTOR*/
	private class Vettore {
		private Vector<Double> row; 
		
		private Vettore() {
			row = new Vector<Double>();
		}
	}

    private Vettore[]  edges;  // adjacency matrix
    
    public WeightedGraphV(int n) {
        edges  = new Vettore[n];
        for (int i=0; i<n; i++) 
        	edges[i].row = new Vector<Double>(n);
     }
     
    public WeightedGraphV(double[][] M) {
        edges  = new Vettore[M.length];
        System.out.println("---------------");
        for (int i=0; i<M.length; i++) {
        	edges[i].row = new Vector<Double>(M.length);
        	for (int j=0; j<M.length; j++)
        		if (M[i][j] < Double.MAX_VALUE && (i != j)) {
                	System.out.println(">> " + i + " " + j + " " + M[i][j]);
                	edges[i].row.set(j, (Double)M[i][j] );
      		}
        	System.out.println("> " + i);
        }
     }
     
    public double[][] toMatrix() {
    	int n = edges.length;
    	double[][] M = new double[n][n];
    	for (int i=0; i<n; i++)
    		for (int j=0; j<n; j++) 
    			if (edges[i].row.elementAt(j) != null)
    				M[i][j] = edges[i].row.elementAt(j);
    			else
    				M[i][j] = Double.MAX_VALUE;
    	for (int i=0; i<n; i++)
			M[i][i] = 0;
    	return M;
    }

    public double[][] toBooleanMatrix() {
    	int n = edges.length;
    	double[][] M = new double[n][n];
    	for (int i=0; i<n; i++)
    		for (int j=0; j<n; j++)
    			if (edges[i].row.elementAt(j) != null)
    				M[i][j] = 1;
    			else
    				M[i][j] = 0;
    	return M;
    }

    public int size() { return edges.length; }
 
    public boolean isEdge (int source, int target)  { 
		return (edges[source].row.elementAt(target) != null); 
	}

   public void addEdge(int s, int t, double w)  { 
    	if (isEdge(s,t))
    		System.out.println("Arco esistente - Operazione add(" + s + "," + t + "," + w + ") non eseguita");
    	else
    		edges[s].row.set(t, new Double(w)); 		
    }
    
    public void removeEdge(int source, int target)  { 
    		edges[source].row.set(target, null); 
    }
    
    public double getWeight(int s, int t)  { 
    	if (isEdge(s,t))
    		return edges[s].row.elementAt(t); 
    	else {
    		System.out.println("Arco non esistente - Operazione getWeight(" + s + "," + t + ") non eseguita");
    		return 0;
		}
	}

    public void setWeight(int s, int t, double w)  { 
    	if (isEdge(s,t))
    		edges[s].row.set(t, new Double(w)); 
    	else 
    		System.out.println("Arco non esistente - Operazione setWeight(" + s + "," + t + "," + w + ") non eseguita");
	}

    public int[] neighbors(int vertex) {
       int count = edges[vertex].row.size();
       int[] answer = new int[count];
       for (int i=0; i<edges.length; i++) {
          if (edges[vertex].row.elementAt(i) != null) 
        	  	answer[count++] = i;
       }
      return answer;
    }
 
    public void print () {
       for (int i=0; i<edges.length; i++) {
           System.out.print(i + ") |");
           //* System.out.print(labels[j] + ": ");
          for (int j=0; j<edges.length; j++) 
        	  System.out.printf("%3.0f |", edges[i].row.elementAt(j));
          System.out.println();
       }
    }

}
