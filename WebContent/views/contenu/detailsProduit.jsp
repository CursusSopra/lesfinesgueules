<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s"	uri="/struts-tags"%>

<div class="row">
	<h1 class="h1"><s:property value="designation" /></h1>
	
	<table>
		<tr>
			<td><img alt="<s:property value="desgination"/>" src="<s:property value="photo"/>"></td>
			<td></td>
		</tr>	
	</table>
	
</div>