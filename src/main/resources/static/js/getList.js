window.addEventListener('DOMContentLoaded', getList);

// document.getElementById('userEditButton').addEventListener('click', async () => {
//     const form = document.getElementById('userEditForm');
//     form.
// })

async function getList() {
    const users = await fetch('/admin/rest').then(res => res.json());

    const roles = await fetch('/admin/rest/roles').then(res => res.json());

    console.log(users)

    users.forEach(user => userToHTML(user, roles));
}

function userToHTML( { id, username, email, authorities }, roles ) {
    const userList = document.getElementById('tableBody');

    userList.insertAdjacentHTML('beforeend', `
    <tr>
       <td>${id}</td>
       <td>${username}</td>
       <td>${email}</td>
       <td>${authorities}</td>
    </tr>
    `)
}

