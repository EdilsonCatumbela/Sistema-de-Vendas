package ao.com.catumbela.sistemavendas.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import ao.com.catumbela.sistemavendas.dao.ClienteDAO;
import ao.com.catumbela.sistemavendas.dao.FornecedorDAO;
import ao.com.catumbela.sistemavendas.dao.FuncionarioDAO;
import ao.com.catumbela.sistemavendas.dao.ProdutoDAO;
import ao.com.catumbela.sistemavendas.dao.VendaDAO;
import ao.com.catumbela.sistemavendas.domain.Cliente;
import ao.com.catumbela.sistemavendas.domain.Fornecedor;
import ao.com.catumbela.sistemavendas.domain.Funcionario;
import ao.com.catumbela.sistemavendas.domain.ItemVenda;
import ao.com.catumbela.sistemavendas.domain.Produto;
import ao.com.catumbela.sistemavendas.domain.Venda;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Named
@ViewScoped
@Getter
@Setter
public class VendaBean implements Serializable {
	private Venda venda;

	private List<Produto> produtos;
	private List<Fornecedor> fornecedores;
	private List<ItemVenda> itensVenda;
	private List<Cliente> clientes;
	private List<Funcionario> funcionarios;


	private FornecedorDAO fornecedorDAO;

	@PostConstruct
	public void listar() {
		try {
			venda = new Venda();
			venda.setPrecoTotal(new BigDecimal("0.00"));

			fornecedorDAO = new FornecedorDAO();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			fornecedorDAO = new FornecedorDAO();

			produtos = produtoDAO.listar();
			fornecedores = fornecedorDAO.listar();

			itensVenda = new ArrayList<>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar carregar a tela de vendas");
			erro.printStackTrace();
		}
	}

	public void adicionar(Produto produtoSelecionado) {
		Produto produto = produtoSelecionado;

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(produto)) { // capturar o intem na posicao corrente
				achou = posicao;
			}
		}
		if (achou < 0) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setValorParcial(produto.getPreco());
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(new Short("1"));

			itensVenda.add(itemVenda);
		} else {
			ItemVenda itemVenda = itensVenda.get(achou);
			itemVenda.setQuantidade(new Short(itemVenda.getQuantidade() + 1 + ""));

			// Adicionar preco parcial
			itemVenda.setValorParcial(produto.getPreco().multiply(new BigDecimal(itemVenda.getQuantidade())));
		}

		calcular();

	}

	public void remover(ItemVenda itemSelecionado) {
		ItemVenda itemVenda = itemSelecionado;

		int achou = -1;
		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			if (itensVenda.get(posicao).getProduto().equals(itemVenda.getProduto())) {
				achou = posicao;
			}
		}

		if (achou > -1) {
			itensVenda.remove(achou);

		}

		calcular();
	}

	public void calcular() {
		venda.setPrecoTotal(new BigDecimal("0.00"));

		for (int posicao = 0; posicao < itensVenda.size(); posicao++) {
			ItemVenda itemVenda = itensVenda.get(posicao);
			venda.setPrecoTotal(venda.getPrecoTotal().add(itemVenda.getValorParcial()));
		}
	}

	public void finalizar() {
		try {
			venda.setHorario(new Date());
			
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			funcionarios = funcionarioDAO.listar();
			
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorrer um erro ao tentar finalizar a venda");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			if(venda.getPrecoTotal().signum()==0) {
				Messages.addGlobalError("Informe pelo menos um valor para a venda");
				return;
			}
			
			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.salvar(venda, itensVenda);
			
			listar();
			
			Messages.addGlobalInfo("Venda salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salva a venda");
		}
	}
}
