$(function() {
//	$('#datepicker').datepicker({
//		changeMonth: true,
//		changeYear: true,
//		dateFormat: 'mm/dd/yy',
//		onClose: function(dateText) {
//			renderAge(dateText);
//		}
//	});
//	
	toggleServiceType();
//	renderAge($('#datepicker').val());
});


$(function() {
    var availableProfessions = [
      "Veterinarian",
      "Tech",
      "Receptionist",
      "Admin"
    ];
    $( "#occupationId" ).autocomplete({
      source: availableProfessions
    });
  });

//function renderAge(dateText) {
//	var ageText = null;
//	
//	if (dateText) {
//		try {
//			// Define date of birth
//			var dobArray = dateText.split('/');
//			if (dobArray.length != 3 || dobArray[2].length != 4) {
//				throw "No valid Entry";	
//			}
//			
//			var dob = new Date();
//			dob.setUTCFullYear(dobArray[2]);
//			dob.setUTCMonth(dobArray[0]-1);
//			dob.setUTCDate(dobArray[1]);
//			dob.setUTCHours(0);
//			dob.setUTCMinutes(0);
//			dob.setUTCSeconds(0);
//			dob.setUTCMilliseconds(0);
//
//			// Define current wall date 
//			var now = new Date();
//			var nowUTC = new Date();
//			nowUTC.setUTCFullYear(now.getFullYear());
//			nowUTC.setUTCMonth(now.getMonth());
//			nowUTC.setUTCDate(now.getDate());
//			nowUTC.setUTCHours(0);
//			nowUTC.setUTCMinutes(0);
//			nowUTC.setUTCSeconds(0);
//			nowUTC.setUTCMilliseconds(0);
//			
//			// Calculate age
//			var age = new Date(nowUTC.getTime() - dob.getTime());
//			age.setUTCHours(0);
//			age.setUTCMinutes(0);
//			age.setUTCSeconds(0);
//			age.setUTCMilliseconds(0);
//			
//			var years = age.getUTCFullYear() - 1970;
//			var months = age.getUTCMonth();
//			var days = age.getUTCDate();
//			
//			if (years >= 0 && months >= 0 && days >= 0) {
//				ageText = "Age: ";
//				if (years > 0) {
//					ageText = ageText + years + "y ";
//				}
//				if (months > 0) {
//					ageText = ageText + months + "m ";
//				}
//				if (days > 0) {
//					ageText = ageText + days + "d";
//				}
//			}
//			
//		} catch (e) {
//			// Do nothing
//		}
//	}
//	
//	// Display age	
//	$(".age label").text(ageText == null ? "Age" : ageText);
//}

function toggleServiceType() {
	var value = $('input[name="interpretationOnly"]:checked').val();
	if (value === "false") {
		$('.imagingSvc').removeAttr("disabled");
	} else {
		$('.imagingSvc').attr("disabled", "disabled");
		$('.imagingSvc option').removeAttr("selected");
	}
}

function getBreedListForSpecies(species) {
	
}
