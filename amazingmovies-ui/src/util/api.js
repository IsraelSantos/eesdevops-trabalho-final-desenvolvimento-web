
import axios from "axios";

export default axios.create({
  baseURL: "http://192.168.99.100:8081/v1.0/",
  responseType: "json"
});