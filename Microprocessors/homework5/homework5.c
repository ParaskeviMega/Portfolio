// Παρασκεύη Μέγα, 2483

// Η συνάρτηση ReadFile() διαβάζει ένα αρχείο και το περνάει σε ένα string, το οποίο επιστρέφει
// δημιουργούνται τα elements και μετά διατάσονται σε σειρά
// για κάθε element που μπορεί να εκτεέστεί ή που δεν έχει εκτελεστεί, καλείται η αντίστοιχη συνάρτηση

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>

#define NUM_OF_INPUTS 20
#define ELEMENT_NUM 44
#define LINES 50
#define numberOfRuns 10000

char *inputs[NUM_OF_INPUTS] = {""};
float SignalsTable[NUM_OF_INPUTS] = {0};
char *outputs[ELEMENT_NUM] = {""};
int numOfOutput = 0;
float s = 0;
int n = 0;

struct Element{
	char *type;
	char *inputs[10];
	char *output; 
};

struct Element ElementsTables[ELEMENT_NUM];
struct Element newElementsTables[ELEMENT_NUM];
int flags[ELEMENT_NUM] = {0};

float spAND(char *inputs[10]){
	int m;
	float s = SignalsTable[0];

	printf("AND Gate for input probabilities (%f", SignalsTable[0]);
	for (m = 1; m < 10; m++){
		if (inputs[m]){
			s = s * SignalsTable[m];
			printf(" %f, ", SignalsTable[m]);
		}
	}
	printf("):\n");

	return s;
}

float spXOR(char *inputs[10]){
	int m;
	float s = 0;
	int numOfOnes = 0;

	printf("XOR Gate for input probabilities (%f", SignalsTable[0]);
	for (m = 0; m < 10; m++){
		if (inputs[m]){
			if (SignalsTable[m] == 1)
				numOfOnes++;
			printf(" %f, ", SignalsTable[m]);
		}
	}
	printf("):\n");
	if (numOfOnes % 2 == 0){
		s = 0;
	}else{
		s = 1;
	}

	return s;
}

float spNOR(char *inputs[10]){
	int m;
	float s = 1 - SignalsTable[0];

	printf("OR Gate for input probabilities (%s", inputs[0]);
	for (m = 1; m < 10; m++){
		if (inputs[m]){
			s = s * (1 - SignalsTable[m]);
			printf(", %s", inputs[m]);
			printf(" %f ", SignalsTable[m]);
		}
	}
	printf("):\n");
	s = 1 - (1 - s);

	return s;
}

float spXNOR(char *inputs[10]){
	int m;
	float s = 0;
	int numOfOnes = 0;
	int ins = 0;

	printf("XNOR Gate for input probabilities (");
	for (m = 0; m < 10; m++){
		if (inputs[m]){
			ins++;
			if (SignalsTable[m] == 1)
				numOfOnes++;
			printf("%s", inputs[m]);
			printf(" %f,", SignalsTable[m]);
		}
	}
	printf("):\n");
	if (ins % 2 == 0){
		if (numOfOnes % 2 == 0){
			s = 1;
		}else{
			s = 0;
		}
	}else{
		if (numOfOnes % 2 == 0){
			s = 0;
		}else{
			s = 1;
		}
	}

	return s;
}

float spNAND(char *inputs[10]){
	int m;
	float s = SignalsTable[0];

	printf("NAND Gate for input probabilities (%s", inputs[0]);
		if (inputs[m]){
			printf(", ");
			s = s * SignalsTable[m];
			printf(", %s", inputs[m]);
			printf(" %f ", SignalsTable[m]);
		}
	}
	printf("):\n");
	s = 1 - s;

	return s;
}

float spOR(char *inputs[10]){
	int m;
	float s = 1 - SignalsTable[0];

	printf("OR Gate for input probabilities (%s", inputs[0]);
	for (m = 1; m < 10; m++){
		if (inputs[m]){
			s = s * (1 - SignalsTable[m]);
			printf(", %s", inputs[m]);
			printf(" %f ", SignalsTable[m]);
		}
	}
	printf("):\n");
	s = 1 - s;

	return s;
}

float spByType(char *type, char *inputs[10]){
	if (strcmp(type, "AND") == 0){
		s = spAND(inputs);
	}else if (strcmp(type, "XOR") == 0){
		s = spXOR(inputs);
	}else if (strcmp(type, "NOR") == 0){
		s = spNOR(inputs);
	}else if (strcmp(type, "XNOR") == 0){
		s = spXNOR(inputs);
	}else if (strcmp(type, "NAND") == 0){
		s = spNAND(inputs);
	}else if (strcmp(type, "OR") == 0){
		s = spOR(inputs);
	}else{
		printf("unsupported\n");
	}

	return s;
}

