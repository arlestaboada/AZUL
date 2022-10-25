function getFechaHora() {

    var dias = new Array("Domingo", "Lunes", "Martes", "Miercoles", "Jueves",
        "Viernes", "Sabado");
    var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo",
        "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
        "Diciembre");

    d = new Date();
    hora = d.getHours();
    minuto = d.getMinutes();
    segundo = d.getSeconds();
    dia = d.getDate();
    mes = d.getMonth() + 1;
    if (hora < 10)
        hora = "0" + hora;
    if (minuto < 10)
        minuto = "0" + minuto;
    if (segundo < 10)
        segundo = "0" + segundo;
    if (dia < 10)
        dia = "0" + dia;
    if (mes < 10)
        mes = "0" + mes;

    var horaFecha = dia + "-" + mes + "-" + (d.getYear()) + " " + hora
    + ":" + minuto + ":" + segundo;
    try {
        document.getElementById("inReloj").value = horaFecha;
    } catch (e) {
        alert(e);
    }
    setTimeout("getFechaHora()", 1000);
}

/Valida entrada de teclado/
function fn_validaTecla(tipo, textbox) {
    var tecla;

    if (navigator.appName.indexOf("Netscape") != -1)
        tecla = event.which;
    else
        tecla = event.keyCode;

    var key = String.fromCharCode(tecla);
    var numeros = "0123456789";
    var sololetras = "_*-AaBbCcDdEeFfGgHhIiJjKkLlMmNn??OoPpQqRrSsTtUuVvWwXxYyZz??????????";
    var letras = "_*-AaBbCcDdEeFfGgHhIiJjKkLlMmNn??OoPpQqRrSsTtUuVvWwXxYyZz??????????,";
    var hexa = "0123456789AaBbCcDdEeFf";
    var fechas = "0123456789/";
    var email = "@.;";

    if (tipo == 'archivo') {
        if (tecla == 34)
            return false;
        if (tecla == 39)
            return false;
        if (tecla == 96)
            return false;
        return true;
    }

    if (tipo == 'email') {
        if (tecla == 34)
            return false;
        if (tecla == 39)
            return false;
        if (tecla == 32)
            return false;

        if ((numeros.indexOf(key) != -1) || (sololetras.indexOf(key) != -1)
            || (email.indexOf(key) != -1))
            return true;
        else
            return false;
    }

    if (tipo == 'nocomillas') {
        if (tecla == 39 || tecla == 34)
            return false;
        else
            return true;
    }

    if (tipo == 'letras') {
        if (tecla == 32)
            return true;

        if (letras.indexOf(key) != -1)
            return true;

        return false;
    }

    if (tipo == 'entero') {
        if (numeros.indexOf(key) != -1)
            return true;
        else
            return false;
    }

    if (tipo == 'decimal') {

        if (numeros.indexOf(key) != -1) {
            return true;
        }
        // 46
        if (tecla == 44) {
            var cadena = textbox.value;
            var iIndex = cadena.indexOf(',');

            if (iIndex == -1) {
                return true;
            }
        }

        return false;
    }

    if (tipo == "NoNumeros") {
        if (tecla == 32)
            return true;

        if (numeros.indexOf(key) != -1)
            return false;

        return true;

    }

    if (tipo == "hexadecimal") {
        if (tecla == 32)
            return true;
        if (tecla == 39)
            return false;
        if (hexa.indexOf(key) != -1)
            return true;
        return false;
    }

    if (tipo == "alfanumerico") {
        if (tecla == 34)
            return false;
        if (tecla == 39)
            return false;

        if ((numeros.indexOf(key) != -1) || (sololetras.indexOf(key) != -1))
            return true;
        else
            return false;
    }
    if (tipo == "fecha") {
        if ((fechas.indexOf(key) != -1))
            return true;
        else
            return false;
    }
    if (tipo == "horas") {

        if ((numeros.indexOf(key) != -1))
            return true;

        if (tecla == 58) {
            var cadena = textbox.value;
            var iIndex = cadena.indexOf(':');

            if (iIndex == -1) {
                return true;
            }
        }

        return false;

    }
    if (tipo == "alfanumericoypunto") {
        if ((numeros.indexOf(key) != -1) || (sololetras.indexOf(key) != -1)
            || key == '.')
            return true;
        else
            return false;
    }
    if (tipo == "alfanumericoydivision") {
        if ((numeros.indexOf(key) != -1) || (sololetras.indexOf(key) != -1)
            || key == '/')
            return true;
        else
            return false;
    }
    if (tipo == "alfanumericoypuntoyblanco") {
        if ((numeros.indexOf(key) != -1) || (sololetras.indexOf(key) != -1)
            || key == '.' || key == ' ')
            return true;
        else
            return false;
    }

}

