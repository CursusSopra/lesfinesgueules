<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1>La DreamTeam de "LesFinesGueules"</h1>

<div class="row">
<s:iterator value="listCollab">
	<div class="col-md-4 col-sm-6">
		<div class="card-container">
			<div class="card">
				<div class="front">
					<div class="cover">
						<img src="_pictures/<s:property value="coverPictures" />" />
					</div>
					<div class="user">
						<img class="img-circle" src="_pictures/<s:property value="identityPictures" />" />
					</div>
					<div class="content">
						<div class="main">
							<h3 class="name"><s:property value="completeName" /></h3>
							<p class="profession"><s:property value="profession" /></p>
							<h5>
								<i class="fa fa-map-marker fa-fw text-muted"></i>
								<s:property value="location" />
							</h5>
							<h5>
								<i class="fa fa-building-o fa-fw text-muted"></i>
								LesFinesGueules Inc.
							</h5>
							<h5>
								<i class="fa fa-envelope-o fa-fw text-muted"></i>
								<s:property value="email" />
							</h5>
						</div>
						<div class="footer">
							<div class="rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
						</div>
					</div>
				</div>
				<!-- end front panel -->
				<div class="back">
					<div class="header">
						<h5 class="motto"><s:property value="motto" /></h5>
					</div>
					<div class="content">
						<div class="main">
							<h4 class="text-center">Expérience</h4>
							<p><s:property value="experience" /></p>
							<h4 class="text-center">Domaines d'expertise</h4>
							<p><s:property value="skills" /></p>
						</div>
					</div>
					<div class="footer">
						<div class="social-links text-center">
							<a href="http://cretive-tim.com" class="facebook"><i
								class="fa fa-facebook fa-fw"></i></a> <a
								href="http://cretive-tim.com" class="google"><i
								class="fa fa-google-plus fa-fw"></i></a> <a
								href="http://cretive-tim.com" class="twitter"><i
								class="fa fa-twitter fa-fw"></i></a>
						</div>
					</div>
				</div>
				<!-- end back panel -->
			</div>
			<!-- end card -->
		</div>
		<!-- end card-container -->
	</div>
</s:iterator>
	
</div>