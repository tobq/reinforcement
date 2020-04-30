let rows = `LOG_SORT,100,0.01,1,2775
TIE_MIN_NEURONS_SORT,100,0.01,1,2514
FITNESS,100,0.01,1,4882
LOG_SORT,100,0.02,1,4198
TIE_MIN_NEURONS_SORT,100,0.02,1,2219
FITNESS,100,0.02,1,1696
LOG_SORT,100,0.04,1,419
TIE_MIN_NEURONS_SORT,100,0.04,1,4319
FITNESS,100,0.04,1,2050
LOG_SORT,100,0.08,1,1571
TIE_MIN_NEURONS_SORT,100,0.08,1,609
FITNESS,100,0.08,1,405
LOG_SORT,100,0.16,1,326
TIE_MIN_NEURONS_SORT,100,0.16,1,686
FITNESS,100,0.16,1,659
LOG_SORT,               100,            0.32,           1,              2036
TIE_MIN_NEURONS_SORT,           100,            0.32,           1,              747
FITNESS,                100,            0.32,       1,              1054
LOG_SORT,200,0.01,1,2533
TIE_MIN_NEURONS_SORT,200,0.01,1,1571
FITNESS,200,0.01,1,2420
LOG_SORT,200,0.02,1,2832
TIE_MIN_NEURONS_SORT,200,0.02,1,2067
FITNESS,200,0.02,1,1287
LOG_SORT,200,0.04,1,1498
TIE_MIN_NEURONS_SORT,200,0.04,1,641
FITNESS,200,0.04,1,1852
LOG_SORT,200,0.08,1,1282
TIE_MIN_NEURONS_SORT,200,0.08,1,2932
FITNESS,200,0.08,1,1995
LOG_SORT,200,0.16,1,455
TIE_MIN_NEURONS_SORT,200,0.16,1,1928
FITNESS,200,0.16,1,788
LOG_SORT,               200,            0.32,           1,              1521
TIE_MIN_NEURONS_SORT,           200,            0.32,           1,              1308
FITNESS,                200,            0.32,           1,              1192
LOG_SORT,400,0.01,1,1998
TIE_MIN_NEURONS_SORT,400,0.01,1,3804
FITNESS,400,0.01,1,2882
LOG_SORT,400,0.02,1,4344
TIE_MIN_NEURONS_SORT,400,0.02,1,1420
FITNESS,400,0.02,1,2374
LOG_SORT,400,0.04,1,3488
TIE_MIN_NEURONS_SORT,400,0.04,1,935
FITNESS,400,0.04,1,1622
LOG_SORT,400,0.08,1,2048
TIE_MIN_NEURONS_SORT,400,0.08,1,3914
FITNESS,400,0.08,1,3784
LOG_SORT,400,0.16,1,2476
TIE_MIN_NEURONS_SORT,400,0.16,1,2486
FITNESS,400,0.16,1,1680
LOG_SORT,               400,            0.32,           1,              1073
TIE_MIN_NEURONS_SORT,           400,            0.32,           1,              3168
FITNESS,                400,            0.32,           1,              1205
LOG_SORT,800,0.01,1,2136
TIE_MIN_NEURONS_SORT,800,0.01,1,4246
FITNESS,800,0.01,1,2523
LOG_SORT,800,0.02,1,4035
TIE_MIN_NEURONS_SORT,800,0.02,1,2624
FITNESS,800,0.02,1,2769
LOG_SORT,800,0.04,1,2650
TIE_MIN_NEURONS_SORT,800,0.04,1,2228
FITNESS,800,0.04,1,3234
LOG_SORT,800,0.08,1,2298
TIE_MIN_NEURONS_SORT,800,0.08,1,3583
FITNESS,800,0.08,1,1885
LOG_SORT,800,0.16,1,2292
TIE_MIN_NEURONS_SORT,800,0.16,1,2141
FITNESS,800,0.16,1,2404
LOG_SORT,               800,            0.32,           1,              1913
TIE_MIN_NEURONS_SORT,           800,            0.32,           1,              1728
FITNESS,                800,            0.32,           1,              2026
LOG_SORT,1600,0.01,1,4458
TIE_MIN_NEURONS_SORT,1600,0.01,1,2558
FITNESS,1600,0.01,1,3472
LOG_SORT,1600,0.02,1,6044
TIE_MIN_NEURONS_SORT,1600,0.02,1,4703
FITNESS,1600,0.02,1,3078
LOG_SORT,1600,0.04,1,3300
TIE_MIN_NEURONS_SORT,1600,0.04,1,3928
FITNESS,1600,0.04,1,3928
LOG_SORT,1600,0.08,1,5322
TIE_MIN_NEURONS_SORT,1600,0.08,1,4908
FITNESS,1600,0.08,1,3357
LOG_SORT,1600,0.16,1,4601
TIE_MIN_NEURONS_SORT,1600,0.16,1,3307
FITNESS,1600,0.16,1,2837
LOG_SORT,               1600,           0.32,           1,              3071
TIE_MIN_NEURONS_SORT,           1600,           0.32,           1,              2618
FITNESS,                1600,           0.32,           1,              3384
LOG_SORT,3200,0.01,1,7912
TIE_MIN_NEURONS_SORT,3200,0.01,1,5073
FITNESS,3200,0.01,1,4939
LOG_SORT,3200,0.02,1,4531
TIE_MIN_NEURONS_SORT,3200,0.02,1,7094
FITNESS,3200,0.02,1,4962
LOG_SORT,3200,0.04,1,6436
TIE_MIN_NEURONS_SORT,3200,0.04,1,5118
FITNESS,                3200,           0.04,           1,              6596
LOG_SORT,               3200,           0.08,           1,              8711
TIE_MIN_NEURONS_SORT,           3200,           0.08,           1,              5668
FITNESS,                3200,           0.08,           1,              5158
LOG_SORT,               3200,           0.16,           1,              5854
TIE_MIN_NEURONS_SORT,           3200,           0.16,           1,              5766
FITNESS,                3200,           0.16,           1,              4838    
LOG_SORT,               3200,           0.32,           1,              6684
TIE_MIN_NEURONS_SORT,           3200,           0.32,           1,              5425
FITNESS,                3200,           0.32,           1,              5550`
    .split("\n")
    .map(string => string.split(",").map(x => x.trim()));
// let ARRAY = rows
//     .map(row => {
//         // return row[4]
//         row[0] = row[0].replace(/_/g, " ");
//         row[4] /= 1000;
//         // row[3] *= 100;
//         row.splice(3, 1)
//         return row.join(" & ");
//     });
// // Math.min.apply(null, ARRAY);
// copy(ARRAY.join(" \\\\\n"));
let collectedTimes = rows.reduce((p, row) => {
    let pclone = {...p};
    pclone[row[2]].push(parseInt(row[4]));
    return pclone;
}, {0.01: [], 0.02: [], 0.04: [],0.08: [], 0.16: [], 0.32: []});

let averageTimes = {};
for (let sort in collectedTimes) {
    let times = collectedTimes[sort];
    let sum = times.reduce((p, c) => p + c, 0);
    averageTimes[sort] = sum / times.length
}

