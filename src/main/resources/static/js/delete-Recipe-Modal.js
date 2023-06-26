
    $(document).ready(function() {
    // Open delete modal when the delete button is clicked
    $("#delete-modal-btn").click(function() {

        let modalId = "deleteModal-" + $(recipe.id);
        $("#" + modalId).css("display", "block");
    });

    // Close modal when the close button or outside the modal is clicked
    $(".close").click(function() {
    $(".modal").css("display", "none");
});
    $(window).click(function(event) {
    if (event.target.classList.contains("modal")) {
    $(".modal").css("display", "none");
}
});
});
