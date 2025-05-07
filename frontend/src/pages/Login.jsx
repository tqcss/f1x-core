import React, { useState, useEffect } from 'react';

function Login() {
    // const [data, setData] = useState([]);
    // const [loading, setLoading] = useState(true);
    // const [error, setError] = useState(null);

    // useEffect(() => {
    //     fetch('http://localhost:8080/api/products')
    //         .then(response => {
    //             if (!response.ok) {
    //                 throw new Error(`HTTP error! status: ${response.status}`);
    //             }
    //             return response.json();
    //         })
    //         .then(data => {
    //             setData(data);
    //             setLoading(false);
    //         })
    //         .catch(error => {
    //             setError(error);
    //             setLoading(false);
    //         });
    // }, []);

    // if (loading) return <p>Loading data...</p>;
    // if (error) return <p>Error fetching data: {error.message}</p>;

    return (
        <>
            <div class="main-content">
                <div class="login-title-header">
                    <h1>F1+X</h1>
                </div>
                <div class="login-wrapper">            
                    <div class="login-container">
                        <h2>LOGIN</h2>
                        <form th:action="@{/login}" method="post">
                            <div class="input-group">
                                <i class="fas fa-user input-icon"></i>
                                <input type="text" name="username" placeholder="Username" required />
                            </div>
                            <div class="input-group">
                                <i class="fas fa-lock input-icon"></i>
                                <input type="password" name="password" placeholder="Password" required />
                            </div>
                            <button type="submit">LOGIN</button>
                        </form>
                        <a th:href="@{/register}">Don't have an account? Register here</a>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Login;
