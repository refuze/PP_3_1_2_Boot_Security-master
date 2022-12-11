document.addEventListener('DOMContentLoaded', getAllUsers);

document.addEventListener('DOMContentLoaded', async () => {
    await fetch("http://localhost:8080/admin/api/roles")
        .then(res => console.log(res.json()))
})

async function getAllUsers() {
    await fetch("http://localhost:8080/admin/api")
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
                <td id="roles${user.id}">${user.authorities}</td>
                
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
    fetch("http://localhost:8080/admin/api/user?id=" + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(user => {
            for (let option of document.getElementById('role-edit').options)
            {
                if (user.authorities.includes(option.value)) {
                    option.selected = 'selected';
                }
            }

            document.getElementById('id-edit').value = user.id;
            document.getElementById('username-edit').value = user.username;
            document.getElementById('email-edit').value = user.email;
        })
    });
}

async function openDeleteModal(id) {
    await fetch("http://localhost:8080/admin/api/user?id=" + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(user => {
            for (let option of document.getElementById('role-delete').options)
            {
                if (user.authorities.includes(option.value)) {
                    option.selected = 'selected';
                }
            }

            document.getElementById('id-delete').value = user.id;
            document.getElementById('username-delete').value = user.username;
            document.getElementById('email-delete').value = user.email;
        })
    });
}

async function refreshTable() {
    let table = document.getElementById('table-body')
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
    await getAllUsers();
}

document.getElementById('delete-submit-button')
    .addEventListener('click', async (event) => {
        event.preventDefault();

        await fetch("http://localhost:8080/admin/api?id=" + document.getElementById("id-delete").value, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
        }).then(r => console.log(r))
            .then(() => {
                document.getElementById("delete-close").click();
                refreshTable();
                document.getElementById("delete-form").reset();
            })
    })

document.getElementById('new-submit-button')
    .addEventListener('click',async (event) => {
        event.preventDefault();

        let roles = [];

        for (let option of document.getElementById('role-new').options)
        {
            if (option.selected) {
                roles.push(option.value);
            }
        }

        await fetch("http://localhost:8080/admin/api", {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: JSON.stringify({
                username: document.getElementById('username-new').value,
                email: document.getElementById('email-new').value,
                password: document.getElementById('password-new').value,
                authorities: roles
            })
        }).then(() => {
            document.getElementById("nav-users-tab").click();
            refreshTable();
            document.getElementById("new-form").reset();
        })
    })

document.getElementById('edit-submit-button')
    .addEventListener('click', async (event) => {
        event.preventDefault();

        let roles = [];

        for (let option of document.getElementById('role-edit').options)
        {
            if (option.selected) {
                roles.push(option.value);
            }
        }

        await fetch('http://localhost:8080/admin/api', {
            method: "PUT",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json;charset=UTF-8'
            },
            body: JSON.stringify({
                id: document.getElementById('id-edit').value,
                username: document.getElementById('username-edit').value,
                email: document.getElementById('email-edit').value,
                password: document.getElementById('password-edit').value,
                authorities: roles
            })
        }).then(r => console.log(r))
            .then(() => {
                console.log(JSON.stringify({
                    id: document.getElementById('id-edit').value,
                    username: document.getElementById('username-edit').value,
                    email: document.getElementById('email-edit').value,
                    password: document.getElementById('password-edit').value,
                    authorities: roles
                }))
            })
            .then(() => {
                document.getElementById("edit-close").click();
                refreshTable()
                document.getElementById("edit-form").reset();
            });
    })
