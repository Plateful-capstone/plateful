const commentBtn= document.querySelector('#comment-btn');
    commentBtn.addEventListener('click', () => {
        console.log("Comment button clicked");
        const commentsRow = document.querySelector('#comments-row')
        //when the comments button is clicked, the comments row will be displayed
        if (commentsRow.style.display === "none") {
            commentsRow.style.display = "block";
        } else {
            commentsRow.style.display = "none";
        }
        //if content is inside the textarea then the comment button will show
        const commentContent = document.querySelector('#comment-content');
        const commentContentBtn = document.querySelector('#comment-content-btn');

        commentContent.addEventListener('keyup', () => {
            if (commentContent.value.length > 0) {
                commentContentBtn.style.display = "block";
            } else {
                commentContentBtn.style.display = "none";
            }
        });

    });