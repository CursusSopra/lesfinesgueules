<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<div class="row" id="divFormRecherche">
	<div class="col-md-8 col-md-offset-2">

	<form  id="formRecherche" method="post" action="<s:url action='listeProduits' />">
		<fieldset>
		<legend > Recherche par type :	</legend>
		
		<div class="row">
			<div class="col-md-4">
				<div class="form-group">
			
					<label class="control-label">Type 1</label>
					 <select name="idType1" class="form-control">
					 	<option value="-1"></option>
					 	<s:iterator value="listeTypes1">
						  <option value="<s:property value="key"/>">
						  <s:property value="value"/>
						  </option>
						</s:iterator>
					</select> 
				</div>
			</div>
			
			<div class="col-md-4 col-md-offset-2">
				<div class="form-group">
					<label>Type 2</label>
					 <select name="idType2" class="form-control">
					 	<option value="-1"></option>
					 	<s:iterator value="listeTypes2">
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
				<div class="col-md-4">
				<label>Producteurs</label>
					 <select name="idProducteur" class="form-control">
						<option value="-1"></option>
					 	<s:iterator value="listeProducteurs">
						  <option value="<s:property value="key"/>">
						  <s:property value="value"/>
						  </option>
						</s:iterator>
					</select> 
				</div>
				
			</div>
		</fieldset>
		
		<div class="row">
			<div class="form-group">
				<div class="col-md-4 col-md-offset-8">
					<button type="submit" class="btn btn-primary">Rechercher</button>
				</div>
			</div>
		</div>
	</form>



	</form>
	</div>
</div>

