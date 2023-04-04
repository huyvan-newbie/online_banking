function loadTable() {
    $('#manage-customer-table').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": "/admin/rest/get-customer-list",
            "type": "POST",
            "dataType": "json",
            "contentType": "application/json",
            "data": function (d) {
                return JSON.stringify(d);
            },
        },
        "columns": [
            {"data": "fullName", "width": "15%"},
            {"data": "email", "width": "15%"},
            {"data": "phoneNumber", "width": "20%"},
            {"data": "ssn", "width": "20%"},
            {
                "mRender": function (data, type, row) {
                    if (row.status == 1) {
                        return '<label class="switch">\n' +
                            '  <input type="checkbox" title="Locked" onclick="changeStatus(' + row.id + ', 2)">\n' +
                            '  <span class="slider round"></span>\n' +
                            '</label>';
                    } else {
                        return '<label class="switch">\n' +
                            '  <input type="checkbox" checked title="Active" onclick="changeStatus(' + row.id + ', 1)">\n' +
                            '  <span class="slider round"></span>\n' +
                            '</label>';
                    }
                }
            },
            {
                "mRender": function (data, type, row) {
                    return '<div>' +
                        '<a onclick="edit(' + row.id + ')"> Edit | </a>' +
                        '<a onclick="deleteUser(' + row.id + ')"> Delete </a>' +
                        '</div>';
                }
            }
        ]
    });
}

function changeStatus(id, status) {
    const data = {
        id,
        status
    }
    doRequest('POST', '/admin/rest/change-status', data, success, error);
}

function success(res) {
    $('#manage-customer-table').DataTable().ajax.reload();
}

function error(e) {
    const error = e.responseJSON ? e.responseJSON.errorDesc : e;
    displayMessageError(error);
}

function edit(id) {
    gotoUrl('/admin/edit-user?id=' + id);
}

function deleteUser(id) {
    if (confirm('Are you sure?')) {
        doRequest('POST', '/admin/rest/delete-user?id=' + id, null, success, error);
    }
}

loadTable();
