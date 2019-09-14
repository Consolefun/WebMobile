function getGithubInfo(user) {
    //1. Create an instance of XMLHttpRequest class and send a GET request using it.
    // The function should finally return the object(it now contains the response!)
    const url = "https://api.github.com/users/"+user;
    const xhttp_request = new XMLHttpRequest();
    xhttp_request.open('GET', url, false);
    xhttp_request.send();
   return xhttp_request;

}

function showUser(user) {
    //2. set the contents of the h2 and the two div elements in the div '#profile' with the user content
    $('#profile h2').html(user.login);
    $('#profile .avatar').html("<img style='height: 150px; width: 150px; margin-right: 30px' src ="+user.avatar_url+", alt = 'avatar'>");

    $('#profile').append("<p style='top: 100px'>"+user.id);
    $('#profile').append("<a href = "+user.html_url+">"+ user.html_url);


}

function noSuchUser(username) {
    //3. set the elements such that a suitable message is displayed

    $('#profile').html("user can't be find");


}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the respsonse
            response = getGithubInfo(username);
            //if the response is successful show the user's details
            if (response.status == 200) {
                showUser(JSON.parse(response.responseText));
                //else display suitable message
            } else {
                noSuchUser(username);
            }
        }
    })
});
