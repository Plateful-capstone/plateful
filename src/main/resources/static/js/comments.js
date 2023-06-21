const commentBtn = document.querySelector('#comment-btn');
commentBtn.addEventListener('click', () => {
    console.log("Comment button clicked");

    const commentsRow = document.querySelector('#comments-row')
    if (commentsRow.style.display === "none") {
        commentsRow.style.display = "block";
    } else {
        commentsRow.style.display = "none";
    }

    const commentContent = document.querySelector('#comment-content');
    const commentContentBtn = document.querySelector('#comment-content-btn');

    commentContent.addEventListener('keyup', () => {
        if (commentContent.value.length > 0) {
            commentContentBtn.style.display = "block";
        } else {
            commentContentBtn.style.display = "none";
        }
    });

    // Fetch operation should be inside the click event listener
    // The id value needs to be defined somehow (placeholder here for you to replace)

        const id = document.querySelector('#hidden-id').value;
        const commentInput = commentContent.value;
        const url = `/comments/${id}/create`;

        fetch(url, {
            method: 'POST',
            headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
        },
            body: JSON.stringify({ comment: commentInput }) // Sending the comment content in the request body
        })
        .then(response => response.json())
        .then(data => {
            const commentId = data.id;
            const commentContent = data.comment;
        })
        .catch(error => {
            console.error(error);
        });
        });