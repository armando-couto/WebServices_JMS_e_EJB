package emissores;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class EnviarNoticias {

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

		// emissor de mensagens
		MessageProducer sender = session.createProducer(topic);

		// mensagem
		TextMessage message = session.createTextMessage();
		message.setText("A copa do mundo de 2014 ser� no Brasil - "
				+ System.currentTimeMillis());

		// enviando
		sender.send(message);

		// fechando
		sender.close();
		session.close();
		connection.close();

		System.out.println("Mensagem Enviada");
		System.exit(0);
	}
}