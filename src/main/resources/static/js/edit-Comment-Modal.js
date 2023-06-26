// JavaScript
const editCommentBtnOpen = document.querySelector("#edit-comment-modal-btn");
const modalContainer = document.querySelector('.edit-comment-modal-container');
const parentElement = document.querySelector('#comments-row');


// Removes default function
// editCommentBtnOpen.addEventListener('click', function(e) {
//     e.preventDefault();
//     modalContainer.style.display = 'block';
//     console.log("modal button clicked");
// });

parentElement.addEventListener('click', function(e) {
    if (e.target.classList.contains("edit-comment-modal-btn")) {
        console.log("edit button clicked")
        let modalContainer = e.target.closest(".comment-btns").querySelector(".edit-comment-modal-container");
        if (modalContainer.style.display === "none") {
            modalContainer.style.display = 'block';
        }else {
            modalContainer.style.display = 'none';
        }
    }
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

    const closeButtonX = document.querySelector('#edit-comment-modal-close');
    closeButtonX.addEventListener('click', closeModal);
});
