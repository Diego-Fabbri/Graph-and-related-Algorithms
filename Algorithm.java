import java.util.*;

public class Algorithm {
                                                                   /*-------------ALGORITMI SUI GRAFI---------------*/
	
	//PROBLEMA DELLA RAGGIUNGIBILITA'
	/*Dato un grafo orientato G = (V, E) e un nodo assegnato s, determinare i nodi raggiungibili da s attraverso un cammino.
	 * CONSIDERAZIONE: Ogni nodo preso in considerazione ad ogni iterazione viene considerato come un nodo intermedio per un possibile cammino
	 * IPOTESI: si suppone che esista arco (i;j) quando i=j. Ovvero che esista un arco che va ad un nodo al nodo stesso . Si pone 1 nella matrice di raggiungibilità
	 * NB:La matrice di raggiungibilità ci dice esistenza di archi e anche di cammini possibili da un nodo ad un altro senza che ci sia il bisogno di un arco diretto*/
	// Transitive closure graph(Chiusura Transitiva del Grafo)
	public static WeightedGraphM reachability(WeightedGraphM G) {//METODO CREAZIONE MATRICE DI RAGGIUNGIBILITA'
		int n = G.size();//si salva il numero n di nodi del grafo in un intero prendendolo dalla dimensione della matrice di adiacenza in input
		WeightedGraphM M = new WeightedGraphM(n);//MATRICE DI BIT DI RAGGIUNGIBILITA':si crea una matrice di puntatori di dimensione n
		//si copiano gli archi già esistenti nella nuova matrice
		for (int i=0; i<n; i++)//ciclo che servono a scorrere la matrice di adiacenza in input
			for (int j=0; j<n; j++)
				if (G.isEdge(i,j))//se arco (i;j) esiste 
					M.addEdge(i,j,1);//allora nella matrice di raggiungibilità si aggiunge al corrispondente elemnto (i,j) il valore 1 che ne indica esistenza 
		//adesso si verifica esistenza di cammini usando nodi intermedi
		for (int k=0; k<n; k++) {//indice che punta al nodo intermedio di cui si vuol creare cammino
			for (int i=0; i<n; i++)//indice che punta al nodo sorgente di cui si vuol creare cammino
				for (int j=0; j<n; j++)//indice che punta al nodo destinazione di cui si vuol creare cammino
					if (M.isEdge(i,k) && M.isEdge(k,j) && !M.isEdge(i,j))
                    //se esite arco (i,k) e esiste arco (k,j) e non esiste già un arco (i,j)
						M.addEdge(i,j,1);
			        //allora esite un cammino che va dal nodo i al nodo j passando per k e lo si aggiunge nella matrice di raggiungibilità in posizione (i,j) con valore 1
		}
		return M;
	}

	public static WeightedGraphM reachability1(WeightedGraphM G) {
		int n = G.size();//si salva il numero n di nodi del grafo in un intero prendendolo dalla dimensione della matrice di adiacenza in input
		double[][] M = G.toBooleanMatrix();//METODO DI CREAZIONE DI UNA MATRICE DI ADIACENZA DI BIT (SENZA ESPRESSIONE DEI PESI)
		
		for (int k=0; k<n; k++) //indice che punta al nodo intermedio di cui si vuol creare cammino
			for (int i=0; i<n; i++)//indice che punta al nodo sorgente di cui si vuol creare cammino 
				for (int j=0; j<n; j++)//indice che punta al nodo destinazione di cui si vuol creare cammino
					if (M[i][k] == 1 && M[k][j] == 1)//se esite arco/cammino (i,k) e esiste arco/cammino (k,j) 
						M[i][j] = 1;
		//allora esite un cammino che va dal nodo i al nodo j passando per k e lo si aggiunge nella matrice di raggiungibilità in posizione (i,j) con valore 1
		return new WeightedGraphM(M);//si restituisce la matrice come una matrice di puntatori
	}
/*COMPLESSITA' DEL METODO reachability1(WeightedGraphM G)
 * O(n^3)= complessità temporale dovuta all'esistenza di tre cicli for innestati.*/
	