/Deshabilita copia y pega/
function fn_DisableCopyPaste() {
    try {
        if (window.event
            && ((window.event.ctrlKey && window.event.keyCode == 86) || (window.event.shiftKey && window.event.keyCode == 45))) {
            return false;
        }
        return true;
    } catch (e) {
    }
}

function cerrar() {
    opener = null;
    window.close()
}



function cambiarMayuscula(el) {
    
    if(!el || !el.value) return;
    el.value = el.value.toUpperCase();
  
}


PrimeFaces.locales['es'] = {
    closeText: 'Cerrar',
    prevText: 'Anterior',
    nextText: 'Siguiente',
    monthNames: ['Enero','Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
    dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'],
    dayNamesShort: ['Dom','Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
    dayNamesMin: ['D','L','M','X','J','V','S'],
    weekHeader: 'Semana',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Sólo hora',
    timeText: 'Tiempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    currentText: 'Fecha actual',
    ampm: false,
    month: 'Mes',
    week: 'Semana',
    day: 'Día',
    allDayText : 'Todo el día'
};



function filterInput(filterType, evt, allowDecimal, allowCustom){
    var keyCode, Char, inputField, filter = '';
    var alpha = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ ';
    var num   = '0123456789';
    if(window.event){
        keyCode = window.event.keyCode;
        evt = window.event;
    }else if (evt)keyCode = evt.which;
    else return true;
    if(filterType == 0) filter = alpha;
    else if(filterType == 1) filter = num;
    else if(filterType == 2) filter = alpha + num;
    if(allowCustom)filter += allowCustom;
    if(filter == '')return true;
    inputField = evt.srcElement ? evt.srcElement : evt.target || evt.currentTarget;
    if((keyCode==null) || (keyCode==0) || (keyCode==8) || (keyCode==9) || (keyCode==13) || (keyCode==27) )return true;
    Char = String.fromCharCode(keyCode);
    if((filter.indexOf(Char) > -1)) return true;
    else if(filterType == 1 && allowDecimal && (Char == '.') && inputField.value.indexOf('.') == -1)return true;
    else if(filterType == 1 && allowDecimal && (Char == '-') && inputField.value.indexOf('-') == -1)return true;
    else return false;
}
function filterInputGrupo(filterType, evt, allowDecimal, allowCustom) {
    var keyCode, Char, inputField, filter = '';
    var alpha = 'abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ ';
    var num = '0123456789';
    if (window.event) {
        keyCode = window.event.keyCode;
        evt = window.event;
    } else if (evt) keyCode = evt.which;
    else return true;
    if (filterType == 0) filter = alpha;
    else if (filterType == 1) filter = num;
    else if (filterType == 2) filter = alpha + num;
    if (allowCustom) filter += allowCustom;
    if (filter == '') return true;
    inputField = evt.srcElement ? evt.srcElement : evt.target || evt.currentTarget;
    if ((keyCode == null) || (keyCode == 0) || (keyCode == 8) || (keyCode == 9) || (keyCode == 13) || (keyCode == 27)) return true;
    Char = String.fromCharCode(keyCode);
    if ((filter.indexOf(Char) > -1)) return true;
    else if (filterType == 1 && allowDecimal && (Char == '.') && inputField.value.indexOf('.') == -1) return true;
    else return false;
}      
function filterEnteros(filterType, evt, allowDecimal, allowCustom){
    var keyCode, Char, inputField, filter = '';
    var alpha = '';
    var num   = '0123456789';
    if(window.event){
        keyCode = window.event.keyCode;
        evt = window.event;
    }else if (evt)keyCode = evt.which;
    else return true;
    if(filterType == 0) filter = alpha;
    else if(filterType == 1) filter = num;
    else if(filterType == 2) filter = alpha + num;
    if(allowCustom)filter += allowCustom;
    if(filter == '')return true;
    inputField = evt.srcElement ? evt.srcElement : evt.target || evt.currentTarget;
    if((keyCode==null) || (keyCode==0) || (keyCode==8) || (keyCode==9) || (keyCode==13) || (keyCode==27) )return true;
    Char = String.fromCharCode(keyCode);
    if((filter.indexOf(Char) > -1)) return true;
    else if(filterType == 1 && allowDecimal && (Char == '.') && inputField.value.indexOf('.') == -1)return true;
    else return false;
}

function ValidaInputTextMessageAndLenMessage(e) {
    var valid = 'ABCDEFGHIJKLMNÑOPQRSTUVWXYZ 0123456789()/\&_-:.,[]{}#';        
    var keyAscii = (document.all) ? event.keyCode : e.which;

    if (keyAscii != 13) {
        var key = String.fromCharCode(keyAscii);
        var keyAj = key.toUpperCase();
        if (valid == '') {
            return true;
        }
        if (valid.indexOf(keyAj) == "-1") {
            if (keyAscii != 8 && keyAscii != 0) { return false };
            return true;
        }
        return true;
    }
}
function ValidaInputTextHora(e) {
    var valid = '0123456789:';
    var keyAscii = (document.all) ? event.keyCode : e.which;
    if (keyAscii != 13) {
        var key = String.fromCharCode(keyAscii);
        var keyAj = key.toUpperCase();
        if (valid == '') {
            return true;
        }
        if (valid.indexOf(keyAj) == "-1") {
            if (keyAscii != 8 && keyAscii != 0) { return false };
            return true;
        }
        return true;
    }
}
function ValidaInputTextSoloLetras(e) {
    var valid = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    var keyAscii = (document.all) ? event.keyCode : e.which;
    if (keyAscii != 13) {
        var key = String.fromCharCode(keyAscii);
        var keyAj = key.toUpperCase();
        if (valid == '') {
            return true;
        }
        if (valid.indexOf(keyAj) == "-1") {
            if (keyAscii != 8 && keyAscii != 0) { return false };
            return true;
        }
        return true;
    }
}
function getObject(obj) {
  var theObj;
  if(document.all) {
    if(typeof obj=="string") {
      return document.all(obj);
    } else {
      return obj.style;
    }
  }
  if(document.getElementById) {
    if(typeof obj=="string") {
      return document.getElementById(obj);
    } else {
      return obj.style;
    }
  }
  return null;
}

function toCount(entrance,exit,text,characters) {
  var entranceObj=getObject(entrance);
  var exitObj=getObject(exit);
  var length=characters - entranceObj.value.length;
  if(length <= 0) {
    length=0;
    text='<span class="disable"> '+text+' </span>';
    entranceObj.value=entranceObj.value.substr(0,characters);
  }
  exitObj.innerHTML = text.replace("{CHAR}",length);
}

/* validar correo*/
function doCheckEmail(email) 
{
	str = email.value;
	var at="@"
	var dot="."
	var lat=str.indexOf(at)
	var lstr=str.length
	var ldot=str.indexOf(dot)

	if (str=='') {return true};

	if (str.indexOf(at) == -1) {
	    alert(" Email inválido...");
		email.focus();
		return false;
	}

	if (str.indexOf(at) == -1 || str.indexOf(at) == 0 || str.indexOf(at) == lstr) {
	    alert(" Email inválido...");
		email.focus();
		return false;
	}

	if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0 || str.indexOf(dot) == lstr) {
	    alert(" Email inválido...");
		email.focus();
		return false;
	}

	if (str.indexOf(at, (lat + 1)) != -1) {
	    alert(" Email inválido...");
		email.focus();
		return false;
	}

	if (str.substring(lat - 1, lat) == dot || str.substring(lat + 1, lat + 2) == dot) {
	    alert(" Email inválido...");
		email.focus();
		return false;
	}

	if (str.indexOf(dot, (lat + 2)) == -1) {
	    alert(" Email inválido...");
		email.focus();
		return false;
	}

	if (str.indexOf(" ")!=-1){
	    alert(" Email inválido...");
		email.focus();
		return false;
	}
	return true;			
}


function doCheckEmail2(email) {
    str = email.value;
    var at = "@"
    var dot = "."
    var lat = str.indexOf(at)
    var lstr = str.length
    var ldot = str.indexOf(dot)

    if (str == '') { return true };

    if (str.indexOf(at) == -1) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
        email.focus();
        return false;
    }

    if (str.indexOf(at) == -1 || str.indexOf(at) == 0 || str.indexOf(at) == lstr) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
        email.focus();
        return false;
    }

    if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0 || str.indexOf(dot) == lstr) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
        email.focus();
        return false;
    }

    if (str.indexOf(at, (lat + 1)) != -1) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
        email.focus();
        return false;
    }

    if (str.substring(lat - 1, lat) == dot || str.substring(lat + 1, lat + 2) == dot) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
        email.focus();
        return false;
    }

    if (str.indexOf(dot, (lat + 2)) == -1) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
        email.focus();
        return false;
    }

    if (str.indexOf(" ") != -1) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
        email.focus();
        return false;
    }
    return true;
}

