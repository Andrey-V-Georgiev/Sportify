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

let datesInMonthCreator = (month) => {
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

let buildTable = (month) => {
    $("#month").remove();
    $("#year").remove();
    $("#row0").remove();
    $("#row1").remove();
    $("#row2").remove();
    $("#row3").remove();
    $("#row4").remove();
    $("#row5").remove();

    $("#menu").append(`<div id="month" class="col-2 ml-5"><h3>${mothsNames[month.month - 1]}</h3></div>`);
    $("#menu").append(`<div id="year" class="col-2"><h3>${month.year}</h3></div>`);

    let dates = datesInMonthCreator(month);
    let counter = 0;
    for (let i = 0; i < 6; i++) {

        let currentID = `row${i}`;
        $("#calendar").append(`<div id=${currentID} class='row'></div>`);

        for (let j = 0; j < 7; j++) {
            let dateNumber = dates[counter++];

            function fullModal() {
                return `<div class='m-1 rounded rounded-sm border' style='width: 125px;height: 80px'>
 
<div id="mybtn" data-toggle="modal" data-target="#aaa">
  <div class='bg-success rounded rounded-sm' style='width: 123px;height: 78px'>
<h3 class="text-white">
${dateNumber}
</h3>
</div>
</div>

<div class="modal fade" id="aaa" tabindex="-1" role="dialog" 
     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered w-25" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body justify-content-center">
                <h3 class="text-center">
                    
                </h3>
            </div>
            <div class="modal-footer justify-content-center">
                <form th:action="@{/sports/sport-details/{id}(id=3)}"
                      th:method="get" class="w-100">
                    <button class="btn btn-block btn-warning">Edit schedule</button>
                </form>
                <form th:action="@{/sports/sport-details/{id}(id=3)}"
                      th:method="get" class="w-100">
                    <button class="btn btn-block btn-success">Show schedule</button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>`
            }

            function emptyModal() {
                return `<div class='m-1 rounded rounded-sm border border-dark' style='width: 125px;height: 80px'>
 
<div id="mybtn" data-toggle="modal" data-target="#aaa">
  <div class='bg-white rounded rounded-sm' style='width: 123px;height: 78px'>
<h3 class="text-dark">
${dateNumber}
</h3>
</div>
</div>

<div class="modal fade" id="aaa" tabindex="-1" role="dialog" 
     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered w-25" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body justify-content-center">
                <h5 class="text-center">
                    There is no schedule for this day! 
                </h5>
            </div>
            <div class="modal-footer justify-content-center">
                <form th:action="@{/sports/sport-details/{id}(id=3)}"
                      th:method="get" class="w-100">
                    <button class="btn btn-block btn-warning">Create schedule</button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>`
            }

            if (dateNumber == 0) {
                $(`#${currentID}`)
                    .append(`<div class='m-1 rounded rounded-sm border' style='width: 125px;height: 80px'>
                             </div>`)
            } else {
                $(`#${currentID}`).append(emptyModal())
                // $(`#${currentID}`).append(fullModal())
            }
        }
    }
};




