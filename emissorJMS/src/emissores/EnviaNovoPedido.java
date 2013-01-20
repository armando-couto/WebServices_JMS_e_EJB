package emissores;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class EnviaNovoPedido {

	/*
	 * A Apostila k19 k23, tem uma configuração no servidor Glassfish para funcionar essa classe.
	 */
	
	public static void main(String[] args) throws Exception {
		 // serviço de nomes - JNDI
		 InitialContext ic = new InitialContext();
		
		// fábrica de conexões JMS
		 ConnectionFactory factory = (ConnectionFactory) ic.lookup("jms/K19Factory");
		
		 // fila
		 Queue queue = (Queue) ic.lookup("jms/pedidos");
		
		 // conexão JMS
		 Connection connection = factory.createConnection();
		
		 // sessão JMS
		 Session session = connection.createSession(false,
		 Session.AUTO_ACKNOWLEDGE);
		
		 // emissor de mensagens
		 MessageProducer sender = session.createProducer(queue);
		
		 // mensagem
		 TextMessage message = session.createTextMessage();
		 message.setText("Uma pizza de cinco queijos e uma coca-cola 2l - " + System.currentTimeMillis());
		
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
