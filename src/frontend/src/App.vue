<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/" class="navbar-brand" style="padding: 10px;">FarolDemo</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/" class="nav-link">Listagem</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/add" class="nav-link">Cadastro</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/about" class="nav-link">Sobre</router-link>
        </li>
      </div>
    </nav>

    <div class="container mt-3">
      <router-view />
    </div>
    <div class="fixed"><strong><h6>Created by yagofx</h6></strong></div>
  </div>
</template>
<script>
export default {
  name: "app"
};
</script>
<style>
div.fixed {
  position: fixed;
  width: 50%;
  bottom: 10px;
  left: 10px;
}
</style>
