<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<h2>Liste des Types1</h2>
	<ul class="list-group">


		<s:iterator value="listeType1">

			<li class="list-group-item">
				<!--L'URL après href sert d'a --> <a href="#"
				id="<s:property value="libelle1"/>" data-type="select"
				data-pk="<s:property value="idType1" />"
				data-url="<s:url action="modifType1"/>"
				data-title="Enter une nouvelle valeur de libelle1"> <s:property
						value="libelle1" />
			</a>
			</li>

		</s:iterator>



	</ul>
	<TABLE BORDER="1">
		<CAPTION>Modification d'un producteur</CAPTION>
		<TR>
			<TH>Raison sociale du producteur : </TH>
			<TD>
				<a href="#"
					id="<s:property value="raisonSociale"/>" data-type="text"
					data-pk="<s:property value="idProducteur" />"
					data-url="<s:url action="modifyProducteur"/>"
					data-title="Enter une nouvelle valeur de raisonSociale">
						<s:property value="raisonSociale"/>
				</a>
			</TD>
		</TR>
		<TR>
			<TH>SIREN : </TH>
			<TD>
				<a href="#"
					id="<s:property value="siren"/>" data-type="text"
					data-pk="<s:property value="idProducteur" />"
					data-url="<s:url action="modifyProducteur"/>"
					data-title="Enter une nouvelle valeur de SIREN">
						<s:property value="siren"/>
				</a>
			</TD>
		</TR>
<!-- 		<TR> -->
<!-- 			<TH>Délai de livraison : </TH> -->
<!-- 			<TD> -->
<!-- 				<a href="#" -->
<%-- 					id="<s:property value="libelle1"/>" data-type="select" --%>
<%-- 					data-pk="<s:property value="idProducteur" />" --%>
<%-- 					data-url="<s:url action="modifyProducteur"/>" --%>
<!-- 					data-title="Enter une nouvelle valeur de libelle1"> -->
<!-- 						Valeur -->
<!-- 				</a> -->
<!-- 			</TD> -->
<!-- 		</TR> -->
		<TR>
			<TH>Description : </TH>
			<TD>
				<a href="#"
					id="<s:property value="description"/>" data-type="textarea"
					data-pk="<s:property value="idProducteur" />"
					data-url="<s:url action="modifyProducteur"/>"
					data-title="Enter une nouvelle description">
						<s:property value="description"/>
				</a>
			</TD>
		</TR>
		<TR>
			<TH>Adresse : </TH>
			<TD>
				<a href="#"
					id="<s:property value="libelle1"/>" data-type="select"
					data-pk="<s:property value="idProducteur" />"
					data-url="<s:url action="modifyProducteur"/>"
					data-title="Enter une nouvelle valeur de libelle1">
						Valeur
				</a>
			</TD>
		</TR>
	</TABLE>
</div>