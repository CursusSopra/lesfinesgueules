<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="row">
	<h1 class="h1">Liste des utilisateurs</h1>

	<table class="table table-striped table-hover">
		
		<thead>
			<tr>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Email</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="listeUtilisateurs" status="stat">
				<s:url action="createListProfil" var="clp">
						<s:property value="idUtilisateur" />
				</s:url>
				<tr>
					<td><a href="<s:property value='#clp'/>"> <s:property value="nom" />
					</a></td>
					<td><a href="<s:property value='#clp'/>"> <s:property value="prenom" />
					</a></td>
					<td><a href="<s:property value='#clp'/>"> <s:property value="email" />
					</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<a href="<s:url action="listeUtilisateurs"/>"> lien</a>

</div>
