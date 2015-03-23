<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="row" id="divFormRecherche">
	<div class="col-md-8 col-md-offset-2">
	
	<form  id="formRecherche" method="post" action="<s:url action='' />">
		<fieldset>
		<legend > Recherche par type :	</legend>
		
		<table>
		
			<tr>
			<td>
			
				<label>Type 1</label>
					 <select name="idType1">
					 	<s:iterator value="listeTypes1">
						  <option value="<s:property value="key"/>">
						  <s:property value="value"/>
						  </option>
						</s:iterator>
					</select> 
					
			</td>
			<td>
			
					<label>Type 2</label>
					 <select name="idType2">
					 	<s:iterator value="listeDesTypes2">
					 	<input type="checkbox"id="idType2" name="type2" value="-1">
						  <option value="<s:property value="key"/>">
						  <s:property value="value"/>
						  </option>
						</s:iterator>
					</select> 
			
			</td>
			</tr>
		</table>
		</fieldset>
		
		<fieldset>
		<legend > Recherche par producteurs :	</legend>
			<div class="row">
				<div class="form-group">
					<label class="col-md-4">Producteurs</label>
					<div class="col-md-4">
						 <select name="idProducteur">
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
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg">Enregistrer</button>
			</div>
		</div>
	</form>
	</div>
</div>

