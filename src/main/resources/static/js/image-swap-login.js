let image = document.getElementById("login-image")
//
window.addEventListener("resize", function() {
    if (window.innerWidth <= 768) {
        image.src = "/css/img/gathering-2.jpg"
    } else {
        image.src = "/css/img/wings.jpg";
    }
});

let screenWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;

if (screenWidth <= 768) {
    image.src = "/css/img/gathering-2.jpg"
} else {
    image.src = "/css/img/wings.jpg";
}

// document.onreadystatechange = function() {
//     if (document.readyState !== "complete") {
//         document.querySelector("body").style.visibility = "hidden";
//         document.querySelector("#loader").style.visibility = "visible";
//     } else {
//         document.querySelector("#loader").style.display = "none";
//         document.querySelector("body").style.visibility = "visible";
//     }
// };