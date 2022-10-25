function justNumbersEnter(e, component) {
	var keynum = window.event ? window.event.keyCode : e.which;
	
	if (keynum == 13) { 
		document.getElementById(component).onclick(); 
		return false; 
	} 
	
	
	if ( keynum == 8 ) return true;
	return /\d/.test(String.fromCharCode(keynum));
}

function justNumbers(e) {
	var keynum = window.event ? window.event.keyCode : e.which;
					
	if ( keynum == 8 ) return true;
	return /\d/.test(String.fromCharCode(keynum));
}	

function evaluarResultado(widgetVar,resultado){
	if(resultado){
		widgetVar.hide();
	}
}

function resultadoRegistroformato(xhr,status,args){
	var resultado=args.registroFormatoOK;
	evaluarResultado(dlgNuevoFormatowv,resultado);
}

