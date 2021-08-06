%%% Παρασκεύη Μέγα, 2483
%%% Δυναμική Κατανάλωση ισχύος-Switching activity
%%% ’σκηση 1.1
%%% N ο αριθμός των μετρήσεων και 200 η μέγιστη τιμή για το x και το y του 
%%% τετραγώνου, ξεκινώντας από το 0
%%% Το Ν πρέπει να είναι <= του maxValue*maxValue
%%% Εμφανίζει την πιθανότητα του τυχαίου σημείου (του τετραγώνου) να βρίσκεται
%%% μέσα στον κύκλο καθώς και το π 
%%%
%%% ’σκηση 1.2
%%% N ο αριθμός των μετρήσεων 
%%% έχουν γίνει αλλαγές στο αρχείο MCAND4.m που δινόταν έτσι ώστε να 
%%% υπολογίζεται το swithing activity μιας OR πύλης, 4 εισόδων
%%% 
%%% ’σκηση 1.3
%%% Έχουν υλοποιηθεί συναρτήσεις για τον υπολογισμό του signal probability
%%% 2, 3 και Ν εισόδων
%%% Για να τρέξει θέλει τουλάχιστον 4 εισόδους.
%%% Υπολογίζεται το switchingActivity και με τους δύο τρόπους:
%%% - σύμφωνα με τον τύπο: switchingActivity = s * (1-s) + (1-s) * s
%%% - και με την μεθοδο Monte Carlo, για 100 μετρήσεις
%%% Το αποτέλεσμα ans που επιστρέφεται είναι το s (signal probability)


%%% για την ’σκηση 1.1
%%% τρέχει ως:
%%% homework2483(10000,200)
%%% αφαιρέστε τα παρακάτω σχόλια για να τρέξετε την 1η ’σκηση
%function s=homework2483(N, maxValue)
%  pCalculation(N, maxValue)
%end

%%% για την ’σκηση 1.2
%%% τρέχει ως:
%%% homework2483 (10)
%%% homework2483 (100)
%%% homework2483 (1000)
%%% αφαιρέστε τα παρακάτω σχόλια για να τρέξετε την 2η ’σκηση
%function s=homework2483(N)
%  MCOR4 (N)
%end

%%% για την ’σκηση 1.3
%%% τρέχει ως:
%%% homework2483(0.5,0.5,0.5,0.5)
%%% homework2483(0.5,0.5,0.5,0.5,0.5,0.5)
%%% homework2483(0,0,0,0)
%%% homework2483(1,1,1,1)
%%% αφαιρέστε τα παρακάτω σχόλια για να τρέξετε την 3η ’σκηση
%function s=homework2483(input1sp, input2sp, input3sp, input4sp, varargin)
%  sp2AND(input1sp, input2sp)
%  sp2OR(input1sp, input2sp)
%  sp2XOR(input1sp, input2sp)
%  sp2NAND(input1sp, input2sp)
%  sp2NOR(input1sp, input2sp)
  
%  sp3AND(input1sp, input2sp, input3sp)
%  sp3OR(input1sp, input2sp, input3sp)
%  sp3XOR(input1sp, input2sp, input3sp)
%  sp3NAND(input1sp, input2sp, input3sp)
%  sp3NOR(input1sp, input2sp, input3sp)
  
%  spAND(input1sp, input2sp, input3sp, input4sp, varargin)
%  spOR(input1sp, input2sp, input3sp, input4sp, varargin)
%  spXOR(input1sp, input2sp, input3sp, input4sp, varargin)
%  spNAND(input1sp, input2sp, input3sp, input4sp, varargin)
%  spNOR(input1sp, input2sp, input3sp, input4sp, varargin)
%end

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ’σκηση 1.1 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
function retval = pCalculation (N, maxValue)
  MonteCarloSize = N;

  a = maxValue;
  r = a/2;
  Esquare = a ^ 2;

  insidePoints = 0;

  for n = 1:MonteCarloSize 
    %0 to a
    x = randi([0 a],1,1);
    y = randi([0 a],1,1);
    
    %center of the circle
    k = [a/2 a/2];
    
    %if it is true we have point inside the cicle
    if (((x - k(1)) ^ 2 + (y - k(2)) ^ 2) <= (r ^ 2))
      insidePoints = insidePoints + 1;
    endif
  endfor

  %probability of the point to be inside to the circle
  Prob = insidePoints / MonteCarloSize
  Ecircle = (insidePoints / MonteCarloSize) * Esquare;
  p = Ecircle / (r ^ 2)
