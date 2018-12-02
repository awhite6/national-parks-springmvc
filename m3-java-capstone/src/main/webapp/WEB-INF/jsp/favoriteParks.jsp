<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/common/header.jsp" />

<div class="main-content">
	<div class="favorites-survey-information">
		<h1> Survey Results</h1>
		<h3>Here are the top picked National Parks according to our wonderful patrons just like you. Click on a park to learn more about it.</h3>
	</div>
	<c:forEach var="park" items="${parksFromSurvey}">
		<c:url var="detailUrl" value="/parkDetails?parkcode=${park.parkCode }" />
		<a href="${detailUrl }" >
			<div class="favorites-row">
				<c:url var="imageUrl" value="/img/parks/${park.parkCode.toLowerCase() }.jpg" />
				<div class="favorites-image">
					<img src="${imageUrl }" />
				</div>
				<div class="favorites-park-information">
					<div class="favorites-name">
						<h1>${park.parkName }</h1>
					</div>
					<div class="favorites-count">
						<h2>Voted for: ${parkCodesFromSurvey[park.parkCode]} times</h2>
					</div>
				</div>
			</div>
		</a>
	</c:forEach>

</div>


<c:import url="/WEB-INF/common/footer.jsp" />