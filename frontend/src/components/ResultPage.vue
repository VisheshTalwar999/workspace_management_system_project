<template>
    <div class="common-layout">
      <el-container>
        <el-header class="main-header">
          <div class="header-icon">
            <div class="logo" />
            <div>Employee Workspace Management System</div>
          </div>
        </el-header>
        <el-container>
          <el-main>
            <div class="result-container">
              <h2>Selected Data</h2>
              <p><strong>Site:</strong> {{ selectedSite }}</p>
              <p><strong>Department:</strong> {{ selectedDepartment }}</p>
              <p><strong>Sub-Department:</strong> {{ selectedSubDepartment }}</p>
            </div>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </template>
  
  <script setup lang="ts">
  import { useRoute } from 'vue-router';
  
  const route = useRoute();
  
  const selectedSite = route.query.site as string;
  const selectedDepartment = route.query.department as string;
  const selectedSubDepartment = route.query.subDepartment as string;
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
  .result-container {
    padding: 20px;
  }
  </style>