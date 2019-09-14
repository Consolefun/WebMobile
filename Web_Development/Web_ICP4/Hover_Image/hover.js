function upDate(previewPic) {
    /* In this function you should
       1) change the url for the background image of the div with the id = "image"
       to the source file of the preview image


       2) Change the text  of the div with the id = "image"
       to the alt text of the preview image
       */
    var src = previewPic.getAttribute("src");
    var myAlt = previewPic.getAttribute("alt");

    // Check if alt text is match then update background image and it's alt text
    if(myAlt == "Batter is ready"){
        document.getElementById("image").style.backgroundImage = "url('"+src+"')";
        document.getElementById('image').innerHTML = myAlt;
    }
    else if(myAlt == "Perfect Baking"){
        document.getElementById("image").style.backgroundImage = "url('"+src+"')";
        document.getElementById('image').innerHTML = myAlt;
    }
    else if(myAlt == "Yummy yummy cup cake"){
        document.getElementById("image").style.backgroundImage = "url('"+src+"')";
        document.getElementById('image').innerHTML = myAlt;
    }


}

function unDo() {
    /* In this function you should
   1) Update the url for the background image of the div with the id = "image"
   back to the orginal-image.  You can use the css code to see what that original URL was


   2) Change the text  of the div with the id = "image"
   back to the original text.  You can use the html code to see what that original text was
   */

    // Set the image back to it's original image. Div with id image
    original_img = document.getElementById('image');
    original_img.style.backgroundImage = "url('')";
    var target = document.getElementById("image");
    target.innerText = "Hover over an image below to display here.";
}
