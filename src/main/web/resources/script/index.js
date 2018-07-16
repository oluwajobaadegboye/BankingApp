$(function () {
    $("#make_transfer").click(function () {
        $("#transfer_arena").toggle();
        $("#transaction_list").hide();

        if (dataTable) {
            dataTable.destroy();
        }
    });

    $("#view_transactions").click(function () {
        $("#transaction_list").toggle();
        $("#transfer_arena").toggle();
        $("#no_account").text("");
        $("#recipient").val("")

        if ($('.transfer_form').length > 0) {
            $('.transfer_form')[0].reset();
        }

        $('#transfer_form').hide();

        var data = [
            {
                "name": "Tiger Nixon",
                "position": "System Architect",
                "salary": "$3,120",
                "start_date": "2011/04/25",
                "office": "Edinburgh",
                "extn": "5421"
            },
            {
                "name": "Garrett Winters",
                "position": "Director",
                "salary": "$5,300",
                "start_date": "2011/07/25",
                "office": "Edinburgh",
                "extn": "8422"
            }
        ];

        function toTransactionHistory(transactionHistoryList) {
            var returnHistory = [];
            for (var key in transactionHistoryList) {
                var history = transactionHistoryList[key];
                var object = {
                    "recipient": history.recipient,
                    "transactionAmount": history.transactionAmount,
                    "transactionType": history.transactionType,
                    "transactionDate": history.transactionDate
                };
                returnHistory.push(object);
            }
            return returnHistory;
        }

        $.ajax({
            method: "GET",
            url: "/bank-app/transactionHistory"
        }).done(function (msg) {
            var msg = JSON.parse(msg);
            console.log("Messssage   " + msg);
            var data = toTransactionHistory(msg.transactionHistoryList);
            console.log("Data is   " + data);
            dataTable = $('#table_id').DataTable({
                paging: true,
                ordering: true,
                data: data,
                columns: [
                    {data: 'recipient'},
                    {data: 'transactionType'},
                    {data: 'transactionAmount'},
                    {data: 'transactionDate'}
                ]
            });
        }).fail(function () {
            console.log("Error!");
        });
    });

    // confirm money transfer recipient
    $("#recipient_form").submit(function (e) {
        e.preventDefault();
        var recipient_account_number = $('#recipient').val(),
            transfer = $("#transfer_arena");

        $.ajax({
            method: "GET",
            url: "/bank-app/account",
            data: {recipient: recipient_account_number},
            beforeSend: function () {
                $("#loaderDiv").show();
                $("#no_account").text("");
            }
        }).done(function (msg) {
            $("#loaderDiv").hide();
            var msg = JSON.parse(msg);
            console.log(msg.responseCode);

            if (msg.responseCode === "02" || msg.responseCode === "03") {
                console.log("Error!", msg);
                $("#no_account").text(msg.responseMessage);
            } else {
                transfer.append($('#transfer_form').show());
            }
        }).fail(function (msg) {
            console.log(msg);
            console.log("Account not verified");
        });
    })

    $("#transfer_form").submit(function (e) {
        e.preventDefault();
        console.log(e);
    })
});