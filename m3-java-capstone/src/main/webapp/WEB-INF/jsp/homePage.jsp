<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 <c:import url="/WEB-INF/common/header.jsp" /> 

	<div class="main-content">
	
	<c:forEach var="park" items="${parks}"> 
	<c:url var="detailUrl" value="/parkDetails?parkcode=${park.parkCode }" />
		<a href="${detailUrl }" >
			<div class="park">
				<c:url var="imageUrl" value="/img/parks/${park.parkCode.toLowerCase()}.jpg" />
				<div class="park-image">
					<img src="${imageUrl }" alt="${park.parkCode }"/>	
				</div>
				<div class="park-description">
					<h1>${park.parkName}</h1>	
					<strong> ${park.state}</strong>
					<p>${park.parkDescription}</p>	
				</div>	
			</div>
		</a>
	</c:forEach>
	
	</div>




 <c:import url="/WEB-INF/common/footer.jsp" /> 