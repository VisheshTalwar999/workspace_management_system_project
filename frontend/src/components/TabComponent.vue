<template>
  <div class="common-layout">
    <el-container>
      <el-main>
        <el-tabs v-model="activeTab">
          <el-tab-pane name="employee-management">
            <template #label>
              <span class="custom-tabs-label">
                <el-icon><calendar /></el-icon>
                <span>Employee Management</span>
              </span>
            </template>
            <div class="tab-content">
              <el-button type="primary" @click="toggleForm" class="new-employee-button">Add New Employee</el-button>
              <el-button type="primary" @click="openUpdateIdDialog" class="update-employee-button">Update Employee</el-button>
              <el-form v-if="showEmployeeForm" :model="employeeForm" label-width="180px" class="employee-form">
                <el-form-item label="Name">
                  <el-input v-model="employeeForm.name"></el-input>
                </el-form-item>
                <el-form-item label="Email">
                  <el-input v-model="employeeForm.email"></el-input>
                </el-form-item>
                <el-form-item label="Department">
                  <el-input v-model="employeeForm.department"></el-input>
                </el-form-item>
                <el-form-item label="Sub-Department">
                  <el-input v-model="employeeForm.subDepartment"></el-input>
                </el-form-item>
                <el-form-item label="Job Grade">
                  <el-input v-model="employeeForm.jobGrade"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm">Submit</el-button>
                </el-form-item>
              </el-form>
              <el-card v-if="submitted && !showEmployeeForm" class="employee-card">
                <div class="card-header">
                  <h3>Employee Details</h3>
                </div>
                <div class="card-content">
                  <p><strong>Name:</strong> {{ submittedData.name }}</p>
                  <p><strong>Email:</strong> {{ submittedData.email }}</p>
                  <p><strong>Department:</strong> {{ submittedData.department }}</p>
                  <p><strong>Sub-Department:</strong> {{ submittedData.subDepartment }}</p>
                  <p><strong>Job Grade:</strong> {{ submittedData.jobGrade }}</p>
                </div>
                <div class="card-footer">
                  <el-button type="primary" plain @click="dialogVisible = true">
                    Show Seats
                  </el-button>
                  <el-dialog
                    v-model="dialogVisible"
                    title="Tips"
                    width="1500"
                    :before-close="handleClose"
                  >
                    <SeatLayout @selectSeat="selectSeat" :seatDetails="seatStore.seatDetails"></SeatLayout>
                    <template #footer>
                      <div class="dialog-footer">
                        <el-button @click="dialogVisible = false">Cancel</el-button>
                        <el-button type="primary" @click="confirmSeatSelect">
                          Confirm
                        </el-button>
                      </div>
                    </template>
                  </el-dialog>
                </div>
              </el-card>
            </div>
          </el-tab-pane>
          <el-tab-pane label="Seat Layout" name="sheet-layout">
            <div class="tab-content">
              <h2>Seat Layout</h2>
              <SeatLayout :seatDetails="seatStore.floorDetails"></SeatLayout>
              <!-- Add your sheet layout content here -->
            </div>
          </el-tab-pane>
          <el-tab-pane label="Report" name="report">
            <div class="tab-content">
              <Report></Report>
              <!-- Add your sheet layout content here -->
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>

    <!-- Update Employee ID Dialog -->
    <el-dialog
      v-model="updateIdDialogVisible"
      title="Enter Employee ID"
      width="400px"
      :before-close="handleClose"
    >
      <el-form :model="updateIdForm" label-width="180px" class="update-id-form">
        <el-form-item label="Employee ID">
          <el-input v-model="updateIdForm.id"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="fetchEmployeeData">Fetch</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- Update Employee Dialog -->
    <el-dialog
      v-model="updateDialogVisible"
      title="Update Employee"
      width="600px"
      :before-close="handleClose"
    >
      <el-form :model="updateForm" label-width="180px" class="update-form">
        <el-form-item label="Employee ID">
          <el-input v-model="updateForm.empId" readonly></el-input>
        </el-form-item>
        <el-form-item label="Name">
          <el-input v-model="updateForm.name" readonly></el-input>
        </el-form-item>
        <el-form-item label="Email">
          <el-input v-model="updateForm.email" readonly></el-input>
        </el-form-item>
        <el-form-item label="Department">
          <el-input v-model="updateForm.department"></el-input>
        </el-form-item>
        <el-form-item label="Sub-Department">
          <el-input v-model="updateForm.subDepartment"></el-input>
        </el-form-item>
        <el-form-item label="Job Grade">
          <el-input v-model="updateForm.jobGrade"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitUpdateForm">Update</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, nextTick } from 'vue';
