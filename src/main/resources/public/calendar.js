function formulaire(){
    document.write(`<form method="GET"><div class="nameEvent"><input type="text" class="nameEvent" name="nameEvent" placeholder="Nom de l'event"></div><div class="location"><input type="text" class="location" placeholder="location"></div><div class="start-hour"><input type="time" class="start-hour" name="start" placeholder="starthour"></div><div class="end-hour"><input type="time" class="end-hour" name="end" placeholder="endhour"></div></div><button type="submit" class="btn btn-primary">Suivant</button></form>`);
};

const calendar = document.querySelector("#calendar");
console.log(calendar);
for(let day = 1; day <= 31; day++){
    console.log(day);
    calendar.insertAdjacentHTML("beforeend", `<div class="day">${day}<div>`);

};
document.querySelectorAll("#calendar .day").forEach
(day => {
    day.addEventListener("click", event => {
        if(event.currentTarget.classList.contains("selected")){
            event.currentTarget.classList.remove("selected");
        }
        else{
            event.currentTarget.classList.add("selected");
            formulaire();

        }
    });
});