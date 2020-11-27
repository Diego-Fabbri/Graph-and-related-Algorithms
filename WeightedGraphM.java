
public class WeightedGraphM {
	/*RAPPRESENTAZIONE DI UN GRAFO PESATO CON LA MATRICE DI ADIACENZA CHE FA USO DI PUNTATORI(VERSIONE MIGLIORATA)
	 * n=numero di nodi del grafo pesato
	 * Si assume che i pesi possano avere valore positivo o negativo o zero
	 * VANTAGGIO: si supera il problema della precedente implementazioni per cui si assumeva che se un arco aveva peso nullo(uguale a zero) non esisteva . 
	 * Adesso un arco non esiste se una casella della matrice di adiacenza di puntatori punta ad un elemento null .
	 * NB: implicitamente si suppone che la numerazione dei nodi vada da zero a salire usando numeri interi positivi */
 
    private Double[][]  edges;  // adjacency matrix; creiamo un matrice di adiacenza in cui elementi sono dei puntatori ad elementi di tipo double
                               //NB: LA MATRICE HA DOUBLE CON D MAIUSCOLA CHE è LA CLASSE INVOLUCRO DEGLI ELEMENTI DI TIPO DOUBLE PER QUANTO DETTO SOPRA 
 
    public WeightedGraphM(int n) {//costruttore che crea matrice dei pesi del grafo prendendo in input il numero di nodi n del grafo
        edges  = new Double[n][n]; //tale matrice inizialmente è vuota
     }
     
    public WeightedGraphM(double[][] M) { //METODO DI CREAZIONE DELLA MATRICE DI ADIACENZA DEL GRAFO A PARTIRE DALLA MATRICE DI ADIACENZA PRESA IN INPUT
        edges  = new Double[M.length][M.length]; //la matrice degli archi  del grafo ha stessa dimensione della matrice di adicenza presa in input
        for (int i=0; i<M.length; i++)//con i cicli si scorre matrice di adiacenza
        	for (int j=0; j<M.length; j++)
        		if (M[i][j] < Double.MAX_VALUE && (i != j))
        			//se l'arco (i;j) ha peso positivo/negativo e non siamo ne caso i=j(si cercherebbe un arco che va da un nodo allo stesso nodo)
        			edges[i][j] = (Double)M[i][j];//la nuova matrice di adiacenza di puntatori avrà contenuti uguali alla vecchia che presentava dei limiti
     }
     
    public double[][] toMatrix() {//METODO DI CONVERSIONE DI UN GRAFO PESATO IN UN MATRICE DI REALI
    	int n = edges.length; //salviamo in numero n di nodi del grafo considerato
    	double[][] M = new double[n][n]; //creiamo una matrice di adiacenza di dimensione nxn
    	for (int i=0; i<n; i++)//con i due cicli scorriamo la matrice
    		for (int j=0; j<n; j++)
    			if (edges[i][j] != null)//se esiste arco (i,j) con un peso non nullo 
    				M[i][j] = edges[i][j];//l'elemento in posizione (i,j) è pari proprio al peso dell'arco (i,j)
    			else//se arco (i,j) non esiste
    				M[i][j] = Double.MAX_VALUE;// il peso si setta con il massimo numero che si può esprimere con un intero (idealmente un infinito)
    	for (int i=0; i<n; i++)//si scorre la diagonale principale della matrice
			M[i][i] = 0;//gli archi che vanno dal nodo i al nodo i non hanno peso 
    	return M;
    }
    /*COMPLESSITA' DEL METODO toMatrix()
     * O(n^2)=complessità spaziale e temporale poichè si scandisce una matrice quadrata nxn e si crea tale matrice di dimensione nxn */

    public double[][] toBooleanMatrix() {//METODO DI CREAZIONE DI UNA MATRICE DI ADIACENZA DI BIT (SENZA ESPRESSIONE DEI PESI)
    	int n = edges.length;//salviamo in numero n di nodi del grafo considerato
    	double[][] M = new double[n][n];//creiamo una matrice di adiacenza di dimensione nxn
    	for (int i=0; i<n; i++)//con i due cicli scorriamo la matrice
    		for (int j=0; j<n; j++)
    			if (edges[i][j] != null)//se esiste arco (i,j),ovvero ha peso non null , si imposta elemento corrispondente nella matrice uguale ad 1
    				M[i][j] = 1;
    			else//se non esiste arco (i,j) si imposta elemento corrispondente nella matrice uguale ad 0
    				M[i][j] = 0;
    	return M;
    }
    /*COMPLESSITA' DEL METODO toBooleanMatrix()
     * O(nxn)=complessità spaziale e temporale poichè si scandisce una matrice quadrata nxn e si crea tale matrice di dimensione nxn */

