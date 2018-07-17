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

        function toTransactionHistory(transactionHistoryList) {
            var returnHistory = [];
            for (var key in transactionHistoryList) {
                var history = transactionHistoryList[key];
                var date = history.transactionDate;

                var object = {
                    "recipient": history.recipient,
                    "transactionAmount": history.transactionAmount,
                    "transactionType": history.transactionType,
                    "transactionDate": new Date(date).toLocaleString()
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
            var data = toTransactionHistory(msg.transactionHistoryList);

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

            if (msg.responseCode === "02" || msg.responseCode === "03") {
                console.log("Error!", msg);
                $("#no_account").text(msg.responseMessage);
            } else {
                console.log(msg);
                $("#recipient_name").val(msg.user.name);
                $("#recipient_email").val(msg.user.email);
                $("#recipient_account").val(msg.account.accountNumber);
                $("#no_account").text(msg.responseMessage);
                transfer.append($('#transfer_form').show());
            }
        }).fail(function (msg) {
            console.log(msg);
        });
    })

    $("#transfer_form").submit(function (e) {
        e.preventDefault();
        console.log($("#recipient_name").val());
        console.log($("#recipient_account").val());
        console.log($('#transfer_form').serialize());

        var payload = {
            "accountNumber": $("#recipient_account").val(),
            "recipientName": $("#recipient_name").val(),
            "narration": $("#narration").val(),
            "transferAmount": $("#amount").val()
        };
        console.log(JSON.stringify(payload));

        $.ajax({
            method: "POST",
            url: "/bank-app/transfer",
            data: JSON.stringify(payload),
            beforeSend: function () {
            }
        }).done(function (msg) {
            var account = JSON.parse(msg).account;
            $("#balance_value").text("$" + account.balance);
            console.log(account);
            $('#transfer_form')[0].reset();
            $('#transfer_form').hide();
        }).fail(function (msg) {
            console.log(msg);
            console.log("FAILED")
        });
    });

});