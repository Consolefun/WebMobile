function upDate(previewPic) {
    /* In this function you should
       1) change the url for the background image of the div with the id = "image"
       to the source file of the preview image


       2) Change the text  of the div with the id = "image"
       to the alt text of the preview image
       */

    var myAlt = previewPic.getAttribute("alt");

    var target = document.getElementById("image");


    if(myAlt == "Batter is ready"){
        target.style.backgroundImage = "url('https://cdn.sallysbakingaddiction.com/wp-content/uploads/2017/06/moist-chocolate-cupcakes-7.jpg')";
        target.innerHTML = myAlt;

    }
    else if(myAlt == "Perfect Baking"){
        target.style.backgroundImage = "url('https://cdn.sallysbakingaddiction.com/wp-content/uploads/2017/06/moist-chocolate-cupcakes-6.jpg')";
        target.innerHTML = myAlt;

    }
    else if(myAlt == "Yummy yummy cup cake"){
        target.style.backgroundImage = "url('https://cdn.sallysbakingaddiction.com/wp-content/uploads/2017/06/moist-chocolate-cupcakes-5.jpg')";
        target.innerHTML = myAlt;

    }


}

function unDo() {
    /* In this function you should
   1) Update the url for the background image of the div with the id = "image"
   back to the orginal-image.  You can use the css code to see what that original URL was


   2) Change the text  of the div with the id = "image"
   back to the original text.  You can use the html code to see what that original text was
   */
    original_img = document.getElementById('image');
    original_img.style.backgroundImage = "url('')";
    var target = document.getElementById("image");
    target.innerText = "Hover over an image below to display here.";

}
