import axios from 'axios';

const API_BASE_URL = "http://10.0.0.92:8080/university/all";

class EmotionService {
  getEmotions() {
    return axios.get(API_BASE_URL);
  }
}

export default new EmotionService();
