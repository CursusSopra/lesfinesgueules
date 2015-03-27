<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s"	uri="/struts-tags"%>

<div class="row">
	<h1 class="h1">Liste des producteurs</h1>

	<table class="table table-stripped table-hover">
		<thead>
			<tr>
				<th class="col-md-2"></th>
				<th class="col-md-10">Description</th>
			</tr>
		</thead>
		<tbody>
		
			<s:iterator value="listeProducteurs" status="stat">
				<s:iterator>
					<s:url action="detailsProducteur" var="dp">
						<s:param name="idProducteur">
							<s:property value="idProducteur" />
						</s:param>
					</s:url>
					<s:url action="modifyProducteurForm" var="mp">
						<s:param name="idProducteur">
							<s:property value="idProducteur" />
						</s:param>
					</s:url>
					<s:url action="modifyPhotoProducteurForm" var="mphoto">
						<s:param name="idProducteur">
							<s:property value="idProducteur" />
						</s:param>
					</s:url>
				
					<tr>
						<td><a href="<s:property value='#dp'/>"><img  alt="image" class="img-responsive img-thumbnail" width="200px" src="/lesfinesgueules/content/images/<s:property value="photo" default="default.jpg"/>" /></a></td>
						<td>
							<h4>
								<s:property value="raisonSociale" />
							</h4>
							<p>
								<s:property value="description" />
							</p>
								<div class="input-group">
									<span class="input-group-addon"><s:property value="delaiLivraison" /> jours</span>
									<a href="<s:property value='#dp'/>"><button type="button" class="btn btn-default">Voir les d&eacute;tails</button></a>
									<a href="<s:property value='#mp'/>"><button type="button" class="btn btn-info">Modifier le Producteur</button></a>
									<a href="<s:property value='#mphoto'/>"><button type="button" class="btn btn-info">Modifier Photo</button></a>
									
								</div>
						</td>
					</tr>
				</s:iterator>
			</s:iterator>
		
		</tbody>
	</table>

</div>