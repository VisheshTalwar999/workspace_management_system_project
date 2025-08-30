<template>
    <div v-if="seatDetails">
    <div class="seat-layout enclosed">
        Enclosed rooms
        <div class="seat-container">
            <div v-for="seat in seatDetails['enclosed_room']" class="seat enclosed-seat" :class="seat.s_status" @click="selectSeat(seat)">
                {{ seat.seatNo }}
            </div>
        </div>
    </div>
 
    <div class="seat-layout open-1">
        Open Cubical Of 1 Seats
        <div class="seat-container">
            <div v-for="seat in seatDetails['open_cub_1']" class="seat open-1-seat" :class="seat.s_status" @click="selectSeat(seat)">
                {{ seat.seatNo }}
            </div>
        </div>
    </div>
 
    <div class="seat-layout open-2">
        Open Cubical Of 2 Seats
        <div class="seat-container">
            <div v-for="seat in seatDetails['open_cub_2']" class="seat open-2-seat" :class="seat.s_status" @click="selectSeat(seat)">
                {{ seat.seatNo }}
            </div>
        </div>
    </div>
 
    <div class="seat-layout open-4">
        Open Cubical Of 4 Seats
        <div class="seat-container">
            <div v-for="seat in seatDetails['open_cub_4']" class="seat open-4-seat" :class="seat.s_status" @click="selectSeat(seat)">
                {{ seat.seatNo }}
            </div>
        </div>
    </div>
    </div>
</template>
 
<script lang="ts" setup>
import { useSeatStore } from '../store/seatStore';
 
 
const props = defineProps<{seatDetails: any}>()
const emit = defineEmits<{(e: 'selectSeat', p: any): void}>()
const seatStore = useSeatStore();
 
// const seats = {
//     "enclosed-rooms": [
//         {
//             seatNo: "GN-G-01",
//             seatType: "enclosed-rooms",
//             status: "filled",
//         },
//         {
//             seatNo: "GN-G-02",
//             seatType: "enclosed-rooms",
//             status: "vacant"
//         },
//         {
//             seatNo: "GN-G-03",
//             seatType: "enclosed-rooms",
//             status: "reserved"
//         },
//         {
//             seatNo: "GN-G-04",
//             seatType: "enclosed-rooms",
//             status: "filled"
//         },
//         {
//             seatNo: "GN-G-05",
//             seatType: "enclosed-rooms",
//             status: "vacant"
//         },
//         {
//             seatNo: "GN-G-06",
//             seatType: "enclosed-rooms",
//             status: "filled"
//         },
//         {
//             seatNo: "GN-G-07",
//             seatType: "enclosed-rooms",
//             status: "filled"
//         },
//         {
//             seatNo: "GN-G-08",
//             seatType: "enclosed-rooms",
//             status: "vacant"
//         },
//         {
//             seatNo: "GN-G-09",
//             seatType: "enclosed-rooms",
//             status: "filled"
//         },
//         {
//             seatNo: "GN-G-10",
//             seatType: "enclosed-rooms",
//             status: "filled"
//         },
//         {
//             seatNo: "GN-G-11",
//             seatType: "enclosed-rooms",
//             status: "vacant"
//         }
//     ],
//     "open-1": [
//         { seatNo: "GN-G-12", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-13", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-14", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-15", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-16", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-17", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-18", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-19", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-20", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-21", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-22", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-23", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-24", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-25", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-26", seatType: "open-1", status: "reserved" },
//         { seatNo: "GN-G-27", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-28", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-29", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-30", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-31", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-32", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-33", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-34", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-35", seatType: "open-1", status: "filled" },
//         { seatNo: "GN-G-36", seatType: "open-1", status: "filled" }
 
