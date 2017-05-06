<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="adicionaBrinquedo.html">Adicionar</a><br><br><br><br>
	

	<table border='1' style='width: 100%;'>
		<tr>
			<td> ID </td>
			<td> descricao </td>
			<td> precoCusto </td>
			<td> precoVenda </td>
			<td> qtdEstoque </td>
			<td> fornecedor </td>
			<td> dataFabricacao </td>
		</tr>

	<jsp:useBean id="dao" class="dao.BrinquedoDao"/>
	
	<c:forEach var="brinquedos" items="${dao.listar()}" varStatus="i">

		<tr>
			<td> ${brinquedos.id} </td>
			<td> ${brinquedos.descricao} </td>
			<td> ${brinquedos.precoCusto} </td>
			<td> ${brinquedos.precoVenda} </td>
			<td> ${brinquedos.qtdEstoque} </td>
			<td> ${brinquedos.fornecedor} </td>
			<td>
				<fmt:formatDate value="${brinquedos.dataFabricacao}" pattern="dd/MM/yyyy" /> 
			</td>
			
					<td>
							<c:choose>
								<c:when test="${brinquedos.qtdEstoque <= 5}">
									É necessário comprar!!
								</c:when>
								<c:otherwise>
									 Nao é Necessário comprar!!
								</c:otherwise>
							</c:choose>
					</td>
			
			
			
			
			
			<td>
				
				<a href='removerBrinquedos?id=${brinquedos.id}'>Remover</a>
			</td>
		</tr>
		
		</c:forEach>
		</table>
	

	

</body>
</html>