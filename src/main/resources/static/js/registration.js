function registration() {
    fetch("http://localhost:8080/api/registration", {
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
    }).then(r => console.log(r))
        .then(() => {
            window.location.href = "http://localhost:8080/login";
        })
}