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
      const params = new URLSearchParams();
      params.append('username', this.username);
      params.append('password', this.password);
      axios.post('/ilohas-api/login', params, {
        headers: {
          'cache-control': 'max-age=0',
          'upgrade-insecure-requests': 1,
        }
      })
      .then((res) => {
        if ('sessionId' in res.data && 'displayName' in res.data) {
          localStorage.setItem('user-token', JSON.stringify(res.data));
          this.$router.push(this.$route.query.redirect || '/');
        } else {
          console.error(res.data)
        }
      })
      .catch((err) => {
        console.error(err);
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
