<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title class="headline text-uppercase">
        <span>Employee Manager</span>
        <span class="font-weight-light"> >MATERIAL DESIGN</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        flat
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
      >
        <span class="mr-2">Latest Release</span>
      </v-btn>
    </v-toolbar>

    <v-content>
      <EmployeeContainer/>
    </v-content>
  </v-app>
</template>

<script>
import EmployeeContainer from "./components/EmployeeContainer";

export default {
  name: "App",
  components: {
    EmployeeContainer
  },
  data() {
    return {
      //
    };
  }
};
</script>