endfunction

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ’σκηση 1.2 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
function SwitchingActivity = MCOR4 (N)

%% I1 I2 I3 I4 | O
%%  0  0  0  0 | 0 
%%  X  X  X  1 | 1 
%%  X  X  1  X | 1 
%%  X  1  X  X | 1 
%%  1  X  X  X | 1 

Workload=[];

MonteCarloSize=N
for i = 1:MonteCarloSize
    Workload=[Workload; round(mod(rand(),2)), round(mod(rand(),2)), round(mod(rand(),2)), round(mod(rand(),2))];
end
vectorsNumber=size(Workload, 1);
gateInputsNumber=size(Workload, 2);
gateOutput=Workload(1,1) |  Workload(1,2) | Workload(1,3) | Workload(1,4);

switchesNumber=0;
for i = 1:vectorsNumber    
    NewGateOutput=Workload(i,1) |  Workload(i,2) | Workload(i,3) | Workload(i,4);
    %NewGateOutput
    if (gateOutput==NewGateOutput)
        continue;
    else
        gateOutput=NewGateOutput;
    end
    
    switchesNumber=switchesNumber+1;
end
switchesNumber
vectorsNumber
SwitchingActivity=switchesNumber/vectorsNumber
    
endfunction

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ’σκηση 1.3 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% 2-input AND gate truth table
% 0 0:0
% 0 1:0
% 1 0:0
% 1 1:1
%% signal probability calculator for a 2-input AND gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%%        s: output signal probability
function s=sp2AND(input1sp, input2sp)
  printf("AND Gate for input probabilities (%f %f):\n",input1sp,input2sp)
  s = input1sp*input2sp;
  switchingActivity = s * (1-s) + (1-s) * s
  AND(2,100)
  endfunction

  
% 2-input OR gate truth table
% 0 0:0
% 0 1:1
% 1 0:1
% 1 1:1
%% signal probability calculator for a 2-input OR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%%        s: output signal probability
function s=sp2OR(input1sp, input2sp)
  printf("OR Gate for input probabilities (%f %f):\n",input1sp,input2sp)
  s = 1-(1-input1sp)*(1-input2sp);
  switchingActivity = s * (1-s) + (1-s) * s
  OR(2, 100)
endfunction


% 2-input XOR gate truth table
% 0 0:0
% 0 1:1
% 1 0:1
% 1 1:0
%% signal probability calculator for a 2-input XOR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%%        s: output signal probability
function s=sp2XOR(input1sp, input2sp)
  printf("XOR Gate for input probabilities (%f %f):\n",input1sp,input2sp)
  s = input1sp * (1 - input2sp) + input2sp * (1 - input1sp);
  switchingActivity = s * (1-s) + (1-s) * s
  XOR(2, 100)
endfunction


% 2-input NAND gate truth table
% 0 0:1
% 0 1:1
% 1 0:1
% 1 1:0
%% signal probability calculator for a 2-input XOR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%%        s: output signal probability
function s=sp2NAND(input1sp, input2sp)
  printf("NAND Gate for input probabilities (%f %f):\n",input1sp,input2sp)
  s = 1 - input1sp*input2sp;
  switchingActivity = s * (1-s) + (1-s) * s
  NAND(2,100)
endfunction


% 2-input NOR gate truth table
% 0 0:1
% 0 1:0
% 1 0:0
% 1 1:0
%% signal probability calculator for a 2-input NOR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%%        s: output signal probability
function s=sp2NOR(input1sp, input2sp)
  printf("NOR Gate for input probabilities (%f %f):\n",input1sp,input2sp)
  s = 1 - (1 -(1-input1sp)*(1-input2sp));
  switchingActivity = s * (1-s) + (1-s) * s
  NOR(2,100)
endfunction

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 3-input gates %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% 3-input AND gate truth table
% 0 0 0:0
% 0 0 1:0
% 0 1 0:0
% 0 1 1:0
% 1 0 0:0
% 1 0 1:0
% 1 1 0:0
% 1 1 1:1
%% signal probability calculator for a 3-input AND gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%% input3sp: signal probability of third input signal
%%        s: output signal probability
function s=sp3AND(input1sp, input2sp, input3sp)
  printf("AND Gate for input probabilities (%f %f %f):\n",input1sp,input2sp,input3sp)
  s = input1sp*input2sp*input3sp;
  switchingActivity = s * (1-s) + (1-s) * s
  AND(3,100)
  endfunction

  
