
/*Implementazione della classe Nodo per usare le liste di adiacenza.
 * Il nodo si modifica: avrà due contenuti informativi, relativi al nodo destinazione a cui si punta e il peso dell'arco sotteso, e un puntatore al nodo ad un nodo successivo , che indica
 * un' altro nodo adicente del nodo sorgente contenuto del vettore dei nodi .*/
public class Node {
	
	private int endNode;//nodo destinazione del nodo sorgente
	private double weight;//peso dell'arco tra nodo sorgente,contenuto nel vettore, e nodo destinazione adiacente contenuto nella lista
	private Node next;//puntatore al nodo ad un nodo successivo , che indica
	                 // un' altro nodo adicente del nodo sorgente contenuto del vettore dei nodi
	
	public Node(int endNode, double weight) {//costruttore che prende in input nodo destinazione e peso del relativo arco (nodo sorgente--nodo destinazione)
		this.endNode = endNode;
		this.weight = weight;
		this.next = null;
	}

	public Node(int endNode, double weight, Node next) {//costruttore che prende in input nodo destinazione e peso del relativo arco (nodo sorgente--nodo destinazione) e il nodo successivo
		this.endNode = endNode;
		this.weight = weight;
		this.next = next;
	}
	
	public int getNode() { return endNode; }//restituisce nodo destinazione
	public double getWeight() { return weight; }	//restituisce peso dell'arco tra nodo sorgente e nodo destinazione
	public Node getNext() { return next; }//restituisce nodo successivo nella lista di adiacenza

	public void setNode(int node) { //modifica il nodo destinazione
		this.endNode = node;
	}

	public void setWight(double w) { //modifica peso di arco
		this.weight = w;
	}

	public void setNext(Node next) { //modifica nodo successivo 
		this.next = next;
	}

}
