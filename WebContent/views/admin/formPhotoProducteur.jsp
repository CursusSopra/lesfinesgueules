<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">

		<fieldset>
		<legend>Modification Photo</legend>
	
		<input type="hidden" class="form-control" id="idProducteur" name="idProducteur" 
											value="<s:property value="idProducteur" />" >
											
											<div class="form-group">
		<label for="idPhotoProducteur" class="col-sm-2 control-label">Photo de votre producteur : </label>
				<div class="col-sm-6">
					<input type="file" name="userImage" id="idPhotoProducteur" />
				</div>
			</div>
		</fieldset>
	
</div>