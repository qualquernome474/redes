
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ClienteA {

    private Socket soquete;
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;
    private ArrayList<Mensagem> mensagens;

    private static String nome = null;

    public ClienteA(String endereco, int porta) throws Exception {
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
        Scanner ler = new Scanner(System.in);
        Mensagem mensagem = new Mensagem();
        ClienteA cliente = null;
        int x = 0;

        while (true) {
            System.out.println("O que voce quer fazer?");
            System.out.println("1-conectar-se");
            System.out.println("2-desconectar-se");
            System.out.println("3-mandar mensagem");
            System.out.println("4-listar pessoas conectadas");
            System.out.println("5-listar mensagens");

            x = ler.nextInt();

            if (x == 1) {
                cliente = new ClienteA("10.90.37.81", 15500);
            }

            if (x == 2) {
                cliente.finalizar();
            }

            if (x == 3 && cliente != null) {
                if (nome == null) {
                    System.out.print("Seu Nome: ");
                    mensagem.setNome(ler.next());
                    System.out.print("Sua Mesagem: ");
                    mensagem.setTexto(ler.next());
                    cliente.enviar_mensagem(mensagem);

                } else {
                    System.out.print("Sua Mesagem: ");
                    mensagem.setTexto(ler.next());
                    cliente.enviar_mensagem(mensagem);

                }
            }

            if (x == 4) {

                
            }

            if (x == 5) {
                cliente.mensagens = (ArrayList<Mensagem>) cliente.receber_mensagem();
                for (Mensagem mostramensagem : cliente.mensagens) {
                    System.out.println(mensagem.toString());
                }
            }

        }

    }
}
