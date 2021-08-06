% τρέχει ως
% Askisi3(0, 0, 0, 0, 0, 0)

function retval = Askisi3(s1, s2, s3, s4, s5, s6)
  % για το 3.1 αφαιρέστε το παρακάτω σχόλιο
  % circuit(s1, s2, s3, s4, s5, s6)
  
  % για το testBench αφαιρέστε το παρακάτω σχόλιο και βαλτε σε σχόλιο το παραπάνω
  %testBench()
endfunction

function retval = testBench()
  array = zeros(2 ^ 3, 3);
  counter = 1;
  for (j = 3:-1:1)
    item = 0;
    counter2 = 0;
    for (i = 1:(2 ^ 3))
      array(i,j) = item;
      counter2++;
      if (counter2 == counter)
        if (item == 0)
          item = 1;
        else
          item = 0;
        endif
        counter2 = 0;
      endif
    endfor
    counter = counter * 2; 
  endfor 
  
  array
  
  outputs = [0 0 0 0 0 0 1 0]
  
  count = 0;
  for (i = 1:(2 ^ 3))
    SignalsTable = circuit(array(i,1),array(i,2),array(i,3),0,0,0)
    if (SignalsTable{4} == outputs(i))
      count++;
    endif
  endfor
  
  if (count == 8)
    disp("All OK")
  endif
  
endfunction

function SignalsTable = circuit(s1, s2, s3, s4, s5, s6)
  
  ElementTypes = {"AND", "OR"};

  SignalsTable = {s1, s2, s3, s4, s5, s6};
  
  % 3.2
  %sorted()
  
  % 3.3
  % notSorted()
  
  E1.type = ElementTypes(1);
  E1.inputs = [1 2];
  E1.output = 5;
  
  E2.type = ElementTypes(2);
  E2.inputs = [3];
  E2.output = 6;
  
  E3.type = ElementTypes(1);
  E3.inputs = [5 6];
  E3.output = 4;
  
  ElementsTable = {E1, E2, E3};
  
  for i = 1 : size(ElementsTable, 2)
    SignalsTable = process(ElementsTable{i}, SignalsTable)
  endfor
endfunction

function retval = sorted()
  filename = "example.text";
  fileInfo = importdata(filename);

  ins = [];
  outs = [];
  
  for (j = 1:size(fileInfo,1))
    line = strsplit (fileInfo{j,1})
    if (strcmp(line{1,1}, "top_inputs"))
      ins = line(2: size(line,2))   
    else
      out = line(2)
      outs = [outs; out,0]
      
      in = line(3: size(line,2))
      x = line{1}
      
      % χωρίς το top_inputs
      for (m = 1:size(outs,2))
        for (k = 1:size(in,2))
          if (strcmp(in(k), outs(m)))
          else
            %in(k) = 0
          endif
        endfor
      endfor   
      % με το top_inputs
      for (m = 1:size(ins,2))
        for (k = 1:size(in,2))
          if (strcmp(in(k), ins(m)))
            %in(k) = 0
          endif
        endfor
      endfor   
      flag1 = 0;
      flag2 = 0;
      flag3 = 0;
      if strcmp(x, "AND")
        for (z = 1:size(outs,1))
          if (strcmp(in{1}, outs{z,1}))
            in{1} = outs{z,2}
            flag1 = 1;          
          endif
          if (strcmp(in{2}, outs{z,1}))
            in{2} = outs{z,2}
            flag2 = 1;
          endif
        endfor
        if (flag1 == 0)
          in{1} = input ("give first input: ")
        endif
        if (flag2 == 0)
           in{2} = input ("give second input: ")
        endif
        outs(size(outs,1),2) = spAND(in{1}, in{2})
      else
        for (z = 1:size(outs,1))
          if (strcmp(in{1}, outs{z,1}))
              in{1} = outs{z,2}
              flag3 = 1;           
          endif
        endfor
        if (flag3 == 0)
           in{1} = input ("give input: ")
        endif
        outs(size(outs,1),2) = spNOT(in{1})
      endif
    endif
  endfor
endfunction

function retval = notSorted()
  filename = "example.text";
  fileInfo = importdata(filename);

  ins = [];
  outs = [];
  
  for (j = 1:size(fileInfo,1))
    line = strsplit (fileInfo{j,1});
    if (!strcmp(line{1,1}, "top_inputs"))
      out = line(2);
      outs = [outs; out,0];
    endif
  endfor

  newFileInfo = [];
  newOuts = [];
  while (size(fileInfo,1) != size(newFileInfo,2))
    for (j = 1:size(fileInfo,1))
      flag = 0;
      line = strsplit (fileInfo{j,1})
      for (k = 3:size(line,2))
        for (m = 1:size(outs,1))
          if (strcmp(line{1,k}, outs{m,1}))
            flag = 1;
          endif
        endfor
      endfor
      if (flag == 0)
        newOuts = [newOuts line(2)]
        newFileInfo = [newFileInfo fileInfo(j,1)]
      endif
    endfor
    
    for (j = 1:size(fileInfo,1))
      fs = 0;
      for (p = 1:size(newFileInfo,2)) 
        if (!strcmp(fileInfo{j},newFileInfo{p}))
          line = strsplit (fileInfo{j,1})    
          for (k = 3:size(line,2))
            f = 0;
            for (m = 1:size(newOuts,2))     
              if (strcmp(line{1,k}, newOuts{1,m}))
                f++;
                if (size(fileInfo,1) == size(newFileInfo,2))
                  return;
                endif
                if (f == 1)
                  for (p = 1:size(newFileInfo,2))
                    if (strcmp(fileInfo{j},newFileInfo{p}))
                      fs = 1;
                    endif
                    if (fs == 0)
                      newFileInfo = [newFileInfo fileInfo(j,1)]
                      newOuts(m) = NaN;
                    endif
                  endfor
                endif  
              endif
            endfor
          endfor
        endif
      endfor
    endfor
  endwhile
endfunction

function SignalsTable = process(element, SignalsTable)
  if strcmp(element.type, "AND")
    SignalsTable{element.output} = spAND(SignalsTable{element.inputs(1)}, SignalsTable{element.inputs(2)})
  else
    SignalsTable{element.output} = spNOT(SignalsTable{element.inputs(1)})
  endif
endfunction

function s=spNOT(input1sp)
  printf("NOT Gate for input probabilities (%f):\n",input1sp)
  s = (1 - input1sp)
endfunction

function s=spAND(input1sp, input2sp)
  printf("AND Gate for input probabilities (%f %f):\n",input1sp,input2sp)
  s = input1sp*input2sp
endfunction