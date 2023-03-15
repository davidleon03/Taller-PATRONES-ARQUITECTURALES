const uri = "http://localhost:4567";
var APIRedirect = "";
function loadValues(){
    var consulta;
    consulta = document.getElementById("archivo").value
    var URL_API = "http://localhost:4567/inicio";
    console.log(URL_API);
    console.log(consulta);
    axios.post(URL_API, consulta)
        .then(function(data){
            console.log(data);
        })
        .catch(function (error) {
            console.log(error)
        })
}
function getValues(){
    var consulta;
    var URL_API = "http://localhost:4567/inicio/get";
    console.log(URL_API);
    console.log(consulta);
    axios.get(URL_API, consulta)
        .then(response => {
            console.log(response.data);
            document.write(JSON.stringify(response.data));
        })
        .catch(e => {
            // Capturamos los errores
        })
}