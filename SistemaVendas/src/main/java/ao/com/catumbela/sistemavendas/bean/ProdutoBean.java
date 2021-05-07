package ao.com.catumbela.sistemavendas.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.collections4.map.HashedMap;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import ao.com.catumbela.sistemavendas.dao.FornecedorDAO;
import ao.com.catumbela.sistemavendas.dao.ProdutoDAO;
import ao.com.catumbela.sistemavendas.domain.Fornecedor;
import ao.com.catumbela.sistemavendas.domain.Produto;
import ao.com.catumbela.sistemavendas.util.JPAUtil;
import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;

@SuppressWarnings("serial")
@Named
@ViewScoped
@Getter
@Setter
public class ProdutoBean implements Serializable {
	private Produto produto;
	private List<Fornecedor> fornecedores;
	private List<Produto> produtos;
	private FornecedorDAO fornecedorDAO;
	private ProdutoDAO produtoDAO;

	@PostConstruct
	public void listar() {
		try {
			fornecedorDAO = new FornecedorDAO();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			fornecedorDAO = new FornecedorDAO();

			produtos = produtoDAO.listar();
			fornecedores = fornecedorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {

			produto = new Produto();
			// produtos = produtoDAO.listar();
			// FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores = fornecedorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		if (produto.getCaminho() == null) {
			Messages.addGlobalError("O campo foto é obrigatório");
			return;
		}

		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produtoRetorno = produtoDAO.merge(produto);

			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get("C://Desenvolvimentos//Upload//" + produtoRetorno.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			produto = new Produto();
			fornecedorDAO = new FornecedorDAO();
			produtos = produtoDAO.listar();
			fornecedores = fornecedorDAO.listar();
			Messages.addGlobalInfo("Produto salvo com sucesso");

		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}

	}

	public void excluir(Produto produtoSelecionado) {

		try {
			produto = produtoSelecionado;
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.excluir(produto.getCodigo());

			// Apagando foto
			Path arquivo = Paths.get("C://Desenvolvimentos//Upload//" + produto.getCodigo() + ".png");
			Files.deleteIfExists(arquivo);

			Messages.addGlobalInfo("Produto removido com sucesso");

			produtos = produtoDAO.listar();
		} catch (RuntimeException | IOException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o produto");
			erro.printStackTrace();
		}

	}

	public void editar(Produto selecionarProduto) {
		produto = selecionarProduto;
		// Caminho da foto
		produto.setCaminho("C://Desenvolvimentos//Upload//" + produto.getCodigo() + ".png");
	}

	public void upload(FileUploadEvent evento) {
		try {
			UploadedFile arquivoUpload = evento.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(arquivoUpload.getInputStream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			produto.setCaminho(arquivoTemp.toString());

			Messages.addGlobalInfo("Upload realizado com sucesso");

		} catch (IOException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar realizar o upload de arquivo");
			erro.printStackTrace();
		}

	}

	public void imprimir() {

		try {
			String caminho = Faces.getRealPath("/reports/produtos.jasper");

			Map<String, Object> paramentros = new HashedMap<>();

			Connection conexao = JPAUtil.getConexao();

			JasperPrint relatorio = JasperFillManager.fillReport(caminho, paramentros, conexao);

			JasperPrintManager.printReport(relatorio, true);
		} catch (JRException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar o relatório");
		}
	};
}
