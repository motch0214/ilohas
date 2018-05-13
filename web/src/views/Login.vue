<template lang="pug">
.login
  el-card.login-card
    .login-header(slot="header")
      .header-title ilohas
    el-form
      el-form-item(size="medium")
        el-input(v-model="username" placeholder="User name")
      el-form-item(size="medium")
        el-input(v-model="password" type="password" placeholder="Password")
      el-form-item
        el-button.login-button(type="primary" @click="login") Login
</template>

<script>
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
      console.log('login!');
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
