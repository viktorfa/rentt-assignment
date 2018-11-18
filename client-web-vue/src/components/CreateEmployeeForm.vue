<template>
  <v-container>
    <v-form>
    <v-container>
      <h2>New employee</h2>
      <v-layout row wrap>
        <v-flex xs12 sm6 md4>
          <v-text-field
            type="text"
            id='name-input'
            label="name"
            v-model="newEmployee.name"
          />
          </v-flex>
              <v-flex xs12 sm6 md4>
          <v-text-field
            type="number"
            id='age-input'
            label="age"
            v-model="newEmployee.age"
          />
          </v-flex>
              <v-flex xs12 sm6 md4>
          <v-btn
            v-on:click="_create"
            color="success"
          >
            Create
          </v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </v-form>
  </v-container>
</template>

<script>
export default {
  name: "CreateEmployeeForm",
  props: {
    create: Function
  },
  data: function() {
    return { newEmployee: {} };
  },
  methods: {
    _create: function() {
      this.create(this.newEmployee);
    }
  }
};
</script>
