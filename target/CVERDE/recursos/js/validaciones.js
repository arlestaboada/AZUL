/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




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
