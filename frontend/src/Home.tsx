import {useEffect, useState} from "react";
import axios from "axios";

export default function Home() {

    const [user, setUser] = useState("no One")

    useEffect(() => {
        axios.get("/api/users/oauth/me")
            .then((response) => {
                setUser(response.data)
            })
    })

    function navigateToGithub() {
        window.open("https://github.com/login/oauth/authorize?client_id=01de35f3dd7e75b77b93", "_self")
    }

    return (
        <div>
            <p>Hello {user}</p>
            <button onClick={navigateToGithub}>Login with github</button>
        </div>
    )
}
