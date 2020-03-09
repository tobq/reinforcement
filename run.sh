git pull && gradle distTar && rm ~/reinforcement-1.0-SNAPSHOT* -r && tar -xf ./build/distributions/reinforcement-1.0-SNAPSHOT.tar --directory ~ && ~/reinforcement-1.0-SNAPSHOT/bin/reinforcement -i python3 -b "{\"outputs\":4,\"constValues\":{},\"neuronInputs\":{\"RELU#498d318c\":[{\"strength\":2.8568960357516247,\"neuron\":\"OUT#0\"}],\"SUBTRACT#353d0772\":[{\"strength\":2.161830536505008,\"neuron\":\"RELU#6a400542\"},{\"strength\":1.180698278314701,\"neuron\":\"IN#3\"}],\"DIVIDE#3c130745\":[{\"strength\":5.0263806107548135,\"neuron\":\"IN#c\"},{\"strength\":-0.03253776870503604,\"neuron\":\"RELU#498d318c\"}],\"ADD#418e7838\":[{\"strength\":0.5854168947334735,\"neuron\":\"ADD#19bb07ed\"},{\"strength\":1.8201315281070485,\"neuron\":\"RELU#34b7ac2f\"}],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[{\"strength\":5.034677246632704,\"neuron\":\"IN#12\"}],\"ADD#19bb07ed\":[{\"strength\":0.7802661409689796,\"neuron\":\"IN#3\"},{\"strength\":0.49289282844546933,\"neuron\":\"IN#3\"}],\"OUT#2\":[{\"strength\":2.5286263074913577,\"neuron\":\"ADD#418e7838\"}],\"OUT#1\":[{\"strength\":2.1787765434657818,\"neuron\":\"IN#c\"}],\"MAX#61230f6a\":[{\"strength\":0.25197377404193855,\"neuron\":\"SUBTRACT#353d0772\"},{\"strength\":1.3528391467539735,\"neuron\":\"IN#e\"}],\"OUT#3\":[{\"strength\":1.189553805621409,\"neuron\":\"MAX#61230f6a\"}],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"RELU#34b7ac2f\":[{\"strength\":-6.468498524776489E-4,\"neuron\":\"ADD#19bb07ed\"}],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"RELU#6a400542\":[{\"strength\":0.8714365597538649,\"neuron\":\"DIVIDE#3c130745\"}]},\"varOutputs\":{\"RELU#498d318c\":[],\"SUBTRACT#353d0772\":[\"IN#2\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#11\",\"IN#5\"],\"DIVIDE#3c130745\":[\"IN#13\",\"IN#0\",\"IN#2\",\"IN#4\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#16\",\"IN#8\",\"IN#10\",\"IN#5\",\"IN#11\",\"IN#6\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#f\",\"IN#c\",\"IN#e\",\"IN#17\",\"IN#12\",\"IN#1\"],\"ADD#418e7838\":[\"IN#15\",\"IN#4\",\"IN#b\",\"IN#a\",\"IN#3\",\"IN#11\"],\"IN#10\":[],\"IN#11\":[],\"IN#12\":[],\"OUT#0\":[\"IN#13\",\"IN#0\",\"IN#2\",\"IN#4\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#16\",\"IN#8\",\"IN#10\",\"IN#5\",\"IN#11\",\"IN#6\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#f\",\"IN#e\",\"IN#17\",\"IN#12\"],\"ADD#19bb07ed\":[],\"OUT#2\":[\"IN#13\",\"IN#0\",\"IN#2\",\"IN#4\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#16\",\"IN#3\",\"IN#8\",\"IN#10\",\"IN#5\",\"IN#11\",\"IN#6\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#f\",\"IN#e\",\"IN#17\",\"IN#1\"],\"OUT#1\":[\"IN#13\",\"IN#0\",\"IN#2\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#16\",\"IN#8\",\"IN#10\",\"IN#5\",\"IN#11\",\"IN#6\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#f\",\"IN#c\",\"IN#e\",\"IN#17\"],\"MAX#61230f6a\":[],\"OUT#3\":[\"IN#13\",\"IN#2\",\"IN#4\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#10\",\"IN#5\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#12\",\"IN#1\",\"IN#0\",\"IN#16\",\"IN#3\",\"IN#8\",\"IN#11\",\"IN#6\",\"IN#f\",\"IN#c\",\"IN#e\",\"IN#17\"],\"IN#a\":[],\"IN#d\":[],\"IN#e\":[],\"IN#b\":[],\"IN#c\":[],\"IN#13\":[],\"IN#14\":[],\"IN#15\":[],\"IN#f\":[],\"IN#16\":[],\"IN#17\":[],\"IN#0\":[],\"IN#1\":[],\"IN#4\":[],\"IN#5\":[],\"IN#2\":[],\"IN#3\":[],\"RELU#34b7ac2f\":[\"IN#13\",\"IN#0\",\"IN#2\",\"IN#4\",\"IN#b\",\"IN#14\",\"IN#a\",\"IN#16\",\"IN#3\",\"IN#8\",\"IN#10\",\"IN#5\",\"IN#11\",\"IN#6\",\"IN#d\",\"IN#9\",\"IN#7\",\"IN#15\",\"IN#f\",\"IN#e\",\"IN#17\",\"IN#1\"],\"IN#8\":[],\"IN#9\":[],\"IN#6\":[],\"IN#7\":[],\"RELU#6a400542\":[\"IN#12\",\"IN#5\",\"IN#1\"]},\"inputs\":24}"