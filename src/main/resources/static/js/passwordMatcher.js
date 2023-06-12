    function passConfirm() {
        let password = document.getElementById(`password`).value;
        let confirmPassword = document.getElementById(`confirm_password`).value;
        let message = document.getElementById(`message`);
        if (password === confirmPassword) {
            message.style.color = `lightgreen`;
            message.innerHTML = `Passwords match.`
        } else {
            message.style.color = `red`;
            message.innerHTML = `Passwords do not match.`
        }
    }


    document.getElementById('password').addEventListener('keyup', passConfirm);
    document.getElementById('confirm_password').addEventListener('keyup', passConfirm);