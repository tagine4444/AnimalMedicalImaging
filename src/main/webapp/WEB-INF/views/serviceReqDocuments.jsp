<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<script type="text/javascript">
	function emailTips(checkBox, anElementId )
	{
		if (checkBox.checked == 1)
		{
	          document.getElementById(anElementId).style.visibility='visible';
		}
		else
		{
			document.getElementById(anElementId).style.visibility='hidden';
		}
	}
</script>



<div class="row">
	<div class="span12">
		<fieldset>
			<div class="span12">
				<div class="span12">
					<legend>Images / Documents</legend>




					<div class="span12">

						<div class="span12">
							<div class="control-group">
								<label class="control-label">I am providing Images / documents with this request</label>

								<div class="span12">
									<label class="checkbox inline">
									<form:checkbox path="docByUpload" id="uploadCheckBoxId" onchange="emailTips(this, 'uploadUlId')"/>By Upload</label>
									
									<label class="checkbox inline">
									<form:checkbox path="docByCarrier"  onchange="emailTips(this, 'carrierUlId')" />By Carrier</label>
									
									<label class="checkbox inline">
									<form:checkbox path="docByEmail" onchange="emailTips(this, 'emailUlId')" />By Email</label>
								</div>
							</div>
							
						</div>

						<div class="span12">
							<div class="span12">
								<ul >
									<li id="emailUlId" style="visibility: hidden;" >Please provide the Request Number in the subject of the email. The Request Number is shown at the top of the page after successfully submitting this Request</li>
									<li id="carrierUlId" style="visibility: hidden;">Please print this request and include it in the carrier's envelope</li>
									<li id="uploadUlId" style="visibility: hidden;">Please indicate in the notes section above the number of documents will be uploading if you know it</li>
								</ul>
								<form:textarea id="docsNotesId" path="docsNotes" rows="4" placeholder="Please Provide any details that will help us identify ALL the documents that go with this request." 
									class="input-block-level" maxlength="1000" />
							</div>



					</div>
				</div>
			</div>
		</fieldset>
	</div>
</div>