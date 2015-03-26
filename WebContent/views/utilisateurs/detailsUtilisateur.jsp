<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="row">
	<div class="col-xs-10 col-xs-offset-1">
		<h1>
			Votre compte utilisateur
		</h1>
	</div>
</div>
<div>
	<table class="table tableverticalalign">
		<tr>
			<td>
				<p>
				Nom : <s:property value="nom" />
				</p>
				<p>
				Prenom : <s:property value="prenom" />
				</p>
				<p>
				Rue : <s:property value="ligneAdresse1" />
				</p>
				<p>
				Code postal : <s:property value="codePostal" />
				</p>
				<p>
				Ville : <s:property value="ville" />
				</p>
				<p>
				Téléphone : <s:property value="tel" />
				</p>
			</td>
		</tr>	
	</table>
	
</div>