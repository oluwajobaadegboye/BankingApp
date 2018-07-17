$(function () {
    // pay bill option on change
    $("#bill_option").change(function (e) {
        var objects = ${billPaymentList};
        var item = objects.filter(object => object.id === this.value);
        console.log(item);
        $("#description").val(item[0].description);
        $("#amount").val(item[0].amount);
    });

    //pay bill post function
    $("#payBill_form").submit(function (e) {
        e.preventDefault();

        $.ajax({
            method: "POST",
            url: "/bank-app/payBill",
            data: $("#bill_option").val(),
            beforeSend: function () {
            }
        }).done(function (msg) {
            var msg = JSON.parse(msg);
            console.log(msg);
            $("#success").text("Payment successful");
            $('#payBill_form')[0].reset();
        }).fail(function (msg) {
            console.log(msg);
            console.log("FAILED")
        });
    });
});