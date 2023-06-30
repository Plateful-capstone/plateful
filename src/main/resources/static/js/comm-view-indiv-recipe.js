document.addEventListener('DOMContentLoaded', function() {
    const commentBtn = document.querySelector('#comment-btn');
    const commentsRow = document.querySelector('#comments-row');
    const commentContent = document.querySelector('#comment-content');
    const commentContentBtn = document.querySelector('#comment-content-btn');
    const modalBtns = document.querySelectorAll(".editModalBtn");
    const editCommentBtns = document.querySelectorAll('.edit-comment-modal-btn');
    const deleteCommentBtns = document.querySelectorAll('.delete-comment-modal-button');

    // Comment Button Click Event
    commentBtn.addEventListener('click', function() {
        console.log("Comment button clicked");
        if (commentsRow.style.display === "none") {
            commentsRow.style.display = "block";
        } else {
            commentsRow.style.display = "none";
        }
    });

    // Comment Content Keyup Event
    commentContent.addEventListener('keyup', function() {
        if (commentContent.value.length > 0) {
            commentContentBtn.style.display = "block";
        } else {
            commentContentBtn.style.display = "none";
        }
    });


    // Edit Modal Button Click Event
    modalBtns.forEach(function(modalBtn) {
        modalBtn.addEventListener("click", function(e) {
            e.preventDefault();

            let modalId = this.getAttribute("data-modal-id");
            let modal = document.getElementById(modalId);
            let span = modal.querySelector(".close");

            modal.style.display = "block";

            span.addEventListener("click", function() {
                modal.style.display = "none";
            });

            window.addEventListener("click", function(event) {
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            });
        });
    });

    // Add event listeners to each edit comment button
    editCommentBtns.forEach((editCommentBtn) => {
        editCommentBtn.addEventListener('click', (e) => {
            // Find the closest parent container of the button
            const commentBtnsContainer = e.target.closest('.comment-btns');

            // Find the corresponding edit comment modal container within the parent container
            const modalContainer = commentBtnsContainer.querySelector('.edit-comment-modal-container');

            // Display the edit comment modal
            modalContainer.style.display = 'block';

            // Find the close button within the modal container
            const closeButton = modalContainer.querySelector('.close');

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
        });
    });

    // Add event listeners to each delete comment button
    deleteCommentBtns.forEach((deleteCommentBtn) => {
        deleteCommentBtn.addEventListener('click', (e) => {
            // Find the closest parent container of the button
            const commentBtnsContainer = e.target.closest('.comment-btns');

            // Find the corresponding delete comment modal container within the parent container
            const modalContainer = commentBtnsContainer.querySelector('.delete-comment-modal-container');

            // Display the delete comment modal
            modalContainer.style.display = 'block';

            // Find the close button within the modal container
            const closeButton = modalContainer.querySelector('.close');

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

            // Find the "Do Not Delete" button within the modal container
            const doNotDeleteButton = modalContainer.querySelector('.do-not-delete-comment');

            // Add event listener to the "Do Not Delete" button to hide the modal
            doNotDeleteButton.addEventListener('click', (e) => {
                e.preventDefault();
                modalContainer.style.display = 'none';
            });
        });
    });


});



