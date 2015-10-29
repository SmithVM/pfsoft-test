/**
 * Created by User on 29.10.2015.
 */

var fileName1;
var fileName2;

$(document).ready(function () {

    $(":button").click(function (event) {

        var id = event.currentTarget.id;
        uploadFormData(id);
    });
    document.getElementById('file1').onchange = function () {
        fileName1 = $(this).val().split('/').pop().split('\\').pop().replace("." , " ");
    };
    document.getElementById('file2').onchange = function () {
        fileName2 = $(this).val().split('/').pop().split('\\').pop().replace("." , " ");;
    };
});

function uploadFormData(id) {

    $('#result').html('');

    dust.isDebug = true;
    var template = $("#diffTemplate").html();
    var compiled = dust.compile(template, "diff_template");
    dust.loadSource(compiled);

    var oMyForm = new FormData();
    oMyForm.append("file1", file1.files[0]);
    oMyForm.append("file2", file2.files[0]);

    $.ajax({
        url: '/upload',
        type: 'POST',
        data: oMyForm,
        dataType: 'json',
        processData: false,
        contentType: false,
        success: function (data) {
            var result = {"deltas": data};
            dust.render("diff_template", result, function (err, out) {
                chooseAction(out, id)
            })
        }
    });
}

function chooseAction(out, id) {

    if (id == "compare") {
        $("#diff_result").html(out);
        $("#explanation").show();
    }
    if (id == "zip") {
        var name = getFileName();
        var zip = new JSZip();
        zip.file(name + ".html", out);
        var content = zip.generate({type: "blob"});
        saveAs(content, name + ".zip");
    }

function getFileName(){

    var name = fileName1 + "_diff_to_" +
               fileName2 + "_ " + getDate();

    return name;
}

function getDate(){
    var currentdate = new Date();
    var datetime =  currentdate.getDate() + "_"
        + (currentdate.getMonth()+1)  + "_"
        + currentdate.getFullYear();
    return datetime;
    }

}

