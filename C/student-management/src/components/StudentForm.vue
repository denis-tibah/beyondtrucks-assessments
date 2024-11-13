<template>
  <v-dialog v-model="showDialog" max-width="500">
    <v-card>
      <v-card-title>
        <span class="headline">{{ student ? 'Edit Student' : 'Add Student' }}</span>
      </v-card-title>

      <v-card-text>
        <v-form v-model="formValid.valid">
          <v-text-field
            v-model="form.name"
            label="Name"
            :rules="[rules.required]"
            required
          ></v-text-field>

          <v-text-field
            v-model="form.age"
            label="Age"
            type="number"
            :rules="[rules.required, rules.minAge]"
            required
          ></v-text-field>

          <v-select
            v-model="form.sexuality"
            :items="['Male', 'Female']"
            label="Sexuality"
            :rules="[rules.required]"
            required
          ></v-select>
        </v-form>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" @click="closeForm">Cancel</v-btn>
        <v-btn color="blue darken-1" :disabled="!formValid.valid" @click="submitForm">Save</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script lang="ts">
import { defineComponent, reactive, watch } from 'vue';
import { Student } from '@/types/student';

export default defineComponent({
  props: {
    student: {
      type: Object as () => Student | null,
      default: null,
    },
    showDialog: {
      type: Boolean,
      default: true,
    },
  },
  emits: ['close', 'save'],
  setup(props, { emit }) {
    const form = reactive<Student>({
      studentId: props.student?.studentId || 0,
      name: props.student?.name || '',
      age: props.student?.age || 1,
      sexuality: props.student?.sexuality || '',
    });

    const formValid = reactive({ valid: false });

    const rules = {
      required: (value: string) => !!value || 'Required.',
      minAge: (value: number) => (value > 0 && value <= 120) || 'Age must be between 1 and 120.',
    };

    watch(
      () => props.student,
      (newStudent) => {
        if (newStudent) {
          form.studentId = newStudent.studentId;
          form.name = newStudent.name;
          form.age = newStudent.age;
          form.sexuality = newStudent.sexuality;
        } else {
          form.studentId = 0;
          form.name = '';
          form.age = 1;
          form.sexuality = '';
        }
      },
      { immediate: true }
    );

    const submitForm = () => {
      console.log(formValid)
      if (formValid.valid) {
        emit('save', form);
        closeForm();
      }
    };

    const closeForm = () => {
      emit('close');
    };

    return {
      form,
      formValid,
      rules,
      submitForm,
      closeForm,
    };
  },
});
</script>

<style scoped>
.headline {
  font-size: 1.2em;
  font-weight: bold;
}
</style>