	//PROBLEMA DELLA RAGGIUNGIBILITA'
	// Floyd-Warshall algorithm relativo a problema della raggiungibilità
	/*Metodo che calcola il cammino di peso minimo da tutti i nodi del grafo a tutti gli altri nodi
	 * NB:funziona con archi di peso <0 , ma non con grafi che abbiano cicli il cui peso complessivo sia <0*/
    public static WeightedGraphM floyd(WeightedGraphM G) {
    	double[][] M = G.toMatrix();//METODO DI CREAZIONE DI UNA MATRICE DI ADIACENZA DI BIT (SENZA ESPRESSIONE DEI PESI)
    	//NB: in questo caso un arco (i,j) dove i=j risulta tale che M[i][j]=0 
    	for (int k=0; k<M.length; k++) {//indice che punta al nodo intermedio di cui si vuol creare cammino
    		for (int i=0; i<M.length; i++)//indice che punta al nodo sorgente di cui si vuol creare cammino
    			for (int j=0; j<M.length; j++)//indice che punta al nodo destinazione di cui si vuol creare cammino
    				//se nelle varie scansioni
    				if (M[i][k] + M[k][j] < M[i][j])//si tra un cammino(i,k,j) di peso minore del cammino/arco esistente (i,j)
    					M[i][j] = M[i][k] + M[k][j];//si aggiorna il peso del cammino (i,j) con il minor peso del cammino(i,k,j) dato dalla somma dei pesi dei cammini/archi (i,k)+(k,j)
    	
    	}
    	
    	WeightedGraphM G1 = new WeightedGraphM(M);//si crea una matrice di puntatori 
    	return G1;//si restituisce la matrice come una matrice di puntatori
    }
    /*COMPLESSITA' DEL METODO floyd(WeightedGraphM G)
    * O(n^3)= complessità temporale dovuta all'esistenza di tre cicli for innestati.*/
    
    //Graph to matrix

                                                             //-----------------ALGORITMO DI DIJKSTRA----------------------//
                                                                    /*IMPLEMENTAZIONE ALGORITMO CON USO DI ARRAY*/
    // con tale algoritmo dato un grafo pesato G di puntatori e un nodo sorgente source si trova il cammino di costo minimo per giugere dal nodo sorgente a tutti altri nodi del grafo
    //NB: il limite di tale implementazione è che non ammette archi il cui peso/distanza sia negativo o uguale a zero.
    public static int[] dijkstra(WeightedGraphM G, int source) {
    double[]  dist    = new double[G.size()];   // vettore delle distanze minime la cui misura è pari proprio al numero di nodi del grafo come la matrice G in input
    int[]     pred    = new int[G.size()];      // vettore dei nodi predecessori nel cammino la cui misura è pari proprio al numero di nodi del grafo come la matrice G in input
    boolean[] visited = new boolean [G.size()]; //vettore dei nodi visitati inizialmente popolato di soli FALSE la cui misura è pari proprio al numero di nodi del grafo come la matrice G in input
 
       for (int i=0; i<dist.length; i++) {//inizialmente il vettore delle distanze è popolato con tutti valori infinito
          dist[i] = Double.MAX_VALUE;//in java si pone con il massimo numero esprimibili con un double della classe involucro Double
       }
       dist[source] = 0;//la distanza dal nodo origine al nodo origine è chiaramente zero
       pred[source] = -1;//il predecessore del nodo sorgente non c'è . Si imposta quindi a -1 che non porterà confusione con numerazione dei nodi
      //ciclo principale che ingloba tutto il resto
       for (int i=0; i<dist.length; i++) {// si scorre vettore delle distanze per generare il cammino di costo minimo 
          int next = minVertex(dist, visited);// si salva il nodo di costo minimo che si vuole visitare ad una generica iterazione e in seguito se ne valutano adiacenti
          visited[next] = true;//una volta considerato il vertice di costo minimo lo si visita quindi si aggiorna il vettore dei visitati nella relativa posizione
 
          // The shortest path to next is dist[next] and via pred[next].
          //il cammino minimo per raggiungee il nodo a costo minimo(next) è la distanza dal nodo sorgente al nodo di costo minimo (next) 
          int[] n = G.neighbors(next);// si crea il vettore che restituisce i nodi adiacenti del nodo di costo minimo (next)
          
          for (int j=0; j<n.length; j++) {//adesso si scorre il vettore n dei nodi adiacenti al nodo di costo minimo next che si sta visitando
             int v = n[j];//si salva il nodo adiacente ad ogni iterazione con un intero
             double d = dist[next] + G.getWeight(next,v);// si salva la distanza dal nodo sorgente al nodo considerato nel vettore dei vicini
                                                        //come somma delle distanza per giungere al nodo di costo minimo next che stiamo visitando più la distanza(peso) tra nodo di costo minimo visitato
                                                       // e suo adiacente considerato ad ogni iterazione
             
             if (dist[v] > d) {//se la nuova distanza tra nodo sorgente e nodo adiacente del nodo next visitato è maggiore di d (nuova distanza calcolata)
                dist[v] = d;//si aggiorna il vettore delle distanze ponendo che la distanza al nodo adiacente (di next)  v considerato è pari a d (nuova distanza più piccola)
                pred[v] = next;//si aggiorna il vettore delle precedenze dicendo che il precendente del nodo adiacente v considerato è il nodo di costo minimo next
             }
          }
       }
       //usciti dal ciclo principale si è trovato il vettore che esprime il cammino minimo ad ogni nodo
       return pred;  //lo si restituisce
    }
    /*COMPLESSITA METODO dijkstra(WeightedGraphM G, int source)
     * NB:si considerano solo i metodi più significativi al netto delle operazioni il cui costo e costante O(1).
     * 
     * T=complessità temporale= T( primo ciclo i)+ T( secondo ciclo i ovvero for principale)x[T(minVertex) + T(neighbors) +T(ciclo j)]
     *                        =       n          +       n                                  x(   n         +      n       +   n    ) =O(n^2) */
 
    
                                                       /*--------------------PROBLEMA DEL MINIMO ALBERO RICOPRENTE----------------------*/
    //NB: Tale algortimo vale per grafi non orientati la cui matrice di adiacenza è simmetrica.
    //SI vuole determinare il minimo albero ricoprente di un grafo non orientato G prtendo da un nodo radice s.
    //CONSIDERAZIONE: tale algoritmo è identico ad algoritmo Dijkstra tranne per il rigo #### e il significato del vettore dist ,
    //che misura la distanza da un nodo ad un altro nodo assegnando il peso dell'arco
    // Prim's algorithm to find a minimum spanning tree with root "source"
    public static int[] prim(WeightedGraphM G, int source) {
    	double[]  dist    = new double[G.size()];  // shortest known distance from "s"
        int[]     pred    = new int[G.size()];  // preceeding node in path
        boolean[] visited = new boolean [G.size()]; // all false initially
 
        for (int i=0; i<dist.length; i++) {
            dist[i] = Double.MAX_VALUE;
       }
       dist[source] = 0;
       pred[source] = -1;
 
       for (int i=0; i<dist.length; i++) {
          int next = minVertex(dist, visited);
          visited[next] = true;
          int[] n = G.neighbors(next);
          for (int j=0; j<n.length; j++) {
              int v = n[j];
           // double d = dist[next] + G.getWeight(next,v); riga relativa ad algoritmo Dijkstra
     /*###*/  double d = G.getWeight(next,v);//non si ha più la distanza/peso dal nodo sorgente al nodo next(dist[next]) sommata alla distanza del nodo next con il nodo v (G.getWeight(next,v))
                                             //ma si ha distanza/peso dal nodo next al nodo v
             if (dist[v] > d && !visited[v]) {
                dist[v] = d;
                pred[v] = next;
             }
          }
       }
       return pred;  
	}
    /*COMPLESSITA METODO prim(WeightedGraphM G, int source)
     * NB:si considerano solo i metodi più significativi al netto delle operazioni il cui costo e costante O(1).
     * 
     * T=complessità temporale= T( primo ciclo i)+ T( secondo ciclo i ovvero for principale)x[T(minVertex) + T(neighbors) +T(ciclo j)]
     *                        =       n          +       n                                  x(   n         +      n       +   n    ) =O(n^2) */

