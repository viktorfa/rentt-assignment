<template>
  <div>
  <v-form>
  <v-container>
      <v-layout row wrap>
        <v-flex xs12 sm6 md4>
    <v-text-field 
      type="text"
      v-model="editedEmployee.name"
      v-on:input="startEdit"
    />
        </v-flex>
        <v-flex xs12 sm6 md4>
    <v-text-field 
      type="number"
      v-model="editedEmployee.age"
      v-on:input="startEdit"
    />
        </v-flex>
        <v-flex xs12 md4>
    <v-btn
      v-on:click="eUpdate"
      v-if="isEditing === true"
      color="info"
    >Save</v-btn>
    <v-btn
      v-on:click="eDeleteEmployee"
      color="error"
    >
      Delete
    </v-btn>
        </v-flex>
      </v-layout>
  </v-container>
  </v-form>
  </div>
</template>


<script>
export default {
  name: "EmployeeListItem",
  props: {
    employee: Object,
    deleteEmployee: Function,
    update: Function
  },
  data: function() {
    return {
      editedEmployee: { ...this.employee },
      isEditing: false
    };
  },
  methods: {
    eDeleteEmployee: function() {
      this.deleteEmployee(this.employee);
    },
    eUpdate: function() {
      this.stopEdit();
      this.update(this.editedEmployee);
    },
    startEdit: function() {
      this.isEditing = true;
    },
    stopEdit: function() {
      this.isEditing = false;
    }
  }
};
</script>
