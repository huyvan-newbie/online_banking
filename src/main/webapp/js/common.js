function doRequest(method, url, data, successFunc, errorFunc) {
    $.ajax({
        type: method,
        contentType: "application/json",
        url: url,
        data: data ? JSON.stringify(data) : null,
        dataType: 'json',
        success: successFunc,
        error: errorFunc
    });
}

function postFormData(method, url, formData, successFunc, errorFunc) {
    const data = Object.fromEntries(formData.entries());
    doRequest(method, url, data, successFunc, errorFunc);
}

function gotoUrl(url) {
    document.location.href = url;
}

function displayMessageSuccess(message) {
    $('#alert-box').css('background-color', '#28a745');
    displayMessage(message);
}
function displayMessageError(message) {
    $('#alert-box').css('background-color', '#f44336');
    displayMessage(message);
}
function displayMessage(message) {
    $('#alert-box').show();
    $('#text-alert').text(message);
    setTimeout(function () {
        $('#alert-box').hide();
        $('#text-alert').text('');
    }, 5000);
}