function loadTable() {
    $('#manage-loans-package-table').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/admin/rest/get-loans-package-list",
            "type": "POST",
            "dataType": "json",
            "contentType": "application/json",
            "data": function (d) {
                return JSON.stringify(d);
            },
        },
        "columns": [
            {"data": "id", "width": "20%"},
            {"data": "duration", "width": "20%"},
            {"data": "interestRate", "width": "20%"},
            {
                "mRender": function (data, type, row) {
                    return '<div>' +
                        '<a onclick="edit(' + row.id + ')"> Edit | </a>' +
                        '<a onclick="deleteLoansPackage(' + row.id + ')"> Delete </a>' +
                        '</div>';
                }
            }
        ]
    });
}

function success(res) {
    $('#manage-loans-package-table').DataTable().ajax.reload();
}

function error(e) {
    const error = e.responseJSON ? e.responseJSON.errorDesc : e;
    displayMessageError(error);
}

function add(id) {
    gotoUrl('/admin/add-loans-package');
}

function edit(id) {
    gotoUrl('/admin/edit-loans-package?id=' + id);
}

function deleteLoansPackage(id) {
    if (confirm('Are you sure?')) {
        doRequest('POST', '/admin/rest/delete-loans-package?id=' + id, null, success, error);
    }
}

loadTable();
