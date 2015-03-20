<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s"	uri="/struts-tags"%>

<div class="row">
	<h1 class="h1">Liste des produits</h1>
	
	<table class="table table-stripped table-hover">
		<thead>
			<tr>
				<th class="col-md-4"></th>
				<th class="col-md-8">Description</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<img alt="image" class="img-responsive img-thumbnail" width="200px" src="images/default.jpg" />
				</td>
				<td>
				<s:iterator value="listeProduits" status="stat">
					<h4> <s:property value="designation" /> </h4>
					<p> <s:property value="description" /> </p>
					<p> <s:property value="prix" /> </p>
				</s:iterator>
<!-- 					<h4>Nom de mon produit</h4> -->
<!-- 					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris mollis enim nulla, eu pharetra nisl porta a. Sed sed pretium metus. Donec vitae nisl bibendum, ullamcorper ex quis, imperdiet purus. Vestibulum fringilla velit a tempor rutrum. Pellentesque orci mauris, ultrices nec orci a, fermentum suscipit metus. Sed nec vulputate ante. Vivamus massa turpis, sodales sed sollicitudin in, facilisis a tortor. Cras sit amet orci tortor. Quisque auctor facilisis orci. Proin nec tortor pharetra, mattis ligula sed, placerat sapien. Vestibulum accumsan tempor metus non pellentesque. Vestibulum varius tristique diam volutpat consequat. Morbi porttitor porta efficitur. Fusce rutrum felis sem, sit amet interdum eros placerat a. Duis pellentesque mauris nunc, vitae vehicula odio scelerisque sed. Ut est lectus, efficitur eget nisl nec, venenatis sodales est.</p> -->
<!-- 					<p>Prix : 9€20</p> -->
				</td>
			</tr>
		</tbody>
	</table>
	
</div>