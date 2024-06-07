getAlbum()
function getAlbum(){
    const  userId = new URLSearchParams(window.location.search).get("userId")
    axios({
        url:` http://localhost:8080/api/album/get/${userId}`,
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
                        <td>
                            <a class="btn btn-success" href="../uy_ishi_json_place_holder/photo.html?albumId=${item.id}">Photo</a>
                        </td>
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
            getAlbum()
        })
    });
}