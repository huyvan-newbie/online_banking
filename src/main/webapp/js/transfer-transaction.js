function success(res) {
    gotoUrl('/customer/transferSuccess?type=TRANSFER&id=' + res.data.id);
}
function error(e) {
    const error = e.responseJSON ? e.responseJSON.errorDesc : e;
    displayMessageError(error);
}
function submitForm(e) {
    e.preventDefault();
    const form = new FormData(e.target);
    postFormData("POST", "/customer/rest/doTransferMoney", form, success, error)
}