let monthsArr = new Array(
    {
        index: 1,
        month: 1,
        year: 2019,
        before: 2,
        start: 1,
        end: 31,
        after: 9
    },
    {
        index: 2,
        month: 2,
        year: 2019,
        before: 5,
        start: 1,
        end: 28,
        after: 9
    },
    {
        index: 3,
        month: 3,
        year: 2019,
        before: 5,
        start: 1,
        end: 31,
        after: 6
    },
    {
        index: 4,
        month: 4,
        year: 2019,
        before: 1,
        start: 1,
        end: 30,
        after: 11
    },
    {
        index: 5,
        month: 5,
        year: 2019,
        before: 3,
        start: 1,
        end: 31,
        after: 8
    },
    {
        index: 6,
        month: 6,
        year: 2019,
        before: 7,
        start: 1,
        end: 30,
        after: 6
    },
    {
        index: 7,
        month: 7,
        year: 2019,
        before: 1,
        start: 1,
        end: 31,
        after: 10
    },
    {
        index: 8,
        month: 8,
        year: 2019,
        before: 4,
        start: 1,
        end: 31,
        after: 7
    },
    {
        index: 9,
        month: 9,
        year: 2019,
        before: 0,
        start: 1,
        end: 30,
        after: 12
    },
    {
        index: 10,
        month: 10,
        year: 2019,
        before: 2,
        start: 1,
        end: 31,
        after: 9
    },
    {
        index: 11,
        month: 11,
        year: 2019,
        before: 5,
        start: 1,
        end: 30,
        after: 7
    },
    {
        index: 12,
        month: 12,
        year: 2019,
        before: 0,
        start: 1,
        end: 31,
        after: 11
    },
    {
        index: 13,
        month: 1,
        year: 2020,
        before: 3,
        start: 1,
        end: 31,
        after: 8
    },
    {
        index: 14,
        month: 2,
        year: 2020,
        before: 6,
        start: 1,
        end: 29,
        after: 7
    },
    {
        index: 15,
        month: 3,
        year: 2020,
        before: 0,
        start: 1,
        end: 31,
        after: 11
    },
    {
        index: 16,
        month: 4,
        year: 2020,
        before: 3,
        start: 1,
        end: 30,
        after: 9
    },
    {
        index: 17,
        month: 5,
        year: 2020,
        before: 5,
        start: 1,
        end: 31,
        after: 6
    },
    {
        index: 18,
        month: 6,
        year: 2020,
        before: 1,
        start: 1,
        end: 30,
        after: 11
    },
    {
        index: 19,
        month: 7,
        year: 2020,
        before: 3,
        start: 1,
        end: 31,
        after: 8
    },
    {
        index: 20,
        month: 8,
        year: 2020,
        before: 6,
        start: 1,
        end: 31,
        after: 5
    },
    {
        index: 21,
        month: 9,
        year: 2020,
        before: 2,
        start: 1,
        end: 30,
        after: 10
    },
    {
        index: 22,
        month: 10,
        year: 2020,
        before: 4,
        start: 1,
        end: 31,
        after: 7
    },
    {
        index: 23,
        month: 11,
        year: 2020,
        before: 0,
        start: 1,
        end: 30,
        after: 12
    },
    {
        index: 24,
        month: 12,
        year: 2020,
        before: 2,
        start: 1,
        end: 31,
        after: 9
    }
);

let daysOfMonthCreator = (month) => {
    let list = [];
    for (let i = 0; i < month.before; i++) {
        list.push(0);
    }
    for (let i = month.start; i <= month.end; i++) {
        list.push(i);
    }
    for (let i = 0; i <= month.after; i++) {
        list.push(0);
    }
    return list;
};

let mothsNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"];

let today = new Date();
let dd = String(today.getDate()).padStart(2, '0');
let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
let yyyy = today.getFullYear();

let currentMonth = monthsArr.find((m) => {
    return m.month == mm && m.year == yyyy;
});

function findMonth(month, year) {
    let searchedMonth = monthsArr.find((m) => {
        return m.month == month && m.year == year;
    });
    return searchedMonth;
}

let buildTable = (monthObj, createScheduleModal, showScheduleModal, scheduleDays) => {

    $("#month").remove();
    $("#year").remove();
    $("#row0").remove();
    $("#row1").remove();
    $("#row2").remove();
    $("#row3").remove();
    $("#row4").remove();
    $("#row5").remove();

    $("#menu").append(`<div id="month" class="col-2 ml-5"><h3>${mothsNames[monthObj.month - 1]}</h3></div>`);
    $("#menu").append(`<div id="year" class="col-2"><h3>${monthObj.year}</h3></div>`);

    let daysOfMonth = daysOfMonthCreator(monthObj);
    let counter = 0;
    for (let i = 0; i < 6; i++) {

        let currentID = `row${i}`;
        $("#calendar").append(`<div id=${currentID} class='row'></div>`);

        for (let j = 0; j < 7; j++) {
            let day = daysOfMonth[counter++];
            let month = monthObj.month;
            let year = monthObj.year;

            if (day == 0) {
                $(`#${currentID}`)
                    .append(`<div class='m-1 rounded rounded-sm border' style='width: 125px;height: 80px'>
                             </div>`)
            } else {
                if (scheduleDays.includes(day)) {
                    $(`#${currentID}`).append(showScheduleModal(day, month, year))
                } else {
                    $(`#${currentID}`).append(createScheduleModal(day, month, year))
                }
            }
        }
    }
    $(`#calendar`).append(`<hr><button onclick="goBack()" class="col-3 btn btn-md btn-outline-dark my-1 mr-2">
                                            Go Back
                                        </button>
                                        <script>
                                            function goBack() {
                                                window.history.back();
                                            }
                                        </script>`)
};

function findSportCenterSchedulesByMonth(monthNum, scID) {
    return fetch(`/rest/sport-centers/schedules-by-month/${scID}/${monthNum}`)
        .then(resp => resp.json());
}