    public int size() { return edges.length; }// ci restitusce la misura del grafo intesa come numero di nodi presente ovvero la dimensione della matrice di adiacenza
 
    public boolean isEdge (int source, int target)  { //METODO DI VERIFICA DELL'ESISTENZA DI UN ARCO NODO SORGENTE E NODO DESTINAZIONE
		if(edges[source][target] != null) //se nella matrice l'elemento corrispondente all'arco preso in input non punto ad un peso null
			return true;//arco esiste e si restitusce true
		else//se punta ad un peso null , arco non esiste
			return false;
	}

   public void addEdge(int s, int t, double w)  { //METODO DI AGGIUNTA DI UN ARCO (s,t) DI PESO w
    	if (isEdge(s,t))//se già arco esiste
    		System.out.println("Arco esistente - Operazione add(" + s + "," + t + "," + w + ") non eseguita");
    	else//se arco non esiste
    		edges[s][t] = new Double(w);//si fa puntare elemento della matrice in posizione (s,t) al valore w 
    }
    
    public void removeEdge(int source, int target)  { //METODO DI RIMOZIONE DI UN ARCO (source,target) 
    		edges[source][target] = null; // si svuota la casella
    }
    
    public double getWeight(int s, int t)  {  //METODO DI RESTITUZIONE DEL PESO  DI UN ARCO (s,t) 
    	if (isEdge(s,t))// se arco esiste
    		return edges[s][t]; 
    	else {// se arco non esiste
    		System.out.println("Arco non esistente - Operazione getWeight(" + s + "," + t + ") non eseguita");
    		return 0;//valore del peso in caso di arco inesistente
		}
	}

    public void setWeight(int s, int t, double w)  { //METODO DI MODIFICA DEL PESO w DI UN ARCO (s,t) 
    	if (isEdge(s,t))//se arco esiste
    		edges[s][t] = new Double(w); //si modifica oggetto puntato da elemento della casella della matrice
    	else //se arco non esiste
    		System.out.println("Arco non esistente - Operazione setWeight(" + s + "," + t + "," + w + ") non eseguita");
	}

    public int[] neighbors(int vertex) {//METODO DI RESTITUZIONE DEI NODI ADIACENTI AD UN NODO PRESO IN INPUT
       int count = 0;//si crea una variabile contatore per contare il numero di elementi adiacenti(archi in uscita dal nodo vertex)
       for (int i=0; i<edges[vertex].length; i++) {//si scorre la riga del nodo vertex nella matrice
          if (edges[vertex][i] != null) // se in tale riga ci sono elementi non  null , vuol dire che esiste un nodo adiacente
        	  	count++;//si incrementa contatore
       }
      
       System.out.println(vertex + ") " + count);
       int[] answer = new int[count];//si crea un vettore contenente la risposta cercata,lista dei nodi adiacenti,di dimensione pari al numero di adiacenze trovare con contatore
       count = 0;// si azzera contatore
       for (int i=0; i<edges[vertex].length; i++) {//si scorre di nuovo la riga del nodo vertex nella matrice
          if (edges[vertex][i] != null) // se in tale riga ci sono elementi non  null , vuol dire che esiste un nodo adiacente
        	  	answer[count++] = i; // si copia nel vettore risposta il numero i del nodo 
       }
       return answer;
    }
    
    /*COMPLESSITA' METODO neighbors(int vertex)
     * O(n)=temporale. Si scandisce una riga della matrice per due volte creando un array(vettore adiacenze) a dimensione crescente.Nel caso peggiore avrà n-1 elementi
     * (ovvero caso in cui nodo vertex è collegato a tutti gli altri n-1 nodi del grafo con un arco )
     * O(1)= complessità spaziale */
 
    public void print () {//METODO DI STAMPA DELLA MATRICE DI ADIACENZA
       for (int i=0; i<edges.length; i++) {//si scorrono le righe
           System.out.print(i + ") |");
           //* System.out.print(labels[j] + ": ");
          for (int j=0; j<edges[i].length; j++) // si scorrono le colonne 
        	  System.out.printf("%3.0f |", edges[i][j]);
          System.out.println();
       }
    }

}
