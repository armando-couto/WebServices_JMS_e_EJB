package receptores;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class ReceberPedido {

	/*
	 * A Apostila k19 k23, tem uma configuração no servidor Glassfish para funcionar essa classe.
	 */
	
	public static void main(String[] args) throws Exception {
		// serviço de nomes - JNDI
		InitialContext ic = new InitialContext();

		// fábrica de conexões JMS
		ConnectionFactory factory = (ConnectionFactory) ic
				.lookup("jms/K19Factory");

		// fila
		Queue queue = (Queue) ic.lookup("jms/pedidos");

		// conexão JMS
		Connection connection = factory.createConnection();

		// sessão JMS
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// receptor de mensagens
		MessageConsumer receiver = session.createConsumer(queue);

		// inicializa conexão
		connection.start();

		// recebendo
		TextMessage message = (TextMessage) receiver.receive();

		System.out.println(message.getText());

		// fechando
		receiver.close();
		session.close();
		connection.close();

		System.out.println("FIM");
		System.exit(0);
	}
}
