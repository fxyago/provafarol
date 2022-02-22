<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="codigo">Código</label>
        <input
          type="text"
          class="form-control"
          id="codigo"
          required
          v-model="item.codigo"
          name="codigo"
          maxlength="6"
        />
      </div>

      <div class="form-group">
        <label for="descricao">Descrição</label>
        <input
          type="text"
          class="form-control"
          id="descricao"
          required
          v-model="item.descricao"
          name="descricao"
          maxlength="100"
        />
      </div>

      <div class="form-group">
        <label for="localizacao">Localização</label>
        <input
          type="text"
          class="form-control"
          id="localizacao"
          required
          v-model="item.localizacao"
          name="localizacao"
        />
      </div>

      <button @click="saveItem" class="btn btn-success">Salvar</button>
    </div>

    <div v-else>
      <h4>Salvo com sucesso!</h4>
      <button class="btn btn-success" @click="newItem">Voltar</button>
    </div>
  </div>
</template>

<script>
import ItemDataService from "../services/ItemDataService";
export default {
  name: "add-item",
  data() {
    return {
      item: {
        codigo: "",
        descricao: "",
        localizacao: "",
      },
      submitted: false
    };
  },
  methods: {
    saveItem() {

      var data = {
        "codigo" : this.item.codigo,
        "descricao" : this.item.descricao,
        "localizacao" : this.item.localizacao,
      };
      ItemDataService.create(data)
        .then(response => {
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    newItem() {
      this.item = {};
      this.submitted = !this.submitted;
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>