function submitGuess() {
  const userInput = document.getElementById('guessInput').value;

  fetch('https://numbergame-production.up.railway.app/api/game/guess', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body: 'number=' + encodeURIComponent(userInput)
  })
    .then(response => response.text())
    .then(data => {
      document.getElementById('result').innerText = data;
    })
    .catch(error => {
      console.error(error);
      alert("Something went wrong!");
    });
}
