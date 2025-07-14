// Cookies policy functions in several languages that allows people from other countries to make it work (please, report any translation error).
// You have to pass the val function in HTML to this code to work.

// Variable that gets the cookies policy container.
let cookiesDiv = document.getElementById('privacyCoookies');

// English function:
function cookies(val){
    if (val === 'accept') {
    window.alert('Thanks for accepting our cookies policy.');
    cookiesDiv.style.display = 'none';
    };
    if (val === 'reject') {
        window.alert('You rejected our cookies policies. You can not keep surfing our website.');
}};


// Función española:
function cookies(val){
    if (val === 'aceptar') {
    window.alert('Gracias por aceptar nuestra política de cookies.');
    cookiesDiv.style.display = 'none';
    };
    if (val === 'rechazar') {
        window.alert('Has rechazado nuestra política de cookies. No puedes seguir navegando en nuestra web');
}};


// Français fonction:
function cookies(val){
    if (val === 'accepte') {
    window.alert("Merci d'avoir accepté notre politique de cookies");
    cookiesDiv.style.display = 'none';
    };
    if (val === 'refuse') {
        window.alert("Vous avez refusé notre politique en matière de cookies");
}};


// Funzione italiana
function cookies(val){
    if (val === 'accetta') {
    window.alert("Grazie per aver accettato la nostra politica dei cookies.");
    cookiesDiv.style.display = 'none';
    };
    if (val === 'rifiutare') {
        window.alert("Hai rifiutato la nostra politica dei cookie");
}};

// Deutsche funktion
function cookies(val){
    if (val === 'akzeptieren') {
    window.alert("Vielen Dank, dass Sie unsere Cookie-Politik akzeptieren.");
    cookiesDiv.style.display = 'none';
    };
    if (val === 'zuruckweisen') {
        window.alert("Sie haben unsere cookie-richtlinien abgelehnt. Sie können nicht weiter auf unserer website surfen.");
}};