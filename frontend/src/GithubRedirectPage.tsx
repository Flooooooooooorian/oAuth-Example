import {useNavigate, useSearchParams} from "react-router-dom";
import axios from "axios";
import {useEffect} from "react";

export default function GithubRedirectPage() {

    const [searchParams] = useSearchParams()

    useEffect(sendCodeToBackend, [])
    const navigate = useNavigate()

    function sendCodeToBackend() {
        axios.post("/api/users/oauth/github", searchParams.get("code"), {
            headers: {
                "Content-Type": "text/plain"
            }
        })
            .then(() => {
                navigate("/")
            })
    }

    return (
        <div>
            <p>
                Welcome back
                Code: {searchParams.get("code")}
            </p>
        </div>
    )
}
