<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="row" id="divFormCommentaire">

	<form class="form-horizontal" id="FormCommentaire" method="post"
		action="<s:url action='commentaire'/>">
		
	<fieldset>
	<legend>Votre avis</legend>
	
		<div class="form-group">
			<label for="idCommentaire" class="col-sm-2 control-label">Commentaire</label>
			<div class="col-sm-6">
				<textarea class="form-control" name="avis" id="idCommentaire" rows="5" placeholder="Vous avez droit à 200 caractères">
				</textarea>
			</div>
		</div>
					
		<div class="form-group">
			<label for="idNote" class="col-sm-2 control-label">Note</label>
			<div class="col-sm-6">
					<input type="text" class="form-control input-lg" id="idNote"
						name="note" value="<s:property value="note"/>"
						placeholder="Note"> 
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg">Envoyer</button>
			</div>
		</div>
		</fieldset>	
	</form>
</div>

