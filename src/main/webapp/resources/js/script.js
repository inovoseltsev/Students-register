let addButton = document.getElementById("formAddBtn");
let editButton = document.getElementById("formEditBtn");
let modalTitle = document.getElementById("modalTitle");
let studentId = document.getElementById("studentId");

function showAddModal() {
    document.forms[0].reset();
    studentId.value = null;
    addButton.style.display = "block";
    editButton.style.display = "none";
    modalTitle.innerText = "Add Student";
}

function showEditModal(rowId) {
    addButton.style.display = "none";
    editButton.style.display = "block";
    modalTitle.innerText = "Edit Student";
    let row = document.getElementById(rowId);
    studentId.value = rowId;
    document.getElementById('firstName').value = row.cells[0].innerText;
    document.getElementById("lastName").value = row.cells[1].innerText;
    document.getElementById("birthday").value = row.cells[3].innerText;
    document.getElementById("faculty").value = row.cells[4].innerText;
}

function setNavigationNumber(number) {
    document.getElementById("navigationLabel").innerText = number;
}


