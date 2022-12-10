showUserInfo();

function showUserInfo() {
    fetch('http://localhost:8080/api/user')
        .then((res) => res.json())
        .then((user) => {
            let temp = "";
            temp += `<tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.authorities}</td>
            </tr>`;
            document.getElementById("user-table-body").innerHTML = temp;
        });
}