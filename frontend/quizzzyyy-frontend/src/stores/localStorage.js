
// saves the users token in the local storage
// this is used to authenticate the user when they are logged in for retrieving
// stored data for the user

login(credentials)
    {
    axios.post('/api/user/login', credentials).then(response => {
        localStorage.setItem('token', response.data.token);
        // Lagre annen brukerinfo om nødvendig
    });
}
