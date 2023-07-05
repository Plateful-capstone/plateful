let image = document.getElementById("login-image")
//
window.addEventListener("resize", function() {
    if (window.innerWidth <= 768) {
        image.src = "/css/img/gathering-2.jpg"
    } else {
        image.src = "/css/img/wings.jpg";
    }
});

if (window.innerWidth <= 768) {
    image.src = "/css/img/gathering-2.jpg"
} else {
    image.src = "/css/img/wings.jpg";
}