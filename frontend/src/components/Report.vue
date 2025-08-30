<template>
    <div>
      <div class="field-title">Select Category Type</div>
      <el-select v-model="choosenOption" placeholder="Select" size="large" style="width: 240px" @change="rebanta">
        <el-option v-for="option in options" :key="option" :label="option" :value="option" />
      </el-select>
      <div ref="chart"></div>
    </div>
  </template>
  
  <script setup lang="ts">
  import Highcharts from 'highcharts';
  import exporting from 'highcharts/modules/exporting';
  import exportData from 'highcharts/modules/export-data';
  import accessibility from 'highcharts/modules/accessibility';
  import { onMounted, ref, computed } from 'vue';
  import { useSeatStore } from '../store/seatStore';
  
  exporting(Highcharts);
  exportData(Highcharts);
  accessibility(Highcharts);
  
  const options = ref<string[]>(['Building', 'Floor', 'dept', 'sub_dept', 'job_grade']);
  const choosenOption = ref<string>('');
  const seatStore = useSeatStore();
  
  const chart = ref<HTMLElement | null>(null);
  let responseData: Array<{
    building: string;
    floor: string;
    department: string;
    'sub-department': string;
    'job-grade': string;
    'seating-type': string;
    status: string;
    emplyeeId: string;
    seatNo: string;
  }> = [];
  
  const barData = computed(() => responseData);
  
  const rebanta = () => {
    const data = barData.value.map(item => item[choosenOption.value.toLowerCase() as keyof typeof item]);
  
    const occurrence: Record<string, number> = {};
    data.forEach((item) => {
      if (occurrence[item]) {
        occurrence[item]++;
      } else {
        occurrence[item] = 1;
      }
    });
  
    const seriesData = Object.entries(occurrence).map(([key, value]) => ({ name: key, y: value }));
  
    Highcharts.chart(chart.value as HTMLElement, {
      chart: {
        type: 'bar'
      },
      title: {
        text: choosenOption.value,
        align: 'left'
      },
      xAxis: {
        categories: seriesData.map(item => item.name),
        title: {
          text: choosenOption.value
        },
        gridLineWidth: 1,
        lineWidth: 0
      },
      yAxis: {
        min: 0,
        title: {
          text: 'Number of Seats',
          align: 'high'
        },
        labels: {
          overflow: 'justify'
        },
        gridLineWidth: 1
      },
      tooltip: {
        valueSuffix: ' responses'
      },
      plotOptions: {
        bar: {
          borderRadius: '50%',
          dataLabels: {
            enabled: true,
            format: '{point.y:.1f}'
          },
          groupPadding: 0.1
        }
      },
      legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'top',
        x: -40,
        y: 0,
        floating: true,
        borderWidth: 1,
        backgroundColor: Highcharts.defaultOptions.legend?.backgroundColor || '#FFFFFF',
        shadow: true
      },
      credits: {
        enabled: false
      },
      series: [{
        name: 'No. of Responses',
        data: seriesData.map(item => item.y)
      }]
    });
  };
  
  onMounted(async () => {
    await fetchReportData();
  });
  
  const fetchReportData = async () => {
    await seatStore.fetchReportsData();
    responseData = seatStore.reportsData;
  };
  </script>
  
  <style scoped>
  .field-title {
    text-transform: capitalize;
    font-size: 14px;
    font-weight: bold;
    padding-top: 10px;
  }
  </style>