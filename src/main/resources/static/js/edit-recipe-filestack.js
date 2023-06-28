window.addEventListener('DOMContentLoaded', function() {
    console.log("DOM content loaded");
    const client = filestack.init(keys.filestackAPIKey);
    const picker = client.picker(options);

    const openButton = document.querySelector('#open');
    openButton.addEventListener('click', (e) =>{
        console.log("Open Button Clicked");
        e.preventDefault();
        picker.open();
    });

});

const options = {
    onFileUploadFinished: (file) => {
        console.log(`File upload complete`);
        console.log(file);
        const imageURL = document.querySelector(`#recipeImageURL`);
        imageURL.value = file.url;
        const openButton = document.querySelector('#open');
        let filename = file.filename;
        const filenameParagraph = document.createElement('p');
        filenameParagraph.textContent = "File: " + filename;
        filenameParagraph.id = "filenameParagraph";
        openButton.insertAdjacentElement('afterend', filenameParagraph);

    }
}

