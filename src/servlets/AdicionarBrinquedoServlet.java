package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BrinquedoDao;
import entidades.Brinquedos;

@WebServlet(urlPatterns = "/adicionaBrinquedo")

public class AdicionarBrinquedoServlet extends HttpServlet {

	private static final long serialVersionUID = 7563781277786590359L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String descricao = request.getParameter("descricao");
		String precoCusto = request.getParameter("precoCusto");
		String precoVenda = request.getParameter("precoVenda");
		String qtdEstoque = request.getParameter("qtdEstoque");
		String fornecedor = request.getParameter("forncecedor");
		String dataFabricacao = request.getParameter("dataFabricacao");

		Brinquedos brinquedos = new Brinquedos();
		brinquedos.setDescricao(descricao);
		brinquedos.setFornecedor(fornecedor);
		brinquedos.setPrecoCusto(Double.parseDouble(precoCusto));
		brinquedos.setPrecoVenda(Double.parseDouble(precoVenda));
		brinquedos.setQtdEstoque(Integer.parseInt(qtdEstoque));

		boolean ocorreuErroConversao = false;

		try {
			Date dataConvertida = new SimpleDateFormat("dd/MM/yyyy").parse(dataFabricacao);
			brinquedos.setDataFabricacao(dataConvertida);
		} catch (ParseException e) {
			ocorreuErroConversao = true;
		}

		if (ocorreuErroConversao == false) {

			BrinquedoDao dao = new BrinquedoDao();
			dao.inserir(brinquedos);

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("Brinquedo " + brinquedos.getDescricao() + " inserido com sucesso");
			out.println("</body>");
			out.println("</html>");

		} else {

			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("Data informada invalida!!!");
			out.println("</body>");
			out.println("</html>");

		}

	}
}
