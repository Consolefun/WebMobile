//Some of this codes was reference from Dev Eds youtube video.
const game = () => {
    let player_score = 0;
    let computer_score = 0;
    const playMatch = () => {
        var computerOptions = ["Rock", "Paper", "Scissors"];

        var options = document.querySelectorAll(".options button");
        var playerHand = document.querySelector(".player-hand");
        var computerHand = document.querySelector(".computer-hand");

        //add event listener for buttons inside option class
        options.forEach(option => {
            option.addEventListener("click", function () {
                //Computer Choice
                var computerNumber = Math.floor(Math.random() * 3);
                var computerChoice = computerOptions[computerNumber];


                //Here is where we call compare hands
                compareHands(this.textContent, computerChoice);
                //Update Images when click buttons
                playerHand.src = `./assets/${this.textContent}.png`;
                computerHand.src = `./assets/${computerChoice}.png`;

            });
        });
    };
    // update the score
    var updateScore = function () {
        var playerScore = document.querySelector(".player-score p");
        var computerScore = document.querySelector(".computer-score p");
        playerScore.textContent = player_score;
        computerScore.textContent = computer_score;
    }

    var compareHands = function(playerChoice, computerChoice){
        var winner = document.querySelector(".winner")

        // check for tie
        if(playerChoice === computerChoice){
            winner.textContent = "It is a tie";
        }
        //console.log(playerChoice);
        //console.log(computerChoice);

        // check for rock
        else if(playerChoice === "Rock"){
            if(computerChoice=== "Scissors"){
                winner.textContent = "Player Wins";
                player_score++;
                updateScore();
            }
            else{
                winner.textContent = "Computer Wins";
                computer_score++;
                updateScore();
            }
        }
        // check for paper
        else if(playerChoice === "Paper"){
            if(computerChoice=== "Scissors"){
                winner.textContent = "Computer Wins";
                computer_score++;
                updateScore();
            }
            else{
                winner.textContent = "Player Wins";
                player_score++;
                updateScore();
            }
        }
        // check for scissors
        else if(playerChoice === "Scissors"){
            if(computerChoice=== "Rock"){
                winner.textContent = "Computer Wins";
                computer_score++;
                updateScore();
            }
            else{
                winner.textContent = "Player Wins";
                player_score++;
                updateScore();
            }
        }
    };
    playMatch();
};
game();