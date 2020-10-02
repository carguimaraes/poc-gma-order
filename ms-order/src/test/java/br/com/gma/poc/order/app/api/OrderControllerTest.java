package br.com.gma.poc.order.app.api;

import java.util.Set;
import java.util.function.Function;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gma.poc.order.app.exception.CfgAdivice;
import br.com.gma.poc.order.app.exception.DadoInvalidoException;
import br.com.gma.poc.order.app.exception.InformacaoNaoEncontradaException;
import br.com.gma.poc.order.app.model.OrderResource;
 
import br.com.gma.poc.order.app.service.OrderService;

public class OrderControllerTest {
	
	private ObjectMapper objectMapper;
	private MockMvc mockMvc;
	
	@InjectMocks
	private OrderController orderController;

	@Mock
	private OrderService orderServiceMock;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(orderController).setControllerAdvice(new CfgAdivice()).build();

		objectMapper = new ObjectMapper();
	}
	
	@Test
	public void rotaFALHA_deveRetornarMsgErroHttpStatusNOT_FOUND_quandoPassarRotaQueNaoExisteParaController() 	throws Exception {

		OrderResource o1 = new OrderResource();
 		 

		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.post("/v1/ordersx")
						.accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(o1))
						.contentType(MediaType.APPLICATION_JSON));
		
				 
		//System.out.print("=====>"+resultActions.andReturn().getResponse().getContentAsString());

		resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
		 

	}
	
	@Test
	public void incluirFALHA_deveRetornarMsgErroHttpStatusBAD_REQUEST_quandoPassarOrderNaoValidaParaInclusao() 	throws Exception {

		OrderResource o1 = new OrderResource();
 
	 
		Mockito.when(orderServiceMock.incluir(o1)).thenAnswer(invocation -> {
			throw new DadoInvalidoException(new String[] { "Dado nao valido" });
		});

		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.post("/v1/orders")
						.accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(o1))
						.contentType(MediaType.APPLICATION_JSON));
		

		String msgERRO = "['Dado nao valido']";
		
		 

		resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());
		resultActions.andExpect(MockMvcResultMatchers.content().json(msgERRO));

	}
	
 
	@Test
	public void incluirSUCESSO_deveRetornarNovaOrderHttpStatusCREATED_quandoPassarOrderValidoParaInclusao() 	throws Exception {

		OrderResource o1= new OrderResource();
		 
	    long id=1967L;

		Function<OrderResource, OrderResource> f = (x) -> { 	x.setId(id); 	return x; 	};

		Mockito.when(orderServiceMock.incluir(o1)).thenReturn((f.apply(o1)));

		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.post("/v1/orders").accept(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(o1)).contentType(MediaType.APPLICATION_JSON));

		 		
		 String location = resultActions.andReturn().getResponse().getHeader("Location");
		 
		 //System.out.println("==================>"+location);
	 
		 
		resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
	  	Assert.assertTrue(location.equals("http://localhost/v1/orders/"+id));
	}
	
	@Test
	public void obterOrderFALHA_deveRetornarListaMsgErroHttpStatusNOT_FOUND_quandoPassarIdQueNaoExiste() throws Exception {
		
		Mockito.when(orderServiceMock.obterPorId(101L)).thenAnswer(invocation -> {
			throw new InformacaoNaoEncontradaException(new String[] { "Informacao nao encontrada" });
		});

		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.get("/v1/orders/{id}", 101l).accept(MediaType.APPLICATION_JSON));

		resultActions.andExpect(MockMvcResultMatchers.status().isNotFound());
		resultActions.andExpect(MockMvcResultMatchers.content().json("['Informacao nao encontrada']"));
	}
	
	@Test
	public void obterOrderSUCESSO_deveRetornarUmaOrderResourceHttpStatusOK_quandoPassarIdOrderQueExiste() 	throws Exception {

		OrderResource o1 = new OrderResource();
		o1.setId(101L);
		o1.setNome("ORDER-01");
		 

		Mockito
			.when(orderServiceMock.obterPorId(101L))
			.thenReturn(o1);

		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.get("/v1/orders/{id}", 101l).accept(MediaType.APPLICATION_JSON));

		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
		resultActions.andExpect(MockMvcResultMatchers.content().json("{'id':101 ,'nome':'ORDER-01'}"));

	}
 
	 

}
