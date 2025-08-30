<!-- <template>
    <div class="login-page">
      <el-dialog
        v-model="loginDialogVisible"
        title="Login"
        width="400px"
        :visible="true"
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
  import axios from 'axios';
  import { ElMessage } from 'element-plus';
  
  const loginDialogVisible = ref(true);
  const loginForm = ref({
    email: '',
    password: ''
  });
  const userName = ref('');
  
  const router = useRouter();
  
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
        resetLoginForm();
        router.push({ name: 'Dashboard' });
      } else {
        ElMessage.error('User not authenticated');
        resetLoginForm();
      }
    } catch (error) {
      console.error('Error logging in:', error);
      ElMessage.error('User not authenticated');
      resetLoginForm();
    }
  };
  </script>
  
  <style scoped>
  .login-page {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }
  .login-form {
    margin-top: 20px;
  }
  </style> -->