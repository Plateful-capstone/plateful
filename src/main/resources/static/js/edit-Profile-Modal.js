const editProfileBtnOpen = document.querySelector("#edit-modal-profile-open")
const modalContainer = document.querySelector('#modal-user-container');

editProfileBtnOpen.addEventListener('click',function(e) {
    e.preventDefault();
    modalContainer.style.display = 'block';
    console.log("modal button clicked")
})



// Close the modal when the user clicks outside of it
window.addEventListener('click', function (event) {
    if (event.target === modalContainer) {
        closeModal();
    }
});

// Function to close the modal
function closeModal() {
    modalContainer.style.display = 'none';
}

const closeButtonX = document.querySelector('#close-x');
closeButtonX.addEventListener('click', closeModal);