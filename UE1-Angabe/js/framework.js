/*
    Helper functions for the first exercise of the Web Engineering course
*/

var birthdateOK = true;
var passwordOK = false;
var userOK = false;

var hasFormValidation = hasFormValidation();
var hasNativeDateInput = hasNativeDateInput();
/* 
    checks if native form validation is available.
    Source/Further informations: http://diveintohtml5.info/everything.html
*/
function hasFormValidation() {
    return 'noValidate' in document.createElement('form');
}

/* 
    checks if native date input is available.
    Source/Further informations: http://diveintohtml5.info/everything.html
*/
function hasNativeDateInput(){
    var i = document.createElement('input');
    i.setAttribute('type', 'date');
    return i.type !== 'text';
}

var DATE_DELIMITERS = ['/','\\','-'];

/*
    returns the string representation of a date input field in the format dd.mm.yyyy.
    If the value of the input field can't be interpreted as a date, the original value is returned.
*/
function getNormalizedDateString(selector){
    value = $(selector).val();
    for(i = 0; i < DATE_DELIMITERS.length; i++){
        value = value.split(DATE_DELIMITERS[i]).join(".");
    }
    rehtml5 = /^(\d{4})\.(\d{1,2})\.(\d{1,2})$/;
    if(regs = value.match(rehtml5)){
        return regs[3] + "." + regs[2] + "." + regs[1];
    }
    return value;
}

/*
    returns the string representation of the given value (seconds) in the format mm:ss.
*/
function secToMMSS(value){
    var minutes = Math.floor(value / 60);
    var seconds = (value % 60);
    
    if(seconds < 10){
        seconds = "0" + seconds;
    }
    if(minutes < 10){
        minutes = "0" + minutes;
    }
    return minutes + ":" + seconds;
}

/*function initialize(){
    if(hasFormValidation){
        if(hasNativeDateInput){
        } else {
            document.getElementById('geburtstag').onchange = function validateDate2(){
                validateDate();
            };
            //$('#geburtstag').onchange = validateDate();
            //document.forms["mainForm"].elements["geburtstag"]. = validateDate();
            //document.forms["mainForm"].elements["geburtstag"].pattern = (\d{1,2})\.(\d{1,2})\.(\d{4});
        }
    }
} */

function validateDate() {
    if (hasFormValidation && hasNativeDateInput) {
        birthdateOK = document.forms["mainForm"].elements["geburtstag"].checkValidity();
    } else {
        var text = getNormalizedDateString("#geburtstag");
        if (text.match(/^(\d{1,2})\.(\d{1,2})\.(\d{4})$/) == null && text != ''){
            birthdateOK = false;
            document.forms["mainForm"].elements["geburtstag"].style.borderColor = "#B00000";
        } else {
            birthdateOK = true;
            document.forms["mainForm"].elements["geburtstag"].style.borderColor = "#FFFFFF";
        }
    }
    validateAll();
}

function validateUser(){
    if (hasFormValidation) {
        userOK = document.forms["mainForm"].elements["benutzername"].checkValidity();
    } else {
        var text = document.forms["mainForm"].elements["benutzername"].value;
        if ( text.length < 4 || text.length > 8){
            userOK = false;
            document.forms["mainForm"].elements["benutzername"].style.borderColor = "#B00000";
        } else {
            userOK = true;
            document.forms["mainForm"].elements["benutzername"].style.borderColor = "#FFFFFF";
        }
    }
    validateAll();
}
function validatePassword(){
    if (hasFormValidation) {
        passwordOK = document.forms["mainForm"].elements["password"].checkValidity();
    } else {
        var text = document.forms["mainForm"].elements["password"].value;
        if ( text.length < 4 || text.length > 8){
            passwordOK = false;
            document.forms["mainForm"].elements["password"].style.borderColor = "#B00000";
        } else {
            passwordOK = true;
            document.forms["mainForm"].elements["password"].style.borderColor = "#FFFFFF";
        }
    }
    validateAll();
}

function validateAll(){
    if (userOK && passwordOK && birthdateOK) {
        document.forms["mainForm"].elements["action"].disabled = false;
        document.forms["mainForm"].elements["action"].style.backgroundColor = "#36d344";
    } else {
        document.forms["mainForm"].elements["action"].disabled = true;
        document.forms["mainForm"].elements["action"].style.backgroundColor = "#CCCCCC";
    }
}

function changeToMarked(id){
	var elem = document.getElementById(id);
	var color = $(elem).css("background-color");
	var colorInHex = stringRGB2HEX(color);
	if(colorInHex == "#b9d6f0"){
		document.getElementById(id).style.backgroundColor = "#033058";
		document.getElementById(id).style.color = "white";
	} else {
		document.getElementById(id).style.backgroundColor = "#b9d6f0";
		document.getElementById(id).style.color = "#033058";
	}
}

//http://stackoverflow.com/questions/638948/background-color-hex-to-javascript-variable
function _rgb2hex(rgb_string, r, g, b) 
   {
      //VERY IMPORTANT: by adding (1 << 24) we avoid 'rgb(0, 0, 0)' to be mistakenly converted into '#0'
      var rgb = (1 << 24) | (parseInt(r) << 16) | (parseInt(g) << 8) | parseInt(b); //same thing of: ( r + (256 * g) + (65536 * b) + 16777216)
      //toString(16) specify hex 16 radix, works only for numbers [source: http://msdn.microsoft.com/en-us/library/dwab3ed2(v=VS.85).aspx]   
      return '#' + rgb.toString(16).substr(1); //substr(1) because we have to remove the (1 << 24) added above
   }

//http://stackoverflow.com/questions/638948/background-color-hex-to-javascript-variable
function stringRGB2HEX(string)
   {
      if(typeof string === 'string')
         string = string.replace(/rgb\((\d+),\s*(\d+),\s*(\d+)\)/g, _rgb2hex);
      return string;
   }