import { Calendar } from '@element-plus/icons-vue';
import { useRouter } from 'vue-router';
import SeatLayout from './SeatLayout.vue';
import { useSeatStore } from '../store/seatStore';
import axios from 'axios';
import { ElMessage, ElMessageBox } from 'element-plus';
// import Report from './Report.vue';

const activeTab = ref('employee-management');
const router = useRouter();
const seatStore = useSeatStore();

const navigateHome = () => {
  router.push('/home');
};

const employeeForm = ref({
  name: '',
  email: '',
  department: '',
  subDepartment: '',
  jobGrade: ''
});

const submittedData = ref({
  name: '',
  email: '',
  department: '',
  subDepartment: '',
  jobGrade: ''
}); // New ref to store submitted data

const showEmployeeForm = ref(false);
const submitted = ref(false);

const toggleForm = () => {
  showEmployeeForm.value = !showEmployeeForm.value;
  if (!showEmployeeForm.value) {
    submitted.value = false;
    resetForm();
  }
};

const dialogVisible = ref(false);

const handleClose = (done: () => void) => {
  ElMessageBox.confirm('Are you sure to close this dialog?')
    .then(() => {
      done();
    })
    .catch(() => {
      // catch error
    });
};

const submitForm = async () => {
  try {
    const formData = { ...employeeForm.value };
    console.log('Data being sent:', formData);
    const response = await axios.post('http://10.199.163.219:9006/wms/setEmployee/', formData);
    console.log('Form submitted:', response.data);
    await seatStore.submitEmployeeForm(formData);
    if (response.data === 'User already exists') {
      ElMessage.error('User already exists');
    } else {
      submittedData.value = { ...employeeForm.value }; // Store submitted data
      submitted.value = true;
      showEmployeeForm.value = false;
      nextTick(() => {
        resetForm(); // Reset the form after storing the submitted data
      });
    }
  } catch (error) {
    console.error('Error submitting form:', error);
    ElMessage.error('Error submitting form');
  }
};

const resetForm = () => {
  employeeForm.value = {
    name: '',
    email: '',
    department: '',
    subDepartment: '',
    jobGrade: ''
  };
};

const selectedSeat = ref();

const selectSeat = (data: any) => {
  selectedSeat.value = data;
};

const confirmSeatSelect = () => {
  seatStore.seatAllocation(selectedSeat.value);
  dialogVisible.value = false;
};

// New code for update functionality
const updateIdDialogVisible = ref(false);
const updateDialogVisible = ref(false);
const updateIdForm = ref({
  id: ''
});
const updateForm = ref({
  empId: '',
  name: '',
  email: '',
  department: '',
  subDepartment: '',
  jobGrade: ''
});

const openUpdateIdDialog = () => {
  updateIdDialogVisible.value = true;
};

const fetchEmployeeData = async () => {
  try {
    const response = await axios.get(`http://10.199.163.219:9006//wms/getSingleEmployee/${updateIdForm.value.id}`);
    if (response.data) {
      const data = response.data;
      updateForm.value = { ...data };
      updateIdDialogVisible.value = false;
      updateDialogVisible.value = true;
    } else {
      ElMessage.error('Employee not found');
    }
  } catch (error) {
    console.error('Error fetching employee data:', error);
    ElMessage.error('Error fetching employee data');
  }
};

const submitUpdateForm = async () => {
  try {
    const formData = { ...updateForm.value };
    console.log('Data being sent for update:', formData);
    const response = await axios.put('http://10.199.163.219:9006/wms/updateEmployee', formData);
    console.log('Form updated:', response.data);
    if (response.data === 'User not registered') {
      ElMessage.error('User not registered');
    } else {
      submittedData.value = { ...updateForm.value }; // Store updated data
      submitted.value = true;
      updateDialogVisible.value = false;
      ElMessage.success('Employee updated successfully');
    }
  } catch (error) {
    console.error('Error updating form:', error);
    ElMessage.error('Error updating form');
  }
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
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: 0;
}
.employee-card {
  margin-top: 20px;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: 20px 0;
}
.card-header {
  border-bottom: 1px solid #ebeef5;
  padding-bottom: 10px;
  margin-bottom: 10px;
}
.card-content p {
  margin: 0 0 10px;
}
.card-footer {
  border-top: 1px solid #ebeef5;
  padding-top: 10px;
  margin-top: 10px;
  text-align: left;
}
.new-employee-button {
  margin-bottom: 20px;
}
.update-employee-button {
  margin-bottom: 20px;
  margin-left: 10px;
}
</style>