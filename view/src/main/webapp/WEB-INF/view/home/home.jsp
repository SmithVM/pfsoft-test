
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%@ page isELIgnored ="false" %>

<link rel="stylesheet" href="../static/css/jquery-ui.css"/>
<script src="../static/js/dust-full.js"></script>
<script src="../static/js/dust-helpers.min.js"></script>
<script src="../static/js/jquery-ui.min.js"></script>
<script src="../static/js/files_diff.js"></script>
<script src="../static/js/FileSaver.min.js"></script>
<script src="../static/js/jszip.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>


<form id="form" method="post" action="/upload" enctype="multipart/form-data">
    File1 to upload: <input type="file" name="file1" id="file1"><br />
    File2 to upload: <input type="file" name="file2" id="file2"><br />
</form>

<button value="Submit" id="compare" class="diff"  >Compare</button>
<button value="Submit" id="zip" class="diff"  >Save ZIP</button>


<div id="explanation" style="display: none; padding: 30px;">
                                    Equal text without color.
    <span style="color:green;">    Inserted text has green color.</span>
    <span style="color:red;">    Deleted text has red color.</span>
</div>

<div id="diff_result" style="padding: 30px;"></div>

<script type="text/dust" id="diffTemplate">
<div>
{#deltas}

{@eq key=operation value="EQUAL"}{text}{/eq}
{@eq key=operation value="INSERT"}<font color="green;">{text}</span>{/eq}
{@eq key=operation value="DELETE"}<span style="color:red;">{text}</span>{/eq}

{/deltas}

</div>
</script>


