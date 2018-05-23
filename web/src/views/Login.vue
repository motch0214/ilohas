<template lang="pug">
doctype html
.login
  el-card.login-card
    .login-header(slot="header")
      .header-title ilohas
    el-form(@submit.native.prevent)
      el-form-item(size="medium")
        el-input(v-model="username" placeholder="User name" autofocus)
      el-form-item(size="medium")
        el-input(v-model="password" type="password" placeholder="Password")
      el-form-item
        el-button.login-button(type="primary" native-type="submit" @click="login") Login
</template>

<script>
import axios from 'axios';

export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: '',
    };
  },
  methods: {
    login() {
      axios.post('/ilohas-api/login', {
        username: this.username,
        password: this.password,
      })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.login-card {
  margin: 0 auto;
  margin-top: 30px;
  max-width: 400px;
}
.header-title {
  text-align: center;
  font-size: 2em;
  font-weight: bold;
}
.login-button {
  width: 100%;
}
</style>