function doCheckEmail3(email) {
    str = email.value;
    var at = "@"
    var dot = "."
    var lat = str.indexOf(at)
    var lstr = str.length
    var ldot = str.indexOf(dot)

    if (str == '') { return true };

    if (str.indexOf(at) == -1) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
//        email.focus();
        return false;
    }

    if (str.indexOf(at) == -1 || str.indexOf(at) == 0 || str.indexOf(at) == lstr) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
//        email.focus();
        return false;
    }

    if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0 || str.indexOf(dot) == lstr) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
//        email.focus();
        return false;
    }

    if (str.indexOf(at, (lat + 1)) != -1) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
//        email.focus();
        return false;
    }

    if (str.substring(lat - 1, lat) == dot || str.substring(lat + 1, lat + 2) == dot) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
        email.focus();
        return false;
    }

    if (str.indexOf(dot, (lat + 2)) == -1) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
//        email.focus();
        return false;
    }

    if (str.indexOf(" ") != -1) {
        //alert(" Email inválido...");
        MsgBox_3('Ingrese un email con formato correcto', 'a', '1', '');
//        email.focus();
        return false;
    }
    return true;
}


function checkDecimals(fieldName, fieldValue) {

decallowed = 3;  // how many decimals are allowed?

if (isNaN(fieldValue) || fieldValue == "" || fieldValue == "0.00") {
fieldName.select();
fieldName.focus();
}
else {
if (fieldValue.indexOf('.') == -1) fieldValue += ".";
dectext = fieldValue.substring(fieldValue.indexOf('.')+1, fieldValue.length);

if (dectext.length >= decallowed)
{
return false;
fieldName.focus();
      }
else {
return true;
      }
   }
}

function calcularEdad(fecha) {
    var hoy = new Date();
    var cumpleanos = new Date(fecha);
    var edad = hoy.getFullYear() - cumpleanos.getFullYear();
    var m = hoy.getMonth() - cumpleanos.getMonth();

    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }

    document.querySelector("#txtEdad").textContent = calcularEdad(fecha);
}
