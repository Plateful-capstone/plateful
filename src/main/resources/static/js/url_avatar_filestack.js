
window.addEventListener('DOMContentLoaded', function(){
    console.log("DOM content loaded");
    const client = filestack.init(keys.filestackAPIKey);
    const picker = client.picker(options);
//clicking on the image with id open will open the filestack picker
    const openImage = document.querySelector('#openImage');
    openImage.addEventListener('click', (e) => {
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
        const imageURL = document.querySelector("#avatarUrl");
        imageURL.value = file.url;
        //submit the form after
        const form = document.querySelector('#form-change-avatar');
        form.submit();
    }
}