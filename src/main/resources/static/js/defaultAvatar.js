function setDefaultAvatar(image) {
    image.onerror = null; // Prevent infinite loop if the placeholder image is also not found
    image.src = '/https://picsum.photos/200/300';
}
