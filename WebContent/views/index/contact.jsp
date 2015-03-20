<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="row">
	<div class="col-xs-10 col-xs-offset-1">
		<h1>
			<s:property value="cinema.nom" />
		</h1>
	</div>
</div>


<div class="row">
	<div class="col-xs-10 col-lg-offset-1">
		<h2 class="text-primary">DESCRIPTION</h2>
		
		<br> <br>
	</div>
</div>



<!-- Pager -->
<ul class="pager">
	<li class="previous"><a
		href="<s:url action="listingDesCinemas"></s:url>">&larr; Liste</a></li>
</ul>