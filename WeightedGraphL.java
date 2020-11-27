
public class WeightedGraphL {
	/*RAPPRESENTAZIONE DI UN GRAFO PESATO CON LE LISTE DI ADIACENZA
	 * n=numero di nodi del grafo pesato
	 * Si assume che i pesi possano avere valore positivo o negativo ma non nullo (ovvero zero)
	 *  NB: implicitamente si suppone che la numerazione dei nodi vada da zero a salire usando numeri interi positivi*/
 
    private Node[]  edges;  //adjacency matrix
                           //questo vettore di nodi punta alla matrice di adiacenza
 
    public WeightedGraphL(int n) {//costruttore che crea il vettore dei nodi del grafo prendendo in input il numero di nodi n del grafo
        edges  = new Node[n];    //tale vettore inizialmente è vuoto
     }
      
    public WeightedGraphL(double[][] M) {//METODO DI CREAZIONE DELLA LISTA DI ADIACENZA DEL GRAFO A PARTIRE DALLA MATRICE DI ADIACENZA PRESA IN INPUT
        edges  = new Node[M.length];    //il vettore dei nodi del grafo ha stessa dimensione della matrice di adicenza ovvero n
        for (int i=0; i<M.length; i++) //scorre righe matrice di adiacenza
        	for (int j=M.length-1; j>=0; j--)//scorre colonne matrice di adiacenza partendo dal nodo adiacente con indice più grande a scendere al più piccolo
        		                            //ciò serve a far si che la lista di adiacenza del nodo sorgente edges[i] sia ordinata in modo che adeacenti siano in ordine crescente per numero di nodo
        		                           //e per far si che inserimento avvenga in testa alla lista e non in coda al fine di ottimizzare la complessità dell'inserimento che sarà costante senza dover
        		                          //scandire la lista relativa al nodo sorgente
        		if (M[i][j] < Double.MAX_VALUE && (i != j))
        			//se l'arco (i;j) ha peso positivo/negativo e non siamo ne caso i=j(si cercherebbe un arco che va da un nodo allo stesso nodo)
        			edges[i] = new Node(j, M[i][j], edges[i]);	//al vettore della lista di adiacenza in posizione i (nodo preso in considerazione come sorgente) si aggiunge un nodo che ha come
                                                               //destinazione il nodo j , peso M[i][j] espresso nella matrice , e successivo il nodo l'elemento in posizione i del vettore
                                                              //(quest'ultima operazione serve a creare collegamento e fare aggiunzione in testa e creare lista di adiacenza).
     }
    
    
     
    public double[][] toMatrix() {//METODO DI CONVERSIONE DI UN GRAFO PESATO IN UN MATRICE DI REALI
    	int n = edges.length;            //salviamo in numero n di nodi del grafo considerato
    	double[][] M = new double[n][n]; //creiamo una matrice di adiacenza di dimensione nxn
    	for (int i=0; i<n; i++)//con i due cicli scorriamo la matrice
    		for (int j=0; j<n; j++)
    			if (isEdge(i,j))//se esiste arco (i,j)
    				M[i][j] = getWeight(i,j);//l'elemento in posizione (i,j) è pari proprio al peso dell'arco (i,j)
    			else//se arco (i,j) non esiste
    				M[i][j] = Double.MAX_VALUE;// il peso si setta con il massimo numero che si può esprimere con un intero (idealmente un infinito)
    	for (int i=0; i<n; i++)//si scorre la diagonale principale della matrice
			M[i][i] = 0;//gli archi che vanno dal nodo i al nodo i non hanno peso 
    	return M;//si restituisce la matrice
    }
    /*COMPLESSITA' DEL METODO toMatrix()
     * O(nxn)=complessità spaziale e temporale poichè si scandisce una matrice quadrata nxn e si crea tale matrice di dimensione nxn */

    public double[][] toBooleanMatrix() {  //METODO DI CREAZIONE DI UNA MATRICE DI ADIACENZA DI BIT (SENZA ESPRESSIONE DEI PESI)
    	int n = edges.length;             //salviamo in numero n di nodi del grafo considerato
    	double[][] M = new double[n][n]; //creiamo una matrice di adiacenza di dimensione nxn
    	for (int i=0; i<n; i++)         //con i due cicli scorriamo la matrice
    		for (int j=0; j<n; j++)
    			if (isEdge(i,j)) //se esiste arco (i,j) si imposta elemento corrispondente nella matrice uguale ad 1
    				M[i][j] = 1;
    			else     //se non esiste arco (i,j) si imposta elemento corrispondente nella matrice uguale ad 0
    				M[i][j] = 0;
     	return M; //restituiamo la matrice di bit
    }
    /*COMPLESSITA' DEL METODO toBooleanMatrix()
     * O(nxn)=complessità spaziale e temporale poichè si scandisce una matrice quadrata nxn e si crea tale matrice di dimensione nxn */
    
    public int size() { return edges.length; }// ci restitusce la misura del grafo intesa come numero di nodi presente ovvero la dimensione del vettore della lista di adiacenza
 
