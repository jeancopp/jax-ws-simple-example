package br.com.coppieters.jaxws.simpleexample.service;

import java.util.List;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;

import br.com.coppieters.jaxws.simpleexample.AutorizacaoException;
import br.com.coppieters.jaxws.simpleexample.modelo.item.Filtro;
import br.com.coppieters.jaxws.simpleexample.modelo.item.Filtros;
import br.com.coppieters.jaxws.simpleexample.modelo.item.Item;
import br.com.coppieters.jaxws.simpleexample.modelo.item.ItemDao;
import br.com.coppieters.jaxws.simpleexample.modelo.item.ItemValidador;
import br.com.coppieters.jaxws.simpleexample.modelo.usuario.TokenDao;
import br.com.coppieters.jaxws.simpleexample.modelo.usuario.TokenUsuario;

@WebService
public class EstoqueWS {
	private final Logger LOG = Logger.getLogger("EstoqueWS");
	
	private ItemDao dao;
	public EstoqueWS() {
		this.dao  = new ItemDao();
	}
	
//	@RequestWrapper(localName="listaItens") // no soapUi é o nome que ficou associado	
	@WebMethod(operationName = "TodosOsItens")
	@WebResult(name = "item") // nome de cada item
	public @ResponseWrapper(localName = "itens")/*<-nome dado ao objeto que está sendo retornado*/List<Item>  
			getItens(@WebParam(name = "filtros") Filtros filtros) {
		LOG.info("Listando os itens");
    	List<Filtro> listaDeFiltros= null;
    	if(filtros != null)
    		listaDeFiltros = filtros.getLista();
    	List<Item> itens = dao.todosItens(listaDeFiltros);
    	return itens;
    }
	
	
	@WebMethod(operationName = "CadastrarItem")
	@WebResult(name = "item")
	public Item 
			cadastrar(
				@WebParam(name = "token", header = true) TokenUsuario token, 
				@WebParam(name = "novoItem") Item item) 
			throws Exception{
		
		boolean valido = new TokenDao().ehValido(token);

		if(!valido) {
		  throw new AutorizacaoException("Token invalido");
		}
		new ItemValidador(item).validate();
		dao.cadastrar(item);
		return item;
	}
	
	
}
