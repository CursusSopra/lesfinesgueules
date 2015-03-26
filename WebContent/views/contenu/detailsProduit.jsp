<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s"	uri="/struts-tags"%>

<div class="row">
	<h1 class="h1">
		<s:property value="designation" />
	</h1>
	<h3>
		<s:if test="disponible"><span class="label label-success">Disponible</span></s:if>
		<s:if test="!disponible"><span class="label label-danger">Non disponible</span></s:if>
	</h3>
	
	<table class="table tableverticalalign">
		<tr>
			<td class="col-md-3">
				<div>
					<input type="hidden" id="idProduit1" value="<s:property value="idProduit"/>"/>
					<img alt="<s:property value="desgination"/>" id="idImage1" src="<s:property value="photo"  default="images/default.jpg"/>" width="300">
					
					<div class="col-md-6">
						<button type="button" class="btn btn-default" id="idButton1" <s:if test="!disponible">disabled="disabled"</s:if>>
							Ajouter au panier
						</button>
					</div>
					<div class="col-md-6">
						<div class="input-group">
							<span class="input-group-btn">
								<button type="button" class="btn btn-default" id="buttonPlus" <s:if test="!disponible">disabled="disabled"</s:if>>
									<span class="glyphicon glyphicon-plus"></span>
								</button>
							</span>
							<input type="text" class="form-control" id="idQuantite1" value="1" disabled="disabled"/>
							<span class="input-group-btn">
								<button type="button" class="btn btn-default" id="buttonMoins" <s:if test="!disponible">disabled="disabled"</s:if>>
									<span class="glyphicon glyphicon-minus"></span>
								</button>
							</span>
						</div>
					</div>
						
				</div>
			</td>
			<td>
				<s:url action="detailsProducteur" var="dp">
					<s:param name="idProducteur">
						<s:property value="idProducteur" />
					</s:param>
				</s:url>
				<span class="input-group-addon"><s:property value="prix" /> &euro;</span>
				<span class="input-group-addon"><a href="<s:property value='#dp'/>"><s:property value="raisonSociale" /></a></span>
				<span class="input-group-addon">Délais de livraison : <s:property value="delaiLivraison" /> jours</span>
				<p><s:property value="description" /></p>
				<h4>
					<span class="label label-default">
						<span class="glyphicon glyphicon-map-marker"></span> &nbsp;Adresse du producteur
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
	
	<span id="caretComments"><a><h3>Avis </h3><span id="chevronListe" class="glyphicon glyphicon-chevron-down"></span></a></span>
	
	<div id="listeComments">
		<s:iterator value="listeCommentaires">
			<div class="col-md-12">
				<div class="panel panel-default">
				
					<div class="panel-heading">
						<span class="text-capitalize"><s:property value="prenom"/> <s:property value="nom"/></span> | 
						<s:iterator status="stat" value="(5).{ #this }">
							<s:if test="note >= #stat.count"><span class="glyphicon glyphicon-star"></span></s:if>
							<s:else><span class="glyphicon glyphicon-star-empty"></span></s:else>
						</s:iterator> |
						<s:date name="tsCreation" format="dd/MM/yyyy hh:mm" />
					</div>
					
					<div class="panel-body">
						<div class="media">
							<div class="media-left">
								<img width="75" src="<s:property value="listeCommentaires.photo" default="images/default.jpg"/>" alt="Photo utilisateur">
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
</div>