    public boolean isEdge(int s, int t)  { //METODO DI VERIFICA DELL'ESISTENZA DI UN ARCO PRENDENDO IN INPUT NODO SORGENTE E NODO DESTINAZIONE
    	                                  //s=sorgente e t=destinazione(target)
    	if (edges[s] == null)//se elemento in posizione s del vettore della lista è null---->il nodo non esiste e quindi neanche l'arco
    		return false;
    	else//se elemento in posizione s del vettore della lista non è null---->il nodo esiste e quindi forse anche l'arco (s;t)
    	Node n = edges[s];// si crea un nodo che punta al nodo sorgente e si scorrerà la relativa lista di adiacenza
    	//con il ciclo si scorre la lista di nodi adiacenti partendo dall'elemento in posizione s del vettore
		while (n != null)//fino a quando il nodo è diverso da null si scorre la lista
			if (n.getNode() != t) {//fino a quando il nodo è diverso dal nodo target si scorre la lista
				n = n.getNext();//puntando al successivo
			                }
			else// nel caso in cui n.getNode()==t , arco esiste 
				return true;//si restiusce true
		
		return false;//usciti dal ciclo vuol dire che non si è trovati un nodo di destinazione t nella lista allora il nodo s non ha come adiacente il nodo t quindi arco non esiste
	}
/*COMPLESSITA' del METODO isEdge(int s, int t)
 * Nel caso peggiore in cui arco abbia il collegamento agli altri n-1 nodi , puntando al nodo sorgente presente nel vettore della lista di adiacenze, si dovrà scorrere tutta la relativa lista di nodi 
 * successivi. Questo accade quando arco(s,t) non esiste o è in ultima posizione. Quindi 0(n).*/
    public void addEdge(int s, int t, double w)  { //METODO DI AGGIUNTA DI UN ARCO (s,t) DI PESO w
    	//NB:si dispone la lista degli adiacenti in modo che tali nodi siano disposti in modo crescente
    	if (isEdge(s,t))//se già arco esiste
    		System.out.println("Arco esistente - Operazione add(" + s + "," + t + "," + w + ") non eseguita");
    	else {//se arco non esiste
    		//(caso in cui t<s)
    		if (edges[s] == null || t < edges[s].getNode())//se nodo s non esiste nel vettore oppure se il nodo destinazione t è minore del nodo sorgente s
    			edges[s] = new Node(t,w,edges[s]);//il nuovo nodo sorgente in poszione s del vettore sarà un nodo di contenuto t , peso w e successivo il vecchio nodo sorgente s
    		                                     //NB:così facendo si sposta il vecchio nodo sorgente a destra e il nuovo nodo sorgente nel vettore è t
    		//(caso in cui s<t)
    		else  {// si deve scandire la lista usando due puntatori p e c
    			Node p = edges[s];//puntatore che parte da elemento in posizione s del vettore
    			Node c = edges[s].getNext();////puntatore che parte da elemento subito successivo a elemento in posizione s del vettore(punta al primo adiacente del nodo s)
    			while (c != null && c.getNode() < t) {//fino a quando c punta ad un nodo non null e il valore del nodo puntato è minore di t si continua a scandire la lista aggiornando i puntatori
    				 p = c;//p si muove verso destra puntando a c
    				 c = c.getNext();//c si muove verso destra puntando al suo successivo 
    			}
    			//usciti dal cilo si effettua inserimento all'interno della lista oppure in coda
    			p.setNext(new Node(t,w,c));
    		}
    	}
	}
    /*COMPLESSITA' DEL METODO addEdge(int s, int t)
     * O(n)= nel caso peggiore in cui arco da aggiungere vada in ultima posizione e che il nodo s sia collegato ad altri n-1 nodi del grafo, si deve scorrere tutta la lista di adiacenza di n-1 elementi. */
    
     public void removeEdge(int s, int t)  { //METODO DI RIMOZIONE DI UN ARCO (s,t)
		Node n = edges[s];//si crea un nodo che salva il nodo sorgente puntandogli
		if (edges[s] == null)//se il nodo sorgente è null
			return ;
		if (edges[s] != null && n.getNode() == t) {//(caso s=t)se il nodo sogente a cui si punta è non null e il suo contenuto è uguale a t 
			edges[s] = edges[s].getNext();
			return;
		}
		else//(s!=t)se il nodo sogente a cui si punta è non null si deve scorrere la relativa lista di adiacenza
			
		while (n.getNext() != null & n.getNext().getNode() != t)// fino a quando il puntatore che parte dal nodo sorgente punta ad un nodo non null il cui contenuto è diverso da quello cercato
			n = n.getNext();//si aggiorna il puntatore facendo muovere a destra
		if (n.getNext() != null && n.getNext().getNode() == t)//se scorrendo si trova un adiacente non null in cui il contenuto del successivo coincide con quello cercato t
			n.setNext(n.getNext().getNext());//si aggiorna il successivo del nodo puntato n , creando collegamento con il successivo del successivo di n. Così facendo si effettua eliminazione
    }
   /*COMPLESSITA' DEL METODO removeEdge(int s, int t)
    * O(n)= nel caso peggiore in cui arco da rimuovere non sia presenta e che il nodo s sia collegato ad altri n-1 nodi del grafo, si deve scorrere tutta la lista di adiacenza di n-1 elementi. */
    
