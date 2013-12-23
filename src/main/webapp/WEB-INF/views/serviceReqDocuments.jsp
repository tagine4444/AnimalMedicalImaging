<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="span12">
		<fieldset>
			<div class="span12">
				<div class="span12">
					<legend>Documents </legend>


					<div class="span12">



						<div class="span12">
							<div class="control-group">
								<label class="control-label">Are you providing any
									documents <b><u>that are not uploaded</u></b> with this request? (emails, paper documents sent with a carrier etc.)</label>

								<div class="controls">
									<div class="btn-group" data-toggle="buttons-radio">
										<label class="radio inline input-micro"> 
											<form:radiobutton value="true" path="paperDocs" />Yes</label>
										
										<label class="radio inline input-mini"> 
											<form:radiobutton value="false" path="paperDocs" />No</label>
										 
									</div>
								</div>
							</div>
							
						</div>

						<div class="span12">
							<div class="control-group">
								<label class="control-label">Are you uploading any
									digital documents as part of this request?</label>

								<div class="controls">
									<div class="btn-group" data-toggle="buttons-radio">
									
										<label class="radio inline input-micro"> <form:radiobutton
												value="true" path="digitalDocs" />Yes</label> 
												
										<label class="radio inline input-mini"> <form:radiobutton
												value="false" path="digitalDocs" />No</label>
										
									</div>
								</div>
							</div>

						</div>
						<div class="span12">
							<span><b>Optional Notes about documents</b></span><br/>
							<i>It is helpful to indicate the number of documents you are uploading if you know it</i>
							<form:textarea id="docsNotesId" path="docsNotes" rows="4"
								class="input-block-level" maxlength="1000" />
						</div>



					</div>
				</div>
			</div>
		</fieldset>
	</div>
</div>