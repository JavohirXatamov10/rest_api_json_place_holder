let form=document.getElementById("login_form")
form.addEventListener("submit",handleSubmit)
function handleSubmit(event){
    event.preventDefault();
    let username=event.target[0].value
    let password=event.target[1].value
    let obj= {
        username: username,
        password: password}
    axios({
        url:"http://localhost:8080/api/auth/login",
        method:"POST",
        data:obj
    }).then(res=>{
        localStorage.setItem("token",res.data.token)
        localStorage.setItem("refreshToken",res.data.refreshToken)
        window.location.href="../admin.html"//bu bizni admin.html ga o`tkazib beradi. Agar login password to`g`ri bo`lsa
    }).catch(err=>{console.log(err)
    })
}