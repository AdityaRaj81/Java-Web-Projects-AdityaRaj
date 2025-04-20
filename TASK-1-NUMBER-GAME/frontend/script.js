const apiUrl = "http://localhost:8080/api/game";

function startGame() {
  fetch(`${apiUrl}/new`)
    .then(res => res.text())
    .then(msg => {
      document.getElementById("message").innerText = msg;
    });
}

function submitGuess() {
  const guess = document.getElementById("guess").value;

  fetch(`${apiUrl}/guess`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: guess
  })
    .then(res => res.text())
    .then(msg => {
      document.getElementById("message").innerText = msg;
    });
}
