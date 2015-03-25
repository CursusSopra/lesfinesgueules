<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s"	uri="/struts-tags"%>

<div class="row">
	<h1 class="h1">
		<s:property value="raisonSociale" />
	</h1>
	
	<table class="table tableverticalalign">
		<tr>
			<td><img alt="<s:property value="raisonSociale"/>" src="/lesfinesgueules/content/images/<s:property value="photo" default="images/default.jpg"/>" width="400"></td>
			<td>
				<span class="input-group-addon">Délais de livraison : <s:property value="delaiLivraison" /> jours</span>
				<p><s:property value="description" /></p>
				<h4>
					
					<span class="label label-default">
						<span class="glyphicon glyphicon-map-marker"></span>&nbsp;Adresse
					</span>
				</h4>
				<div id="map-canvas"  class="bg-primary"></div>
				<p>
					<s:property value="ligneAdresse1"/><br/>
					<s:property value="ligneAdresse2"/><br/>
					<s:property value="codePostal"/><br/>
					<s:property value="ville"/>
				</p>
			</td>
		</tr>	
	</table>
	
</div>