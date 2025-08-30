import axios from "axios";
import { defineStore } from "pinia";
import { ref } from "vue";
 
 
export const useSeatStore = defineStore('seatStore', ()=>{
    const site = ref<string>('');
    const department = ref<string>('');
    const subDepartment = ref<string>('');
    const seatDetails = ref<any>();
    const reportsData = ref<any[]>();
    const building = ref<any>();
    const floor= ref<any>();
    const floorDetails = ref<any>();
    const empEmail = ref<any>();
    const jobGrade = ref<any>();
 
    // const getWorkFlow = computed(()=>{
    //     return workflow;
    // })
 
    async function setSiteDetails(s: string,dep: string, subDep: string) {
        site.value = s;
        building.value = dep;
        floor.value = subDep;
            console.log("site---", site)
            console.log("site---", department)
        await fetchFloorSeatDetails();
    }
 
    async function fetchSeatDetails() {
        const response = await axios.post('http://10.199.163.219:9006/wms/getSeatDetailsDeptWise/', {
            "details":
            {
                "seatNo": "",
                "site": site.value,
                "building": "",
                "floor": "",
                "enclosedRoom": "",
                "openCub1": "",
                "openCub2": "",
                "openCub4": "",
                "sStatus": "",
                "sEmpId": 0,
                "jobGrade": "",
                "dept":department.value,
                "subDept":subDepartment.value
            }
        });
    seatDetails.value = response.data;
    console.log('Fetched sites:', response); // Debugging line
    }
 
    async function submitEmployeeForm(data: any) {
            department.value = data.department;
            subDepartment.value = data.subDepartment;
            empEmail.value = data.email;
            jobGrade.value = data.jobGrade
            await fetchSeatDetails();
    }
 
    async function fetchFloorSeatDetails() {
        const response = await axios.post('http://10.199.163.219:9006/wms/getSeatDetailsFloorWise/', {
            "details":
            {
                "site": site.value,
                "building": building.value,
                "floor": floor.value,
            }
        });
        floorDetails.value = response.data;
    console.log('Fetched sites:', response); // Debugging line
    }
 
    async function fetchReportsData() {
        const response = await axios.post('http://10.199.163.219:9006/wms/getSeatDetailsDeptWise/', {
            "details":
            {
                "seatNo": "",
                "site": "Noida",
                "building": "",
                "floor": "",
                "enclosedRoom": "",
                "openCub1": "",
                "openCub2": "",
                "openCub4": "",
                "sStatus": "",
                "sEmpId": 0,
                "jobGrade": "",
                "dept":"",
                "subDept":""
            }
        });
    reportsData.value = [...response.data['enclosed_room'], ...response.data['open_cub_1'], ...response.data['open_cub_2'], ...response.data['open_cub_4']];
    console.log('Fetched sites:', reportsData.value); // Debugging line
    }
 
    async function seatAllocation(data: any) {
        const response = await axios.post('http://10.199.163.219:9006/wms/assignSeatToEmployee/', {
            "department":department.value,
        "empEmail": empEmail.value,
        "jobgrade": jobGrade.value,
        "seatNo": data.seatNo
        }
       
        );

        await fetchSeatDetails();
    }
   
    return {site, department, subDepartment, seatDetails, setSiteDetails, fetchSeatDetails, reportsData, fetchReportsData,submitEmployeeForm,floorDetails,seatAllocation}
})
 