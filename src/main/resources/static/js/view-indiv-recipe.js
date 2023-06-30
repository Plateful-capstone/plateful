//backup file in case the comm doesn't work

document.addEventListener('DOMContentLoaded', function() {
    const commentBtn = document.querySelector('#comment-btn');
    const commentsRow = document.querySelector('#comments-row');
    const commentContent = document.querySelector('#comment-content');
    const commentContentBtn = document.querySelector('#comment-content-btn');
    const modalBtns = document.querySelectorAll(".editModalBtn");

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

});
