<template>
  <div class="common-layout">
    <el-container>
      <el-header class="main-header">
        <div class="header-icon">
          <div class="logo" />
          <div>Employee Workspace Management System</div>
        </div>
        <div class="user-actions">
          <el-button
            type="primary"
            icon="el-icon-user"
            @click="openLoginDialog"
            v-if="!userName"
          >
            Login
          </el-button>
          <el-dropdown v-else>
            <el-button type="primary" icon="el-icon-user">
              {{ userName }}<i class="el-icon-arrow-down el-icon--right"></i>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click="logout">Logout</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-main>
          <DropdownMenu @submit="handleSubmit" @change="handleChange" />
          <div v-if="submitted" class="tabs-container">
            <TabComponent
              :activeTab="activeTab"
              @update:activeTab="activeTab = $event"
              :employeeForm="employeeForm"
              :toggleForm="toggleForm"
              :submitForm="submitForm"
              :key="tabKey"
            />
          </div>
        </el-main>
      </el-container>
    </el-container>

    <!-- Login Dialog -->
    <el-dialog
      v-model="loginDialogVisible"
      title="Login"
      width="400px"
      :before-close="handleClose"
    >
      <el-form :model="loginForm" label-width="100px" class="login-form">
        <el-form-item label="Email">
          <el-input v-model="loginForm.email"></el-input>
        </el-form-item>
        <el-form-item label="Password">
          <el-input type="password" v-model="loginForm.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitLoginForm">Login</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import DropdownMenu from './DropdownMenu.vue';
import TabComponent from './TabComponent.vue';
import { useSeatStore } from '../store/seatStore';
import axios from 'axios';
import { ElMessage, ElMessageBox } from 'element-plus';

interface EmployeeForm {
  name: string;
  id: string;
  department: string;
  jobTitle: string;
}

const seatStore = useSeatStore();
const submitted = ref<boolean>(false);
const activeTab = ref('employee-management');
const tabKey = ref(0); // Key to force re-render

const showForm = ref(false);
const employeeForm = ref<EmployeeForm>({
  name: '',
  id: '',
  department: '',
  jobTitle: ''
});

const toggleForm = () => {
  showForm.value = !showForm.value;
};

// Highlighted part: submitForm method that logs the submitted data
const submitForm = () => {
  console.log('Employee Form Submitted:', employeeForm.value);
  // Add your form submission logic here
  toggleForm();
};

const router = useRouter();

const handleSubmit = (site: string, department: string, subDepartment: string) => {
  seatStore.setSiteDetails(site, department, subDepartment);
  submitted.value = true;
};

const handleChange = () => {
  submitted.value = false;
  activeTab.value = 'employee-management';
  tabKey.value += 1;
};

// New code for login functionality
const loginDialogVisible = ref(false);
const loginForm = ref({
  email: '',
  password: ''
});
const userName = ref('');

const openLoginDialog = () => {
  loginDialogVisible.value = true;
};

const handleClose = (done: () => void) => {
  ElMessageBox.confirm('Are you sure to close this dialog?')
    .then(() => {
      done();
    })
    .catch(() => {
      // catch error
    });
};

const resetLoginForm = () => {
  loginForm.value.email = '';
  loginForm.value.password = '';
};

const submitLoginForm = async () => {
  try {
    const formData = {
      username: loginForm.value.email,
      password: loginForm.value.password
    };
    console.log('Login Data:', formData);
    const response = await axios.post('http://10.199.163.219:9006/wms/login', formData);
    console.log('Login response:', response.data);
    if (response.data !== 'user not found') {
      ElMessage.success('Login successful');
      userName.value = response.data;
      loginDialogVisible.value = false;
      resetLoginForm();
    } else {
      ElMessage.error('User not authenticated');
      resetLoginForm();
      loginDialogVisible.value = false;
    }
  } catch (error) {
    console.error('Error logging in:', error);
    ElMessage.error('User not authenticated');
    resetLoginForm();
    loginDialogVisible.value = false;
  }
};

const logout = () => {
  userName.value = '';
  ElMessage.success('Logout successful');
};
</script>

<style scoped>
.main-header {
  background-color: #0c2545;
  height: 80px !important;
  display: flex;
  align-items: center;
  color: #ffff;
  font-weight: bold;
  font-size: 20px;
  justify-content: space-between;
}
.header-icon {
  display: flex;
  align-items: center;
}
.user-actions {
  display: flex;
  align-items: center;
}
.tabs-container {
  margin-top: 20px;
}
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
.demo-tabs .custom-tabs-label .el-icon {
  vertical-align: middle;
}
.demo-tabs .custom-tabs-label span {
  vertical-align: middle;
  margin-left: 4px;
}
.tab-content {
  padding: 20px;
}
.employee-form {
  margin-top: 20px;
}
.login-form {
  margin-top: 20px;
}
</style>