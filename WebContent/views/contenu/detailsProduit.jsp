<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s"	uri="/struts-tags"%>

<div class="row">
	<h1 class="h1">
		<s:property value="designation" />
		<button type="button" class="btn btn-default" <s:if test="!disponible">disabled="disabled"</s:if>>Ajouter au panier</button>
	</h1>
	<h3>
		<s:if test="disponible"><span class="label label-success">Disponible</span></s:if>
		<s:if test="!disponible"><span class="label label-danger">Non disponible</span></s:if>
	</h3>
	
	<table class="table tableverticalalign">
		<tr>
			<td><img alt="<s:property value="desgination"/>" src="<s:property value="photo"/>"></td>
			<td>
				<s:url action="detailsProducteur" var="dp">
					<s:param name="idProducteur">
						<s:property value="idProducteur" />
					</s:param>
				</s:url>
				<span class="input-group-addon"><span class="price"><s:property value="prix" /> &euro;</span></span>
				<span class="input-group-addon"><a href="<s:property value='#dp'/>"><s:property value="raisonSociale" /></a></span>
				<span class="input-group-addon">Délais de livraison : <s:property value="delaiLivraison" /> jours</span>
				<p><s:property value="description" /></p>
				<h4>
					<span class="label label-default">
						<span class="glyphicon glyphicon-map-marker"></span>
						&nbsp;Adresse du producteur
					</span>
				</h4>
				<p>
					<s:property value="raisonSociale"/><br/>
					<s:property value="ligneAdresse1"/><br/>
					<s:property value="ligneAdresse2"/><br/>
					<s:property value="codePostal"/><br/>
					<s:property value="ville"/>
				</p>
			</td>
		</tr>	
	</table>
	
	<h3>Avis</h3>
	
	<s:iterator value="listeCommentaires">
		<div class="col-md-12">
			<div class="panel panel-default">
			
				<div class="panel-heading"><s:property value="idUtilisateur"/> | 
				<s:iterator status="stat" value="(5).{ #this }">
					<s:if test="note >= #stat.count"><span class="glyphicon glyphicon-star"></span></s:if>
					<s:else><span class="glyphicon glyphicon-star-empty"></span></s:else>
				</s:iterator> |
				<s:property value="tsCreation"/></div>
				
				<div class="panel-body">
					<div class="media">
						<div class="media-left">
							<s:property value=""/>
						</div>
						<div class="media-body">
							<s:property value="avis"/>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:iterator>
	
</div>


