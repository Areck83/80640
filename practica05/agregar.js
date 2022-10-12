function leer(){
    return document.getElementById("todo").value
}

function agregar (){
    nodo = document.createElement("li")
    //texto = document.createTextNode("hola")
    //texto = document.createTextNode(document.getElementById("todo").value)
    texto = document.createTextNode(leer())
    console.log(texto)
    nodo.appendChild(texto)
    nodo.setAttribute("id", leer()) //Nueva linea de clase, sirve solamente para indexar
    document.getElementById("lista").appendChild(nodo)
}

function buscarFor(){
    listaTodo = document.getElementsByTagName("li")
    for (let index=0; index < listaTodo.length; index++){
        txt = listaTodo[index].textContent
        //console.log(txt)
        if(txt == leer()){
            alert("Ya existe un elemento asi")
        }
    }
}

function buscarID(){
    if( document.getElementById(leer()) == null ){
        console.log("No existe")
    }else{
        alert("Ojo! Ya existe")
    }
}

function buscarOf(){
    listaTodo = document.getElementsByTagName("li")
    for (const tarea of listaTodo) {
        if(leer() == tarea.textContent){
            console.log("ya existe")
        }
    }
}

function buscarIn(){
    listaTodo = document.getElementsByTagName("li")
    for (const tarea in listaTodo) {
        if (Object.hasOwnProperty.call(listaTodo, tarea)) {
            //console.log(listaTodo[tarea].childNodes[0])
            //if(leer() == listaTodo[tarea].textContent){
            //if(leer () == listaTodo[tarea].childNodes[0])
                console.log("ya existe")
            //}         
        }
    }
}

