import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'api/';

class UserService {

  getUserBoard() {
    return axios.get(API_URL + 'user/volunteer', { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'user/requester', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'user/admin', { headers: authHeader() });
  }
}

export default new UserService();
