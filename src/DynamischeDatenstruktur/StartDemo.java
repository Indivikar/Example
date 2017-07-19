package DynamischeDatenstruktur;

// https://www.youtube.com/watch?v=HfsHn4NPRjs
// http://page.mi.fu-berlin.de/esponda/fuel/Dynamische_Datenstrukturen/02_stapel/stapschnitt.shtml
class SingleNode{
	int wert;
	SingleNode next = null;
	public SingleNode(int w){
		this.wert = w;
	}
}

class DoubleNode{
	int wert;
	DoubleNode next = null;
	DoubleNode prev = null;
	public DoubleNode(int w){
		this.wert = w;
	}
}

class DoubleNodeMitBenutzer{
	int wert;
	String benutzerName;
	DoubleNodeMitBenutzer next = null;
	DoubleNodeMitBenutzer prev = null;
	public DoubleNodeMitBenutzer(int w, String benutzerName){
		this.wert = w;
		this.benutzerName = benutzerName;
	}
}

public class StartDemo {

	public static void main(String[] args) {

		// ---------------------------
		// Einfache verkettete Liste
		// ---------------------------

//		SingleNode list = new SingleNode(0);
//		SingleNode start = list;
//
//		for (int i = 0; i < 10; i++) {
//			list.next = new SingleNode(i);
//			list = list.next;
//		}
//
//		list = start;
//		for (int i = 0; i < 10; i++) {
//			System.out.println(list.wert);
//		}


		// ---------------------------
		// Doppelt verkettete Liste
		// ---------------------------

//		DoubleNode list = new DoubleNode(0);
//		DoubleNode start = list;
//
//		for (int i = 0; i < 10; i++) {
//			list.next = new DoubleNode(i);
//			list.next.prev = list;
//			list = list.next;
//		}
//
//		list = start;
//		for (int i = 0; i < 10; i++) {
//			System.out.println(list.wert);
//			list = list.next;
//		}
//
//		for (int i = 0; i < 10; i++) {
//			System.out.println(list.wert);
//			list = list.prev;
//		}


		// ---------------------------
		// Doppelt verkettete Liste mit Benutzer
		// ---------------------------

		DoubleNodeMitBenutzer list = new DoubleNodeMitBenutzer(0, null);
		DoubleNodeMitBenutzer start = list;


			list.next = new DoubleNodeMitBenutzer(0, "Inge");
			list.next.prev = list;
			list = list.next;

			list.next = new DoubleNodeMitBenutzer(1, "Ute");
			list.next.prev = list;
			list = list.next;

			list.next = new DoubleNodeMitBenutzer(2, "Jens");
			list.next.prev = list;
			list = list.next;


		list = start;
		for (int i = 0; i < 3; i++) {
			System.out.println(list.wert + " -> " + list.benutzerName);
			list = list.next;
		}

		for (int i = 0; i < 3; i++) {
			System.out.println(list.wert + " -> " + list.benutzerName);

			list = list.prev;
		}
	}

}
