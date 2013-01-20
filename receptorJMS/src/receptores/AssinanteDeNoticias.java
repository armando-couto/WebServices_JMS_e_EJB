package receptores;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class AssinanteDeNoticias {

	/*
	 * A Apostila k19 k23, tem uma configura��o no servidor Glassfish para funcionar essa classe.
	 */
	
	public static void main(String[] args) throws Exception {
		// servi�o de nomes - JNDI
		InitialContext ic = new InitialContext();

		// f�brica de conex�es JMS
		ConnectionFactory factory = (ConnectionFactory) ic
				.lookup("jms/K19Factory");

		// t�pico
		Topic topic = (Topic) ic.lookup("jms/noticias");

		// conex�o JMS
		Connection connection = factory.createConnection();

		// sess�o JMS
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		// receptor de mensagens
		MessageConsumer receiver = session.createConsumer(topic);

		// inicializa conex�o
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
