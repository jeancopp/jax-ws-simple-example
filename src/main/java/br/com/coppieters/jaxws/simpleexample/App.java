package br.com.coppieters.jaxws.simpleexample;

import javax.xml.ws.Endpoint;

import br.com.coppieters.jaxws.simpleexample.service.EstoqueWS;

public class App {
	public static void main(String[] args) {
		EstoqueWS implementacaoWS = new EstoqueWS();
		String URL = "http://localhost:8080/estoquews";

		System.out.println("EstoqueWS rodando: " + URL);

		// associando URL com implementacao
		Endpoint.publish(URL, implementacaoWS);
	}
}
