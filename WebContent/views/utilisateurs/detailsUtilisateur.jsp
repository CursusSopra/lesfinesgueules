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
				<s:property value="nom" />
				<s:property value="prenom" />
				<s:property value="ligneAdresse1" />
				<s:property value="codePostal" />
				<s:property value="ville" />
				<s:property value="tel" />
				<s:property value="email" />
				<s:property value="mdp" />
			</td>
		</tr>	
	</table>
	
</div>

