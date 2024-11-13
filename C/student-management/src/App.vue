<script lang="ts">
import StudentManagement from './views/StudentManagement.vue';
import Header from '@/components/Header.vue';
import Menu from '@/components/Menu.vue';
import { defineComponent } from 'vue';

export default defineComponent({
    components: {
      StudentManagement,
      Header,
      Menu,
    },
  });
</script>

<template>
  <div class="container">
    <Header />
    <div class="content">
      <Menu />
      <StudentManagement />
    </div>
  </div>
</template>

<style scoped>
  .container {
    display: flex;
    flex-direction: column;
    height: 100vh;
  }
  
  .content {
    display: flex;
    flex: 1;
    width: 100%;
  }
  
  header {
    flex-basis: 50px;
  }
</style>
