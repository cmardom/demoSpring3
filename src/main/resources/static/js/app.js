const modal = document.getElementById("modal");
const modalBody = modal.querySelector('.modal-body');
const modalFooter = modal.querySelector('.modal-footer');
const botonEliminar = modalFooter.lastElementChild;
let clickListener = null;



/*Alert hecho con bootstrap para confirmar eliminar*/
modal.addEventListener('show.bs.modal', (event)=> {
    const button =  event.relatedTarget;

    const form = button.parentElement;

    const bodyTable = button.closest('.bodyTable');
    const bodyTableChildren = bodyTable.children;
    const id = bodyTableChildren[0].innerText;
    const nombre = bodyTableChildren[1].innerText;
    const apellido1 = bodyTableChildren[2].innerText;
    const apellido2 = bodyTableChildren[3].innerText;

    // console.log(id, nombre, apellido1, apellido2);

    //Para traducir el texto del modal: cambiarlo para internacionalización
    if(form.innerText.includes("Delete")){
        modalBody.innerText = `Do you still want to delete user with ID ${id}: ${nombre} ${apellido1} ${apellido2}?`

    } else{
        modalBody.innerText = `¿Estás seguro/a de querer borrar el usuario con ID ${id}: ${nombre} ${apellido1} ${apellido2}?`

    }

    clickListener = botonEliminar.addEventListener('click', () => {
        form.submit();
    });
});

modal.addEventListener('hide.bs.modal', () => {
    botonEliminar.removeEventListener('click', clickListener);
});
