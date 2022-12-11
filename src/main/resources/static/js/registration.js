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
        body: JSON.stringify({
            username: document.getElementById('username-reg').value,
            email: document.getElementById('email-reg').value,
            password: document.getElementById('password-reg').value
        })
    }).then((res) => {
        res.status===200 ? window.location.href = "http://localhost:8080/login" : null;
    })
}