

import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClienteB {
	
	private Socket soquete;
	private ObjectOutputStream saida;
	private ObjectInputStream entrada;
	private ArrayList<Mensagem> mensagens;
	
	public ClienteB(String endereco, int porta) throws Exception {
		super();
		this.soquete = new Socket(endereco, porta);
		this.saida = new ObjectOutputStream(this.soquete.getOutputStream()); 
		this.entrada = new ObjectInputStream(this.soquete.getInputStream());
	}

	public void enviar_mensagem(Object mensagem) throws Exception {
		this.saida.writeObject(mensagem);
	}
	
	public Object receber_mensagem() throws Exception {
		return this.entrada.readObject();
	}
	
	public void finalizar() throws IOException {
		this.soquete.close();
	}
	
	public static void main(String[] args) throws Exception {
		ClienteB cliente = new ClienteB ("10.90.37.83", 15500);
		cliente.enviar_mensagem(new Mensagem("Cliente 3", "tudo bem, e voces?"));
		cliente.mensagens = (ArrayList<Mensagem>)cliente.receber_mensagem();
		for (Mensagem mensagem : cliente.mensagens) {
			System.out.println(mensagem.toString());
		}
		cliente.finalizar();
	}
}