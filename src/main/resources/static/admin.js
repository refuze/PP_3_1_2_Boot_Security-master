getAllUsers();

function getAllUsers() {
    fetch("http://localhost:8080/api/users")
        .then(res => res.json())
        .then(users => {
            let temp = '';
            console.log(users);
            users.forEach(function (user) {
                temp += `
                <tr>
                <td id="id${user.id}">${user.id}</td>
                <td id="username${user.id}">${user.username}</td> 
                <td id="email${user.id}">${user.email}</td>
                <td id="roles${user.id}">${user.authorities.map(r => r.name)}</td>
                
                <td>
                <button type="button" class="btn btn-info" 
                data-bs-toggle="modal" data-bs-target="#edit-modal"
                onclick="openEditModal(${user.id})">Edit</button>
                </td>
                
                <td>
                <button type="button" class="btn btn-danger" 
                data-bs-toggle="modal" data-bs-target="#delete-modal"
                onclick="openDeleteModal(${user.id})">Delete</button>
                </td>
                
              </tr>`;
            });
            document.getElementById("table-body").innerHTML = temp;
        });
}

function openEditModal(id) {
    fetch("http://localhost:8080/api/admin/user?id=" + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(u => {
            document.getElementById('id-edit').value = u.id;
            document.getElementById('username-edit').value = u.username;
            document.getElementById('email-edit').value = u.email;
        })
    });
}

function openDeleteModal(id) {
    fetch("http://localhost:8080/api/admin/user?=" + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(u => {
            document.getElementById('id-delete').value = u.id;
            document.getElementById('username-delete').value = u.username;
            document.getElementById('email-delete').value = u.email;
        })
    });
}

function refreshTable() {
    let table = document.getElementById('table-body')
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
    getAllUsers();
}

async function deleteUser() {
    await fetch("http://localhost:8080/api/admin?id=" + document.getElementById("id-delete").value, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
    }).then(r => console.log(r))
        .then(() => {
        refreshTable()
        document.getElementById("delete-form").reset();
    })
}

function newUser() {
    fetch("http://localhost:8080/api/newUser", {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            username: document.getElementById('username-new').value,
            email: document.getElementById('email-new').value,
            password: document.getElementById('password-new').value,
            authorities: getRoles(Array.from(document.getElementById('role-new').selectedOptions)
                .map(r => r.value))
        })
    }).then(r => console.log(r))
        .then(() => {
            document.getElementById("nav-users-tab").click();
            refreshTable();
            document.getElementById("new-form").reset();
        })
}

function getRoles(list) {
    let roles = [];
    if (list.indexOf("ADMIN") >= 0) {
        roles.push({"id": 1});
    }
    if (list.indexOf("USER") >= 0) {
        roles.push({"id": 2});
    }
    return roles;
}

async function editUser() {
    await fetch('http://localhost:8080/api/admin', {
        method: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            id: document.getElementById('id').value,
            name: document.getElementById('editUsername').value,
            surname: document.getElementById('editSurname').value,
            email: document.getElementById('editEmail').value,
            age: document.getElementById('editAge').value,
            password: document.getElementById('editPassword').value,
            roles: getRoles(Array.from(document.getElementById('editRole').selectedOptions)
                .map(r => r.value))
        })
    }).then(r => console.log(r))
        .then(() => {
        refreshTable()
        document.getElementById("edit-form").reset();
    });
}