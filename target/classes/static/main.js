
const addModal = document.getElementById("addModal");
const addBtn = document.getElementById("addBtn");
const deleteBtns = document.getElementsByClassName("deleteBtn");

const closeAdd = document.getElementById("closeAdd");
const closeDlt = document.getElementsByClassName("closeDlt");
const closeEdit = document.getElementsByClassName("closeEdit");
const deleteModals = document.getElementsByClassName("deleteModal");
const editBtns = document.getElementsByClassName("editBtn");
const editModals = document.getElementsByClassName("editModal");



addBtn.onclick = function() {
    addModal.style.display = "block";
}
closeAdd.onclick = function() {
    addModal.style.display = "none";

}
window.onclick = function(event) {
    if (event.target === addModal) {
        addModal.style.display = "none";
    }
}

for (let i = 0; i < deleteBtns.length; i++) {
    deleteBtns[i].onclick = function() {
        deleteModals[i].style.display = "block";
    };
}
for (let i = 0; i < editBtns.length; i++) {
    editBtns[i].onclick = function() {
        editModals[i].style.display = "block";
    };
}
for (let i = 0; i < closeEdit.length; i++) {
    closeEdit[i].onclick = function() {
        editModals[i].style.display = "none";
    };
}

for (let i = 0; i < closeDlt.length; i++) {
    closeDlt[i].onclick = function() {
        deleteModals[i].style.display = "none";
    };
}
for (let i = 0; i < editModals.length; i++) {
    window.onclick = function(event) {
            editModals[i].style.display = "none";
        }
}

for (let i = 0; i < deleteModals.length; i++) {
    window.onclick = function(event) {
            deleteModals[i].style.display = "none";
    }
}

