<template>
  <v-container>
    <v-btn color="primary" class="mb-4" @click="openForm">Add Student</v-btn>
    <v-data-table
      :items="students"
      :headers="headers"
      class="elevation-1"
      item-value="studentId"
      item-key="studentId"
    >
      <template #item.actions="{ item }">
        <div class="table--actions">
          <v-btn color="blue" @click="editStudent(item)">Edit</v-btn>
          <v-btn color="red" @click="confirmDelete(item.studentId)"> Delete </v-btn>
        </div>
      </template>
    </v-data-table>

    <StudentForm
      v-if="showForm"
      :student="currentStudent"
      :showDialog="showForm"
      @close="closeForm"
      @save="saveStudent"
    />
  </v-container>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import api from '@/services/api';
import { Student } from '@/types/student';
import StudentForm from './StudentForm.vue';

export default defineComponent({
  components: { StudentForm },
  setup() {
    const students = ref<Student[]>([]);
    const showForm = ref(false);
    const currentStudent = ref<Student | null>(null);

    const headers = [
      { title: 'Student ID', value: 'studentId' },
      { title: 'Name', value: 'name' },
      { title: 'Age', value: 'age' },
      { title: 'Sexuality', value: 'sexuality' },
      { title: 'Actions', value: 'actions', sortable: false, align: 'center', },
    ];

    const fetchStudents = async () => {
      try {
        const response = await api.get<Student[]>('');
        students.value = response.data;
      } catch (error) {
        console.error(error);
      }
    };

    const openForm = () => {
      currentStudent.value = null;
      showForm.value = true;
    };

    const editStudent = (student: Student) => {
      currentStudent.value = { ...student };
      showForm.value = true;
    };

    const confirmDelete = async (studentId: number) => {
      if (confirm('Are you sure you want to delete this student?')) {
        await deleteStudent(studentId);
      }
    };

    const deleteStudent = async (studentId: number) => {
      try {
        await api.delete(`/${studentId}`);
        fetchStudents();
      } catch (error) {
        console.error(error);
      }
    };

    const saveStudent = async (student: Student) => {
      try {
        if (student.studentId) {
          await api.put(`/${student.studentId}`, student);
        } else {
          await api.post('', student);
        }
        closeForm();
        fetchStudents();
      } catch (error) {
        console.error(error);
      }
    };

    const closeForm = () => {
      showForm.value = false;
      currentStudent.value = null;
    };

    onMounted(fetchStudents);

    return {
      students,
      headers,
      showForm,
      currentStudent,
      openForm,
      editStudent,
      confirmDelete,
      closeForm,
      saveStudent,
    };
  },
});
</script>

<style scoped>
.table--actions {
  display: flex;
  gap: 3px;
}
</style>
