<template>
  <div>
    <div title="Muda o layout da lista de itens" class="layoutSwitch">
      <label class="switch2">
        <input type="checkbox" @click="toggleLayout">
        <span class="slider round2"></span>
      </label>
    </div>
    <div class="layoutText">
      <h6>
        Layout compacto:
      </h6>
    </div>
    <div class="list row" v-if="layoutCompact">
      <div class="col-lg-12">
        <div class="input-group mb-3">
          <input type="text" v-on:keyup.enter="searchDescricao" class="form-control" placeholder="Procurar por descrição"
            v-model="descricao"/>
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button"
              @click="searchDescricao"
            >
              Procurar
            </button>
          </div>
          <div>
            <h6 class="switchDescription">
              Incluir itens deletados:
            </h6>
          </div>
          <div title="Inclui na lista os itens deletados" class="sliderDiv">
            <label class="switch">
              <input type="checkbox" @click="toggleShowAll">
              <span class="slider round"></span>
            </label>
          </div>
        </div>
      </div>
      <div class="col-md-6">
        <h4>Listagem de itens</h4>
        <ul class="list-group">
          <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(item, index) in itens"
            :key="index"
            @click="setActiveItem(item, index)"
          >
            {{ item.codigo }}
          </li>
        </ul>
      </div>
      <div class="col-md-6">
        <div v-if="currentItem">
          <h4>Item</h4>
          <div>
            <label><strong>Código:</strong></label> {{ currentItem.codigo }}
          </div>
          <div>
            <label><strong>N° de Série:</strong></label> {{ currentItem.numeroDeSerie }}
          </div>
          <div>
            <label><strong>Descrição:</strong></label> {{ currentItem.descricao }}
          </div>
          <div>
            <label><strong>Localização:</strong></label> {{ currentItem.localizacao }}
          </div>
          <div>
            <label><strong>Status:</strong></label> {{ currentItem.status }}
          </div>
          <div>
            <label><strong>Disponibilidade:</strong></label> {{ currentItem.disponibilidade }}
          </div>
          <div>
            <label><strong>Data de movimentação:</strong></label> {{ currentItem.dataDeMovimentacao }}
          </div>
          <div class="buttonEditar" >
            <router-link :to="'/itens/' + currentItem.id" class="buttonEditar" >Editar</router-link>
          </div>
        </div>
        <div v-else>
          <br />
          <p>Por favor clique em um item...</p>
        </div>
      </div>
    </div>
    <div v-else>
      <div>
        <input type="text" v-on:keyup.enter="searchDescricao" class="form-control" placeholder="Procurar por descrição"
            v-model="descricao"/>
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button"
              @click="searchDescricao">
              Procurar
            </button>
          </div>
          <div>
            <h6 class="switchDescription">
              Incluir itens deletados:
            </h6>
          </div>
          <div title="Inclui na lista os itens deletados" class="sliderDiv">
            <label class="switch">
              <input type="checkbox" @click="toggleShowAll">
              <span class="slider round"></span>
            </label>
          </div>
      </div>

      <table id="table" class="table table-striped">
        <thead>
          <tr>
            <th scope="col" data-field="codigo">Código</th>
            <th scope="col">N° de Série</th>
            <th scope="col">Descrição</th>
            <th scope="col">Localização</th>
            <th scope="col">Status</th>
            <th scope="col">Disponibilidade</th>
            <th scope="col">Data de movimentação</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in itens" :key="item.codigo">
            <td>{{item.codigo}}</td>
            <td>{{item.numeroDeSerie}}</td>
            <td>{{item.descricao}}</td>
            <td>{{item.localizacao}}</td>
            <td>{{item.status}}</td>
            <td>{{item.disponibilidade}}</td>
            <td>{{item.dataDeMovimentacao}}</td>
            <td><router-link :to="'/itens/' + item.id" class="buttonEditar" >Editar</router-link></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import ItemDataService from "../services/ItemDataService";
export default {
  name: "itens-list",
  data() {
    return {
      itens: [],
      currentItem: null,
      currentIndex: -1,
      descricao: "",
      isShowAll: false,
      layoutCompact: false,
    };
  },
  methods: {
    retrieveItens() {
      ItemDataService.getAll()
        .then(response => {
          response.data.forEach(item => {
            if(!this.isShowAll) {
              if(item.status === "ATIVO") {
                this.itens.push(item);
              }
            } else {
                this.itens.push(item);
            }
          })
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.itens = [];
      this.retrieveItens();
      this.currentItem = null;
      this.currentIndex = -1;
    },
    setActiveItem(item, index) {
      this.currentItem = item;
      this.currentIndex = index;
    },
    searchDescricao() {
      ItemDataService.findByDescricao(this.descricao)
        .then(response => {
          this.itens = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    toggleShowAll() {
      this.isShowAll = !this.isShowAll;
      this.refreshList();
    },
    toggleLayout() {
      this.layoutCompact = !this.layoutCompact;
      this.refreshList();
    }
  },
  mounted() {
    this.retrieveItens();
  }
};
</script>

<style>
@import '../assets/styles/bootstrap.min.css';
.buttonEditar {
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
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch input { 
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #2196F3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #2196F3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
.switchDescription {
  max-width: 100px;
  margin-left: 20px;
  margin-top: 10px;
}
.sliderDiv {
  margin-top: 12px;
}

.slider.round2 {
  border-radius: 34px;
}

.slider.round2:before {
  border-radius: 50%;
}

.slider2 {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider2:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

.switch2 {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch2 input { 
  opacity: 0;
  width: 0;
  height: 0;
}

.layoutSwitch {
  position: absolute;
  left: 110px;
  top: 84px;
}

.layoutText {
  position: absolute;
  left: 20px;
  max-width: 100px;
}
</style>