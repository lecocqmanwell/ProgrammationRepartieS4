package tpTCPServeur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerTCP {
	
	ServerSocket serveurSocket;
	Socket client;
	BufferedReader in;
	PrintWriter out;

	public ServerTCP() throws IOException {
		
		//On crée le socket serveur avec le port de reception du serveur ouvert
		serveurSocket = new ServerSocket(1313);
		
		//On ajoute une propriété qui permet d'ajouter un séparateur
		System.setProperty("line.separator","\r\n");
		
	}
	
	public void go() throws IOException {
		
		//boucle infinie
		while(true) {
			
			//accept = attente de reception
			//retourne le socket client
			client = serveurSocket.accept();
			
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));

			out = new PrintWriter(client.getOutputStream(),true);
			
			String ligne;
			/*
			//Création de la date
			Date d = new Date();
			ligne = d.toString();
			*/
			//boucle infinie
			while(true) {

				ligne = in.readLine();

				out.println("reçu " + ligne);

				//condition d'arret de la boucle quand le client tape "fin"
				//afin de repasser dans le accept pour un autre client
				if(ligne.equalsIgnoreCase("FIN"))break;
				
			}
			out.close();
			client.close();
		}
	}
	
	public static void main(String argv[]) {
		
		try {
			ServerTCP s = new ServerTCP();
			s.go();
			
			//On test avec telnet bouleau12 1313
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
