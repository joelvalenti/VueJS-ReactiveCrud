import http from "../http-common";

class InstrumentoDataService {
  _url = "http://localhost:9000/api/v1/instrumento/";

  getAll() {
    return http.get(this._url);
  }

  getOne(id) {
    return http.get(this._url + id);
  }

  create(data) {
    return http.post(this._url, data);
  }

  update(id, data) {
    return http.put(this._url + id, data);
  }

  delete(id) {
    return http.delete(this._url + id);
  }
}

export default new InstrumentoDataService();
