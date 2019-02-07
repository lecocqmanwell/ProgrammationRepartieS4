package tpTCPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTCP {
	
	Socket sc;
	BufferedReader in;
	PrintWriter out;
	
	public ClientTCP() {
		
		try {
			
			//On crée le socket pour se connecter au serveur en renseignant l'adresse et le port du serveur
			sc = new Socket("localhost", 1313);
			
			
			//On ajoute une propriété qui permet d'ajouter un séparateur
			System.setProperty("line.separator","\r\n");
			
			//On crée un bufferReader afin de pouvoir utiliser la méthode readLine() qui donne directement
			//un string
			//Pour cela le constructeur de BufferedReader ne prend que l'objet Reader donc on utilise le InputStreamReader
			//dont le constructeur prend en parametre le InputStream du socket
			in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			
			//On crée un PrintWriter afin de pouvoir utiliser le println directement 
			//On utilise le constructeur qui prend en parametre le OutputStream du socket client
			//On ajoute le deuxieme parametre true qui est l'autoflush
			out = new PrintWriter(sc.getOutputStream(),true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void go() {
		
		//On crée la requete HTTP GET dans le PrintWriter 
		//qui est envoyée directement avec l'autoflush
		out.println("GET / HTTP/1.0\r\n");
		
		try {
			//On lit la ligne recue dans le BufferReader
			String ligne = in.readLine();
			
			//tant que la ligne n'est pas nulle on continue(affichage/lecture)
			while(ligne != null) {
				
				System.out.println(ligne);
				
				ligne=in.readLine();
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String argv []) {
		ClientTCP client = new ClientTCP();
		client.go();
		
	}

}