% 3-input OR gate truth table
% 0 0 0:0
% 0 0 1:1
% 0 1 0:1
% 0 1 1:1
% 1 0 0:1
% 1 0 1:1
% 1 1 0:1
% 1 1 1:1
%% signal probability calculator for a 2-input OR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%% input3sp: signal probability of third input signal
%%        s: output signal probability
function s=sp3OR(input1sp, input2sp, input3sp)
  printf("OR Gate for input probabilities (%f %f %f):\n",input1sp,input2sp,input3sp)
  s = 1 - (1-input1sp)*(1-input2sp)*(1-input3sp);
  switchingActivity = s * (1-s) + (1-s) * s
  OR(3, 100)
endfunction


% 3-input XOR gate truth table
% 0 0 0:0
% 0 0 1:1
% 0 1 0:1
% 0 1 1:0
% 1 0 0:1
% 1 0 1:0
% 1 1 0:0
% 1 1 1:1
%% signal probability calculator for a 2-input XOR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%% input3sp: signal probability of third input signal
%%        s: output signal probability
function s=sp3XOR(input1sp, input2sp, input3sp)
  printf("XOR Gate for input probabilities (%f %f %f):\n",input1sp,input2sp,input3sp)
  s = input1sp * input2sp * input3sp + input1sp * (1 - input2sp) * (1 - input3sp) ...
      + (1 - input1sp) * input2sp * (1 - input3sp) + (1 - input1sp) * (1 - input2sp) * input3sp;
  switchingActivity = s * (1-s) + (1-s) * s
  XOR(3, 100)
endfunction


% 3-input NAND gate truth table
% 0 0 0:1
% 0 0 1:1
% 0 1 0:1
% 0 1 1:1
% 1 0 0:1
% 1 0 1:1
% 1 1 0:1
% 1 1 1:0
%% signal probability calculator for a 2-input XOR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%% input3sp: signal probability of third input signal
%%        s: output signal probability
function s=sp3NAND(input1sp, input2sp, input3sp)
  printf("NAND Gate for input probabilities (%f %f %f):\n",input1sp,input2sp,input3sp)
  s = 1 - input1sp*input2sp*input3sp;
  switchingActivity = s * (1-s) + (1-s) * s
  NAND(3,100)
endfunction


% 3-input NOR gate truth table
% 0 0 0:1
% 0 0 1:0
% 0 1 0:0
% 0 1 1:0
% 1 0 0:0
% 1 0 1:0
% 1 1 0:0
% 1 1 1:0
%% signal probability calculator for a 2-input NOR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%% input3sp: signal probability of third input signal
%%        s: output signal probability
function s=sp3NOR(input1sp, input2sp, input3sp)
  printf("NOR Gate for input probabilities (%f %f %f):\n",input1sp,input2sp,input3sp)
  s = 1 - (1 - (1-input1sp)*(1-input2sp)*(1-input3sp));
  switchingActivity = s * (1-s) + (1-s) * s
  NOR(3,100)
endfunction

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% N-input gates %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% N-input AND gate truth table
%% signal probability calculator for a N-input AND gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%% input3sp: signal probability of third input signal
%% input4sp: signal probability of fourth input signal
%% varargin: signal probability of the rest input signals
%%        s: output signal probability
function s=spAND(input1sp, input2sp, input3sp, input4sp, varargin)
  printf("AND Gate for input probabilities (")
  s = 1;
 
  [parameters] = [input1sp input2sp input3sp input4sp varargin{1}];
  
  [lines columns] = size(parameters);

  for i = 1:lines
    for j = 1:columns
      printf(" %f",parameters{i,j})
      s = s * parameters{i,j};
    endfor
  endfor
  printf("): \n"); 
  switchingActivity = s * (1-s) + (1-s) * s
  AND(columns,100)
endfunction


