let ARRAY = `LOG_SORT,\t\t100,\t\t0.01,\t\t1,\t\t72
TIE_MIN_NEURONS_SORT,\t\t100,\t\t0.01,\t\t1,\t\t46
FITNESS,\t\t100,\t\t0.01,\t\t1,\t\t86
LOG_SORT,\t\t100,\t\t0.02,\t\t1,\t\t14
TIE_MIN_NEURONS_SORT,\t\t100,\t\t0.02,\t\t1,\t\t6
FITNESS,\t\t100,\t\t0.02,\t\t1,\t\t118
LOG_SORT,\t\t100,\t\t0.04,\t\t1,\t\t2
TIE_MIN_NEURONS_SORT,\t\t100,\t\t0.04,\t\t1,\t\t33
FITNESS,\t\t100,\t\t0.04,\t\t1,\t\t32
LOG_SORT,\t\t100,\t\t0.08,\t\t1,\t\t2
TIE_MIN_NEURONS_SORT,\t\t100,\t\t0.08,\t\t1,\t\t16
FITNESS,\t\t100,\t\t0.08,\t\t1,\t\t2
LOG_SORT,\t\t100,\t\t0.16,\t\t1,\t\t4
TIE_MIN_NEURONS_SORT,\t\t100,\t\t0.16,\t\t1,\t\t2
FITNESS,\t\t100,\t\t0.16,\t\t1,\t\t2
LOG_SORT,\t\t200,\t\t0.01,\t\t1,\t\t324
TIE_MIN_NEURONS_SORT,\t\t200,\t\t0.01,\t\t1,\t\t26
FITNESS,\t\t200,\t\t0.01,\t\t1,\t\t19
LOG_SORT,\t\t200,\t\t0.02,\t\t1,\t\t1748
TIE_MIN_NEURONS_SORT,\t\t200,\t\t0.02,\t\t1,\t\t22
FITNESS,\t\t200,\t\t0.02,\t\t1,\t\t30
LOG_SORT,\t\t200,\t\t0.04,\t\t1,\t\t75
TIE_MIN_NEURONS_SORT,\t\t200,\t\t0.04,\t\t1,\t\t11
FITNESS,\t\t200,\t\t0.04,\t\t1,\t\t21
LOG_SORT,\t\t200,\t\t0.08,\t\t1,\t\t2
TIE_MIN_NEURONS_SORT,\t\t200,\t\t0.08,\t\t1,\t\t5
FITNESS,\t\t200,\t\t0.08,\t\t1,\t\t1
LOG_SORT,\t\t200,\t\t0.16,\t\t1,\t\t54
TIE_MIN_NEURONS_SORT,\t\t200,\t\t0.16,\t\t1,\t\t3
FITNESS,\t\t200,\t\t0.16,\t\t1,\t\t9
LOG_SORT,\t\t400,\t\t0.01,\t\t1,\t\t26
TIE_MIN_NEURONS_SORT,\t\t400,\t\t0.01,\t\t1,\t\t44
FITNESS,\t\t400,\t\t0.01,\t\t1,\t\t35
LOG_SORT,\t\t400,\t\t0.02,\t\t1,\t\t82
TIE_MIN_NEURONS_SORT,\t\t400,\t\t0.02,\t\t1,\t\t5
FITNESS,\t\t400,\t\t0.02,\t\t1,\t\t8
LOG_SORT,\t\t400,\t\t0.04,\t\t1,\t\t13
TIE_MIN_NEURONS_SORT,\t\t400,\t\t0.04,\t\t1,\t\t12
FITNESS,\t\t400,\t\t0.04,\t\t1,\t\t3
LOG_SORT,\t\t400,\t\t0.08,\t\t1,\t\t199
TIE_MIN_NEURONS_SORT,\t\t400,\t\t0.08,\t\t1,\t\t12
FITNESS,\t\t400,\t\t0.08,\t\t1,\t\t3
LOG_SORT,\t\t400,\t\t0.16,\t\t1,\t\t13
TIE_MIN_NEURONS_SORT,\t\t400,\t\t0.16,\t\t1,\t\t2
FITNESS,\t\t400,\t\t0.16,\t\t1,\t\t1`
    .split("\n")
    .map(string => {
        let row = string.split(",").map(x => x.trim());
        // return row[4]
        row[0] = row[0].replace(/_/g, " ");
        // row[4] /= 1000;
        // row[3] *= 100;
        row.splice(3, 1)
        return row.join(" & ");
    });
// Math.min.apply(null, ARRAY);
copy(ARRAY.join(" \\\\\n"));