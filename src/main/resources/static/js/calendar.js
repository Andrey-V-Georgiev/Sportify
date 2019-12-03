let monthsArr = new Array(
    {
        month: 1,
        year: 2019,
        before: 2,
        start: 1,
        end: 31,
        after: 9
    },
    {
        month: 2,
        year: 2019,
        before: 5,
        start: 1,
        end: 28,
        after: 9
    },
    {
        month: 3,
        year: 2019,
        before: 5,
        start: 1,
        end: 31,
        after: 6
    },
    {
        month: 4,
        year: 2019,
        before: 1,
        start: 1,
        end: 30,
        after: 11
    },
    {
        month: 5,
        year: 2019,
        before: 3,
        start: 1,
        end: 31,
        after: 8
    },
    {
        month: 6,
        year: 2019,
        before: 7,
        start: 1,
        end: 30,
        after: 6
    },
    {
        month: 7,
        year: 2019,
        before: 1,
        start: 1,
        end: 31,
        after: 10
    },
    {
        month: 8,
        year: 2019,
        before: 4,
        start: 1,
        end: 31,
        after: 7
    },
    {
        month: 9,
        year: 2019,
        before: 0,
        start: 1,
        end: 30,
        after: 12
    },
    {
        month: 10,
        year: 2019,
        before: 2,
        start: 1,
        end: 31,
        after: 9
    },
    {
        month: 11,
        year: 2019,
        before: 5,
        start: 1,
        end: 30,
        after: 7
    },
    {
        month: 12,
        year: 2019,
        before: 0,
        start: 1,
        end: 31,
        after: 11
    },
    {
        month: 1,
        year: 2019,
        before: 3,
        start: 1,
        end: 31,
        after: 8
    },
    {
        month: 2,
        year: 2019,
        before: 6,
        start: 1,
        end: 29,
        after: 7
    },
    {
        month: 3,
        year: 2019,
        before: 0,
        start: 1,
        end: 31,
        after: 11
    },
    {
        month: 4,
        year: 2019,
        before: 3,
        start: 1,
        end: 30,
        after: 9
    },
    {
        month: 5,
        year: 2019,
        before: 5,
        start: 1,
        end: 31,
        after: 6
    },
    {
        month: 6,
        year: 2019,
        before: 1,
        start: 1,
        end: 30,
        after: 11
    },
    {
        month: 7,
        year: 2019,
        before: 3,
        start: 1,
        end: 31,
        after: 8
    },
    {
        month: 8,
        year: 2019,
        before: 6,
        start: 1,
        end: 31,
        after: 5
    },
    {
        month: 9,
        year: 2019,
        before: 2,
        start: 1,
        end: 30,
        after: 10
    },
    {
        month: 10,
        year: 2019,
        before: 4,
        start: 1,
        end: 31,
        after: 7
    },
    {
        month: 11,
        year: 2019,
        before: 0,
        start: 1,
        end: 30,
        after: 12
    },
    {
        month: 12,
        year: 2019,
        before: 2,
        start: 1,
        end: 31,
        after: 9
    }
);

let today = new Date();
let dd = String(today.getDate()).padStart(2, '0');
let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
let yyyy = today.getFullYear();
let todayStr = mm + '/' + dd + '/' + yyyy;

let currentMonth = monthsArr.find((m) => {
    return m.month == mm && m.year == yyyy;
});

let datesInMonth = (month) => {
    let list = [];
    for (let i = 0; i < month.before; i++) {
        list.push(i);
    }
    for (let i = month.start; i <= month.end; i++) {
        list.push(i);
    }
    for (let i = 0; i <= month.after; i++) {
        list.push(i);
    }
    return list;
};

let mothsNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"];

let buildTable = () => {
    $("#menu").append(`<div class="col-2 ml-5"><h3>${mothsNames[mm - 1]}</h3></div>`);
    $("#menu").append(`<div class="col-2"><h3>${yyyy}</h3></div>`);

    let dates = datesInMonth(currentMonth);
    let counter = 0;
    for (let i = 0; i < 6; i++) {

        let currentID = `row${i}`;
        $("#calendar").append(`<div id=${currentID} class='row'></div>`);

        for (let j = 0; j < 7; j++) {
            $(`#${currentID}`)
                .append(`<div class='border border-dark' style='width: 130px;height: 100px'>${dates[counter++]}</div>`)
        }
    }
};