% N-input OR gate truth table
%% signal probability calculator for a N-input OR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%% input3sp: signal probability of third input signal
%% input4sp: signal probability of fourth input signal
%% varargin: signal probability of the rest input signals
%%        s: output signal probability
function s=spOR(input1sp, input2sp, input3sp, input4sp, varargin)
  printf("OR Gate for input probabilities (")
  a = 1;
  
  [parameters] = [input1sp input2sp input3sp input4sp varargin{1}];
  
  [lines columns] = size(parameters);

  for i = 1:lines
    for j = 1:columns
      printf(" %f",parameters{i,j})
      a = a * (1 - parameters{i,j});
    endfor
  endfor
  printf("): \n"); 
  s = 1 - a;
  switchingActivity = s * (1-s) + (1-s) * s
  OR(100, columns)
endfunction


% N-input XOR gate truth table
%% signal probability calculator for a N-input XOR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%% input3sp: signal probability of third input signal
%% input4sp: signal probability of fourth input signal
%% varargin: signal probability of the rest input signals
%%        s: output signal probability
function s=spXOR(input1sp, input2sp, input3sp, input4sp, varargin)
  a = 1;
  printf("XOR Gate for input probabilities (")
  s = input1sp * input2sp * input3sp + input1sp * (1 - input2sp) * (1 - input3sp) ...
      + (1 - input1sp) * input2sp * (1 - input3sp) + (1 - input1sp) * (1 - input2sp) * input3sp;
      
  [parameters] = [input1sp input2sp input3sp input4sp varargin{1}];
  [lines columns] = size(parameters);
  
  for i = 1:lines
    for j = 1:columns
      printf(" %f",parameters{i,j})
      a = a * (1 - parameters{i,j});
    endfor
  endfor
  printf("): \n"); 
  
  %o pinakas tis XOR einai sxedon idios gia plithos metavlitwn 4 kai panw
  if (columns > 3)
    s = (s * (columns - 2)) / (columns - 2);
  endif
  switchingActivity = s * (1-s) + (1-s) * s
  XOR(columns, 100)
endfunction


% N-input NAND gate truth table
%% signal probability calculator for a N-input XOR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%% input3sp: signal probability of third input signal
%% input4sp: signal probability of fourth input signal
%% varargin: signal probability of the rest input signals
%%        s: output signal probability
function s=spNAND(input1sp, input2sp, input3sp, input4sp, varargin)
  printf("NAND Gate for input probabilities (")
  a = 1;
 
  [parameters] = [input1sp input2sp input3sp input4sp varargin{1}];
  
  [lines columns] = size(parameters);

  for i = 1:lines
    for j = 1:columns
      printf(" %f",parameters{i,j})
      a = a * parameters{i,j};
    endfor
  endfor
  printf("): \n"); 
  s = 1 - a;
  switchingActivity = s * (1-s) + (1-s) * s
  NAND(columns,100)
endfunction


% N-input NOR gate truth table
%% signal probability calculator for a N-input NOR gate
%% input1sp: signal probability of first input signal
%% input2sp: signal probability of second input signal
%% input3sp: signal probability of third input signal
%% input4sp: signal probability of fourth input signal
%% varargin: signal probability of the rest input signals
%%        s: output signal probability
function s=spNOR(input1sp, input2sp, input3sp, input4sp, varargin)
  printf("NOR Gate for input probabilities (") 
  a = 1;
  
  [parameters] = [input1sp input2sp input3sp input4sp varargin{1}];
  
  [lines columns] = size(parameters);

  for i = 1:lines
    for j = 1:columns
      printf(" %f",parameters{i,j})
      a = a * (1 - parameters{i,j});
    endfor
  endfor
  printf("): \n"); 
  s = 1 - (1 - a);
  switchingActivity = s * (1-s) + (1-s) * s
  NOR(columns,100)
endfunction

%%%%%%%%%%%%%%%%%%%%%%%%% SWITCHING ACTIVITY %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%AND
function retval = AND(inputs, N)
 
  Workload=[];

  MonteCarloSize=N;
  for i = 1:MonteCarloSize
    array = [];

    for i = 1:inputs
      array = [array round(mod(rand(),2))];
    endfor
    Workload=[Workload; array];
  end
  vectorsNumber=size(Workload, 1);
  gateInputsNumber=size(Workload, 2);
  gateOutput=0&0;

  switchesNumber=0;
  for i = 1:vectorsNumber  
      NewGateOutput = 1; 
      for j = 1:inputs
        NewGateOutput=NewGateOutput & Workload(i,j);
      endfor
      %NewGateOutput
      if (gateOutput==NewGateOutput)
          continue;
      else
          gateOutput=NewGateOutput;
      end
      
      switchesNumber=switchesNumber+1;
  end
  switchesNumber;
  vectorsNumber;
  SwitchingActivity=switchesNumber/vectorsNumber
    
