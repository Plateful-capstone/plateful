const editProfileBtnOpen = document.querySelector("#edit-modal-profile-open")
const modalContainer = document.querySelector('#modal-user-container');

// Removes default function
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
const deleteFromCookbookBtn = document.querySelectorAll(".delete-recipe-image-wrapper");

deleteFromCookbookBtn.forEach((button) => {
    button.addEventListener('click', (event) => {
        event.stopPropagation(); // Prevent event bubbling to the parent elements
        let parentElement = button.closest('.user-recipe-card');
        let modalContainer = parentElement.querySelector(".rfc-modal-container");
        modalContainer.style.display = 'block';

        // Find the close button within the modal container
        const closeButton = modalContainer.querySelector('.rfc-close');

        // Add event listener to the close button to hide the modal
        closeButton.addEventListener('click', () => {
            modalContainer.style.display = 'none';
        });

        // Add window event listener to close the modal when clicking outside of it
        window.addEventListener('click', (event) => {
            if (event.target === modalContainer) {
                modalContainer.style.display = 'none';
            }
        });
        // Find the "No" button within the modal container
        const noButton = modalContainer.querySelector('.do-not-rfc');

        // Add event listener to the "No" button to prevent default and close the modal
        noButton.addEventListener('click', (event) => {
            event.preventDefault();
            modalContainer.style.display = 'none';
        });
    });
});
