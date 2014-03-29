/*
    Helper functions for the first exercise of the Web Engineering course
*/

var birthdateOK = true;
var passwordOK = false;
var userOK = false;

//var hasFormValidation = hasFormValidation();
//var hasNativeDateInput = hasNativeDateInput();
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

function validateDate() {
    var text = getNormalizedDateString("#geburtstag");
    if (text.match(/^(\d{1,2})\.(\d{1,2})\.(\d{4})$/) == null && text != ''){
        birthdateOK = false;
        //document.forms["mainForm"].elements["geburtstag"].value = "invalid";
    } else {
        birthdateOK = true;
        //document.forms["mainForm"].elements["geburtstag"].valid = true;
    }
    validateAll();
}

function validateUser(){
    var text = document.forms["mainForm"].elements["benutzername"].value;
    if ( text.length < 4 || text.length > 8){
        userOK = false;
    } else {
        userOK = true;
    }
    validateAll();
}
function validatePassword(){
    var text = document.forms["mainForm"].elements["password"].value;
    if ( text.length < 4 || text.length > 8){
        passwordOK = false;
    } else {
        passwordOK = true;
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