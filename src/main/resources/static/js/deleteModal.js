const deleteModalBtnOpen = document.querySelector("#delete-modal-btn");
const deleteModalContainer = document.querySelector("#delete-modal-container");

deleteModalBtnOpen.addEventListener('click', function(e) {
    e.preventDefault();
    deleteModalContainer.style.display = 'block';
});

// Close the modal when the user clicks outside of it
window.addEventListener('click', function(event) {
    if (event.target === deleteModalContainer) {
        closeModal();
    }
});

// Function to close the modal
function closeModal() {
    deleteModalContainer.style.display = 'none';
}

const deleteModalCloseButton = document.querySelector('#delete-modal-close');
deleteModalCloseButton.addEventListener('click', closeModal);
