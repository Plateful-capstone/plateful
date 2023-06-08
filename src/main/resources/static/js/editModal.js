let modalBtns = document.querySelectorAll(".editModalBtn");

modalBtns.forEach(function (modalBtn) {
    modalBtn.addEventListener("click", function(e) {
        e.preventDefault(); // Prevent the default form submission

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