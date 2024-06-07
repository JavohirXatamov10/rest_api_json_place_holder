getPhoto()
function getPhoto(){
    const  albumId = new URLSearchParams(window.location.search).get("albumId")
    axios({
        url:` http://localhost:8080/api/photo/get/${albumId}`,
        method:"GET",
        headers:{
            "Authorization": localStorage.getItem("token")
        }
    }).then(
        res=>{
            let s="";
            res.data.map(item=>{
                s+=`<tr>
                        <td>${item.id}</td>
                        <td>${item.title}</td>
                        <td>${item.url}</td>
                        <td>${item.thumbnailUrl}</td>
                     </tr>`
            })
            let tbody=document.getElementById("tbody")
            tbody.innerHTML=s;
        }
    ).catch(err=> {
        axios({
            url:"http://localhost:8080/api/refresh",
            method: "GET",
            headers: {"Authorization":localStorage.getItem("refreshToken")}
        }).then(res=>{
            console.log("refresh ishladi")
            localStorage.setItem("token",res.data)
            getPhoto()
        })

    });
}