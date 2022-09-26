function moveCarousel(radioId){
    const firstRadio = document.getElementById("first-radio")
    const secondRadio = document.getElementById("second-radio")
    const thirdRadio = document.getElementById("third-radio")
    const firstIcons = document.getElementById("first-icons")
    const secondIcons = document.getElementById("second-icons")
    const thirdIcons = document.getElementById("third-icons")

    if(radioId == 1){
        secondRadio.checked = false
        thirdRadio.checked = false
        firstIcons.style.display = "flex";
        secondIcons.style.display = "none";
        thirdIcons.style.display = "none";
    }else if(radioId == 2){
        firstRadio.checked = false
        thirdRadio.checked = false
        secondIcons.style.display = "flex";
        firstIcons.style.display = "none";
        thirdIcons.style.display = "none";
    }else if(radioId == 3){
        firstRadio.checked = false
        secondRadio.checked = false
        thirdIcons.style.display = "flex";
        secondIcons.style.display = "none";
        firstIcons.style.display = "none";
    }
}