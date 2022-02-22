import http from "../http-common";

class ItemDataService {
  getAll() {
    return http.get("/itens");
  }

  get(codigo) {
    return http.get(`/itens/${codigo}`);
  }

  create(data) {
    return http.post("/itens", data);
  }

  update(id, data) {
    return http.put(`/itens/${id}`, data);
  }

  delete(id) {
    return http.delete(`/itens/${id}`);
  }

  findByDescricao(descricao) {
    return http.get(`/itens/descricao=${descricao}`);
  }
}

export default new ItemDataService();