<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s"	uri="/struts-tags"%>

<div class="row">
	<h1 class="h1">Liste des produits</h1>
	
	<input type="hidden" id="idType1" name="idType1" value="<s:property value="idType1"/>"/>
	<input type="hidden" id="idType2" name="idType2" value="<s:property value="idType2"/>"/>
	<input type="hidden" id="idProducteur" name="idProducteur" value="<s:property value="idProducteur"/>"/>
	
	<div id="listeProduits">
	
		<!-- Contenu de la liste des produits -->
	
	</div>
</div>

<!-- LAISSEZ MOI MON ANCIEN CODE SVP, Benoît -->

<%-- 
			<s:iterator value="listeProduits" status="stat">
				<s:iterator>
					<s:url action="detailsProduit" var="dp">
						<s:param name="id">
							<s:property value="idProduit" />
						</s:param>
					</s:url>
				
					<tr>
						<td><img alt="image" class="img-responsive img-thumbnail" width="200px" src="<s:property value="photo"/>" /></td>
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
--%>