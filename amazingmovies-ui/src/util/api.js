
import realAxios from "axios";

const headers = {
  "Content-Type": "application/json"
}

const axios = realAxios.create({
  baseURL: "http://172.17.0.1:8081/v1.0/",
  //baseURL: "http://localhost:8081/v1.0/",
  responseType: "json"
});

export default {axios, headers};