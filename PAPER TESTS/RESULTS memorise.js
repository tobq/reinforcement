var myString = `03:49:13: Executing task 'Main.main()'...

Starting Gradle Daemon...
Gradle Daemon started in 5 s 385 ms
> Task :gym:compileJava UP-TO-DATE
> Task :gym:processResources UP-TO-DATE
> Task :gym:classes UP-TO-DATE
> Task :gym:jar UP-TO-DATE

> Task :compileJava
Note: Some input files use unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.

> Task :processResources NO-SOURCE
> Task :classes

> Task :Main.main()
[]
Sort:TIE_MIN_NEURONS_SORT\tSize:150\tMut:0.2
 - GENERATIONS TAKENS = [2254]
 - SYNAPSE COUNT = [8]
 - HIDDEN NODE COUNTS = [4]
FINISH RATE = 100.0%
AVERAGE SYNAPSE COUNTS = 8.0
AVERAGE TIME TAKEN = 29238.0
AVERAGE GENERATIONS TAKEN = 2254.0
STDEV OF GENERATIONS TAKEN: 0.0
AVERAGE HIDDEN NODES = 4.0
STDEV OF HIDDEN NODES USED: 0.0



Sort:TIE_MIN_NEURONS_SORT\tSize:150\tMut:0.2
 - GENERATIONS TAKENS = [562]
 - SYNAPSE COUNT = [7]
 - HIDDEN NODE COUNTS = [4]
FINISH RATE = 100.0%
AVERAGE SYNAPSE COUNTS = 7.0
AVERAGE TIME TAKEN = 6112.0
AVERAGE GENERATIONS TAKEN = 562.0
STDEV OF GENERATIONS TAKEN: 0.0
AVERAGE HIDDEN NODES = 4.0
STDEV OF HIDDEN NODES USED: 0.0



Sort:TIE_MIN_NEURONS_SORT\tSize:150\tMut:0.2
 - GENERATIONS TAKENS = [591]
 - SYNAPSE COUNT = [8]
 - HIDDEN NODE COUNTS = [4]
FINISH RATE = 100.0%
AVERAGE SYNAPSE COUNTS = 8.0
AVERAGE TIME TAKEN = 6885.0
AVERAGE GENERATIONS TAKEN = 591.0
STDEV OF GENERATIONS TAKEN: 0.0
AVERAGE HIDDEN NODES = 4.0
STDEV OF HIDDEN NODES USED: 0.0



Sort:TIE_MIN_NEURONS_SORT\tSize:150\tMut:0.2
`;
// for (let i = 0.15; i < 0.3; i += 0.01) {
// for (let i = 100; i <= 3200; i *= 2)
console.log(search("AVERAGE TIME TAKEN"))
console.log(search("AVERAGE GENERATIONS TAKEN"))

function search(line) {
    i = 0.2
    {
        let printI = i;
        // let printI = i.toFixed(2);
        var myRegexp = new RegExp(`Mut:.*(\\n.+)*\\n${line} = (\\d*(\\.(\\d*))?)`, "g");
        var captured = [];
        match = myRegexp.exec(myString);
        while (match != null) {
            // matched text: match[0]
            // match start: match.index
            // capturing group n: match[n]
            console.log(match[2]);
            captured.push(parseFloat(match[2]));

            match = myRegexp.exec(myString);
        }

        console.log(captured, captured.length);
        return `(${printI},${captured.reduce((p, c) => p + c, 0) / captured.length})`;
    }
}