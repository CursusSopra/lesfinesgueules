<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<h1 class="h1">Liste des produits</h1>

	<table class="table table-stripped table-hover">
		<thead>
			<tr>
				<th class="col-md-2"></th>
				<th class="col-md-10">Description</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="listeProduits" status="stat">
			<s:iterator>
				<s:url action="detailsProduit" var="dp">
					<s:param name="id">
						<s:property value="idProduit" />
					</s:param>
				</s:url>
			
				<tr>
					<td><img alt="image" class="img-responsive img-thumbnail"
						width="200px" src="images/default.jpg" /></td>
					<td>
						<h4>
							<s:property value="designation" />
						</h4>
						<p>
							<s:property value="description" />
						</p>
						<p>
							<div class="input-group">
								<span class="input-group-addon"><s:property value="prix" /> &euro;</span>
								<button type="button" class="btn btn-default">Ajouter au panier</button>
								<a href="<s:property value='#dp'/>"><button type="button" class="btn btn-default">Voir les d&eacute;tails</button></a>
							</div>
						</p>
					</td>
				</tr>
			</s:iterator>
			</s:iterator>
		</tbody>
	</table>

</div>