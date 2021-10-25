
function post() {
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": questionId,
            "parentType": 1,
            "comment": commentContent
        }),
        success: function (response) {
            if (response.code == 200) {
                $("#answer-section").hide();
            } else {
                alert(response.message);
            }
        },
        dataType: "json"
    });
    // console.log(questionId);
    // console.log(commentContent);
}