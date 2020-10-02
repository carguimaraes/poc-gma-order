package br.com.gma.poc.order.app.service;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.gma.poc.order.app.service.impl.MapperOrderImpl;
import br.com.gma.poc.order.app.service.impl.OrderServiceImpl;
import br.com.gma.poc.order.domain.repository.CustomerRepository;
import br.com.gma.poc.order.domain.repository.OrderRepository;
import br.com.gma.poc.order.domain.repository.PaymentDetailsRepository;
import br.com.gma.poc.order.domain.repository.ProductRepository;
import br.com.gma.poc.order.app.exception.DadoInvalidoException;
import br.com.gma.poc.order.app.exception.InformacaoNaoEncontradaException;
import br.com.gma.poc.order.app.model.CustomerResource;
import br.com.gma.poc.order.app.model.OrderItemResource;
import br.com.gma.poc.order.app.model.OrderResource;
import br.com.gma.poc.order.app.model.PaymentDetailsResource;
import br.com.gma.poc.order.app.model.ProductResource;
import br.com.gma.poc.order.domain.entity.*;

public class OrderServiceImplTest {
	
	@InjectMocks
	private OrderServiceImpl orderService;
	
	@Mock
	private OrderRepository orderRepositoryMock;
	
	@Mock
	private CustomerRepository customerRepositoryMock;
	
	@Mock
	private PaymentDetailsRepository paymentDetailsRepositoryMock;
	
	@Mock
	private ProductRepository  productRepositoryMock;
	
