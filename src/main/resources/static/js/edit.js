const editUserForm = document.getElementById('editUserForm')
editUserForm.addEventListener('submit', handleFormSubmit)

async function handleFormSubmit(event) {
    event.preventDefault()
    const data = serializeForm(editUserForm)
    const {status, error} = await sendData(data)

    if (status === 200) {
        onSuccess(event.target)
    } else {
        onError(error)
    }

}

function serializeForm(formNode) {
    const {elements} = formNode

    const data = new FormData()

    Array.from(elements)
        .filter((item) => !!item.name)
        .forEach(element => {
            const {name, type} = element

            const value = type === 'checkbox' ? element.checked : element.value

            data.append(name, value)
        })

    return data
}

async function sendData(data) {
    return await fetch('/admin/rest', {
        method: 'PUT',
        headers: {'Content-Type': 'multipart/form-data'},
        body: data,
    })
}

function onSuccess(formNode) {
    // window.location.replace("/admin");
}

function onError(error) {
    alert(error.message)
}