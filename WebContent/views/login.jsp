<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">

	<form class="form-horizontal" id="idLogin" method="post" action="<s:url action='loginControl'/>">
		
		<fieldset>
			<legend> Vos données personnelles</legend>
			
			<div class="form-group">
				<label for="idEmail" class="col-sm-2 control-label">Votre email</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="idEmail"
						name="email" value="<s:property value="email" />"/> 
				</div>
			</div>
			<div class="form-group">
				<label for="idMdp" class="col-sm-2 control-label">Votre mot de passe</label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="idMdp"
						name="mdp" /> 
				</div>
			</div>
		</fieldset>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg">Je m'identifie</button>
			</div>
		</div>
	</form>
</div>
