<template>
  <div class="dropdown-menu-container">
    <el-select v-model="selectedSite" placeholder="Select Site" @change="onSiteChange" class="dropdown">
      <el-option v-for="site in sites" :key="site.site" :label="site.site" :value="site.site"></el-option>
    </el-select>
    <el-select v-model="selectedBuilding" placeholder="Select Building" @change="onBuildingChange" :disabled="!selectedSite" class="dropdown">
      <el-option v-for="building in buildings" :key="building.name" :label="building.name" :value="building.name"></el-option>
    </el-select>
    <el-select v-model="selectedFloor" placeholder="Select Floor" :disabled="!selectedBuilding" class="dropdown">
      <el-option v-for="floor in floors" :key="floor" :label="floor" :value="floor"></el-option>
    </el-select>
    <el-button type="primary" @click="submit" :disabled="!selectedFloor" class="submit-button">Submit</el-button>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, defineEmits } from 'vue';
import axios from 'axios';

interface Building {
  name: string;
  floor: string[];
}

interface Site {
  site: string;
  building: Building[];
}

const sites = ref<Site[]>([]);
const buildings = ref<Building[]>([]);
const floors = ref<string[]>([]);

const selectedSite = ref<string>('');
const selectedBuilding = ref<string>('');
const selectedFloor = ref<string>('');

const emits = defineEmits(['submit']);

const fetchSiteDetails = async () => {
  try {
    const response = await axios.get('http://10.199.163.219:9006/wms/getSiteBuildingFloorDetails');
    sites.value = response.data;
    console.log('Fetched sites:', sites.value); // Debugging line
  } catch (error) {
    console.error('Error fetching site details:', error);
  }
};

onMounted(() => {
  fetchSiteDetails();
});

const onSiteChange = (siteName: string) => {
  const site = sites.value.find(site => site.site === siteName);
  if (site) {
    buildings.value = site.building;
  } else {
    buildings.value = [];
  }
  selectedBuilding.value = '';
  selectedFloor.value = '';
  floors.value = [];
};

const onBuildingChange = (buildingName: string) => {
  const building = buildings.value.find(building => building.name === buildingName);
  if (building) {
    floors.value = building.floor;
  } else {
    floors.value = [];
  }
  selectedFloor.value = '';
};

const submit = () => {
  // Emit the selected data
  emits('submit', selectedSite.value, selectedBuilding.value, selectedFloor.value);
};
</script>

<style scoped>
.dropdown-menu-container {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

.dropdown {
  width: 200px;
}

.submit-button {
  align-self: center;
}
</style>