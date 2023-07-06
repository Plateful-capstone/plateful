let image = document.getElementById("login-image")

window.addEventListener("resize", function() {
    if (window.innerWidth <= 768) {
        image.src = "/css/img/gathering-2.jpg"
    } else {
        image.src = "/css/img/wings.jpg";
    }
});

// / Check the screen resolution
// var screenWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
// var screenHeight = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
// // Change a property based on the screen resolution
// if (screenWidth < 1024 && screenHeight < 768) {
//     // Set property for smaller screens
//     document.body.style.backgroundColor = 'red';
// } else {
//     // Set property for larger screens
//     document.body.style.backgroundColor = 'blue';
// }