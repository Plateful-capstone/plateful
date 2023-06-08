import keys from './keys.js';

window.addEventListener('DOMContentLoaded', function(){
    console.log("DOM content loaded");
    const client = filestack.init(keys.filestackAPIKey);
    const picker = client.picker(options);

    const openButton = document.querySelector('#open');
    openButton.addEventListener('click', (e) => {
        console.log("Add picture clicked");
        e.preventDefault();
        picker.open();
    });
});

const options = {
    accept: ['image/*'],
    onFileUploadFinished: (file) => {
        console.log('File upload complete');
        console.log(file);
        const imageURL = document.querySelector("#recipeImage");
        imageURL.value = file.url;


        // Render thumbnail image
        const container = document.querySelector("#thumbnailContainer");
        const thumbnail = document.querySelector("#thumbnail") || new Image();
        thumbnail.src = file.url;
        thumbnail.style.width = "200px"; // Adjust the width as desired

        if (!container.contains(thumbnail)) {
            container.appendChild(thumbnail);
        }
    },
};
