import axios from 'axios';


class AuthService {
  login(user: { username: any; password: any; }) {
    const endpoint = '/auth/login'
    return axios
      .post(endpoint, {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response && response.data.token) {
          console.log('Token generated  ' + response.data.token)
          console.log('Token generated  ' + JSON.stringify(response.data))
          sessionStorage.setItem('user', JSON.stringify(response.data))
          localStorage.setItem('user', JSON.stringify(response.data))
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
    sessionStorage.clear()
  }
}

export default new AuthService();