    private static int minVertex(double[] dist, boolean[] v) {//METODO DI RICERCA DEL NODO DI COSTO MINIMO
       double x = Double.MAX_VALUE;//serve a riconoscere quei nodi non ancora etichettati che hanno nel vettore delle distanze casella a infinito
       int y = -1;   // graph not connected, or no unvisited vertices, serve a salvare il vertice di costo minimo si setta a -1 come valore iniziale 
       // con il ciclo seguente si scandiscono i vettori delle distanze e dei visitati presi in input al fine di trovare il nodo y di costo(dist) minimo
       //ad ogni iterazione il nodo e il costo si aggiornano
       for (int i=0; i<dist.length; i++) {// si scorre vettore delle distanze
          if (!v[i] && dist[i]<x) {// se elemento in posizione i del vettore dei visitati v è false( nodo i non visitato) e la distanza al nodo i è minore di x
        	     
        	  y=i;//si salva il nodo i 
        	     
        	
        	  x=dist[i];//si aggiorna la distanza
        	  }
       }
       return y;// si restituisce il nodo o vertice di costo minimo
    }
 /*COMPLESSITA' DEL METODO minVertex(double[] dist, boolean[] v)
  * 0(n)=complessità temporale poichè si deve scandire intero vettore
  * O(1)=complessità spaziale*/
    public static void printPath(WeightedGraphM G, int[] pred, int s, int e){//METODO DI STAMPA DEL CAMMINO DAL NODO s AL NODO e CONSIDERANDO IL VETTORE DEI PRECEDENTI pred ED IL GRAFO PESATO G
    	// source s, end e
    	   String path = "";
    	   int x = e;
       while (x!=s) {
          path = x + " " + path;
          x = pred[x];
       }
       path = s + " " + path;
       System.out.println(path);
    }
	
}
