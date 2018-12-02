<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/common/header.jsp" />

<div class="main-content"> 
	<div id="detail-view">
		<div class="details-top">
			<c:url var="imageUrl" value="/img/parks/${park.parkCode.toLowerCase()}.jpg" />
			<div class="details-image">
				<img src="${imageUrl }" alt="${park.parkCode }"/>
			</div>	
			<div class="park-details-name">
				<h1>${ park.parkName} </h1>
				<h3>"${park.inspirationalQuote}"</h3>
				<h4>-${park.inspirationalQuoteSource}</h4>
				<div class="detail-description">
					<p>${ park.parkDescription } </p>
				</div>
			</div>
		</div>
		<div id="details-block-holder">
			<div class="details-block">
				<strong>Park State: </strong><p> ${park.state } </p>
				<br>
				<strong>Entry Fee:</strong><p> $${park.entryFee } </p>
				<br>
				<strong>Total Acreage:</strong><p> ${park.acreage } </p>
			</div>
			<div class="details-block">
				<strong>Elevation (ft): </strong><p>${park.elevationInFeet } </p>
				<br>
				<strong># Of Camp Sites:</strong><p> ${park.numberOfCampsites } </p>
				<br>
				<strong>Climate:</strong><p> ${park.climate } </p>
			</div>
			<div class="details-block">
				<strong>Year Founded:</strong><p>${park.yearFounded } </p>
				<br>
				<strong>Annual Visitors: </strong><p>${park.annualVisitorCount } </p>
				<br>
				<strong># Of Animal Species: </strong><p> ${park.numberOfAnimalSpecies } </p>
			</div>
		</div>
	</div>
	
	<div id="weather">
		<h1 style="display: inline-block">Five Day Forecast</h1>		
		<c:url var="changeTempUrl" value="/changeMyTempTypeUrl"/>
		<c:choose>
			<c:when test="${temperatureScale == 'F' }">
		 		<form action="${changeTempUrl }"  method="POST">
		 			<input type="hidden" name="parkCode" value="${park.parkCode }" />
		 			<input type="hidden" name="degreeType" value="C" />
					<input type="submit" value="change to celcius" />
				</form> 
			</c:when>
			<c:when test="${temperatureScale == 'C' }">
		 		<form action="${changeTempUrl }"  method="POST">
		 			<input type="hidden" name="parkCode" value="${park.parkCode }" />
		 			<input type="hidden" name="degreeType" value="F" />
					<input type="submit" value="change to Fahrenheit" />
				</form> 
			</c:when>
			<c:otherwise>
		 		<form action="${changeTempUrl }" method="POST">
		 			<input type="hidden" name="parkCode" value="${park.parkCode }" />
		 			<input type="hidden" name="degreeType" value="C" />
					<input type="submit" value="change to celcius" />
				</form>
			</c:otherwise>
		</c:choose>
		<c:set var="count" value="1" />
		<c:forEach var="weather" items="${weatherList }">
			<c:url var="partlyCloudy" value="/img/weather/partlycloudy.png" />
			<c:choose>
				<c:when test="${count == 1 }">
					<div id="weather-today">
						<h2>Today</h2>
						<c:url var="weatherImage" value="/img/weather/${weather.forecast}.png" />
						<c:choose>
							<c:when test="${weather.forecast == 'partly cloudy' }">'
								<img src="${partlyCloudy }" alt="partlyCloudy"/>
							</c:when>
							<c:otherwise>
								<img src="${weatherImage }" />
							</c:otherwise>
						</c:choose>
						<div class="high-and-low-today">
							<p>High: ${weather.high } ${weather.degreeType }</p>
							<p>Low: ${weather.low } ${weather.degreeType } </p>
						</div>
						<div id="weather-advisory">
							<strong>${weather.weatherAdvisory }</strong>
						</div>
					</div>
				</c:when>
				<c:otherwise> 
					<div class="weather-tile">
						<c:url var="weatherImage" value="/img/weather/${weather.forecast}.png" />
						<c:choose>
							<c:when test="${weather.forecast == 'partly cloudy' }">
								<div class="weather-image">
									<img src="${partlyCloudy }" alt="uhh son"/>
								</div>
							</c:when>
							<c:otherwise>
							<div class="weather-image">
								<img src="${weatherImage }" />
							</div>
							</c:otherwise>
						</c:choose>
						<div class="high-and-low">
							<p>High: ${weather.high } ${weather.degreeType }</p>
							<p>Low: ${weather.low } ${weather.degreeType }</p>
						</div>				
					</div>
				</c:otherwise>
			</c:choose>
			<c:set var="count" value="${count + 1}" />
		</c:forEach>
	</div>
</div>

<c:import url="/WEB-INF/common/footer.jsp" />