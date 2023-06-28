function setDefaultAvatar(image) {
    image.onerror = null; // Prevent infinite loop if the placeholder image is also not found
    image.src = "css/img/profile-placeholder.png";
}

document.addEventListener("DOMContentLoaded", function () {
    let image = document.getElementById("openImage");
    if (!image.complete || image.naturalWidth === 0) {
        setDefaultAvatar(image);
    }
});
