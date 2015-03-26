<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<h1 class="h1">
		<s:property value="raisonSociale" />
	</h1>
	
	<ul class="media">	
		<li class="media">
			<div class="media-left media-top">
				<div><img alt="<s:property value="raisonSociale"/>"
				src="/lesfinesgueules/content/images/<s:property value="photo" default="images/default.jpg"/>" 
	 				width="400">
	 			</div>
			</div>
			<div class="media-body">
				<h2 class="media-heading">
					<span class="label label-primary">&nbsp;Description </span>
				</h2>
				
				<div class="list-group">
	 				<a class="list-group-item"><s:property value="description" /></a>
	 				<s:if test="delaiLivraison>1">
	 				<a class="list-group-item">Délai de Livraison: <s:property value="delaiLivraison" /> jours</a>
	 				</s:if>
	 				<s:if test="delaiLivraison==1">
	 				<a class="list-group-item">Délai de Livraison: <s:property value="delaiLivraison" /> jour</a>
	 				</s:if>
				</div>
				<div class="media">
					<div class="media-left">
						<div id="map-canvas" class="bg-primary"></div>
					</div>
					<div class="media-body">
						<h2 class="media-heading">
							<span class="label label-primary"><span
								class="glyphicon glyphicon-map-marker"> </span>&nbsp;Adresse </span>
						</h2>
						
						<div class="list-group col-md-10">
			 				<a class="list-group-item"><s:property value="raisonSociale" /></a>
							  <a class="list-group-item"><s:property value="ligneAdresse1" /></a>
							  <s:if test="ligneAdresse2!=''">
							  <a class="list-group-item"><s:property value="ligneAdresse2"/></a>
							  </s:if>
							  <a class="list-group-item"><s:property value="codePostal" /> </a>
							  <a class="list-group-item"><s:property value="ville" /></a>
						</div>
					</div>
				</div>
			</div>
		
		</li>
	</ul>
	
	

<!-- 	<table class="table tableverticalalign"> -->
<!-- 		<tr> -->
<%-- 			<td><img alt="<s:property value="raisonSociale"/>" --%>
<%-- 				src="/lesfinesgueules/content/images/<s:property value="photo" default="images/default.jpg"/>" --%>
<!-- 				width="400"></td> -->
<%-- 			<td><span class="input-group-addon">Délais de livraison : --%>
<%-- 					<s:property value="delaiLivraison" /> jours --%>
<%-- 			</span> --%>
<!-- 				<p> -->
<%-- 					<s:property value="description" /> --%>
<!-- 				</p></td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

	<input type="hidden" class="form-control" id="latitude" name="latitude"
		value="<s:property value="latitude" />"> <input type="hidden"
		class="form-control" id="longitude" name="longitude"
		value="<s:property value="longitude" />">


<!-- 	<div class="media"> -->
<!-- 		<div class="media-left media-top"> -->
<!-- 			<div id="map-canvas" class="bg-primary"></div> -->
<!-- 		</div> -->
<!-- 		<div class="media-body"> -->
<!-- 			<h2 class="media-heading"> -->
<%-- 				<span class="label label-primary"><span --%>
<%-- 					class="glyphicon glyphicon-map-marker"> </span>&nbsp;Adresse </span> --%>
<!-- 			</h2> -->
			
<!-- 			<div class="list-group col-md-6"> -->
<%--  				<a class="list-group-item"><s:property value="raisonSociale" /></a> --%>
<%-- 				  <a class="list-group-item"><s:property value="ligneAdresse1" /></a> --%>
<%-- 				  <s:if test="ligneAdresse2!=''"> --%>
<%-- 				  <a class="list-group-item"><s:property value="ligneAdresse2"/></a> --%>
<%-- 				  </s:if> --%>
<%-- 				  <a class="list-group-item"><s:property value="codePostal" /> </a> --%>
<%-- 				  <a class="list-group-item"><s:property value="ville" /></a> --%>
<!-- 			</div> -->
			
<!-- 			<p> -->
<%-- 				<s:property value="ligneAdresse1" /> --%>
<!-- 				<br /> -->
<%-- 				<s:property value="ligneAdresse2" /> --%>
<!-- 				<br /> -->
<%-- 				<s:property value="codePostal" /> --%>
<!-- 				<br /> -->
<%-- 				<s:property value="ville" /> --%>
<!-- 			</p> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>