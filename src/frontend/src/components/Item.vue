<template>
  <div v-if="currentItem" class="edit-form">
    <h4>Item</h4>
    <form>
      <div class="form-group">
        <label for="descricao">Descrição</label>
        <input type="text" class="form-control" id="descricao"
          v-model="currentItem.descricao"
        />
      </div>
      <div class="form-group">
        <label for="localizacao">Localização</label>
        <input type="text" class="form-control" id="localizacao"
          v-model="currentItem.localizacao"
        />
      </div>
      <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentItem.status }}
      </div>
		<div class="form-group">
        <label><strong>Disponibilidade:</strong></label>
        {{ currentItem.disponibilidade }}
      </div>
      <div class="form-group">
        <label><strong>Data de movimentação</strong></label>
        {{ currentItem.dataDeMovimentacao }}
      </div>
    </form>

    <input type="button" v-if="currentItem.disponibilidade === 'DISPONIVEL'"  class="buttonDisponibilidade" @click="updateDisponibilidade('INDISPONIVEL')" value="Pegar">
    <input type="button" v-else class="buttonDisponibilidade" @click="updateDisponibilidade('DISPONIVEL')" value="Devolver">
    <input type="button" class="buttonUpdate" @click="updateItem" value="Atualizar">
    <input type="button" class="buttonDesativar" @click="deleteItem" value="Desativar">
    
    <div v-if="showAlert" class="alert alert-success alert-dismissible fade show" role="alert">
      {{ message }}
      <button type="button" class="close" @click="hideAlert" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
  </div>

  <div v-else>
    <br />
    <p>Selecione um item</p>
  </div>
</template>

<script>
import ItemDataService from "../services/ItemDataService";
export default {
  name: "cur-item",
  data() {
    return {
      currentItem: null,
      message: '',
      showAlert: false,
    };
  },
  methods: {
    getItem(codigo) {
      ItemDataService.get(codigo)
        .then(response => {
          this.currentItem = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    hideAlert() {
      this.showAlert = false;
    },
    updateDisponibilidade(disponibilidade) {
      var data = {
        id: this.currentItem.id,
        disponibilidade: disponibilidade,
      };
      ItemDataService.update(this.currentItem.id, data)
        .then(response => {
          this.currentItem.disponibilidade = disponibilidade;
          console.log(response.data);
          this.getItem(this.$route.params.id)
          this.message = 'O item foi alterado com sucesso!';
          this.showAlert = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    updateItem() {
      ItemDataService.update(this.currentItem.id, this.currentItem)
        .then(response => {
          console.log(response.data);
          this.message = 'O item foi alterado com sucesso!';
          this.showAlert = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteItem() {
      ItemDataService.delete(this.currentItem.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "itens" });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getItem(this.$route.params.id);
  }
};
</script>

<style>
@import '../assets/styles/bootstrap.min.css';
.edit-form {
  max-width: 400px;
  margin: auto;
}
.buttonDisponibilidade {
  background-color: #0d6efd;
  border: none;
  color: white;
  padding: 4px 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
.buttonDesativar {
  background-color: #dc3545;
  border: none;
  color: white;
  padding: 4px 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
.buttonUpdate {
  background-color: #198754;
  border: none;
  color: white;
  padding: 4px 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
</style>