    public double getWeight(int s, int t)  { //METODO DI RESTITUZIONE DEL PESO  DI UN ARCO (s,t) 
    		Node n = edges[s];//si crea un nodo che salva il nodo sorgente
    		//si deve scandire lista di adiacenza del nodo sorgente s
    		while (n != null & n.getNode() != t)// fino a quando nodo puntato è non null e il suo adiacente è diverso da t
    			n = n.getNext();// si scorre in avanti la lista verso destra aggiornando puntatore al successivo
    		if (n != null && n.getNode() == t)//se il nodo n puntato è non null ed nodo puntato è proprio quello dell'arco da esaminare
    			return n.getWeight(); // si restituisce il peso
        	else {
        		System.out.println("Arco non esistente - Operazione getWeight(" + s + "," + t + ") non eseguita");
        		return 0;//peso di un arco non esistente
    		}
    	}
    /*COMPLESSITA' DEL METODO getWeight(int s, int t)
     * O(n)= nel caso peggiore in cui arco da cercare non sia presenta e che il nodo s sia collegato ad altri n-1 nodi del grafo, si deve scorrere tutta la lista di adiacenza di n-1 elementi. */
     
 
    public void setWeight(int s, int t, double w)  { //METODO DI MODIFICA DEL PESO w DI UN ARCO (s,t) 
    	if (isEdge(s,t)) {//se arco esiste
    		Node n = edges[s];//si punta con un nodo al nodo sorgete s nel vettore
    		while (n != null & n.getNode() != t)//fino a quando il nodo puntato è non null e il suo nodo destinazione non corrisponde a quello cercato 
    			n = n.getNext();//si scorre lista di adiacenza spostando a destra il puntatore
    		//usciti dal cilo piò accadere che
    		
    		//si trova arco cercato
    		 n.setWight(w);//si modifica il peso
    	}
    	else //non si trova arco cercato
    		System.out.println("Arco non esistente - Operazione setWeight(" + s + "," + t + "," + w + ") non eseguita");
	}
    /*COMPLESSITA' DEL METODO setWeight(int s, int t)
     * O(n)= nel caso peggiore in cui arco da cercare sia presenta e che il nodo s sia collegato ad altri n-1 nodi del grafo, si deve scorrere tutta la lista di adiacenza di n-1 elementi. */

    public int[] neighbors(int vertex) {//METODO DI RESTITUZIONE DEI NODI ADIACENTI AD UN NODO PRESO IN INPUT
       int count = 0;//si crea una variabile contatore per contare il numero di elementi adiacenti(archi in uscita dal nodo vertex)
       Node n = edges[vertex];//si punta con un nodo n elemento vertex nel vettore
       //si conta il numero di nodi adiacenti scandendo la lista relativa del nodo sorgente a cui si è puntato
       while (n != null) {//fino a quando elemento puntato è non null
    	   	  count++;//si incrementa contatore
    	   	  n = n.getNext();//si aggiorna il puntatore facendolo spostare verso destra
       }
       //usciti dal ciclo
       int[] answer = new int[count];//si crea un vettore la cui dimensione è pari proprio al numero di adiacenze contate 
       count = 0;//si azzera il contatore
       n = edges[vertex];//si punta con un nodo n elemento vertex nel vettore
     //si salvano i nodi adiacenti scandendo la lista nel vettore creato
       while (n != null) {//fino a quando elemento puntato è non null
    	  answer[count++] = n.getNode();//si copia nodo adiacente nel vettore creato 
       	  n = n.getNext();// si sposta in avanti il puntatore
       }
       return answer;
    }
    /*COMPLESSITA' METODO neighbors(int vertex)
     * O(n)=temporale. Si scandisce una riga della matrice per due volte creando un array(vettore adiacenze) a dimensione crescente.Nel caso peggiore avrà n-1 elementi
     * (ovvero caso in cui nodo vertex è collegato a tutti gli altri n-1 nodi del grafo con un arco )
     * O(1)= complessità spaziale */
    public void print() {//METODO DI STAMPA DELLA LISTA DI ADIACENZA
       for (int i=0; i<edges.length; i++) {//ciclo con cui si scorre il vettore dei nodi 
           System.out.print(i + ") |");
           Node n = edges[i];//con un nodo si punta ad ogni elementi del vettore
	       for (int j=0; j<edges.length; j++) {//si scandisce la lista di adiacenze dell'elemento i del vettore dei nodi 
	    	   if (n != null) {//se elemento puntato nella lista è non null
	    		   if (n.getNode() == j) {//il nodo destinazione del nodo puntato è pari a j
	    			   // System.out.print( n.getWeight() + " |");
	    			   System.out.printf("%3.0f |", n.getWeight());// si stampa il peso
	    		   		n = n.getNext();// si scorre verso destra
	    		   }
	    		   else 
	    			   System.out.print("    |");
	    	   }
	    	   else
	    		   System.out.print("    |");
	       }
          System.out.println();
       }
    }

}
