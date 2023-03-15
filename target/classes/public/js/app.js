const uri = "http://localhost:4567";
var APIRedirect = "";
function loadValues(){
    var consulta;
    consulta = document.getElementById("archivo").value;
    var URL_API = "http://localhost:4567/inicio";
    console.log(URL_API);
    console.log(consulta);
                return new Promise((resolve, reject) => {
                  fetch('/inicio', {
                    method: 'POST',
                    body: JSON.stringify(consulta),
                    headers:{
                      'Content-Type': 'application/json'
                    }
                  })
                  .then((response) => {
                      if (response.ok) {
                        return response.json();
                      }
                      reject(

                      );
                    })
                  .then((json) => resolve(json))
                  .catch((err) => reject(err));
                });
}