void checkElement(struct Element element, int number){
	int numberOfInput = 0, OK = 0;
	int i, j, m;

	for (m = 0; m < 10; m++){
		if (element.inputs[m]){
			numberOfInput++;
			for (i = 0; i < NUM_OF_INPUTS; i++){
				if (strcmp(element.inputs[m], inputs[i]) == 0){
					OK++;
				}
			}
			for (i = 0; i < ELEMENT_NUM; i++){
				if (outputs[i]){
					if (strcmp(element.inputs[m], outputs[i]) == 0){
						OK++;
					}
				}
			}
		}
	}

	if (numberOfInput == OK){
		newElementsTables[n] = element;
		n++;
		outputs[numOfOutput] = element.output;
		numOfOutput++;
		flags[number] = 1;
	}
}

char* ReadFile(char *filename)
{
   char *buffer = NULL;
   int string_size, read_size;
   FILE *handler = fopen(filename, "r");

   if (handler)
   {

       fseek(handler, 0, SEEK_END);

       string_size = ftell(handler);

       rewind(handler);

       buffer = (char*) malloc(sizeof(char) * (string_size + 1) );

       read_size = fread(buffer, sizeof(char), string_size, handler);

       buffer[string_size] = '\0';

       if (string_size != read_size)
       {
           free(buffer);
           buffer = NULL;
       }
       fclose(handler);
    }

    return buffer;
}

void individuals(){
	int lower = 0, upper = 1, i;
	float SignalsBefore[NUM_OF_INPUTS] = {0};
	float Signals[NUM_OF_INPUTS] = {0};

	for (i = 0; i < NUM_OF_INPUTS; i++){
		SignalsTable[i] = (rand() % (upper - lower + 1)) + lower;
	}
}

int main(){
	char *string = ReadFile("circuit.txt");
	int i = 0, j = 0, el = 0, k = 0, l = 0, m = 0;
	int counter = 0;
	float SignalsBefore[ELEMENT_NUM] = {0};
	float Signals[ELEMENT_NUM] = {0};
	int score[numberOfRuns] = {0};
	
    
    char *array[LINES] = {""};
    
    if (string)
    {
        puts(string);
        char *p = strtok (string, "\n");
        while (p != NULL)
	    {
	        array[i++] = p;
	        p = strtok (NULL, "\n");
	    }

	    for (i = 0; i < LINES; ++i){
	    	if (array[i]){
	    		char *p = strtok (array[i], " ");
	    		char *words[250] = {""};
	    		j = 0;
		        while (p != NULL)
			    {
			        words[j++] = p;
			        p = strtok (NULL, " ");
			    }
			    if (strcmp(words[0], "TLPINPUTS") == 0){
		    		for (k = 1; k < NUM_OF_INPUTS + 1; ++k){
				    	if (words[k]){
				    		inputs[k-1] = words[k];
				    	}
				    }
				}else{
					struct Element E = {""};

					E.type = words[0];
					E.output = words[1];

					for (m = 2; m < NUM_OF_INPUTS + 2; m++){
						if (words[m]){
							E.inputs[m-2] = words[m];
						}
					}

					ElementsTables[l] = E;
					l++;
				}
	    	}
	        
	    }


	    while (counter != ELEMENT_NUM){
	    	for (j = 0; j < ELEMENT_NUM; j++){
	    		if (flags[j] == 0)
		    		checkElement(ElementsTables[j], j);
		    }

		    counter = 0;
		    for (j = 0; j < ELEMENT_NUM; j++){
		    	if (flags[j] == 1)
		    		counter++;
		    }
		}

    }

    // για το πρώτο ερώτημα
    // επανάληψη για 10.000 φορές 
    for (i = 0; i < numberOfRuns; i++){
    	// L1
	    for (j = 0; j < ELEMENT_NUM; j++){
	    	SignalsBefore[j] = spByType(newElementsTables[j].type, newElementsTables[j].inputs);
		}

		individuals();

		// L2
		for (j = 0; j < ELEMENT_NUM; j++){
	    	Signals[j] = spByType(newElementsTables[j].type, newElementsTables[j].inputs);
		}

		// score για κάθε individual
		for (j = 0; j < ELEMENT_NUM; j++){
			if (SignalsBefore[j] != Signals[j])
		    	score[i]++;
		}

	}

	/*for (i = 0; i < numberOfRuns; i++){
		printf("score %d\n", score[i]);
	}*/

	// προσθέτει στο αρχείο data.temp τα individuals με τα score τους. 
    /*FILE * temp = fopen("data.temp", "w");
    
    for (i=0; i < numberOfRuns; i++)
    {
    	fprintf(temp, "%d %d \n", (i+1), score[i]); //Write the data to a temporary file
    }*/

    // το plot έγινε από terminal με gnuplot με την εξής εντολή: 
    // gnuplot -e "set terminal jpeg; set style data lines; set xlabel 'Individual'; set ylabel 'score'; plot 'data.temp'">out.jpeg

	free(string);

	return 0;
}