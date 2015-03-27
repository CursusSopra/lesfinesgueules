<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="row">
	<h1 class="h1">Liste des utilisateurs</h1>
	
	<table class="table table-stripped table-hover">
		<tbody>
			<s:iterator value="listeUtilisateurs" status="stat">
				<s:iterator>
					<s:url action="details" var="dp">
						<s:param name="idUtilisateur">
							<s:property value="idUtilisateur" />
						</s:param>
					</s:url>
					<s:url action="modification" var="mp">
						<s:param name="idUtilisateur">
							<s:property value="idUtilisateur" />
						</s:param>
					</s:url>
					<tr>
						<td><img alt="image" class="img-responsive img-thumbnail" width="200px" src="<s:property value="photo"/>" /></td>
						<td>
							<p>
								<s:property value="nom" />
							</p>
							<p>
								<s:property value="prenom" />
							</p>
							<p>
								<s:property value="email" />
							</p>
							<p>
								<div class="input-group">
									<a href="<s:property value='#dp'/>"><button type="button" class="btn btn-default">Profil</button></a>
									<a href="<s:property value='#mp'/>"><button type="button" class="btn btn-info">Modifier</button></a>
									<a href="<s:property value='#sp'/>"><button type="button" class="btn btn-info">Supprimer</button></a>
								</div>
							</p>
						</td>
					</tr>
				</s:iterator>
			</s:iterator>
		</tbody>
	</table>

</div>



