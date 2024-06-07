getPost()
function getPost(){
    axios({
        url:" http://localhost:8080/api/user",
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
                <td>${item.name}</td>
                <td>${item.username}</td>
                <td>${item.phone}</td>
                <td>
                    <a class="btn btn-success" href="../uy_ishi_json_place_holder/post.html?userId=${item.id}">posts</a>
                    <a class="btn btn-success" href="../uy_ishi_json_place_holder/album.html?userId=${item.id}">abums</a>
                    <a class="btn btn-success" href="../uy_ishi_json_place_holder/todo.html?userId=${item.id}">todos</a>
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
            getPost()
        })
    })
}