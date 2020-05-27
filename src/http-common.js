import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:9000/api/v1/instrumento/",
  headers: {
    "Content-type": "application/json"
  }
});
