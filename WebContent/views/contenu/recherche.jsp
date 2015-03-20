<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="row" id="divFormRecherche">
	<div class="col-md-8 col-md-offset-2">
	
	<form class="form-horizontal" id="formRecherche" method="post" action="<s:url action='' />">
		<fieldset>
		<legend > Recherche par type :	</legend>
		
		<div class="row">
			<div class="form-group">
				<label class="col-md-4 control-label">Type 1</label>
				<div class="col-sm-4">
					 <select name="listeDesTypes1">
					 	<s:iterator value="listeDesTypes1">
						  <option value="<s:property value="key"/>">
						  <s:property value="value"/>
						  </option>
						</s:iterator>
					</select> 
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-4 control-label">Type 2</label>
				<div class="col-sm-4">
					 <select name="idType2">
					 	<s:iterator value="listeDesTypes2">
						  <option value="<s:property value="key"/>">
						  <s:property value="value"/>
						  </option>
						</s:iterator>
					</select> 
				</div>
			</div>
			
		</div>
		</fieldset>
		
		<fieldset>
		<legend > Recherche par producteurs :	</legend>
			<div class="row">
				<div class="form-group">
					<label class="col-md-4 control-label">Producteurs</label>
					<div class="col-sm-4">
						 <select name="idProducteurs">
						 	<s:iterator value="listeDesProducteurs">
							  <option value="<s:property value="key"/>">
							  <s:property value="value"/>
							  </option>
							</s:iterator>
						</select> 
					</div>
				</div>
			</div>
		</fieldset>
	</form>

</div>

