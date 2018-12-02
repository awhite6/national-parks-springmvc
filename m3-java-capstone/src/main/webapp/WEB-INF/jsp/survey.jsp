<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/common/header.jsp" />

<c:url value="/handleSurvey" var="newSurveyURL" />

<div class="main-content">
	<div id="survey-content">
		<h1>National Parks Survey</h1>
		<h3>Have you visited a National Park recently that was exceptional? Vote for it on our survey and let other users know which one is your top pick!</h3>
		<h5>This survey is brought to you by the National Parks Assocation. NPA  2018</h5>
		<c:url var="handleSurveyUrl" value="/handleSurvey" />
		<form:form action="${handleSurveyUrl}" method="POST" modelAttribute="survey">         
	        <div id="survey-inline-block-tag">
	        	<div class="field-names">
	        		<strong>National Park:</strong>
	        	</div>		
		        <div class="field-names">
		        	<strong> Email: </strong>
		        </div>
		        <div class="field-names">
		        	<strong>State of Residency: </strong>
		        </div>
		        <div class="field-names">
		        	<strong>Activity Level: </strong>
		        </div>
	       	</div>
			<div id="field-input-box">
				<div class="field-input">
					<form:select path="parkCode">
						<c:forEach var="park" items="${parks}">
							<option value="${park.parkCode }">${park.parkName }</option>
						</c:forEach>
					</form:select>
				</div>
		
		        <div class="field-input">
		            <form:input path="emailAddress" />
		            <form:errors path="emailAddress" />
		        </div>
		        
		        <div class="field-input">
		       		<c:import url="/WEB-INF/etc/stateList.jsp" />
		     		<form:errors path="state" />
		        </div>
		        
		        <div class="field-input" id="activity-level">
		            <form:radiobutton path="activityLevel" value="extremeley active" />  Extremeley Active
		            <form:radiobutton path="activityLevel" value="active" />	Active
		            <form:radiobutton path="activityLevel" value="sedentary"/> Sedentary	
		            <form:radiobutton path="activityLevel" value="inactive" />	Inactive
		        </div>
		    </div>
		
		    <div id="survey-submit">
		        <input type="submit" value="submit"/>
		    </div>
		</form:form>
		</div>
	</div>

<c:import url="/WEB-INF/common/footer.jsp" />