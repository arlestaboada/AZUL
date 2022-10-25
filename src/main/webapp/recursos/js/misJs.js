function onlyDigits(evt) {
         var charCode = (evt.which) ? evt.which : event.keyCode;        
	if (charCode > 31 && (charCode < 48 || charCode > 57))
            return true;
         return false;
}