endfunction


%OR
function retval = OR(inputs, N)
 
  Workload=[];

  MonteCarloSize=N;
  for i = 1:MonteCarloSize
    array = [];

    for i = 1:inputs
      array = [array round(mod(rand(),2))];
    endfor
    Workload=[Workload; array];
  end
  vectorsNumber=size(Workload, 1);
  gateInputsNumber=size(Workload, 2);
  gateOutput=0|0;

  switchesNumber=0;
  for i = 1:vectorsNumber  
      NewGateOutput = 0; 
      for j = 1:inputs
        NewGateOutput=NewGateOutput | Workload(i,j);
      endfor
      %NewGateOutput
      if (gateOutput==NewGateOutput)
          continue;
      else
          gateOutput=NewGateOutput;
      end
      
      switchesNumber=switchesNumber+1;
  end
  switchesNumber;
  vectorsNumber;
  SwitchingActivity=switchesNumber/vectorsNumber
      
endfunction


%XOR
function retval = XOR(inputs, N)
   
  Workload=[];

  MonteCarloSize=N;
  for i = 1:MonteCarloSize
    array = [];

    for i = 1:inputs
      array = [array round(mod(rand(),2))];
    endfor
    Workload=[Workload; array];
  end
  vectorsNumber=size(Workload, 1);
  gateInputsNumber=size(Workload, 2);
  gateOutput=xor(0,0);

  switchesNumber=0;
  for i = 1:vectorsNumber  
      NewGateOutput = 0; 
      for j = 1:inputs
        NewGateOutput=xor(NewGateOutput, Workload(i,j));
      endfor
      %NewGateOutput
      if (gateOutput==NewGateOutput)
          continue;
      else
          gateOutput=NewGateOutput;
      end
      
      switchesNumber=switchesNumber+1;
  end
  switchesNumber;
  vectorsNumber;
  SwitchingActivity=switchesNumber/vectorsNumber
    
endfunction


%NAND
function retval = NAND(inputs, N)
 
  Workload=[];

  MonteCarloSize=N;
  for i = 1:MonteCarloSize
    array = [];

    for i = 1:inputs
      array = [array round(mod(rand(),2))];
    endfor
    Workload=[Workload; array];
  end
  vectorsNumber=size(Workload, 1);
  gateInputsNumber=size(Workload, 2);
  gateOutput=0&0;

  switchesNumber=0;
  for i = 1:vectorsNumber  
      NewGateOutput = 1; 
      for j = 1:inputs
        NewGateOutput=NewGateOutput & Workload(i,j);
      endfor
      %NewGateOutput
      if (NewGateOutput == 0)
        NewGateOutput = 1;
      else
        NewGateOutput = 0;
      end
      %NewGateOutput
      if (gateOutput==NewGateOutput)
          continue;
      else
          gateOutput=NewGateOutput;
      end
      
      switchesNumber=switchesNumber+1;
  end
  switchesNumber;
  vectorsNumber;
  SwitchingActivity=switchesNumber/vectorsNumber
    
endfunction


%NOR
function retval = NOR(inputs, N)
 
  Workload=[];

  MonteCarloSize=N;
  for i = 1:MonteCarloSize
    array = [];

    for i = 1:inputs
      array = [array round(mod(rand(),2))];
    endfor
    Workload=[Workload; array];
  end
  vectorsNumber=size(Workload, 1);
  gateInputsNumber=size(Workload, 2);
  gateOutput=0|0;

  switchesNumber=0;
  for i = 1:vectorsNumber  
      NewGateOutput = 0; 
      for j = 1:inputs
        NewGateOutput=NewGateOutput | Workload(i,j);
      endfor
      %NewGateOutput
      if (NewGateOutput == 0)
        NewGateOutput = 1;
      else
        NewGateOutput = 0;
      end
      if (gateOutput==NewGateOutput)
          continue;
      else
          gateOutput=NewGateOutput;
      end
      
      switchesNumber=switchesNumber+1;
  end
  switchesNumber;
  vectorsNumber;
  SwitchingActivity=switchesNumber/vectorsNumber
    
endfunction