//     ],
//     "open-2": [
//         { seatNo: "GN-G-37", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-38", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-39", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-40", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-41", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-42", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-43", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-44", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-45", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-46", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-47", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-48", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-49", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-50", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-51", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-52", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-53", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-54", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-55", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-56", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-57", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-58", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-59", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-60", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-61", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-62", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-63", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-64", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-65", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-66", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-67", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-68", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-69", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-70", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-71", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-72", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-73", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-74", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-75", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-76", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-77", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-78", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-79", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-80", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-81", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-82", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-83", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-84", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-85", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-86", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-87", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-88", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-89", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-90", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-91", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-92", seatType: "open-2", status: "filled" }
//     ],
//     "open-4": [
//         { seatNo: "GN-G-93", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-94", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-95", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-96", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-97", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-98", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-99", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-100", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-101", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-102", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-103", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-104", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-105", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-106", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-107", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-108", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-109", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-110", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-111", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-112", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-113", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-114", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-115", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-116", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-117", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-118", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-119", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-120", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-121", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-122", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-123", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-124", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-125", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-126", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-127", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-128", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-129", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-130", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-131", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-132", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-133", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-134", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-135", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-136", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-137", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-138", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-139", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-140", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-141", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-142", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-143", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-144", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-145", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-146", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-147", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-148", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-149", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-150", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-151", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-152", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-153", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-154", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-155", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-156", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-157", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-158", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-159", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-160", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-161", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-162", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-163", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-164", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-165", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-166", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-167", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-168", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-169", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-170", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-171", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-172", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-173", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-174", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-175", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-176", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-177", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-178", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-179", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-180", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-181", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-182", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-183", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-184", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-185", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-186", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-187", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-188", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-189", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-190", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-191", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-192", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-193", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-194", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-195", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-196", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-197", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-198", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-199", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-200", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-201", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-202", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-203", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-204", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-205", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-206", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-207", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-208", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-209", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-210", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-211", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-212", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-213", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-214", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-215", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-216", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-217", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-218", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-219", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-220", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-221", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-222", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-223", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-224", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-225", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-226", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-227", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-228", seatType: "open-2", status: "filled" },
//         { seatNo: "GN-G-229", seatType: "open-2", status: "filled" }
 
//     ]
// }
 
 
const selectSeat = (data:any)=>{
    emit('selectSeat', data);
        // seatStore.seatAllocation(data);
}
</script>
<style scoped>
.seat {
    /* width:100px;
        height: 50px;
        background-color: darkgray; */
 
 
    width: 100px;
    height: 50px;
    background-color: #4caf50;
    /* Default color for available seats */
    color: white;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 5px;
    /* Rounded corners for seats */
    cursor: pointer;
    transition: background-color 0.3s;
    /* Smooth transition for color change */
}
 
.enclosed-seat {
    width: 120px;
}
.open-1-seat {
    width: 90px;
}
 
.open-2-seat {
    width: 70px;
}
 
.open-4-seat {
    width: 60px;
    height: 40px;
    font-size: 12px;
}
 
 
 
.seat-container {
    display: flex;
    gap: 5px;
    flex-wrap: wrap;
}
 
.seat-layout {
    margin: 15px;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
    /* Space between rows */
    padding: 20px;
    /* Padding around the layout */
    background-color: #f5f5f5;
    /* Background color for the layout */
    border: 1px solid #ccc;
    /* Border around the layout */
    border-radius: 10px;
    /* Rounded corners for the layout */
}
 
.enclosed {
    background-color: lightgoldenrodyellow;
}
 
.open-1 {
    background-color: lightblue;
}
 
.open-2 {
    background-color: lightsalmon;
}
 
.open-4 {
    background-color: linen;
}
 
.FILLED {
    background-color: #f44336;
}
 
.FILLED:hover {
    background-color: #c70f02;
}
 
.VACANT {
    background-color: #4caf50;
    /* Green */
}
.VACANT:hover {
    background-color: #064a09;
}
.reserved {
    background-color: #ff9800;
    cursor: not-allowed;
}
</style>
 