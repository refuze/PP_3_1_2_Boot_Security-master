document.getElementById('registration-form-submit')
    .addEventListener('click', (event) => {
    event.preventDefault();
    registration();
})

function registration() {
    fetch("http://localhost:8080/registration/api", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: {
            username: document.getElementById('username-reg').value,
            email: document.getElementById('email-reg').value,
            password: document.getElementById('password-reg').value
        }
    }).then(r => console.log(r))
        .then(() => {
            console.log({
                username: document.getElementById('username-reg').value,
                email: document.getElementById('email-reg').value,
                password: document.getElementById('password-reg').value
            })
        })
        // .then(() => {
        //     window.location.href = "http://localhost:8080/login";
        // })
}