	@Mock
	private OrderAprovacaoService validateTransactionServiceMock;
	
	
	//TODO nao é bom fazer isso... fiz para simplificar 
	@Spy
	private MapperOrder mapperOrderMock=new MapperOrderImpl();;
		 	 
	
	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

	}
	
	
	
	@Test
	public void obterPorIdFALHA_deveRetonarInformacaoNaoEncontradaException_quandoInformarIDqueNaoExiste()  {
		 
		String msgErros[]=null;
		 
		Mockito
		.when(orderRepositoryMock.findById(1967L))
		.thenAnswer(invocation -> { throw new InformacaoNaoEncontradaException(); });
		
		 
		try {
			OrderResource  orderResource=orderService.obterPorId(1967L);
		} catch (InformacaoNaoEncontradaException e) {
			 
			msgErros=e.getErrors();
		}
		
		Assert.assertEquals(msgErros[0], "Informação não encontrada");
		 
	}
	
	
	@Test
	public void obterPorIdSUCESSO_deveRetonarOrderResource_quandoInformarIDqueExisteNaBaseDeDados() throws InformacaoNaoEncontradaException  {
		 
		Order o= new Order();
		o.setId(1967L); 
		o.setNome("ORDER-01");
		 
		Mockito
		.when(orderRepositoryMock.findById(1967L))
		.thenReturn(Optional.of(o));
		
		
		OrderResource  orderResource=orderService.obterPorId(1967L);
		
		Assert.assertEquals(orderResource.getId(),o.getId());
		Assert.assertEquals(orderResource.getNome(),o.getNome());
		 
	}
	
	@Test
	public void incluirFALHA_deveRetonrDadoInvalidoException_quandoOrderResourceNaoValido()   {
		String msgErros[]=null;
		Long idNovo=1967L;
		OrderResource orderResource=OrderResourceBuilder.build(false);
		  
		
		
		try {
			orderService.incluir(orderResource);
		} catch (DadoInvalidoException e) {
			 
			msgErros=e.getErrors();
		}
		 		
		for(String msgErro : msgErros) {
			System.out.println("=============>"+msgErro);
		}
		 
		//TODO melhorar a validacao e teste para produtos lista itens
		List<String> lst=Arrays.asList(msgErros); 
		Assert.assertTrue(	lst.size()==4);
		Assert.assertTrue(lst.contains("[OrderItem] O campo nome eh obrigatorio"));
		Assert.assertTrue(lst.contains("[Order] O campo observacao eh obrigatorio"));
		Assert.assertTrue(lst.contains("[Order] O campo nome eh obrigatorio"));
		Assert.assertTrue(lst.contains("[OrderItem] O campo nome eh obrigatorio"));
		
		 
		 
	 
		
	}
	
	@Test
	public void incluirFALHA_deveRetonarDadoInvalidoException_quandoNaoExistirCliente_ou_PaymentDetails_ou_()   {
		String msgErros[]=null;
			 
		//aqui coloco true porque o objetivo e so validar os itens
		OrderResource orderResourceParaInc=  OrderResourceBuilder.build(true);
		 
		Mockito
		.when(customerRepositoryMock.findById(ArgumentMatchers.anyLong()))
		.thenReturn(Optional.empty());
		
		Mockito
		.when(paymentDetailsRepositoryMock.findById(ArgumentMatchers.anyLong()))
		.thenReturn(Optional.empty());
	 		 
		Mockito
		.when(productRepositoryMock.findById(ArgumentMatchers.anyLong()))
		.thenReturn(Optional.empty());
		
		

		try {
			OrderResource orderResource=orderService.incluir(orderResourceParaInc);
		} catch (DadoInvalidoException e) {
			 
			msgErros=e.getErrors();
		}
		
		
		for(String msgErro : msgErros) {
			System.out.println("GMA=============>"+msgErro);
		}
		 
		
		List<String> lst=Arrays.asList(msgErros);
		Assert.assertTrue(	lst.size()==4);
		Assert.assertTrue(lst.contains("Cliente id:219670430 não encontrado"));
		Assert.assertTrue(lst.contains("PaymentDetails id:30 não encontrado"));
		Assert.assertTrue(lst.contains("Product id:4 não encontrado"));
		Assert.assertTrue(lst.contains("Product id:5 não encontrado"));
	 
	}
	
	
	@Test
	public void incluirSUCESSO_deveRetonrOrderResourceComIdDaOrdemCriada_quandoOrderResourceValido() throws DadoInvalidoException {
	
	 
		Long idNovo=1967L;
		OrderResource orderResourceParaInc=  OrderResourceBuilder.build(true);
		
		Customer ct=new Customer(); 		ct.setNome("GMA");
		PaymentDetails py= new PaymentDetails(); py.setNome("GMA-PY");
		
		Product pr=new Product() ; pr.setNome("GMA-pr");
		 
		
		 
		Mockito
		.when(customerRepositoryMock.findById(ArgumentMatchers.anyLong()))
		.thenReturn(Optional.of(ct));
		
		Mockito
		.when(paymentDetailsRepositoryMock.findById(ArgumentMatchers.anyLong()))
		.thenReturn(Optional.of(py));
		

		Mockito
		.when(productRepositoryMock.findById(ArgumentMatchers.anyLong()))
		.thenReturn(Optional.of(pr));
		
		Mockito
		.when(productRepositoryMock.findById(ArgumentMatchers.anyLong()))
		.thenReturn(Optional.of(pr));
		
		Mockito.doNothing()
		 .when(validateTransactionServiceMock)
		 .enviar(ArgumentMatchers.anyLong());
		
		 
		
		Mockito
		.when(orderRepositoryMock.save(ArgumentMatchers.any(Order.class)))
		.thenAnswer(new Answer<Order>() {

			@Override
			public Order answer(InvocationOnMock invocation) throws Throwable {

				Order o = invocation.getArgument(0, Order.class);
				 
				o.setId(idNovo);
				return o;
			}

		});
				 
		
		OrderResource orderResource=orderService.incluir(orderResourceParaInc);
		
		//TODO assert anemico nao esta cobrndo todo o objeto
		Assert.assertEquals(orderResource.getId(),idNovo);
		Assert.assertEquals(orderResource.getNome(),orderResourceParaInc.getNome());
		
	}
	
	 
	
	
	

}
